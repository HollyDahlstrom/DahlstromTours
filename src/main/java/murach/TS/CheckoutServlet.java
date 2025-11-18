package murach.TS;

import murach.DB.OrderDB;
import murach.T.User;
import murach.T.Tour;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Map;

/**
 * CheckoutServlet.java - DahlstromTours
 * @author Holly Dahlstrom
 * @date 11/17/25
 *
 * The CheckoutServlet handles requests related to the checkout process
 * in the DahlstromTours web application.
 *
 * doGet:
 *   - Redirects users to the checkout.jsp page when they visit /checkout.
 *
 * doPost:
 *   - Retrieves the user's cart and billing information from the session and request.
 *   - Validates that the cart is not empty and billing info is provided correctly.
 *   - Ensures the user is logged in before placing an order.
 *   - Calculates the total price based on cart items and their quantities.
 *   - Inserts the order and its items into the database using OrderDB.
 *   - Forwards the user to order-confirmation.jsp with order details.
 *   - Clears the cart and quantities from the session after processing.
 *
 * This servlet ensures a smooth and secure checkout process with proper
 * validation and order tracking for users.
 */
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("checkout.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Map<String, Tour> cart = (Map<String, Tour>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            request.setAttribute("error", "Your cart is empty.");
            request.getRequestDispatcher("/checkout.jsp").forward(request, response);
            return;
        }

        // Billing info
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");

        if (fullName == null || fullName.isEmpty() || email == null || !email.contains("@")) {
            request.setAttribute("error", "Please provide valid billing information.");
            request.getRequestDispatcher("/checkout.jsp").forward(request, response);
            return;
        }

        // Get logged-in user
        User user = (User) session.getAttribute("user");
        if (user == null) {
            request.setAttribute("error", "You must be logged in to place an order.");
            request.getRequestDispatcher("/checkout.jsp").forward(request, response);
            return;
        }

        // Calculate total price
        Map<String, Integer> quantities = (Map<String, Integer>) session.getAttribute("quantities");
        double totalPrice = 0;

        if (cart != null && quantities != null) {
            for (Map.Entry<String, Tour> entry : cart.entrySet()) {
                String tourId = entry.getKey();
                int qty = quantities.getOrDefault(tourId, 1);
                totalPrice += entry.getValue().getPrice() * qty;
            }
        }
        // Save order + order items
        int orderId = OrderDB.insertOrder(user.getUserId(), totalPrice, cart, quantities);

        if (orderId > 0) {
        // Save cart to show on confirmation
        request.setAttribute("orderedTours", cart.values());
        request.setAttribute("orderId", orderId);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("quantities", session.getAttribute("quantities"));

        // Clear cart/quantities after passing the data
        session.removeAttribute("cart");
        session.removeAttribute("quantities");

        request.getRequestDispatcher("/order-confirmation.jsp").forward(request, response);
        }
    }
}