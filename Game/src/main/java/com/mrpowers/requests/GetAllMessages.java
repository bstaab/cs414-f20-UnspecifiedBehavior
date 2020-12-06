package com.mrpowers.requests;

import com.mrpowers.exceptions.IllegalMoveException;
import com.mrpowers.QueryBuilder;
import java.util.ArrayList;
public class GetAllMessages extends RequestData{
    private boolean valid;
    private String[] messages;
    private String[] from;
    private String username;
    private String err;

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
        }catch(Exception e){
            QueryBuilder.disconnectDb();
            err="0";
            return valid;
        }
        messages=new String[from.length];
        for(int i=0;i<from.length;i++){
            try{messages[i]=QueryBuilder.getMessage(username, from[i]);}
            catch(Exception e){
                QueryBuilder.disconnectDb();
                err=Integer.toString(i);
                return valid;
            }
        }
        valid=true;
        QueryBuilder.disconnectDb();
        return valid;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        this.Do();
        System.out.println("err");
    }
}
