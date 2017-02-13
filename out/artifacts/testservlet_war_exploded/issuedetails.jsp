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
</head>
<body background="http://bgfons.com/upload/paper_texture327.jpg">
<br>   <strong><u>Issue Details</u></strong> <br>
<div class="container-fluid">
    <form class="form-horizontal col-md-4" action="SolutionServ" method="POST">
        <div class="form-group">
            <strong> Type: </strong> ${param.type}
        </div>
        <div class="form-group">
            <strong> Title: </strong> ${param.title}
        </div>
        <div class="form-group">
            <strong> Age: </strong> ${param.description}
        </div>

        <div class="form-group">
            <strong> Username: </strong> ${param.user}
        </div>

        <div class="form-group">
            <strong> Issuetime: </strong> ${param.issuetime}
        </div>
        <div class="form-group">
            <strong> Priority: </strong> ${param.priority}
        </div>
          <strong> Solution: </strong>
        <textarea col="100" rows="5" class="form-control" name="solution"
                              id="solution">${param.solution}</textarea>

                    <div>
                        <input type="hidden" name="id" value="${param.id}"/>
                        <button type="submit" class="btn btn-success">Submit</button>
                    </div>


    </form>
</div>
</body>
</html>
