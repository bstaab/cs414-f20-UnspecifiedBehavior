package com.mrpowers.requests;

import com.mrpowers.exceptions.IllegalMoveException;
import com.mrpowers.chess.*;

public class NewChessMatch extends RequestData{
    private String user1;
    private String user2;

    public NewChessMatch(String user1, String user2){
        this.user1=user1;
        this.user2=user2;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        ChessBoard board=new ChessBoard();

    }
}
