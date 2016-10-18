<%-- 
    Document   : authorsTable
    Created on : Sep 19, 2016, 4:05:09 PM
    Author     : jcarrillo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Authors Table</title>
        <link rel="stylesheet" href="main.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    </head>
    <body class="alignCenter">
        <div class="navbar navbar-inverse navbar-fixed-top" style="background-color: #1b6d85">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                    <a class="navbar-brand" href="index.jsp">Book Web App</a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="authors?action=list">Authors</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <c:if test="${errMsg != null}">
            <h1>${errMsg}</h1>
        </c:if>
        <div class="container" style="width:800px; margin:50px auto;">
        <h1>Authors Table</h1>
        <form id="form1" name="form1" method="POST" action="authors?action=AddUpdateDelete">
            <button title="Add" type="submit" id="btnAdd" name="btnAdd" class="btn btn-default">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
            <button title="Delete" type="sumbit" id="btnDelete" name="btnDelete" class="btn btn-default">
                <span class="glyphicon glyphicon-trash"></span>
            </button>
            <button title="Update" type="submit" id="btnUpdate" name="btnUpdate" class="btn btn-default">
                <span class="glyphicon glyphicon-pencil"></span>
            </button>
            <button title="Clear" type="button" id="clear" class="btn btn-default">Clear Selection</button><br><br>
            <table id="table1" name="authorTable" class=" table table-bordered table-hover">
            <thead>
            <th class="blueHeader"></th>
                <th class="text-center blueHeader">Author ID</th>
                <th class="text-center blueHeader">Author Name</th>
                <th class="text-center blueHeader">Date Added</th>
            </thead>
        <c:forEach var="author" items="${authorList}">
            <tr>
                <td>
                    <input type="checkbox" name="checkboxes" value="${author.authorId}" class="checkbox checkbox-inline">
                </td>
                <td>${author.authorId}</td>
                <td>${author.authorName}</td>
                <td><fmt:formatDate pattern="M/d/yyyy" value="${author.dateAdded}"></fmt:formatDate></td>
            </tr>
        </c:forEach>
        </table>
        </form>
        </div>
        <h3><a href="index.jsp">Home</a></h3>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="main.js"></script>
    </body>
</html>
