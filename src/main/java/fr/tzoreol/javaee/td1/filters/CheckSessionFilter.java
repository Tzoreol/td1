package fr.tzoreol.javaee.td1.filters;

import fr.tzoreol.javaee.td1.utils.Properties;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckSessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        if(httpServletRequest.getSession().getAttribute(Properties.ROLE_ATTRIBUTE) == null) {
            System.out.println("Redirect to: " + ((HttpServletRequest) servletRequest).getContextPath() + Properties.CONNECTION_SERVLET_PATTERN);
           httpServletResponse.sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + Properties.CONNECTION_SERVLET_PATTERN);
           return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
