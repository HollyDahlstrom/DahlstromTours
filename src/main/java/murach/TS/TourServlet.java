package murach.TS;

import murach.DB.TourDB;
import murach.T.Tour;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/tour-details")
public class TourServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tourId = request.getParameter("tour");
        Tour tour = TourDB.selectTour(tourId);

        if (tour != null) {
            request.setAttribute("tour", tour);
            request.getRequestDispatcher("/tour-details.jsp").forward(request, response);
        } else {
            response.sendRedirect("all-tours");
        }
    }
}
