package org.dteliukov.model.domain;

public class Order {
    private User client;
    private Trip trip;
    private String processed;
    private Integer orderedSeats;
    private Float paymentAmount;
    private String status;

    public Order(User client, Trip trip, String processed, Integer orderedSeats, Float paymentAmount, String status) {
        this.client = client;
        this.trip = trip;
        this.processed = processed;
        this.orderedSeats = orderedSeats;
        this.paymentAmount = paymentAmount;
        this.status = status;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public String getProcessed() {
        return processed;
    }

    public void setProcessed(String processed) {
        this.processed = processed;
    }

    public Integer getOrderedSeats() {
        return orderedSeats;
    }

    public void setOrderedSeats(Integer orderedSeats) {
        this.orderedSeats = orderedSeats;
    }

    public Float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
