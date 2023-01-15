package org.dteliukov.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.dteliukov.model.authorization.AuthorizedUser;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dteliukov.service.UserService;

import java.io.IOException;

@WebServlet("/profile")
public class Profile extends HttpServlet {

    private UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AuthorizedUser user = (AuthorizedUser) request.getSession().getAttribute("user");
        var profile = service.getUserProfileByEmail(user.email());

        request.setAttribute("lastname", profile.getLastname());
        request.setAttribute("firstname", profile.getFirstname());
        request.setAttribute("email", profile.getEmail());
        request.setAttribute("phone", profile.getPhone());
        request.setAttribute("role", profile.getRole());

        getServletContext().getRequestDispatcher("/user-profile.jsp").forward(request, response);
    }
}
