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