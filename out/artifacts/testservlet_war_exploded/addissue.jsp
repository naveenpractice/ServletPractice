<%--
  Created by IntelliJ IDEA.
  User: naveen-pt1475
  Date: 07-02-2017
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Issue</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body background="http://bgfons.com/upload/paper_texture327.jpg">
<div class="container-fluid">
    <form class="form-horizontal col-md-4" action="IssueServ" method="POST">
        <div class="form-group">
            <label class="control-label" for="type">Issue Title:</label>
            <div>
                <select name="type" id="type">
                    <option value="Assistance">Assistance</option>
                    <option value="Suggestion">Suggestion</option>
                    <option value="Complaint">Complaint</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label" for="product">Issue Title:</label>
            <div>
                <select name="product" id="product">
                    <option value="Zoho Mail">Zoho Mail</option>
                    <option value="Zoho People">Zoho People</option>
                    <option value="SDP">SDP</option>
                    <option value="Zoho Desk">Zoho Desk</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label" for="title">Issue Title:</label>
            <div>
                <input type="text" class="form-control" name="title" id="title">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label" for="description">Description:</label>
            <div>
                <textarea cols="100" rows="5" class="form-control" name="description" id="description"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label" for="priority">Issue Title:</label>
            <div>
                <select name="priority" id="priority">
                    <option value="low">low</option>
                    <option value="medium">medium</option>
                    <option value="high">high</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div>
                <button type="submit" class="btn btn-success">Submit</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
