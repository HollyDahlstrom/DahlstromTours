package murach.TS;

import murach.DB.TourDB;
import murach.T.Tour;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

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