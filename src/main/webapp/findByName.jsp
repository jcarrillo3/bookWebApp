<%-- 
    Document   : findByName
    Created on : Oct 31, 2016, 9:33:30 PM
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
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp"></jsp:include>
        <c:forEach var="author" items="${authors}">
            <h1>${author.authorName}</h1>
            ID: <span>${author.authorId}</span><br />
            Date Added: <span><fmt:formatDate pattern="M/d/yyyy" value="${author.dateAdded}"></fmt:formatDate></span>
        </c:forEach>
    </body>
</html>
