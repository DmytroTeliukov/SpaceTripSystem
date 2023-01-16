package org.dteliukov.dao.mysql.queries;

public enum UserSqlQuery {

    AUTHORIZATION("select role_name from user_view where email = ? and `password` = ?"),
    REGISTRATION("call registration(?,?,?,?,?,?)"),
    UPDATE_USER("update `user` set lastname = ?, firstname = ?, email = ?, phone = ?, password = ?"),
    UPDATE_STATUS("call set_user_status(?,?);"),
    DELETE_USER("delete from `user` where email = ?"),
    READ_USERS("select * from `user_view`;"),
    READ_OPERATORS("select * from user_view where role_name = 'OPERATOR'"),
    GET_USER("select * from `user_view` where email = ?");

    private final String query;

    UserSqlQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
