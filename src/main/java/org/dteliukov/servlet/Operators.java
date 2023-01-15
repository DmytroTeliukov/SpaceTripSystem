package org.dteliukov.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dteliukov.service.UserService;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/operators")
public class Operators extends HttpServlet {

    private UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("operators", service.readOperators());
        getServletContext().getRequestDispatcher("/operators.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var email = String.valueOf(request.getParameter("email"));
        service.deleteUser(email);
        response.sendRedirect(getServletContext().getContextPath() + "/operators");
    }


}
