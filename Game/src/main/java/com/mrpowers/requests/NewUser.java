package com.mrpowers.requests;

import com.mrpowers.exceptions.IllegalMoveException;
import com.mrpowers.QueryBuilder;

public class NewUser extends RequestData {
    String user;
    String password;
    String email;

    public NewUser(){}

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.createUser(user, email,password);
        QueryBuilder.disconnectDb();
        System.out.println("test");
    }
}
