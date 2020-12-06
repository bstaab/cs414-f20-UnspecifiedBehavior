package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;

public class MatchRequest extends RequestData {
    private String from;
    private String to;
    private Boolean valid;
    private String message="INVITATION";
    private String err;

    public MatchRequest(String to, String from){
        this.to=to;
        this.from=from;
    }

    public boolean Do() throws RequestException {
        System.out.println(to);
        System.out.println(from);
        valid=false;
        if(from.equals(to)){
            err="Can't invite yourself";
            System.out.println("message");
            return valid;
        }
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getMessagesTable();
        if(!QueryBuilder.checkUsername(to)){
            err=("invalid user to");
            QueryBuilder.disconnectDb();
            return valid;
        }
        if(!QueryBuilder.checkUsername(from)){
            err=("invalid user from");
            QueryBuilder.disconnectDb();
            return valid;
        }
        try{QueryBuilder.addMessage(to, from, message);}
        catch(Exception e){
            err="DB error";
            QueryBuilder.disconnectDb();
            return valid;
        }
        try{String s = QueryBuilder.getMessage(to, from);
            if(s.equals(message)){
                System.out.println(s);
                valid=true;
            }
            else{
                err="failed to add message";
                valid=false;
            }
        }
        catch(Exception e){
            err="DB error 2";
            QueryBuilder.disconnectDb();
            return valid;
        }
        QueryBuilder.disconnectDb();
        return valid;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        this.Do();
    }
}
