package org.dteliukov.model.domain;

import java.util.Objects;

public class TripInfo extends Trip{

    private String created;
    private Float price;
    private Integer countVacancies;
    private User operator;

    public TripInfo() {}

    public TripInfo(int id, User operator, String created, String started, Float price,
                    Integer countVacancies, String planet, String status) {
        super(id, started, planet, status);
        this.price = price;
        this.countVacancies = countVacancies;
        this.created = created;
        this.operator = operator;
    }

    public String getCreated() {
        return created;
    }

    public TripInfo created(String created) {
        this.created = created;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public TripInfo price(Float price) {
        this.price = price;
        return this;
    }

    public Integer getCountVacancies() {
        return countVacancies;
    }

    public TripInfo countVacancies(Integer countVacancies) {
        this.countVacancies = countVacancies;
        return this;
    }

    public User getOperator() {
        return operator;
    }

    public TripInfo operator(User operator) {
        this.operator = operator;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TripInfo tripInfo = (TripInfo) o;
        return Objects.equals(created, tripInfo.created) &&
                Objects.equals(price, tripInfo.price) &&
                Objects.equals(countVacancies, tripInfo.countVacancies) &&
                Objects.equals(operator, tripInfo.operator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), created, price, countVacancies, operator);
    }

    public TripInfo clone() {
        return new TripInfo(id, operator, created, started, price, countVacancies, planet, status);
    }
}
