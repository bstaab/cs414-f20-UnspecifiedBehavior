package com.mrpowers.requests;

import com.mrpowers.exceptions.IllegalMoveException;
import com.mrpowers.QueryBuilder;

public class NewUser extends RequestData {
    private String user;
    private String password;
    private String email;
    private Boolean valid;

    public NewUser(String user, String password, String email){
        this.email=email;
        this.password=password;
        this.user=user;
    }
    public String getUser(){
        return user;
    }
    public String getPassword(){
        return password;
    }
    public String getEmail(){
        return email;
    }
    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.createUser(user, email, password);
        if(QueryBuilder.checkUsername(user)){
            valid=true;
        }
        else{valid=false;}
        QueryBuilder.disconnectDb();
    }
}
