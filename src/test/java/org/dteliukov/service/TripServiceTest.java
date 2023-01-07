package org.dteliukov.service;


import org.dteliukov.exception.RegistrationException;
import org.dteliukov.model.domain.TripInfo;
import org.dteliukov.model.domain.UserProfile;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class TripServiceTest {
    static UserProfile operator;
    static TripInfo tripInfo;
    static UserService userService;
    static TripService tripService;

    @BeforeAll
    static void setUp() {
        userService = new UserService();
        tripService = new TripService();
        operator = new UserProfile("Lastname", "Firstname", "operator@gmail.com",
                "0987654322", "1", "OPERATOR", "ACTIVE");
        try {
            userService.registration(operator, "1");
        } catch (RegistrationException e) {
            throw new RuntimeException(e);
        }
        var pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        tripInfo = new TripInfo(1, operator, LocalDateTime.now().format(pattern),
                LocalDateTime.now().plusDays(2).format(pattern), 160.50f, 10,
                "MERCURY", "ACTIVE");
        tripService.createTrip(tripInfo);
        var trips = tripService.readTrips();
        tripInfo.id(trips.get(trips.size()-1).getId());
    }

    @Test
    void updateTripStatus() {
        tripService.updateTripStatus(tripInfo.getId(), "FILLED");

        var updatedTripInfo = tripService.getTrip(tripInfo.getId());

        Assertions.assertNotEquals(tripInfo.getStatus(), updatedTripInfo.getStatus());
    }

    @Test
    void getTripsHistoryOperator() {
        var historyTrips = tripService.getHistoryTripsByOperator(operator);

        Assertions.assertNotEquals(0, historyTrips.getTrips().size());
    }

    @AfterAll
    static void deleteAll() {
        tripService.deleteTrip(tripInfo.getId());
        userService.deleteUser(operator.getEmail());
    }
}