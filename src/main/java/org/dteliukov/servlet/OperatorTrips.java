package org.dteliukov.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dteliukov.model.authorization.AuthorizedUser;
import org.dteliukov.service.TripService;
import org.dteliukov.service.UserService;

import java.io.IOException;

@WebServlet("/operator-trips")
public class OperatorTrips extends HttpServlet {
    private TripService tripService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        tripService = new TripService();
        userService = new UserService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var user = ((AuthorizedUser) request.getSession().getAttribute("user"));
        request.setAttribute("trips", tripService
                .getHistoryTripsByOperator(userService.getUserProfileByEmail(user.email())));
        getServletContext().getRequestDispatcher("/operator-trips.jsp").forward(request, response);
    }
}
