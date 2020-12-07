package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;
import com.mrpowers.exceptions.IllegalPositionException;

public class Reject extends RequestData{
    public String to;
    public String from;
    public Boolean valid;

    public void Do(){
        QueryBuilder.connectDb();
        QueryBuilder.connectDb();
        QueryBuilder.getMessagesTable();
        QueryBuilder.removeMessage(to, from, "INVITATION");
        QueryBuilder.disconnectDb();
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException, IllegalPositionException {
        this.Do();
    }
}
