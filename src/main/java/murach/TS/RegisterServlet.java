package murach.TS;

import murach.DB.UserDB;
import murach.T.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 * RegisterServlet.java - DahlstromTours
 * @author Holly Dahlstrom
 * @date 11/17/25
 *
 * The RegisterServlet handles user registration requests for the
 * DahlstromTours web application. It collects user information from
 * the registration form, creates a new User object, and attempts to
 * insert that user into the database through UserDB.
 *
 * If registration is successful, the servlet forwards the user to the
 * login page with a success message. If registration fails, typically due
 * to an existing email, an error message is displayed and the user is
 * returned to the registration form.
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean success = UserDB.insertUser(new User(email, password, firstName, lastName));

        if (success) {
            // Show message temporarily before redirecting to login
            request.setAttribute("success", "Registration successful! You can now log in.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Registration failed. Email may already exist.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}
