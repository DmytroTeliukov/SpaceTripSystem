package org.dteliukov.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dteliukov.model.authorization.AuthorizedUser;

import java.io.IOException;

@WebFilter({"/operator-trips", "/check-orders", "/accept", "/deny"})
public class OperatorFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var request = ((HttpServletRequest) servletRequest);
        var response = ((HttpServletResponse) servletResponse);
        var user = ((AuthorizedUser) request.getSession().getAttribute("user"));

        if (user == null)
            response.sendRedirect("/403.jsp");
        else {
            if (user.role().equals("OPERATOR"))
                filterChain.doFilter(servletRequest, servletResponse);
            else
                response.sendRedirect("/403.jsp");
        }
    }
}
