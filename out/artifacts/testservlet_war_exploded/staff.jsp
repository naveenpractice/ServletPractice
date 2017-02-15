<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Refresh" content="30">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>User Details</title>
    <style>
        h3 {
            text-align: right;
        }

        button.left {
            float: left;
        }

        button.right {
            float: right;
        }
    </style>
</head>
<body background="http://bgfons.com/upload/paper_texture327.jpg">
<h3>Welcome ${user.name}</h3> <br>
<div class="container-fluid">
    <c:choose>
        <c:when test="${user.type == 'Customer'}">
            <a href="addissue.jsp">
                <button type="button" class="left btn btn-success btn-sm">
                    <span class="glyphicon glyphicon-plus"></span> Add issue
                </button>
            </a>
        </c:when>
        <c:otherwise>
            <form action="/ReportServ" method="POST">
                <button type="submit" class="left btn btn-success btn-sm">
                    <span class="glyphicon glyphicon-plus"></span> Reports
                </button>
            </form>
        </c:otherwise>
    </c:choose>
    <form action="LogoutServ" method="POST">
        <button type="submit" class="right btn btn-danger btn-sm">
            <span class="glyphicon glyphicon-log-out"></span> Logout
        </button>
    </form>
    <br> <br>
    <table border=1 class="table table-hover">
        <thead class="thead-default">
        <tr>
            <th>User</th>
            <th>type</th>
            <th>Product</th>
            <th>Title</th>
            <th>issued At</th>
            <th>Priority</th>
            <th>Due Date</th>
            <th>Resolved Time</th>
            <th>Action</th>

        </tr>
        </thead>
        <c:forEach var="issue" items="${sessionScope.issues}">
            <jsp:useBean id="dateObject" class="java.util.Date"/>
            <fmt:parseDate value="${issue.due_time}" var="parsedEmpDate" pattern="yyyy-MM-dd HH:mm:ss"/>
            <c:choose>
                <c:when test="${(parsedEmpDate.time - dateObject.time) > 60*60*24*1000}">
                    <c:set value="success" var="cssClass"></c:set>
                </c:when>
                <c:when test="${(parsedEmpDate.time - dateObject.time) <= 60*60*24*1000}">
                    <c:set value="warning" var="cssClass"></c:set>
                </c:when>
                <c:when test="${(parsedEmpDate.time - dateObject.time) < 0}">
                    <c:set value="danger" var="cssClass"></c:set>
                </c:when>
                <c:otherwise>
                    <c:set value="info" var="cssClass"></c:set>
                </c:otherwise>
            </c:choose>
            <tr class="${cssClass}">

                <td>${issue.user}</td>
                <td>${issue.type}</td>
                <td>${issue.product}</td>
                <td>${issue.title}</td>
                <td><fmt:parseDate value="${issue.issuetime}" var="parsedEmpDate" pattern="yyyy-MM-dd HH:mm:ss"/>
                    <fmt:formatDate value="${parsedEmpDate}" pattern="MMM dd HH:mm:ss"/></td>
                <td>${issue.priority}</td>
                <td>
                    <fmt:parseDate value="${issue.due_time}" var="parsedEmpDate"
                                   pattern="yyyy-MM-dd HH:mm:ss"/>
                    <fmt:formatDate value="${parsedEmpDate}" pattern="MMM dd HH:mm:ss"/>
                </td>
                <td>
                    <fmt:parseDate value="${issue.resolvedtime}" var="parsedEmpDate"
                                   pattern="yyyy-MM-dd HH:mm:ss"/>
                    <fmt:formatDate value="${parsedEmpDate}" pattern="MMM dd HH:mm:ss"/>
                </td>
                <td>
                    <form action="ConversationServ" method="POST">
                        <input type="hidden" name="usertype" value="${user.type}"/>
                        <input type="hidden" name="issue" value="${issue}"/>
                        <input type="hidden" name="id" value="${issue.id}"/>
                        <input type="hidden" name="user" value="${issue.user}"/>
                        <input type="hidden" name="type" value="${issue.type}"/>
                        <input type="hidden" name="title" value="${issue.title}"/>
                        <input type="hidden" name="priority" value="${issue.priority}"/>
                        <input type="hidden" name="status" value="${issue.resolved}"/>
                        <input type="hidden" name="issuetime" value="${issue.issuetime}"/>
                        <button class="btn btn-info" type="submit">View Details</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>