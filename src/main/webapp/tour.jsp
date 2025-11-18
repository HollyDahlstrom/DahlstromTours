<%-- 
    Document   : tour.jsp
    Created on : 11-17-2025
    Author     : Holly Dahlstrom
    Description: Displays the details of a single tour, including image, name,
                 price, description, and buttons to add to cart or go back to all tours.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container my-5">
    <div class="card shadow-sm mx-auto" style="max-width: 600px;">
        <img src="${image}" class="card-img-top" alt="${tourName}">
        <div class="card-body">
            <h2 class="card-title text-center mb-3">${tourName}</h2>

            <p class="text-center fs-5 mb-3">
                <strong>Price:</strong>
                $<fmt:formatNumber value="${price}" type="number" minFractionDigits="2" maxFractionDigits="2" groupingUsed="true"/>
            </p>

            <p class="card-text text-center">${description}</p>

            <div class="text-center mt-4">
                <a href="cart?action=add&tour=${tourId}" class="btn btn-success me-2">Add to Cart</a>
                <a href="all-tours" class="btn btn-secondary">Back to All Tours</a>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>