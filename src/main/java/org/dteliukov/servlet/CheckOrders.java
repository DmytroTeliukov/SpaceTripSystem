package org.dteliukov.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.dteliukov.service.OrderService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/check-orders")
public class CheckOrders extends HttpServlet {

    private OrderService service;

    @Override
    public void init() throws ServletException {
        service = new OrderService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
        request.setAttribute("orders", service.getOrdersByTrip(id));
        getServletContext().getRequestDispatcher("/check-orders.jsp").forward(request, response);
    }
}
