package org.dteliukov.model.domain;

import java.util.Objects;

public class Trip {
    protected Integer id;
    protected String started;
    protected String planet;
    protected String status;

    public Trip() {}
    public Trip(int id, String started, String planet, String status) {
        this.id = id;
        this.started = started;
        this.planet = planet;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Trip id(int id) {
        this.id = id;
        return this;
    }

    public String getStarted() {
        return started;
    }

    public Trip started(String started) {
        this.started = started;
        return this;
    }

    public String getPlanet() {
        return planet;
    }

    public Trip planet(String planet) {
        this.planet = planet;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Trip status(String status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Objects.equals(id, trip.id) &&
                Objects.equals(started, trip.started) &&
                Objects.equals(planet, trip.planet) &&
                Objects.equals(status, trip.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, started, planet, status);
    }

    public Trip clone() {
        return new Trip(id, started, planet, status);
    }
}
