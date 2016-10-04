<%-- 
    Document   : authorsTable
    Created on : Sep 19, 2016, 4:05:09 PM
    Author     : jcarrillo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    Object obj = request.getAttribute("authorList");
    if (obj == null){
        response.sendRedirect("authors");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Authors Table</title>
        <link rel="stylesheet" href="main.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    </head>
    <body>
        <div class="container" style="width:800px; margin:0 auto;">
        <h1>Authors Table</h1>
        <form id="form1" name="form1" method="POST" action="authors?action=AddUpdateDelete">
            <button title="Add" type="submit" class="btn btn-default">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
            <!--<input type="submit" value="Add" id="btn1" name="btn1" />-->
            <button title="Delete" type="sumbit" class="btn btn-default">
                <span class="glyphicon glyphicon-trash"></span>
            </button>
            <button title="Edit" type="submit" class="btn btn-default">
                <span class="glyphicon glyphicon-pencil"></span>
            </button><br><br>
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
                    <input type="checkbox" name="" value="checked" class="checkbox checkbox-inline">
                </td>
                <td>${author.authorId}</td>
                <td>${author.authorName}</td>
                <td>${author.dateAdded}</td>
            </tr>
        </c:forEach>
        </table>
        </form>
        </div>
        <h3><a href="index.jsp">Home</a></h3>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
