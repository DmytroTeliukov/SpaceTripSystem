package org.dteliukov.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dteliukov.model.domain.Trip;
import org.dteliukov.model.domain.TripInfo;
import org.dteliukov.model.domain.User;
import org.dteliukov.service.TripService;

import java.io.IOException;

@WebServlet("/add-trip")
public class AddTrip extends HttpServlet {
    private TripService service;

    @Override
    public void init() throws ServletException {
        service = new TripService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String planet = (String) request.getSession().getAttribute("planet");
        User operator = (User) request.getSession().getAttribute("operator");

        if(planet != null) {
            request.setAttribute("planet", planet);
            request.getSession().removeAttribute("planet");
        }
        else
            request.setAttribute("planet", "MERCURY");

        if(operator != null)
            request.setAttribute("operator", operator);

        getServletContext().getRequestDispatcher("/add-trip.jsp").forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User operator = ((User) request.getSession().getAttribute("operator"));
        String planet = request.getParameter("planet");
        String started = request.getParameter("date") + " " + request.getParameter("time");
        Float price = Float.parseFloat(request.getParameter("price"));
        Integer countVacancies = Integer.parseInt(request.getParameter("countVacancies"));

        TripInfo trip = new TripInfo(0, operator, null, started, price, countVacancies, planet, "CREATED");
        service.createTrip(trip);

        request.getSession().removeAttribute("operator");
        response.sendRedirect(getServletContext().getContextPath() + "/trips");
    }
}
