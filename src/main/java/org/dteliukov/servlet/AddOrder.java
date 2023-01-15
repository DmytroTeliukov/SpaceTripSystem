package org.dteliukov.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dteliukov.model.authorization.AuthorizedUser;
import org.dteliukov.model.domain.Order;
import org.dteliukov.model.domain.Trip;
import org.dteliukov.model.domain.TripInfo;
import org.dteliukov.model.domain.User;
import org.dteliukov.service.OrderService;
import org.dteliukov.service.TripService;
import org.dteliukov.service.UserService;

import java.io.IOException;

@WebServlet("/add-order")
public class AddOrder extends HttpServlet {

    private TripService tripService;
    private UserService userService;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        orderService = new OrderService();
        userService = new UserService();
        tripService = new TripService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("trip", ((Trip) request.getSession().getAttribute("trip")));
        getServletContext().getRequestDispatcher("/add-order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User client = userService
                .getUserProfileByEmail(((AuthorizedUser) request.getSession().getAttribute("user")).email());
        TripInfo trip = tripService.getTrip(((Trip) request.getSession().getAttribute("trip")).getId());
        int orderedSeats = Integer.parseInt(request.getParameter("orderedSeats"));


        Order order = new Order()
                .client(client)
                .orderedSeats(orderedSeats)
                .trip(trip)
                .paymentAmount(trip.getPrice() * orderedSeats)
                .status("ORDERED");

        orderService.createOrder(order);

        response.sendRedirect(getServletContext().getContextPath() + "/history");
    }
}
