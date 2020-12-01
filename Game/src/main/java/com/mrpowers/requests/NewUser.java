package com.mrpowers.requests;

import com.mrpowers.exceptions.IllegalMoveException;
import com.mrpowers.QueryBuilder;

public class NewUser extends RequestData {
    private String user;
    private String password;
    private String email;

    public NewUser(String user, String password, String email){
        this.email=email;
        this.password=password;
        this.user=user;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        System.out.println(user);
        System.out.println(password);
        System.out.println(email);
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.createUser(user, email,password);
        QueryBuilder.disconnectDb();
    }
}
