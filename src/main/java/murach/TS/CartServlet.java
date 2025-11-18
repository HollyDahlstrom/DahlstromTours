package murach.TS;

import murach.T.Tour;
import murach.T.User;
import murach.DB.TourDB;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * CartServlet.java - DahlstromTours
 * @author Holly Dahlstrom
 * @date 11/17/25
 *
 * The CartServlet handles the shopping cart functionality for the DahlstromTours web application.
 * Users can add, update, or remove tours from their cart. The servlet also ensures that only
 * logged-in users can modify their cart.
 *
 * doPost:
 *   - Retrieves the logged-in user from the session; if not logged in, redirects to login.jsp with a message.
 *   - Retrieves or initializes the user's cart and quantity maps from the session.
 *   - Processes "add", "update", or "remove" actions for tours based on request parameters:
 *       - add: Adds a tour to the cart or increments the quantity if already in cart.
 *       - update: Updates the quantity of a tour or removes it if the quantity is zero or less.
 *       - remove: Removes a tour from the cart and quantity map.
 *   - Updates the cart in the session and redirects back to cart.jsp.
 *
 * doGet:
 *   - Forwards the request to cart.jsp to display the current contents of the user's cart.
 *
 * This servlet ensures that cart operations are properly tracked in the user's session and
 * reflects the latest quantities and selections for checkout.
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            session.setAttribute("loginMessage", "You must log in to add tours to your cart.");
            response.sendRedirect("login.jsp");
            return;
        }

        Map<String, Tour> cart = (Map<String, Tour>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }

        Map<String, Integer> quantities = (Map<String, Integer>) session.getAttribute("quantities");
        if (quantities == null) {
            quantities = new HashMap<>();
            session.setAttribute("quantities", quantities);
        }

        String action = request.getParameter("action");
        String tourId = request.getParameter("tour");

        if (action != null && tourId != null) {
            switch (action) {
                case "add":
                    if (!cart.containsKey(tourId)) {
                        Tour tour = TourDB.selectTour(tourId);
                        if (tour != null) {
                            cart.put(tourId, tour);
                            quantities.put(tourId, 1);
                            tour.setQuantity(1);
                        }
                    } else {
                        int qty = quantities.get(tourId) + 1;
                        quantities.put(tourId, qty);
                        cart.get(tourId).setQuantity(qty);
                    }
                    break;

                case "update":
                    if (cart.containsKey(tourId)) {
                        int qty = Integer.parseInt(request.getParameter("quantity"));
                        if (qty <= 0) {
                            cart.remove(tourId);
                            quantities.remove(tourId);
                        } else {
                            quantities.put(tourId, qty);
                            cart.get(tourId).setQuantity(qty);
                        }
                    }
                    break;

                case "remove":
                    cart.remove(tourId);
                    quantities.remove(tourId);
                    break;
            }
        }

        response.sendRedirect("cart.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }
}