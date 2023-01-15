package org.dteliukov.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dteliukov.exception.RegistrationException;
import org.dteliukov.model.domain.UserProfile;
import org.dteliukov.service.UserService;

import java.io.IOException;

@WebServlet("/add-operator")
public class AddOperator extends HttpServlet {
    private UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/add-operator.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastname = String.valueOf(request.getParameter("lastname")).trim();
        String firstname = String.valueOf(request.getParameter("firstname")).trim();
        String email = String.valueOf(request.getParameter("email")).trim();
        String phone = String.valueOf(request.getParameter("phone")).trim();
        String password = request.getParameter("password").trim();
        String confirmedPassword = request.getParameter("confirmed_password").trim();

        try {
            UserProfile user = new UserProfile(lastname, firstname, email, phone,
                    password, "OPERATOR", "ACTIVE");

            service.registration(user, confirmedPassword);
            response.sendRedirect(getServletContext().getContextPath() + "/operators");
        } catch (RegistrationException e) {
            request.setAttribute("error", e.getMessage());
            getServletContext().getRequestDispatcher("/add-operator.jsp").forward(request, response);
        }


    }
}
