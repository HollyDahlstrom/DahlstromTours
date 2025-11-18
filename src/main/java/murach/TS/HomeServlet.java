package murach.TS;

import murach.DB.TourDB;
import murach.T.Tour;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * HomeServlet.java - DahlstromTours
 * @author Holly Dahlstrom
 * @date 11/17/25
 *
 * The HomeServlet handles requests for displaying all available tours
 * in the DahlstromTours web application. It supports two URL patterns:
 *  - /home
 *  - /all-tours
 *
 * When a GET request is received, this servlet retrieves a list of all
 * tours from the database using TourDB and attaches the list to the
 * request as an attribute. It then forwards the user to the
 * all-tours.jsp page, where the tours are displayed.
 *
 * This servlet ensures that users always see the most up-to-date tour
 * information each time they visit the tours page.
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/home", "/all-tours"})
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Tour> tours = TourDB.selectAllTours();
        request.setAttribute("tours", tours);
        request.getRequestDispatcher("/all-tours.jsp").forward(request, response);
    }
}