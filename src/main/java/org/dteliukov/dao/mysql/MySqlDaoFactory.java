package org.dteliukov.dao.mysql;

import org.dteliukov.dao.DaoFactory;
import org.dteliukov.dao.OrderDao;
import org.dteliukov.dao.TripDao;
import org.dteliukov.dao.UserDao;

public class MySqlDaoFactory implements DaoFactory {
    @Override
    public UserDao getUserDao() {
        return new UserMySqlDao();
    }

    @Override
    public OrderDao getOrderDao() {
        return new OrderMySqlDao();
    }

    @Override
    public TripDao getTripDao() {
        return new TripMySqlDao();
    }
}

