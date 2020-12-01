package com.mrpowers.requests;

import com.mrpowers.exceptions.IllegalMoveException;
import com.mrpowers.chess.*;

public class NewChessMatch extends RequestData{
    private String user1;
    private String user2;

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        ChessBoard board=new ChessBoard();

    }
}
