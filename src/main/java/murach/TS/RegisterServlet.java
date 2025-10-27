package murach.TS;

import murach.DB.UserDB;
import murach.T.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // ✅ Correct parameter order: email, passwordHash, firstName, lastName
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
