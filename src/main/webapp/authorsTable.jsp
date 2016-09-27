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
    Object obj = request.getAttribute("authors");
    if (obj == null){
        response.sendRedirect("AuthorController");
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
        <div class="container">
        <h1>Authors Table</h1>
        <table id="table1" name="authorTable" class="table table-bordered table-hover">
            <thead>
                <th class="text-center blueHeader">Author ID</th>
                <th class="text-center blueHeader">Author Name</th>
                <th class="text-center blueHeader">Date Added</th>
            </thead>
        <c:forEach var="author" items="${authors}">
            <tr>
                <td>${author.authorId}</td>
                <td>${author.authorName}</td>
                <td>${author.dateAdded}</td>
            </tr>
        </c:forEach>
        </table>
        </div>
        <h3><a href="index.jsp">Home</a></h3>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
