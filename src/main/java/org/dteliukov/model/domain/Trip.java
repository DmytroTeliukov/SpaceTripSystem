package org.dteliukov.model.domain;

public class Trip {
    protected int id;
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

    public void setId(int id) {
        this.id = id;
    }

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
