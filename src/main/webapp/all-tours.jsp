<%-- 
    Document   : all-tours
    Created on : Oct 16, 2025, 2:38:22?PM
    Author     : Holly
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="header.jsp" %>

<div class="container my-5">
    <h2 class="text-center mb-4">All Guided Tours</h2>
    <div class="row">
        <c:forEach var="tour" items="${tours}">
            <div class="col-md-4 mb-4 d-flex">
                <div class="card h-100 w-100 d-flex flex-column">
                    <img src="${tour.image}" class="card-img-top" alt="${tour.name}">
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title">${tour.name}</h5>
                        <p class="card-text">${tour.description}</p>
                        <p class="card-text">
                            <strong>
                                $<fmt:formatNumber value="${tour.price}" type="number" minFractionDigits="2" maxFractionDigits="2" groupingUsed="true"/>
                            </strong>
                        </p>
                        <div class="mt-auto d-flex gap-2">
                            <!-- Add to Cart Button as POST -->
                            <form action="cart" method="post">
                                <input type="hidden" name="action" value="add">
                                <input type="hidden" name="tour" value="${tour.id}">
                                <button type="submit" class="btn btn-success w-100">Add to Cart</button>
                            </form>

                            <!-- View Details Button -->
                            <a href="tour-details?tour=${tour.id}" class="btn btn-primary">View Details</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<%@ include file="footer.jsp" %>