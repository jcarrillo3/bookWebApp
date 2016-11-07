<%-- 
    Document   : index
    Created on : Sep 19, 2016, 4:03:36 PM
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
        <title>Book Authors - Home</title>
        <link rel="stylesheet" href="main.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    </head>
    <body>
        <jsp:include page="navbar.jsp"></jsp:include>
        <div class="container well" style="margin:40px auto; background-color: lightgoldenrodyellow">
            <div style="float: left">
                <h2>Click the link below to view all authors.</h2>
                <h4><a href="authors?action=list">View all Authors</a></h4>
                <br />
                <h2>Click the link below to view all books.</h2>
                <h4><a href="booksCo?action=list">View all Books</a></h4>
            </div>
            <img src="http://www.clipartbest.com/cliparts/biy/LEz/biyLEz9iL.png" alt="stack of books" style="float: right" />
        </diV>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
