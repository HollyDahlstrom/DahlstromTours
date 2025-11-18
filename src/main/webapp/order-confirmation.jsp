<%-- 
    File: orderConfirmation.jsp
    Author: Holly Dahlstrom
    Created: 11/18/2025
    Description: Displays order confirmation details after a successful purchase.
                 Shows order ID, total price, and a breakdown of purchased tours
                 including quantity and line totals. Uses data passed from the 
                 CheckoutServlet and formats output for user-friendly display.
--%>

<%@ include file="header.jsp" %>
<%@ page import="murach.T.Tour" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.text.NumberFormat" %>

<%
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(java.util.Locale.US);

    int orderId = request.getAttribute("orderId") != null ? (Integer) request.getAttribute("orderId") : -1;
    double totalPrice = request.getAttribute("totalPrice") != null ? (Double) request.getAttribute("totalPrice") : 0.0;
    Collection<Tour> orderedTours = (Collection<Tour>) request.getAttribute("orderedTours");
    Map<String, Integer> quantities = (Map<String, Integer>) request.getAttribute("quantities");
%>

<div class="container my-5">
    <h2 class="text-center mb-4">Order Confirmation</h2>

    <p class="lead text-center text-success">
        Thank you! Your order has been placed successfully.
    </p>

    <div class="text-center mb-4">
        <h5>Order ID: <%= orderId %></h5>
        <h6>Total: <%= currencyFormat.format(totalPrice) %></h6>
    </div>

    <h4>Purchased Tours</h4>

    <table class="table table-striped">
        <thead>
            <tr>
                <th>Tour</th>
                <th>Country</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Line Total</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (orderedTours != null && !orderedTours.isEmpty()) {
                    for (Tour tour : orderedTours) {
                        int qty = 1;
                        if (quantities != null && quantities.containsKey(tour.getId())) {
                            qty = quantities.get(tour.getId());
                        }
                        double lineTotal = tour.getPrice() * qty;
            %>
                <tr>
                    <td><%= tour.getName() %></td>
                    <td><%= tour.getCountry() %></td>
                    <td><%= currencyFormat.format(tour.getPrice()) %></td>
                    <td><%= qty %></td>
                    <td><%= currencyFormat.format(lineTotal) %></td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="5" class="text-center">No tours found.</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>

    <div class="text-end mt-3">
        <h5>Grand Total: <%= currencyFormat.format(totalPrice) %></h5>
    </div>

    <div class="text-center mt-4">
        <a href="all-tours" class="btn btn-primary">Browse More Tours</a>
    </div>
</div>

<%@ include file="footer.jsp" %>