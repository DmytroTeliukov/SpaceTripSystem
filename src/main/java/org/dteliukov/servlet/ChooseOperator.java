package org.dteliukov.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dteliukov.model.domain.User;
import org.dteliukov.service.UserService;

import java.io.IOException;

@WebServlet("/choose-operator")
public class ChooseOperator extends HttpServlet {
    private UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("operators", service.readOperators());
        getServletContext().getRequestDispatcher("/choose-operator.jsp").forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var email = String.valueOf(request.getParameter("email"));
        User operator = service.getUserProfileByEmail(email);
        request.getSession().setAttribute("operator", operator);
        response.sendRedirect(getServletContext().getContextPath() + "/add-trip");
    }
}
