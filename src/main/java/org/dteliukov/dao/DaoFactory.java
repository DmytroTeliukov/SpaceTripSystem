package org.dteliukov.dao;

public interface DaoFactory {
    UserDao getUserDao();
    OrderDao getOrderDao();
    TripDao getTripDao();
}
