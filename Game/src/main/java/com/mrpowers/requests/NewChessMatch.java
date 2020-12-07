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
    private String err;
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

    public boolean DeleteInvitation(){
        Boolean yes=false;
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getMessagesTable();
        if(QueryBuilder.doesMessageExist(user1, user2, "INVITATION")){
            QueryBuilder.removeMessage(user1, user2, "INVITATION");
            QueryBuilder.addMessage(user1, user2, "ACCEPTED");
            yes=true;
        }
        else if(QueryBuilder.doesMessageExist(user2, user1, "INVITATION")){
            QueryBuilder.removeMessage(user2, user1, "INVITATION");
            QueryBuilder.addMessage(user2, user1, "ACCEPTED");
            yes=true;
        }
        QueryBuilder.disconnectDb();
        return yes;
    }

    public boolean Do(){
        valid=false;
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getStateTable();
        if(!(QueryBuilder.doesItExist(user1, user2)||QueryBuilder.doesItExist(user2, user1))){
            ChessBoard board=new ChessBoard();
            board.initialize();
            fen=board.toFen()+"w";
            Random rand=new Random();
            if(rand.nextInt()%2==0){
                valid=QueryBuilder.addGame(user1, user2, fen);
                whiteUser=user1;
                blackUser=user2;
            }
            else{
                valid=QueryBuilder.addGame(user2, user1, fen);
                whiteUser=user2;
                blackUser=user1;
            }
        }
        QueryBuilder.disconnectDb();
        err="game already exists";
        return valid;
    }

    public Boolean TestDo(){
        return Do();
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        this.Do();
        this.DeleteInvitation();
    }
}