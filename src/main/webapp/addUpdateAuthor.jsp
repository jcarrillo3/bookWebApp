<%-- 
    Document   : addUpdateAuthor
    Created on : Sep 29, 2016, 3:17:42 PM
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
        <title>Add/Update Author</title>
        <link rel="stylesheet" href="main.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    </head>
    <body style="margin: 50px;">
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
        <div class="container well" style="background-color: lightgoldenrodyellow">
        <h1>${pageTitle}</h1>
        <c:choose>
            <c:when test="${addOrUpdate == 'add'}">
                <form id="addForm" method="POST" action="authors?action=add">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" id="name" name="name" class="form-control" />
                    </div>
                    <input type="submit" id="save" name="save" value="Save" class="btn btn-primary" />
                    <input type="submit" id="cancel" name="cancel" value="Cancel" class="btn btn-default" />

                </form>
            </c:when>
            <c:when test="${addOrUpdate == 'update'}">
                <form id="updateForm" method="POST" action="authors?action=update&id=${id}">
                    <div class="form-group">
                        <label for="aId">ID</label>
                        <input type="text" id="aId" name="aId" class="form-control" value="${id}" readonly="true"/>
                    </div>
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" id="name" name="name" class="form-control" value="${name}" />
                    </div>
                    <div class="form-group">
                        <label for="date">Date Added</label>
                        <input type="date" id="date" name="date" class="form-control" value="${date}" />
                    </div>
                    <input type="submit" id="save" name="save" value="Save" class="btn btn-primary" />
                    <input type="submit" id="cancel" name="cancel" value="Cancel" class="btn btn-default" />

                </form>
            </c:when>
        </c:choose>
        </div>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="main.js"></script>
    </body>
</html>
