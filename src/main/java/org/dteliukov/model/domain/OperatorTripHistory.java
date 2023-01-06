package org.dteliukov.model.domain;

import java.util.List;

public class OperatorTripHistory {
    private User operator;
    private List<Trip> trips;

    public OperatorTripHistory() {}

    public OperatorTripHistory(User operator, List<Trip> trips) {
        this.operator = operator;
        this.trips = trips;
    }

    public User getOperator() {
        return operator;
    }

    public OperatorTripHistory operator(User operator) {
        this.operator = operator;
        return this;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }
}
