package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;

public class Unregister extends RequestData{
    private String username;
    private Boolean valid;

    public Unregister(String username){
        this.username=username;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        if(QueryBuilder.checkUsername(username)){
            QueryBuilder.deleteUser(username);
        }
        if(QueryBuilder.checkUsername((username))){
            valid=false;
        }
        else{
            valid=true;
        }
        QueryBuilder.disconnectDb();
    }
}
