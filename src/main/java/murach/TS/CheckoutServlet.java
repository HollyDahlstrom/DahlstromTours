package murach.TS;

import murach.DB.OrderDB;
import murach.T.User;
import murach.T.Tour;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Map;

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