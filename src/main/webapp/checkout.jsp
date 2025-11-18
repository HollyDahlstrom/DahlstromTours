<%-- 
    File        : checkout.jsp
    Description : Displays cart summary and billing form for final order placement.
    Author      : Holly Dahlstrom
    Created     : 11-18-2025
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<%@ page import="java.util.Map" %>
<%@ page import="murach.T.Tour" %>
<%@ page import="java.text.NumberFormat" %>

<%
    Map<String, Tour> cart = (Map<String, Tour>) session.getAttribute("cart");
    if (cart == null || cart.isEmpty()) {
        response.sendRedirect("all-tours");
        return;
    }

    NumberFormat currency = NumberFormat.getCurrencyInstance(java.util.Locale.US);
%>

<div class="container my-5">
    <h2 class="text-center mb-4">Checkout</h2>

    <table class="table table-striped">
        <thead>
            <tr>
                <th>Tour</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Subtotal</th>
            </tr>
        </thead>
        <tbody>
            <%
                Map<String, Integer> quantities = (Map<String, Integer>) session.getAttribute("quantities");
                double total = 0;
                for (Map.Entry<String, Tour> entry : cart.entrySet()) {
                    Tour tour = entry.getValue();
                    int quantity = quantities.getOrDefault(tour.getId(), 1);
                    double subtotal = tour.getPrice() * quantity;
                    total += subtotal;
            %>
            <tr>
                <td><%= tour.getName() %></td>
                <td><%= currency.format(tour.getPrice()) %></td>
                <td><%= quantity %></td>
                <td><%= currency.format(subtotal) %></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>

    <h5 class="text-end">Total: <%= currency.format(total) %></h5>

    <form action="checkout" method="POST" class="col-md-6 mx-auto mt-4">
        <h4 class="mb-3">Billing Information</h4>

        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger text-center">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>

        <div class="mb-3">
            <label for="fullName" class="form-label">Full Name</label>
            <input type="text" class="form-control" id="fullName" name="fullName"
                   value="<%= request.getParameter("fullName") != null ? request.getParameter("fullName") : "" %>"
                   required>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">Email Address</label>
            <input type="email" class="form-control" id="email" name="email"
                   value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>"
                   required>
        </div>

        <button type="submit" class="btn btn-success w-100">Place Order</button>
    </form>
</div>

<%@ include file="footer.jsp" %>