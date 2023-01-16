package org.dteliukov.dao.mysql;

import org.dteliukov.dao.DaoRepository;
import org.dteliukov.dao.TripDao;
import org.dteliukov.dao.TypeDao;
import org.dteliukov.dao.UserDao;
import org.dteliukov.model.domain.TripInfo;
import org.dteliukov.model.domain.UserProfile;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class TripMySqlDaoTest {
    static UserProfile operator;
    static TripInfo tripInfo;
    static UserDao userDao;
    static TripDao tripDao;

    @BeforeAll
    static void setUp() {
        userDao = DaoRepository.getDao(TypeDao.MySQL).getUserDao();
        tripDao = DaoRepository.getDao(TypeDao.MySQL).getTripDao();
        operator = new UserProfile("Lastname", "Firstname", "operator@gmail.com",
                "0987654322", "1", "OPERATOR", "ACTIVE");
        userDao.registration(operator);
        var pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        tripInfo = new TripInfo(1, operator, LocalDateTime.now().format(pattern),
                LocalDateTime.now().plusDays(2).format(pattern), 160.50f, 10,
                "MERCURY", "ACTIVE");
        System.out.println(tripInfo.getStarted());
        tripDao.createTrip(tripInfo);
        var trips = tripDao.readTrips();
        tripInfo.id(trips.get(trips.size()-1).getId());
    }

    @Test
    void updateTripStatus() {
        tripDao.updateTripStatus(tripInfo.getId(), "FILLED");

        var updatedTripInfo = tripDao.getTripInfo(tripInfo.getId()).get();

        Assertions.assertNotEquals(tripInfo.getStatus(), updatedTripInfo.getStatus());
    }

    @Test
    void getTripsHistoryOperator() {
        var historyTrips = tripDao.getHistoryByOperator(operator).get();

        Assertions.assertNotEquals(0, historyTrips.getTrips().size());
    }

    @AfterAll
    static void deleteAll() {
        tripDao.deleteTrip(tripInfo.getId());
        userDao.deleteUser(operator.getEmail());
    }
}