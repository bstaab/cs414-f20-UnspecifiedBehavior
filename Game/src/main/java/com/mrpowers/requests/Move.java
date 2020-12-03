package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
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
        ChessBoard board=new ChessBoard();
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getStateTable();
        QueryBuilder.disconnectDb();
        try {
            board.move(from, to);
            from="move worked";
        }catch(IllegalMoveException e){
            from="did not work";
            throw new RequestException();
        }
    }
}
