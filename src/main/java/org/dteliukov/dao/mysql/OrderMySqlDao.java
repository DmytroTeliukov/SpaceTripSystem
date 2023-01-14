package org.dteliukov.dao.mysql;

import org.dteliukov.dao.OrderDao;
import org.dteliukov.dao.mysql.queries.OrderSqlQuery;
import org.dteliukov.model.domain.Order;
import org.dteliukov.model.domain.Trip;
import org.dteliukov.model.domain.User;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OrderMySqlDao implements OrderDao {
    @Override
    public void createOrder(Order order) {
        int n = 1;
        try (var connection = MySqlConnection.getConnection()) {
            try (var statement = connection.prepareCall(OrderSqlQuery.CREATE_ORDER.getQuery())) {
                statement.setInt(n++, order.getOrderedSeats());
                statement.setFloat(n++, order.getPaymentAmount());
                statement.setInt(n++, order.getTrip().getId());
                statement.setString(n, order.getClient().getEmail());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateOrderStatus(int id, String status) {
        int n = 1;
        try (var connection = MySqlConnection.getConnection()){
            try (var statement = connection.prepareCall(OrderSqlQuery.UPDATE_ORDER_STATUS.getQuery())) {
                statement.setInt(n++, id);
                statement.setString(n, status);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void processOrder(int id) {
         try (var connection = MySqlConnection.getConnection()){
            try (var statement = connection.prepareStatement(OrderSqlQuery.PROCESS_ORDER.getQuery())) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> readOrders() {
        List<Order> orders = new ArrayList<>();
        try(var connection = MySqlConnection.getConnection()) {
            try(var statement = connection.prepareStatement(OrderSqlQuery.READ_ORDERS.getQuery())) {
                try (var resultSet = statement.executeQuery()) {
                    User user = new User();
                    Trip trip = new Trip();
                    Order order = new Order();
                    while (resultSet.next()) {
                        user.lastname(resultSet.getString("client_lastname"))
                                .firstname(resultSet.getString("client_firstname"))
                                .email(resultSet.getString("client_email"))
                                .phone(resultSet.getString("client_phone"));
                        trip.id(resultSet.getInt("id_trip"))
                                .started(resultSet.getString("started"))
                                .planet(resultSet.getString("planet_name"))
                                .status(resultSet.getString("trip_status"));
                        order.id(resultSet.getInt("id_order"))
                                .client(user.clone())
                                .trip(trip.clone())
                                .processed(resultSet.getString("processed"))
                                .orderedSeats(resultSet.getInt("ordered_seats"))
                                .paymentAmount(resultSet.getFloat("payment_amount"))
                                .status(resultSet.getString("order_status"));
                        orders.add(order.clone());
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    @Override
    public void deleteOrder(int id) {
        try (var connection = MySqlConnection.getConnection()){
            try (var statement = connection.prepareStatement(OrderSqlQuery.DELETE_ORDER.getQuery())) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getOrdersByTrip(int idTrip) {
        List<Order> orders = new ArrayList<>();
        try(var connection = MySqlConnection.getConnection()) {
            try(var statement = connection.prepareStatement(OrderSqlQuery.GET_ORDERS_BY_TRIP.getQuery())) {
                statement.setInt(1, idTrip);
                try (var resultSet = statement.executeQuery()) {
                    User user = new User();
                    Trip trip = new Trip();
                    Order order = new Order();
                    while (resultSet.next()) {
                        user.lastname(resultSet.getString("client_lastname"))
                                .firstname(resultSet.getString("client_firstname"))
                                .email(resultSet.getString("client_email"))
                                .phone(resultSet.getString("client_phone"));
                        trip.id(resultSet.getInt("id_trip"))
                                .started(resultSet.getString("started"))
                                .planet(resultSet.getString("planet_name"))
                                .status(resultSet.getString("trip_status"));
                        order.id(resultSet.getInt("id_order"))
                                .client(user.clone())
                                .trip(trip.clone())
                                .processed(resultSet.getString("processed"))
                                .orderedSeats(resultSet.getInt("ordered_seats"))
                                .paymentAmount(resultSet.getFloat("payment_amount"))
                                .status(resultSet.getString("order_status"));
                        orders.add(order.clone());
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersByUser(String email) {
        List<Order> orders = new ArrayList<>();
        try(var connection = MySqlConnection.getConnection()) {
            try(var statement = connection.prepareStatement(OrderSqlQuery.GET_ORDERS_BY_USER.getQuery())) {
                statement.setString(1, email);
                try (var resultSet = statement.executeQuery()) {
                    User user = new User();
                    Trip trip = new Trip();
                    Order order = new Order();
                    while (resultSet.next()) {
                        user.lastname(resultSet.getString("client_lastname"))
                                .firstname(resultSet.getString("client_firstname"))
                                .email(resultSet.getString("client_email"))
                                .phone(resultSet.getString("client_phone"));
                        trip.id(resultSet.getInt("id_trip"))
                                .started(resultSet.getString("started"))
                                .planet(resultSet.getString("planet_name"))
                                .status(resultSet.getString("trip_status"));
                        order.id(resultSet.getInt("id_order"))
                                .client(user.clone())
                                .trip(trip.clone())
                                .processed(resultSet.getString("processed"))
                                .orderedSeats(resultSet.getInt("ordered_seats"))
                                .paymentAmount(resultSet.getFloat("payment_amount"))
                                .status(resultSet.getString("order_status"));
                        orders.add(order.clone());
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        try(var connection = MySqlConnection.getConnection()) {
            try(var statement = connection.prepareStatement(OrderSqlQuery.GET_ORDER.getQuery())) {
                statement.setInt(1, id);
                try (var resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        User user = new User(resultSet.getString("client_lastname"),
                                resultSet.getString("client_firstname"),
                                resultSet.getString("client_email"),
                                resultSet.getString("client_phone"));
                        Trip trip = new Trip(resultSet.getInt("id_trip"),
                                resultSet.getString("started"),
                                resultSet.getString("planet_name"),
                                resultSet.getString("trip_status"));
                        Order order = new Order(resultSet.getInt("id_order"),
                                user,
                                trip,
                                resultSet.getString("processed"),
                                resultSet.getInt("ordered_seats"),
                                resultSet.getFloat("payment_amount"),
                                resultSet.getString("order_status"));
                        return Optional.of(order);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
