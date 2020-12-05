package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;
import com.mrpowers.requests.RequestData;
import com.mrpowers.requests.RequestException;

public class CheckMessages extends RequestData {
    public String username;
    public String[] messagesFrom;

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getMessagesTable();
        //getallmessages
        //messagesFrom=getAllMessages
        QueryBuilder.disconnectDb();
    }
}
