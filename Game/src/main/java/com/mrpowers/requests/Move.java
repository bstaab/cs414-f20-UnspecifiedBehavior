package com.mrpowers.requests;

import com.mrpowers.chess.ChessBoard;
import com.mrpowers.exceptions.IllegalMoveException;

public class Move extends RequestData {
    private String from;
    private String to;
    private int match;

    public Move(String from, String to, int match){
        this.from=from;
        this.to=to;
        this.match=match;
    }
    public String getFrom(){
        return from;
    }
    public String getTo(){
        return to;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        System.out.println("test");
        ChessBoard board=new ChessBoard();
        board.initialize();
        try {
            board.move(from, to);
            System.out.println("success");
        }catch(IllegalMoveException e){
            System.out.println("fail");
        }
    }
}