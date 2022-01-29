package fr.tzoreol.javaee.td1.restricted;

import fr.tzoreol.javaee.td1.dto.Administrator;
import fr.tzoreol.javaee.td1.dto.Anyone;
import fr.tzoreol.javaee.td1.dto.User;
import fr.tzoreol.javaee.td1.utils.Properties;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = Properties.HELLO_SERVLET_NAME, value = Properties.HELLO_SERVLET_PATTERN)
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        String role = (String) session.getAttribute(Properties.ROLE_ATTRIBUTE);
        System.out.println(role);
        Anyone anyone;

        switch (role) {
            case Properties.ADMIN:
                anyone = new Administrator();
                break;
            default:
                anyone = new User();
        }

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + anyone.introduceYourself() + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}