package org.dteliukov.dao.mysql;


import org.dteliukov.dao.TripDao;
import org.dteliukov.dao.mysql.queries.TripSqlQuery;
import org.dteliukov.model.domain.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripMySqlDao implements TripDao {

    @Override
    public void createTrip(TripInfo trip) {
        int n = 1;
        try (var connection = MySqlConnection.getConnection()) {
            try (var statement = connection.prepareCall(TripSqlQuery.CREATE_TRIP.getQuery())) {
                statement.setString(n++, trip.getStarted());
                statement.setFloat(n++, trip.getPrice());
                statement.setInt(n++, trip.getCountVacancies());
                statement.setString(n++, trip.getOperator().getEmail());
                statement.setString(n++, trip.getPlanet());
                statement.setString(n, "ACTIVE");
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTripStatus(int id, String status) {
        int n = 1;
        try (var connection = MySqlConnection.getConnection()){
            try (var statement = connection.prepareCall(TripSqlQuery.UPDATE_TRIP_STATUS.getQuery())) {
                statement.setInt(n++, id);
                statement.setString(n, status);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTrip(int id) {
        try (var connection = MySqlConnection.getConnection()){
            try (var statement = connection.prepareStatement(TripSqlQuery.DELETE_TRIP.getQuery())) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Trip> readTrips() {
        List<Trip> trips = new ArrayList<>();
        try(var connection = MySqlConnection.getConnection()) {
            try(var statement = connection.prepareStatement(TripSqlQuery.READ_TRIPS.getQuery())) {
                try (var resultSet = statement.executeQuery()) {
                    Trip trip = new Trip();
                    while (resultSet.next()) {
                        trip.id(resultSet.getInt("id_trip"))
                                .started(resultSet.getString("started"))
                                .planet(resultSet.getString("planet_name"))
                                .status(resultSet.getString("trip_status"));
                        trips.add(trip.clone());
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trips;
    }

    @Override
    public Optional<OperatorTripHistory> getHistoryByOperator(User operator) {
        try (var connection = MySqlConnection.getConnection()) {
            try (var statement = connection
                    .prepareCall(TripSqlQuery.GET_OPERATOR_HISTORY_TRIPS.getQuery())) {
                statement.setString(1, operator.getEmail());
                try (var resultSet = statement.executeQuery()) {
                    OperatorTripHistory tripsHistory = new OperatorTripHistory(operator, new ArrayList<>());
                    Trip trip = new Trip();
                    while (resultSet.next()) {
                        trip.id(resultSet.getInt("id_trip"))
                                .started(resultSet.getString("started"))
                                .planet(resultSet.getString("planet_name"))
                                .status(resultSet.getString("trip_status"));
                        tripsHistory.addTrip(trip.clone());
                    }
                    return Optional.of(tripsHistory);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<TripInfo> getTripInfo(int id) {
        try(var connection = MySqlConnection.getConnection()) {
            try(var statement = connection.prepareStatement(TripSqlQuery.GET_TRIP.getQuery())) {
                statement.setInt(1, id);
                try (var resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        User user = new User(resultSet.getString("operator_lastname"),
                                resultSet.getString("operator_firstname"),
                                resultSet.getString("operator_email"),
                                resultSet.getString("operator_phone"));
                        TripInfo trip = new TripInfo(resultSet.getInt("id_trip"),
                                user,
                                resultSet.getString("created"),
                                resultSet.getString("started"),
                                resultSet.getFloat("price"),
                                resultSet.getInt("count_vacancies"),
                                resultSet.getString("planet_name"),
                                resultSet.getString("trip_status"));
                        return Optional.of(trip);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
