<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Customer details</title>
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
    <a href="addissue.jsp">
        <button type="button" class="left btn btn-success btn-sm">
            <span class="glyphicon glyphicon-plus"></span> Add issue
        </button>
    </a>
    <a href="login.jsp">
        <button type="button" class="right btn btn-danger btn-sm">
            <span class="glyphicon glyphicon-log-out"></span> Logout
        </button>
    </a>
    <br> <br>
    <table border=1 class="table table-hover">
        <thead class="thead-default">
        <tr>
            <th>type</th>
            <th>Title</th>
            <th>issued At</th>
            <th>resolved</th>
            <th>solution</th>
            <th>solved At</th>
        </tr>
        </thead>
        <c:forEach var="issue" items="${sessionScope.issues}">
            <tr class="success">
                <td>${issue.type}</td>
                <td>${issue.title}</td>
                <td>${issue.issuetime}</td>
                <c:choose>
                    <c:when test="${issue.resolved == 'no'}">
                        <td>no</td>
                    </c:when>
                    <c:otherwise>
                        <td>Yes</td>
                    </c:otherwise>
                </c:choose>
                <td>${issue.response}</td>
                <td>${issue.responsetime}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>