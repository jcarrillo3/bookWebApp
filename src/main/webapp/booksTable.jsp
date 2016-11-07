<%-- 
    Document   : booksTable
    Created on : Nov 7, 2016, 12:50:44 PM
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
        <jsp:include page="navbar.jsp"></jsp:include>
        <div class="container" style="width:800px; margin:50px auto;">
            <c:if test="${errMsg != null}">
            <h1>${errMsg}</h1>
        </c:if>
        <h1>Books Table</h1>
        <form method="POST" action="authors?action=findByName">
        <label>Find Author: </label>
        <input type="text" id="name" name="name" class="form-control" placeholder="Author Name" />
        <input type="submit" id="submit" name="submit" value="Find" class="btn btn-default" />
        </form>
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
                <th class="text-center blueHeader">Book ID</th>
                <th class="text-center blueHeader">Book Title</th>
                <th class="text-center blueHeader">ISBN</th>
                <th class="text-center blueHeader">Author</th>
            </thead>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>
                    <input type="checkbox" name="checkboxes" value="${book.bookId}" class="checkbox checkbox-inline">
                </td>
                <td>${book.bookId}</td>
                <td>${book.title}</td>
                <td>${book.isbn}</td>
                <td>${book.authorId}</td>
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