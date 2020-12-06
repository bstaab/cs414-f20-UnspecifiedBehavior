package com.mrpowers.requests;

import com.mrpowers.exceptions.IllegalMoveException;
import com.mrpowers.QueryBuilder;
import java.util.ArrayList;
public class GetAllMessages extends RequestData{
    private boolean valid;
    private String[] messages;
    private String[] from;
    private String username;

    public GetAllMessages(String username){
        this.username=username;
    }

    public String[][] GetData(){
        return new String[][]{from, messages};
    }

    public boolean Do(){
        valid=false;
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getMessagesTable();
        try{
            from=QueryBuilder.getUsernamesFromMessages(username);
            messages=new String[from.length];
            for(int i=0;i<from.length;i++){
                messages[i]=QueryBuilder.getMessage(username, from[i]);
            }
            valid=true;
        }catch(Exception e){
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
