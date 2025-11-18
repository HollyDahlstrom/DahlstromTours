<%-- 
    File        : cart.jsp
    Description : Displays cart contents, allows quantity updates and item removal.
    Author      : Holly Dahlstrom
    Created     : 11-18-2025
--%>

<%@ include file="header.jsp" %>
<%@ page import="java.util.Map" %>
<%@ page import="murach.T.Tour" %>
<%@ page import="java.text.NumberFormat" %>

<%
    Map<String, Tour> cart = (Map<String, Tour>) session.getAttribute("cart");
    Map<String, Integer> quantities = (Map<String, Integer>) session.getAttribute("quantities");

    NumberFormat currency = NumberFormat.getCurrencyInstance(java.util.Locale.US);

    String loginMessage = (String) session.getAttribute("loginMessage");
    if (loginMessage != null) {
%>
    <div class="alert alert-warning text-center">
        <%= loginMessage %>
    </div>
<%
        session.removeAttribute("loginMessage");
    }
%>

<div class="container my-5">
    <h2 class="text-center mb-4">Your Shopping Cart</h2>

<%
    if (cart == null || cart.isEmpty()) {
%>
    <div class="alert alert-info text-center">
        Your cart is empty.
        <a href="all-tours" class="btn btn-primary ms-2">Browse Tours</a>
    </div>
<%
    } else {
        double total = 0;
%>

    <table class="table table-striped align-middle text-center">
        <thead>
            <tr>
                <th>Tour</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Subtotal</th>
                <th>Remove</th>
            </tr>
        </thead>
        <tbody>
<%
        for (Map.Entry<String, Tour> entry : cart.entrySet()) {
            Tour tour = entry.getValue();
            int qty = quantities.getOrDefault(tour.getId(), 1);
            tour.setQuantity(qty); // Ensure the object has the correct quantity
            double subtotal = tour.getPrice() * qty;
            total += subtotal;
%>
            <tr>
                <td><%= tour.getName() %></td>
                <td><%= currency.format(tour.getPrice()) %></td>
                <td>
                <form action="cart" method="post" style="display:inline-flex; align-items:center; margin:0; padding:0;">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="tour" value="<%= tour.getId() %>">
                    <input type="number" name="quantity" value="<%= qty %>" min="1"
               class="form-control form-control-sm"
               style="width:60px; height:32px; padding:0 0.25rem; text-align:center; margin-right:0.25rem;">
                    <button type="submit"
                class="btn btn-secondary btn-sm"
                style="height:32px; padding:0 0.5rem;">
                        Update
                    </button>
                </form>
                </td>
                <td><%= currency.format(subtotal) %></td>
                <td>
                    <form action="cart" method="post" class="d-inline">
                        <input type="hidden" name="action" value="remove">
                        <input type="hidden" name="tour" value="<%= tour.getId() %>">
                        <button type="submit" class="btn btn-danger btn-sm">Remove</button>
                    </form>
                </td>
            </tr>
<%
        } // end for
%>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="3" class="text-end"><strong style="font-size:1.2rem">Total:</strong></td>
                <td colspan="2"><strong style="font-size:1.2rem"><%= currency.format(total) %></strong></td>
            </tr>
        </tfoot>
    </table>

<div class="text-end mt-4">
    <a href="checkout.jsp" class="btn btn-success mb-2" style="width:100%; max-width:250px;">
        Proceed to Checkout
    </a><br>
    <a href="all-tours" class="btn btn-info text-white" style="width:100%; max-width:250px;">
        Add More Tours
    </a>
</div>

<% } // end else %>

</div>

<%@ include file="footer.jsp" %>