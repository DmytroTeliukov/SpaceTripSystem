package org.dteliukov.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dteliukov.service.OrderService;

import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/deny")
public class DenyOrder extends HttpServlet {

    private OrderService service;

    @Override
    public void init() throws ServletException {
        service = new OrderService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service.updateOrderStatus(Integer.parseInt(request.getParameter("id_order")), "DENIED");
        response.sendRedirect(getServletContext().getContextPath() + "/operator-orders");
    }
}
