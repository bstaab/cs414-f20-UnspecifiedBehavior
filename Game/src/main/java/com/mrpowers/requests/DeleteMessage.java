package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;

public class DeleteMessage extends RequestData{
    private String from;
    private String to;
    private String message;
    private Boolean valid;

    public Boolean Do(){
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getMessagesTable();
        try{

        }catch(Exception e){

        }
        QueryBuilder.disconnectDb();
        return valid;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        this.Do();
    }
}
