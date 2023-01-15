package org.dteliukov.filter;

import jakarta.servlet.*;

import jakarta.servlet.annotation.WebFilter;
import org.dteliukov.model.authorization.AuthorizedUser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/profile")
public class ProfileFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var request = ((HttpServletRequest) servletRequest);
        var response = ((HttpServletResponse) servletResponse);
        var user = ((AuthorizedUser) request.getSession().getAttribute("user"));

        if (user != null)
            filterChain.doFilter(servletRequest, servletResponse);
        else
            response.sendRedirect("/login");

    }
}
