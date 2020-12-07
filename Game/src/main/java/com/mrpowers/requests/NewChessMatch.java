package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;
import com.mrpowers.chess.*;
import java.util.Random;

public class NewChessMatch extends RequestData{
    private String user1;
    private String user2;
    private String fen;
    private String whiteUser;
    private String blackUser;
    private Boolean valid;

    public NewChessMatch(String user1, String user2){
        this.user1=user1;
        this.user2=user2;
    }

    public String getFen(){
        return fen;
    }

    public String getWhiteUser(){return this.whiteUser;}
    public String getBlackUser(){return this.blackUser;}

    public boolean Do() throws RequestException{
        valid=false;
        ChessBoard board=new ChessBoard();
        Random rand=new Random();
        int r=rand.nextInt();
        board.initialize();
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getStateTable();
        QueryBuilder.getMessagesTable();
        System.out.println(user1+" "+user2);
        fen=board.toFen();
        fen+="w";
        if(r%2==0){
            if(!QueryBuilder.addGame(user1, user2, fen)){
                QueryBuilder.disconnectDb();
                return valid;
            }
            whiteUser=user1;
            blackUser=user2;
            QueryBuilder.removeMessage(user1, user2, "INVITATION");
            QueryBuilder.addMessage(user2, user1, "ACCEPTED");
            valid=true;
        }
        else{
            if(!QueryBuilder.addGame(user2, user1, fen)){
                QueryBuilder.disconnectDb();
                return valid;
            }
            whiteUser=user2;
            blackUser=user1;
            QueryBuilder.removeMessage(user1, user2, "INVITATION");
            QueryBuilder.addMessage(user2, user1, "ACCEPTED");
            valid=true;
        }
        QueryBuilder.disconnectDb();
        return valid;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        this.Do();
    }
}