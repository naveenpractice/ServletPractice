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
    <script>
        $(document).ready(function () {
            $.post('ReportServ', {}, function (json) {
                $("#low").append("<td>" + json.lowcount + "</td>" + "<td>" + json.lowmean + "</td>")
                $("#mid").append("<td>" + json.midcount + "</td>" + "<td>" + json.midmean + "</td>")
                $("#high").append("<td>" + json.highcount + "</td>" + "<td>" + json.highmean + "</td>")
                $("#mail").append("<td>" + json.mail + "</td>")
                $("#people").append("<td>" + json.people + "</td>")
                $("#desk").append("<td>" + json.desk + "</td>")
                $("#sdp").append("<td>" + json.sdp + "</td>")
                $("#issuecount").append("<td>" + json.issuecount + "</td>")
                $("#resolved").append("<td>" + json.resolved + "</td>")
                $("#pending").append("<td>" + json.unresolved + "</td>")

                $.each(json.users, function (index, user) {
                    var issues_resolved;
                    if (user.issues_resolved != null)
                        issues_resolved = user.issues_resolved
                    else
                        issues_resolved = 0
                    $("#tbody").append("<tr class='info'>" +
                        "<td> " + user.name + "</td>" +
                        "<td> " + issues_resolved + "</td>" +
                        "</tr>")
                })
            })
            $("#back").click(function () {
                window.location.replace("/details.jsp")
            })
        })
    </script>
</head>
<body background="http://bgfons.com/upload/paper_texture327.jpg">
<div>
        <button id="back" type="submit" class="btn btn-warning btn-sm">
            <span class="glyphicon glyphicon-chevron-left"></span>BACK
        </button>
    <br>
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
            <tr id="low" class="info">
                <td>low</td>

            </tr>
            <tr id="mid" class="info">
                <td>mid</td>

            </tr>
            <tr id="high" class="info">
                <td>high</td>

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
            <tr id="mail" class="info">
                <td>Zoho Mail</td>
            </tr>
            <tr id="people" class="info">
                <td>Zoho People</td>
            </tr>
            <tr id="desk" class="info">
                <td>Zoho Desk</td>
            </tr>
            <tr id="sdp" class="info">
                <td>SDP</td>
            </tr>
        </table>
    </div>
    <div class="col-md-6 quarter">
        <strong>RECENT ACTIVITY(LAST 7 DAYS)</strong>
        <table border=1 class="table table-hover">
            <tr id="issuecount" class="info">
                <td>Total Issues</td>
            </tr>
            <tr id="resolved" class="info">
                <td>Issues resolved</td>
            </tr>
            <tr id="pending" class="info">
                <td>Issues Pending</td>
            </tr>
        </table>
    </div>
    <div class="col-md-6 quarter">
        <strong>STAFF DETAILS</strong>
        <table border=1 class="table table-hover">
            <thead class="thead-default">
            <tr class="info">
                <th> Staff Name</th>
                <th> Resolved Issues</th>
            </tr>
            </thead>
            <tbody id="tbody">
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
