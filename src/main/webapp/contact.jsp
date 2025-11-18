<%-- 
    File        : contact.jsp
    Description : Contact page allowing users to send a message.
    Author      : Holly Dahlstrom
    Created     : 11-18-2025
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>

<div class="container my-5">
    <h2 class="text-center mb-4">Get in Touch</h2>

    <!-- Display success or error messages -->
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form action="contact-us" method="POST" class="col-md-6 mx-auto">
        <div class="mb-3">
            <input type="text" class="form-control" id="name" name="name" placeholder="Your Name" required>
        </div>
        <div class="mb-3">
            <input type="email" class="form-control" id="email" name="email" placeholder="name@example.com" required>
        </div>
        <div class="mb-3">
            <textarea class="form-control" id="message" name="message" rows="4" placeholder="How can we help you?" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary w-100">Send Message</button>
    </form>
</div>

<%@ include file="footer.jsp" %>