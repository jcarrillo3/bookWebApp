<%-- 
    Document   : navbar
    Created on : Oct 22, 2016, 4:44:25 PM
    Author     : jcarrillo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="paintings.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
        
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
                        <li><a href="booksCo?action=list">Books</a></li>
                    </ul>
                </div>
            </div>
        </div>
    
</html>
