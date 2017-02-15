<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Issue Details</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        table {
            border-collapse: collapse;
            table-layout: fixed;
        }

        table td {
            word-wrap: break-word;
        }
    </style>
</head>
<body background="http://bgfons.com/upload/paper_texture327.jpg">

<div class="container-fluid">
    <div>
        <a href="/staff.jsp">
            <button type="button" class="btn btn-warning btn-sm">
                <span class="glyphicon glyphicon-chevron-left"></span>BACK
            </button>
            <br>
        </a>
    </div>
    <div><strong><u>Issue Details</u></strong></div>
    <div>
        <strong> Type: </strong> ${issue.type}
    </div>
    <div>
        <strong> Product: </strong> ${issue.product}
    </div>
    <div>
        <strong> Title: </strong> ${issue.title}
    </div>
    <div>
        <strong> Posted by: </strong> ${issue.user}
    </div>
    <div>
        <strong> Priority: </strong> ${issue.priority}
    </div>
    <div>
        <strong> Messages: </strong> <br/>
        <table border=1 class="table table-hover">
            <thead class="thead-default">
            <tr>
                <th>User</th>
                <th>time</th>
                <th>Message</th>
            </tr>
            </thead>
            <c:forEach var="convo" items="${sessionScope.convos}">
                <tr class="info">
                    <td>${convo.user}</td>
                    <td> ${convo.timestamp}</td>
                    <td>${convo.message}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <c:if test="${issue.status == 'no' && usertype == 'Staff'}">
        <div>
            <form class="form-horizontal" action="SolutionServ" method="GET">
                <button class="btn btn-danger" type="submit"><span class="glyphicon glyphicon-lock"></span>CLOSE ISSUE</button>
            </form>
        </div>
    </c:if>
    <c:if test="${issue.status == 'no'}">
        <form class="form-horizontal" action="SolutionServ" method="POST">
            <div><strong>Message:</strong>
                <textarea col="100" rows="5" class="form-control" name="solution"
                          id="solution">${param.solution}</textarea></div>
            <div>
                <input type="hidden" name="id" value="${issue.id}"/>
                <button type="submit" class="btn btn-success">Send</button>
            </div>
        </form>
    </c:if>
    <c:if test="${issue.status == 'yes'}">
        Issue resolved
    </c:if>
</div>
</body>
</html>
