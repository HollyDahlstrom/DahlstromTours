<%-- 
    File: login.jsp
    Author: Holly Dahlstrom
    Created: 11/18/2025
    Description: Displays the user login form and handles login-related messages.
                 Shows success messages after registration, login errors, and
                 warning messages if the user was redirected from the cart or
                 checkout pages. Form submits to the LoginLogoutServlet.
--%>

<%@ include file="header.jsp" %>

<%
    // Display login message if redirected from cart/checkout
    String loginMessage = (String) session.getAttribute("loginMessage");
    if (loginMessage != null) {
%>
    <div class="alert alert-warning text-center">
        <%= loginMessage %>
    </div>
<%
        session.removeAttribute("loginMessage"); // clear after showing
    }
%>

<div class="container my-5">
    <h2 class="text-center mb-4">User Login</h2>

    <form action="login-logout" method="POST" class="col-md-4 mx-auto border p-4 rounded shadow-sm bg-light">
        <input type="hidden" name="action" value="login">

        <!-- Success message after registration -->
        <c:if test="${not empty success}">
            <div class="alert alert-success text-center">${success}</div>
        </c:if>

        <!-- Display login error if present -->
        <c:if test="${not empty loginError}">
            <div class="alert alert-danger text-center">${loginError}</div>
        </c:if>

        <div class="mb-3">
            <label for="email" class="form-label">Email Address</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
        </div>

        <button type="submit" class="btn btn-primary w-100">Login</button>

        <p class="text-center mt-3">
            Don't have an account? <a href="register.jsp">Register</a>
        </p>
    </form>
</div>

<%@ include file="footer.jsp" %>