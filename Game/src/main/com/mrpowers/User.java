package com.mrpowers;

import com.mrpowers.ChessMatch;
import com.mrpowers.ChessPiece;
import com.mrpowers.IllegalMoveException;
import com.mrpowers.Move;

public class User {
    ChessMatch myGame;
    String name;
    String password;

    private ChessPiece.Color color;

    public User(ChessMatch myGame){
        this.myGame=myGame;
    }

    public void setColor(ChessPiece.Color color){
        this.color=color;
    }

    public ChessPiece.Color getColor(){
        return this.color;
    }

    //probably have to parse a json file or something to get the move from the client
    public boolean Move(Move myMove){
        try{
            myGame.board.move(myMove.getFrom(), myMove.getTo());
        }catch(IllegalMoveException e){
            e.printStackTrace();
            return false;
        }
        myGame.addMove(myMove);
        myGame.nextTurn();
        return true;
    }
}
