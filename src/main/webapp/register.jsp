<%-- 
    File: register.jsp
    Created on: 11-18-2025
    Author: Holly Dahlstrom
    Description: Registration page allowing new users to create an account.
                 Displays success or error messages and submits form data 
                 to the RegisterServlet for processing. Includes Bootstrap 
                 styling and JSTL for conditional message display.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>

<div class="container my-5">
    <h2 class="text-center mb-4">Create an Account</h2>

    <!-- Display success or error messages -->
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form action="register" method="POST" class="col-md-4 mx-auto border p-4 rounded shadow-sm bg-light">
        <div class="mb-3">
            <label for="firstName" class="form-label">First Name</label>
            <input type="text" class="form-control" id="firstName" name="firstName" required>
        </div>
        <div class="mb-3">
            <label for="lastName" class="form-label">Last Name</label>
            <input type="text" class="form-control" id="lastName" name="lastName" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email Address</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>

        <button type="submit" class="btn btn-primary w-100">Register</button>
        <p class="text-center mt-3">Already have an account? <a href="login.jsp">Login</a></p>
    </form>
</div>

<%@ include file="footer.jsp" %>