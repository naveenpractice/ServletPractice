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
        .hidden {
            display: none;
        }

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
    <script>
        var resolvedtime;
        $(document).ready(function () {
            $.post('DetailsServ', {}, function (json) {
                $.each(json.issues, function (index, value) {
                    var duetime = value.due_time
                    if (value.resolvedtime == null)
                        resolvedtime = ""
                    else
                        resolvedtime = value.resolvedtime
                    var today = new Date();
                    var dd = parseInt(today.getDate());
                    if ((parseInt(duetime.substr(4, 6))) - dd <= 1) {
                        col = "warning"
                    } else if ((dd - parseInt(duetime.substr(4, 6))) > 0) {
                        col = "danger"
                    } else
                        col = "success"
                    $("#tbody").append("<tr id=" + "'row" + index + "'" + " class=" + col + ">" +
                        "<td class='id'> " + value.id + "</td>" +
                        "<td> " + value.user + "</td>" +
                        "<td>" + value.type + "</td>" +
                        "<td>" + value.product + "</td>" +
                        "<td>" + value.title + "</td>" +
                        "<td>" + value.issuetime + "</td>" +
                        "<td>" + value.priority + "</td>" +
                        "<td>" + value.due_time + "</td>" +
                        "<td>" + resolvedtime + "</td>" +
                        "<td id=" + "'column" + index + "'" + " >" +
                        "<button type='button' class='test btn btn-info' id=button" + index + "> View </button>" +
                        "</td>" +
                        "</tr>")
                })
                if (json.userType == "Staff") {
                    $("#reportbtn").removeClass("hidden")
                } else
                    $("#addissue").removeClass("hidden")
            })


            $("#reportbtn").click(function () {
                window.location.replace("/report.jsp")
            })
            $("#addissue").click(function () {
                window.location.replace("/addissue.jsp")
            })
        })
        $(document).on("click", ".test", function () {
            var id = $(this).parent().parent().children().first().html();
            $.get('DetailsServ', {id: id}, function (json) {
                window.location.replace("/issuedetails.jsp")
            })
        });

    </script>
</head>


<body background="http://bgfons.com/upload/paper_texture327.jpg">
<h3>Welcome ${username}</h3> <br>
<div class="container-fluid">
    <div>
        <button type="button" id="addissue" class="hidden left btn btn-success btn-sm">
            <span class="glyphicon glyphicon-plus"></span> Add issue
        </button>
    </div>
    <div>
        <button type="button" id="reportbtn" class="hidden left btn btn-success btn-sm">
            <span class="glyphicon glyphicon-plus"></span> Reports
        </button>
    </div>

    <form action="LogoutServ" method="POST">
        <button type="submit" class="right btn btn-danger btn-sm">
            <span class="glyphicon glyphicon-log-out"></span> Logout
        </button>
    </form>
    <br> <br>
    <table border=1 class="table table-hover">
        <thead class="thead-default">
        <tr>
            <th>Id</th>
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
        <tbody id="tbody">
        </tbody>
    </table>
</div>

</body>
</html>