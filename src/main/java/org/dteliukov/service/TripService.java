package org.dteliukov.service;

import org.dteliukov.dao.DaoRepository;
import org.dteliukov.dao.TripDao;
import org.dteliukov.dao.TypeDao;
import org.dteliukov.model.domain.OperatorTripHistory;
import org.dteliukov.model.domain.Trip;
import org.dteliukov.model.domain.TripInfo;
import org.dteliukov.model.domain.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TripService {

    private final TripDao dao;

    public TripService() {
        dao = DaoRepository.getDao(TypeDao.MySQL).getTripDao();
    }

    public void createTrip(TripInfo trip) {
        var pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

        trip.started(LocalDateTime.parse(trip.getStarted()).format(pattern));
        dao.createTrip(trip);
    }

    public void updateTripStatus(int id, String status) {
        dao.updateTripStatus(id, status);
    }

    public void deleteTrip(int id) {
        dao.deleteTrip(id);
    }

    public OperatorTripHistory getHistoryTripsByOperator(User operator) {
       var history = dao.getHistoryByOperator(operator);

       return history.orElseThrow(() -> new RuntimeException("History does not exist!"));
    }

    public TripInfo getTrip(int id) {
        var trip = dao.getTripInfo(id);

        return trip.orElseThrow(() -> new RuntimeException("Trip does not exist!"));
    }

    public List<Trip> readTrips() {
        return dao.readTrips();
    }
}
