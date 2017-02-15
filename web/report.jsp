<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: naveen-pt1475
  Date: 14-02-2017
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Report</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        .quarter {
            width: 50%;
            height: 100%;
            float: left;
        }

        .contents {
            height: 50%;
            width: 100%;
        }
    </style>
</head>
<body background="http://bgfons.com/upload/paper_texture327.jpg">
<div>
    <a href="/staff.jsp">
        <button type="button" class="btn btn-warning btn-sm">
            <span class="glyphicon glyphicon-chevron-left"></span>BACK
        </button>
        <br>
    </a>
</div>
<div class="contents">
    <div class="col-md-6 quarter">
        <strong>MEAN TIME TO RESOLVE ISSUES</strong>
        <table border=1 class="table table-fixed">
            <thead class="thead-default">
            <tr class="info">
            <th>Priority</th>
            <th>Total Resolved issues</th>
            <th>mean time to resolve</th>
            </tr>
            </thead>
            <tbody>
            <tr class="info">
                <td>low</td>
                <td>${lowcount}</td>
                <td>${lowmean}</td>
            </tr>
            <tr class="info">
                <td>mid</td>
                <td>${midcount}</td>
                <td>${midmean}</td>
            </tr>
            <tr class="info">
                <td>high</td>
                <td>${highcount}</td>
                <td>${highmean}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class=" col-md-6 quarter">
        <strong>ISSUES BY PRODUCT</strong>
        <table border=1 class="table table-hover">
            <thead class="thead-default">
            <tr class="info">
            <th>Product</th>
            <th>Issues</th>
            </tr>
            </thead>
            <tr class="info">
                <td>Zoho Mail</td>
                <td>${mail}</td>
            </tr>
            <tr class="info">
                <td>Zoho People</td>
                <td>${people}</td>
            </tr>
            <tr class="info">
                <td>Zoho Desk</td>
                <td>${desk}</td>
            </tr>
            <tr class="info">
                <td>SDP</td>
                <td>${sdp}</td>
            </tr>
        </table>
    </div>
    <div class="col-md-6 quarter">
        <strong>RECENT ACTIVITY(LAST 7 DAYS)</strong>
        <table border=1 class="table table-hover">
            <tr class="info">
                <td>Total Issues</td>
                <td>${issuecount}</td>
            </tr>
            <tr class="info">
                <td>Issues resolved</td>
                <td>${resolved}</td>
            </tr>
            <tr class="info">
                <td>Issues Pending</td>
                <td>${unresolved}</td>
            </tr>
        </table>
    </div>
    <div class="col-md-6 quarter">
        <strong>STAFF DETAILS</strong>
        <table border=1 class="table table-hover">
            <thead  class="thead-default">
            <tr class="info">
            <th> Staff Name </th>
            <th> Resolved Issues</th>
            </tr>
            </thead>
            <c:forEach var="user" items="${sessionScope.users}">
                <tr class="info">
                    <td>${user.name}</td>
                    <c:choose>
                        <c:when test="${user.issues_resolved != null}">
                            <td>${user.issues_resolved}</td>
                        </c:when>
                        <c:otherwise>
                            <td>0</td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
