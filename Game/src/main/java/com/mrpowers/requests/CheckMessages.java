package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;
import com.mrpowers.requests.RequestData;
import com.mrpowers.requests.RequestException;

public class CheckMessages extends RequestData {
    public String username;
    public Boolean hasMessages;
    public String[] messagesFrom;

    public CheckMessages(String username){
        this.username=username;
    }

    public boolean Check(){
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getMessagesTable();
        int i=QueryBuilder.getListSize(username);
        if(i!=0){
            messagesFrom=QueryBuilder.getUsernamesFromMessages(username);
            hasMessages=true;
        }
        else {
            hasMessages=false;
        }
        QueryBuilder.disconnectDb();
        return hasMessages;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        this.Check();
    }
}
