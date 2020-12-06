package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;

public class DeleteMessage extends RequestData{
    private String from;
    private String to;
    private String message;
    private Boolean valid;

    public DeleteMessage(String to){
        this.to=to;
    }

    public Boolean DeleteAll() throws RequestException{
        valid=false;
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getMessagesTable();
        try {
            String[] u = QueryBuilder.getUsernamesFromMessages(to);
            for (int i=0;i<u.length;i++){
                QueryBuilder.removeMessage(to, u[i], QueryBuilder.getMessage(to, u[i]));
            }
            valid=true;
        }catch (Exception e){
            throw new RequestException();
        }
        QueryBuilder.disconnectDb();
        return valid;
    }

    public Boolean Do(){
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getMessagesTable();
        try{
            QueryBuilder.removeMessage(to, from, message);
            valid=true;
        }catch(Exception e){
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
