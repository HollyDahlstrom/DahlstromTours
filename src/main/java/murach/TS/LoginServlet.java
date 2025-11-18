package murach.TS;

import murach.DB.UserDB;
import murach.T.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 * LoginServlet.java - DahlstromTours
 * @author Holly Dahlstrom
 * @date 11/17/25
 *
 * The LoginServlet manages both login and logout functionality for
 * the DahlstromTours web application. It handles user authentication,
 * session creation, and session invalidation, depending on the
 * requested action.
 *
 * POST Requests:
 *  - action="login": Validates user credentials using UserDB.
 *      - On success: stores the User object in the session and
 *        redirects to the homepage.
 *      - On failure: returns the user to the login page with an error message.
 *
 * GET Requests:
 *  - action="logout": Invalidates the user's session and redirects to the homepage.
 *  - Any other GET action loads the login page.
 *
 * This servlet ensures that user session data is securely managed and
 * that login/logout workflows operate smoothly throughout the site.
 */
@WebServlet("/login-logout")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("login".equals(action)) {

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // Validate and get User object
            User user = UserDB.validateUser(email, password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user); // store full User object
                session.setAttribute("isLoggedIn", true);

                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("loginError", "Invalid credentials. Please try again.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }

        } else {
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("logout".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("index.jsp");
        } else {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}