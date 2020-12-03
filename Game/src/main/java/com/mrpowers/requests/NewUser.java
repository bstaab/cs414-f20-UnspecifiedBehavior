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
        System.out.println("0");
        QueryBuilder.connectDb();
        System.out.println("1");
        QueryBuilder.getDBTable();
        System.out.println("2");
        QueryBuilder.createUser(user, email, password);
        System.out.println("3");
        QueryBuilder.disconnectDb();
        System.out.println("4");
    }
}
