package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;

public class Unregister extends RequestData{
    private String username;
    private String password;
    private Boolean valid;

    public Unregister(String username){
        this.username=username;
    }
    public Unregister(String username, String password){
        this.username=username;
        this.password=password;
    }
    public Boolean Delete(){
        valid=false;
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        if(QueryBuilder.checkUsername(username)){
            String[] results=QueryBuilder.searchUser(username);

            if(password.equals(results[2])){
                valid=true;
                valid=QueryBuilder.deleteUser(username);
            }
        }
        QueryBuilder.disconnectDb();
        return valid;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        this.Delete();
    }
}
