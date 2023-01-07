package org.dteliukov.service;


import org.dteliukov.exception.RegistrationException;
import org.dteliukov.model.domain.Order;
import org.dteliukov.model.domain.TripInfo;
import org.dteliukov.model.domain.UserProfile;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class OrderServiceTest {
    static UserProfile client;
    static UserProfile operator;
    static TripInfo tripInfo;
    static UserService userService;
    static TripService tripService;
    static OrderService orderService;
    static Order order;
    static DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @BeforeAll
    static void setUp() {
        userService = new UserService();
        tripService = new TripService();
        orderService = new OrderService();
        client = new UserProfile("Lastname", "Firstname", "client@gmail.com",
                "0987654321", "1", "CLIENT", "ACTIVE");
        operator = new UserProfile("Lastname", "Firstname", "operator@gmail.com",
                "0987654322", "1", "OPERATOR", "ACTIVE");
        try {
            userService.registration(client, client.getPassword());
            userService.registration(operator, operator.getPassword());
        } catch (RegistrationException e) {
            throw new RuntimeException(e);
        }


        tripInfo = new TripInfo(1, operator, LocalDateTime.now().format(pattern),
                LocalDateTime.now().plusDays(2).format(pattern), 160.50f, 10,
                "MERCURY", "ACTIVE");
        tripService.createTrip(tripInfo);
        var trips = tripService.readTrips();
        tripInfo.id(trips.get(trips.size()-1).getId());

        order = new Order(1, client, tripInfo, null, 1, 160.50f, "ORDERED");
        orderService.createOrder(order);
        var orders = orderService.getOrdersByTrip(tripInfo.getId());
        order.id(orders.get(orders.size()-1).getId());
    }

    @Test
    void updateOrderStatus() {
        orderService.updateOrderStatus(order.getId(), "ACCEPTED");

        var updatedOrder = orderService.getOrder(order.getId());

        Assertions.assertNotEquals(order.getStatus(), updatedOrder.getStatus());
    }

    @Test
    void processOrder() {
        orderService.processOrder(order.getId());

        var updatedOrder = orderService.getOrder(order.getId());

        Assertions.assertNotNull(updatedOrder.getProcessed());
    }

    @Test
    void readOrders() {
        var orders = orderService.readOrders();

        Assertions.assertNotEquals(0, orders.size());

    }

    @AfterAll
    static void deleteAll() {
        orderService.deleteOrder(order.getId());
        tripService.deleteTrip(tripInfo.getId());
        userService.deleteUser(operator.getEmail());
        userService.deleteUser(client.getEmail());
    }
}