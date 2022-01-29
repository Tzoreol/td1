package fr.tzoreol.javaee.td1;

import fr.tzoreol.javaee.td1.utils.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = Properties.CONNECTION_SERVLET_NAME, urlPatterns = {Properties.CONNECTION_SERVLET_PATTERN})
public class ConnectionServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String login = "";
        Cookie[] cookies = request.getCookies();

        for(Cookie cookie: cookies) {
            if(cookie.getName().equals(Properties.LOGIN_PARAMETER)) {
                login = cookie.getValue();
            }
        }
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<form method=\"POST\" action=\"" + Properties.CONNECTION_SERVLET_NAME + "\">");
        out.println("<p><input type=\"text\" name=\"" + Properties.LOGIN_PARAMETER + "\" value=\"" + login + "\"/></p>");
        out.println("<p><input type=\"password\" name=\"" + Properties.PASSWORD_PARAMETER + "\"></p>");
        out.println("<p><input type=\"submit\" value=\"Connect\" /></p>");
        out.println("</form>");
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(Properties.LOGIN_PARAMETER);
        String password = request.getParameter(Properties.PASSWORD_PARAMETER);
        HttpSession session = request.getSession();

        if(isBlank(login) || isBlank(password)) {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/error.html");
            requestDispatcher.forward(request, response);
        } else {
            if(login.equals(Properties.ADMIN) && password.equals(Properties.ADMIN)) {
                session.setAttribute(Properties.ROLE_ATTRIBUTE, Properties.ADMIN);
                response.addCookie(createCookie(login));
                response.sendRedirect(request.getContextPath() + Properties.HELLO_SERVLET_PATTERN);
            } else if(login.equals(Properties.USER) && password.equals(Properties.USER)) {
                session.setAttribute(Properties.ROLE_ATTRIBUTE, Properties.USER);
                response.addCookie(createCookie(login));
                response.sendRedirect(request.getContextPath() + Properties.HELLO_SERVLET_PATTERN);
            }
        }
    }

    private boolean isBlank(String toCheck) {
        if((toCheck == null) || (toCheck.trim().equals(""))) {
            return true;
        }

        return false;
    }

    public Cookie createCookie(String login) {
        Cookie cookie = new Cookie(Properties.LOGIN_PARAMETER, login);
        cookie.setMaxAge(1800);

        return cookie;
    }
}
