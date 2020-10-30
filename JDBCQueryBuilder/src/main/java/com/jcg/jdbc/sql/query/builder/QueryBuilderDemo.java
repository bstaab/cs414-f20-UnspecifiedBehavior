package com.jcg.jdbc.sql.query.builder;

import java.util.Arrays;

public class QueryBuilderDemo {
    public static void main(String[] args){
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.createUser("testuser","test@user","test123");
        String [] s = QueryBuilder.searchUser("testuser");
        QueryBuilder.changePassword("testuser","weeeeeee");
        QueryBuilder.disconnectDb();
    }
}
