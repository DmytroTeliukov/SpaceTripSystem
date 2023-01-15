package org.dteliukov.servlet;

import org.dteliukov.exception.AuthorizationException;
import org.dteliukov.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class SignIn extends HttpServlet {

    private UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = String.valueOf(request.getParameter("email")).trim();
        String password = String.valueOf(request.getParameter("password")).trim();

        try {
            var user = service.authorize(email, password);
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/profile");
        } catch (AuthorizationException e) {
            request.setAttribute("error", e.getMessage());
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }
    
}
