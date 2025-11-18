<%-- 
    Document   : tour-details.jsp
    Created on : 11-17-2025
    Author     : Holly Dahlstrom
    Description: Displays the full details of a selected tour, including name, image,
                 price, full description, and options to book the tour or return to all tours.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="murach.T.Tour" %>

<%
    Tour tour = (Tour) request.getAttribute("tour");
    if (tour == null) {
        response.sendRedirect("all-tours");
        return;
    }
%>

<div class="container mt-5">
    <h2><%= tour.getName() %></h2>
    <img src="<%= tour.getImage() %>" class="img-fluid mb-3" alt="<%= tour.getName() %>">
    <p>
        <strong>Price:</strong>
        <fmt:formatNumber value="${tour.price}" type="currency" currencySymbol="$" minFractionDigits="2" maxFractionDigits="2"/>
    </p>
    <p><%= tour.getFullDescription() %></p>

    <div class="mt-3">
        <a href="all-tours" class="btn btn-secondary">Back to Tours</a>

        <!-- Book Now button adds tour to cart and redirects to checkout -->
        <form action="cart" method="post" style="display:inline;">
            <input type="hidden" name="tour" value="<%= tour.getId() %>">
            <input type="hidden" name="action" value="add">
            <button type="submit" class="btn btn-success">Book Now</button>
        </form>
    </div>
</div>

<%@ include file="footer.jsp" %>