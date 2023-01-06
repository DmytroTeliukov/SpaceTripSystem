package org.dteliukov.dao.mysql.queries;

public enum TripSqlQuery {
    CREATE_TRIP("call add_trip(?,?,?,?,?,?);"),
    UPDATE_TRIP_STATUS("call set_trip_status(?,?);"),
    DELETE_TRIP("delete from trip where id_trip = ?;"),
    GET_OPERATOR_HISTORY_TRIPS("call get_operator_trip_history(?);"),
    READ_TRIPS("select * from trip_view"),
    GET_TRIP("select * from trip_view where id_trip = ?");

    private final String query;

    TripSqlQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
