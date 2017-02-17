<%--
  Created by IntelliJ IDEA.
  User: naveen-pt1475
  Date: 06-02-2017
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            $("button").click(function () {
                var name = $("#name").val()
                var age = $("#age").val()
                var email = $('#email').val()
                var user = $('#username').val()
                var pass = $('#password').val()
                var repass = $('#repassword').val()
                var type = $("input[name='type']:checked").val()
                if (pass != repass) {
                    alert('Passwords do not match')
                } else {
                    $.post('RegisterServ', {
                        name: name,
                        age: age,
                        email: email,
                        user: user,
                        pass: pass,
                        type: type
                    }, function (responseText) {
                        if (responseText.success == "true") {
                            alert("Registration Successful")
                            window.location.replace("/login.jsp")
                        } else
                            alert("Error");
                    })
                }
            })
        })


    </script>
</head>
<body background="http://bgfons.com/upload/paper_texture327.jpg">
<div class="container-fluid">
    <div class="form-horizontal col-md-4">
        <div class="form-group">
            <label class="control-label" for="name">Name:</label>
            <div>
                <input type="text" class="form-control" name="name" id="name" placeholder="Enter Name" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label" for="age">Age:</label>
            <div>
                <input type="text" class="form-control" name="age" id="age" placeholder="Enter Age" required>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label" for="password">Email:</label>
            <div>
                <input type="email" class="form-control" name="email" id="email" placeholder="Enter Email" required>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label" for="password">Username:</label>
            <div>
                <input type="text" class="form-control" name="username" id="username" placeholder="Enter username"
                       required>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label" for="password">Password:</label>
            <div>
                <input type="password" class="form-control" name="password" id="password" placeholder="Enter Password"
                       required>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label" for="repassword">ReType Password:</label>
            <div>
                <input type="password" class="form-control" name="repassword" id="repassword"
                       placeholder="ReEnter password" required>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label" for="radio">Type</label>
            <div class="radio" id="radio">
                <label><input type="radio" name="type" value="Customer">Customer</label>
            </div>
            <div class="radio">
                <label><input type="radio" name="type" value="Staff">Staff</label>
            </div>
        </div>

        <div class="form-group">
            <div>
                <button type="button" class="btn btn-success">Submit</button>
                Already signed up <a href="login.jsp">Login here</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
