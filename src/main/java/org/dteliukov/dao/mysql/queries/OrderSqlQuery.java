package org.dteliukov.dao.mysql.queries;

public enum OrderSqlQuery {
    CREATE_ORDER("call add_order(?,?,?,?);"),
    UPDATE_ORDER_STATUS("call set_order_status(?,?);"),
    PROCESS_ORDER("update `order` set `processed` = NOW() where id_order = ?;"),
    DELETE_ORDER("delete from `order` where id_order = ?;"),
    READ_ORDERS("select * from order_view"),
    GET_ORDERS_BY_TRIP("select * from order_view where id_trip = ?"),
    GET_ORDERS_BY_USER("select * from order_view where client_email = ?"),
    GET_ORDER("select * from order_view where id_order = ?");

    private final String query;

    OrderSqlQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
