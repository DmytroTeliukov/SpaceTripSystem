package org.dteliukov.dao;

import org.dteliukov.model.domain.OperatorTripHistory;
import org.dteliukov.model.domain.Trip;
import org.dteliukov.model.domain.TripInfo;
import org.dteliukov.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface TripDao {
    void createTrip(TripInfo trip);
    void updateTripStatus(int id, String status);
    void deleteTrip(int id);
    List<Trip> readTrips();
    Optional<OperatorTripHistory> getHistoryByOperator(User operator);
    Optional<TripInfo> getTripInfo(int id);
}
