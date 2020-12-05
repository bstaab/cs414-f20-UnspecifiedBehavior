package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;

public class MatchRequest extends RequestData {
    private String from;
    private String to;
    private Boolean valid;
    private final String message="INVITATION";

    public MatchRequest(String to, String from){
        this.to=to;
        this.from=from;
    }

    public boolean Do(){
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getMessagesTable();
        QueryBuilder.addMessage(to, from, message);
        String s = QueryBuilder.getMessage(to, from);
        if(s.equals(message)){
            valid=true;
        }
        else{
            valid=false;
        }
        QueryBuilder.disconnectDb();
        return valid;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        this.Do();
    }
}
