package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;
import com.mrpowers.chess.*;
import java.util.Random;

public class NewChessMatch extends RequestData{
    private String user1;
    private String user2;
    private String toUser;
    private Boolean accepted;

    public NewChessMatch(String user1, String user2){
        this.user1=user1;
        this.user2=user2;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        ChessBoard board=new ChessBoard();
        Random rand=new Random();
        int r=rand.nextInt();
        board.initialize();
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getStateTable();
        String fen=board.toFen();
        if(r%2==0){
            QueryBuilder.addGame(user1, user2, fen);
        }
        else{
            QueryBuilder.addGame(user2, user1, fen);
        }
        QueryBuilder.disconnectDb();
    }
}