<%-- 
    Document   : header
    Created on : Oct 16, 2025, 2:33:17â¯PM
    Author     : Holly
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>How Swede Guided Tours Abroad</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="style.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">How Swede Guided Tours Abroad</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    
    <!-- Greeting outside the nav links -->
    <c:if test="${not empty sessionScope.user}">
    <!-- store first name in a local variable -->
    <c:set var="firstName" value="${sessionScope.user.firstName}" />

    <!-- get first letter uppercased -->
    <c:set var="firstLetter" value="${fn:toUpperCase(fn:substring(firstName, 0, 1))}" />

    <!-- get the rest of the name -->
    <c:set var="restOfName" value="${fn:substring(firstName, 1, fn:length(firstName))}" />

    <!-- display greeting -->
    <div class="text-white me-3 d-lg-none mb-2">
        Hello, ${firstLetter}${restOfName}!
    </div>
    <div class="text-white me-3 d-none d-lg-block">
        Hello, ${firstLetter}${restOfName}!
    </div>
    </c:if>
    
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
        <li class="nav-item"><a class="nav-link" href="about.jsp">About</a></li>
        <li class="nav-item"><a class="nav-link" href="all-tours">Tours</a></li>
        <li class="nav-item"><a class="nav-link" href="contact.jsp">Contact</a></li>
        <li class="nav-item">
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <a class="nav-link" href="login-logout?action=logout">Logout</a>
                </c:when>
                <c:otherwise>
                    <a class="nav-link" href="login.jsp">Login</a>
                </c:otherwise>
            </c:choose>
        </li>
        
      </ul>
    </div>
  </div>
</nav>
<c:if test="${not empty sessionScope.cart}">
    <div class="bg-light text-center py-2 shadow-sm border-bottom">
        <c:set var="totalQty" value="0" />
        <c:forEach var="entry" items="${sessionScope.quantities}">
            <c:set var="totalQty" value="${totalQty + entry.value}" />
        </c:forEach>
        <a href="cart.jsp" class="btn btn-success">
            🛒 View Cart (${totalQty})
        </a>
    </div>
</c:if>