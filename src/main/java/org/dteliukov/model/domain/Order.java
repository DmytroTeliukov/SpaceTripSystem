package org.dteliukov.model.domain;

import java.util.Objects;

public class Order {
    private Integer id;
    private User client;
    private Trip trip;
    private String processed;
    private Integer orderedSeats;
    private Float paymentAmount;
    private String status;

    public Order() {}
    public Order(Integer id, User client, Trip trip, String processed,
                 Integer orderedSeats, Float paymentAmount, String status) {
        this.id = id;
        this.client = client;
        this.trip = trip;
        this.processed = processed;
        this.orderedSeats = orderedSeats;
        this.paymentAmount = paymentAmount;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Order id(Integer id) {
        this.id = id;
        return this;
    }

    public User getClient() {
        return client;
    }

    public Order client(User client) {
        this.client = client;
        return this;
    }

    public Trip getTrip() {
        return trip;
    }

    public Order trip(Trip trip) {
        this.trip = trip;
        return this;
    }

    public String getProcessed() {
        return processed;
    }

    public Order processed(String processed) {
        this.processed = processed;
        return this;
    }

    public Integer getOrderedSeats() {
        return orderedSeats;
    }

    public Order orderedSeats(Integer orderedSeats) {
        this.orderedSeats = orderedSeats;
        return this;
    }

    public Float getPaymentAmount() {
        return paymentAmount;
    }

    public Order paymentAmount(Float paymentAmount) {
        this.paymentAmount = paymentAmount;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Order status(String status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(client, order.client) &&
                Objects.equals(trip, order.trip) &&
                Objects.equals(processed, order.processed) &&
                Objects.equals(orderedSeats, order.orderedSeats) &&
                Objects.equals(paymentAmount, order.paymentAmount) &&
                Objects.equals(status, order.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, trip, processed, orderedSeats, paymentAmount, status);
    }

    public Order clone() {
        return new Order(id, client, trip, processed, orderedSeats, paymentAmount, status);
    }
}
