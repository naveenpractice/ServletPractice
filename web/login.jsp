<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>WELCOME</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Customer details</title>

    <script>
        $(document).ready(function () {
            $("button").click(function () {
                var user = $("#username").val()
                var pass = $("#password").val()
                $.post('LoginServ', {username: user, password: pass}, function (responseText) {
                    if (responseText.valid == "true") {
                        window.location.replace("/details.jsp")
                    } else
                        alert("Invalid Username/Password");
                })
            })
        });
    </script>
</head>
<body background="http://bgfons.com/upload/paper_texture327.jpg">
<div class="container-fluid">
    <div class="form-horizontal col-md-4">
        <div class="form-group">
            <label class="control-label" for="username">Username:</label>
            <div>
                <input type="text" class="form-control" name="username" id="username" placeholder="Enter Username">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label" for="password">Password:</label>
            <div>
                <input type="password" class="form-control" name="password" id="password" placeholder="Enter password">
            </div>
        </div>
        <div class="form-group">
            <div>
                <button type="button" class="btn btn-success">Submit</button>
                if not registered, <a href="register.jsp">register here</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>