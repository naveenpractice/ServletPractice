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
        .hidden {
            display: none;
        }

        table {
            border-collapse: collapse;
            table-layout: fixed;
        }

        table td {
            word-wrap: break-word;
        }
    </style>

    <script>
        $(document).ready(function () {
            $.post('ConversationServ', {}, function (json) {
                $("#type").append(json.issue.type)
                $("#product").append(json.issue.product)
                $("#title").append(json.issue.title)
                $("#user").append(json.issue.user)
                $("#priority").append(json.issue.priority)
                if (json.issue.resolved === 'yes') {
                    $("#issuetext").removeClass("hidden")
                } else if (json.issue.resolved === 'no' && json.userType === 'Staff') {
                    $("#close").removeClass("hidden")
                    $("#message").removeClass("hidden")
                }
                else {
                    $("#message").removeClass("hidden")
                }
                $.each(json.messages, function (index, message) {
                    $("#tbody").append("<tr id=" + "'row" + index + "'" + " class='success'>" +
                        "<td class='id'> " + message.user + "</td>" +
                        "<td> " + message.timestamp + "</td>" +
                        "<td>" + message.message + "</td>" +
                        "</tr>")
                })
            })
                $("#closebtn").click(function () {
                    $.post('CloseIssueServ', {}, function (json) {
                        if (json.success == "true") {
                            alert("Issue closed successfully")
                            window.location.replace("/issuedetails.jsp")
                        }
                    })
                })
                $("#back").click(function () {
                    window.location.replace("/details.jsp")
                })

                $("#send").click(function () {
                    var message = $("#solution").val()
                    $.post('SolutionServ', {message: message}, function (json) {
                        if (json.success == "true") {
                            window.location.replace("/issuedetails.jsp")
                        } else
                            alert("Some Error");
                    })

                })
            })
    </script>
</head>
<body background="http://bgfons.com/upload/paper_texture327.jpg">
<div class="container-fluid">
    <div>
        <button id="back" type="button" class="btn btn-warning btn-sm">
            <span class="glyphicon glyphicon-chevron-left"></span>BACK
        </button>
        <br>

    </div>
    <div><strong><u>Issue Details</u></strong></div>
    <div>
        <strong> Type: </strong> <span id="type"></span>
    </div>
    <div>
        <strong> Product: </strong> <span id="product"></span> ${issue.product}
    </div>
    <div>
        <strong> Title: </strong> <span id="title"></span>${issue.title}
    </div>
    <div>
        <strong> Posted by: </strong> <span id="user"></span>${issue.user}
    </div>
    <div>
        <strong> Priority: </strong> <span id="priority"></span>${issue.priority}
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
            <tbody id="tbody">
            </tbody>
        </table>
    </div>
    <div class="hidden" id="close">
        <div>
            <button class="btn btn-danger" id="closebtn" type="button"><span class="glyphicon glyphicon-lock"></span>CLOSE
                ISSUE
            </button>
        </div>
    </div>
    <div class="hidden" id="message">
        <div><strong>Message:</strong>
            <textarea col="100" rows="5" class="form-control" name="solution"
                      id="solution"></textarea></div>
        <div>
            <input type="hidden" name="id" value="${issue.id}"/>
            <button id="send" type="button" class="btn btn-success">Send</button>
        </div>
    </div>
    <span id="issuetext" class="hidden">Issue resolved</span>
</div>
</body>
</html>
