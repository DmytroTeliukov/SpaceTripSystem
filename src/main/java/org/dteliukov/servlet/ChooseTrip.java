package org.dteliukov.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dteliukov.model.authorization.AuthorizedUser;
import org.dteliukov.model.domain.Trip;
import org.dteliukov.model.domain.User;
import org.dteliukov.service.TripService;

import java.io.IOException;

@WebServlet("/choose-trip")
public class ChooseTrip extends HttpServlet {
    private TripService service;

    @Override
    public void init() throws ServletException {
        service = new TripService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("trips", service.readTrips());
        getServletContext().getRequestDispatcher("/choose-trips.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var id = Integer.parseInt(request.getParameter("id"));
        Trip trip = service.getTrip(id);
        request.getSession().setAttribute("trip", trip);
        response.sendRedirect(getServletContext().getContextPath() + "/add-order");
    }
}
