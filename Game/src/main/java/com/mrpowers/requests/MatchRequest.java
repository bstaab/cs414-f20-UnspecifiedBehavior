package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;

public class MatchRequest extends RequestData {
    private String userRequesting;
    private String userRequested;
    private Boolean valid;
    private final String message="Would you like to play a game of chess?";

    public MatchRequest(String userRequested, String userRequesting){
        this.userRequested=userRequested;
        this.userRequesting=userRequesting;
    }


    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getMessagesTable();
        QueryBuilder.addMessage(userRequested,userRequesting,message);
        String s = QueryBuilder.getMessage(userRequested,userRequesting);
        if(s==message){
            valid=true;
        }
        else{
            valid=false;
        }
        QueryBuilder.disconnectDb();
    }
}
