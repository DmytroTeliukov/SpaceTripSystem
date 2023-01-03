package org.dteliukov.model.domain;

public class TripInfo extends Trip{

    private String created;
    private Float price;
    private Integer countVacancies;
    private User operator;

    public TripInfo() {}

    public TripInfo(int id, User operator, String created, String started, Float price, Integer countVacancies, String planet, String status) {
        super(id, started, planet, status);
        this.price = price;
        this.countVacancies = countVacancies;
        this.created = created;
        this.operator = operator;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getCountVacancies() {
        return countVacancies;
    }

    public void setCountVacancies(Integer countVacancies) {
        this.countVacancies = countVacancies;
    }

    public User getOperator() {
        return operator;
    }

    public void setOperator(User operator) {
        this.operator = operator;
    }
}
