package com.mrpowers.requests;
import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;

public class GetMessage extends RequestData{
    String userFrom;
    String userTo;
    String message;
    Boolean valid;

    public GetMessage(String userFrom, String userTo){
        this.userFrom=userFrom;
        this.userTo=userTo;
    }

    public String Get() throws RequestException {
        valid=false;
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getMessagesTable();
        int s=QueryBuilder.getListSize(userTo);
        System.out.println(s);
        if(s>0) {
            String users[]=QueryBuilder.getUsernamesFromMessages(userTo);
            for(int i=0;i<s;i++){
                if(users[i].equals(userFrom)){
                    message = QueryBuilder.getMessage(userTo, userFrom);
                    valid=true;
                }
            }
        }
        QueryBuilder.disconnectDb();
        if(valid){
            return message;
        }
        throw new RequestException();
    }


    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        this.Get();
    }
}
