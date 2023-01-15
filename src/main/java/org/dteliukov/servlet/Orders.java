package org.dteliukov.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.dteliukov.model.authorization.AuthorizedUser;
import org.dteliukov.service.OrderService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/history")
public class Orders extends HttpServlet {

    private OrderService service;

    @Override
    public void init() throws ServletException {
        service = new OrderService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var user = ((AuthorizedUser) request.getSession().getAttribute("user"));
        request.setAttribute("role", user.role());
        request.setAttribute("orders", service.readOrders());
        getServletContext().getRequestDispatcher("/history.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var id = Integer.parseInt(request.getParameter("id"));
        service.deleteOrder(id);
        response.sendRedirect(getServletContext().getContextPath() + "/history");
    }
}
