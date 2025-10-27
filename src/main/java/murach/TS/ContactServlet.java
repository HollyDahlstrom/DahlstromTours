package murach.TS;

import murach.DB.ContactDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/contact-us")
public class ContactServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        // 2. Validate input
        if (name == null || name.isEmpty() || email == null || !email.contains("@") || message == null || message.isEmpty()) {
            request.setAttribute("error", "Please fill in all fields with valid information.");
            request.getRequestDispatcher("/contact.jsp").forward(request, response);
            return;
        }

        // 3. Save to database
        boolean success = ContactDB.insertMessage(name, email, message);

        if (success) {
            request.setAttribute("success", "Thank you for contacting us! We will be in touch soon.");
        } else {
            request.setAttribute("error", "Sorry, there was a problem submitting your message. Please try again.");
        }

        request.getRequestDispatcher("/contact.jsp").forward(request, response);
    }
}