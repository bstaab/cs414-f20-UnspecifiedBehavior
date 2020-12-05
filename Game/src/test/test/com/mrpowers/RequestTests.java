package com.mrpowers;
import com.mrpowers.exceptions.IllegalMoveException;
import com.mrpowers.exceptions.IllegalPositionException;
import org.junit.jupiter.api.Test;
import com.mrpowers.chess.*;
import com.mrpowers.requests.*;
import static org.junit.jupiter.api.Assertions.*;

public class RequestTests {
    @Test
    public void NewUserTest(){
        NewUser aUser=new NewUser("user", "password", "user@mail.com");
        try {
            aUser.buildResponse();
        }catch(RequestException e){
            System.out.println("error");
        }catch(IllegalMoveException e){
            System.out.println("error");
        }
    }
    @Test
    public void MoveTest() throws IllegalPositionException {
        Move aMove=new Move();
        String fen0 = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        String fen1 = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
        ChessBoard board0=aMove.makeBoard(fen0);
        ChessBoard board1=aMove.makeBoard(fen1);
        System.out.println(board0);
        if(board0.getPiece("c1")!=new Bishop(board0, ChessPiece.Color.WHITE)){
            fail();
        }
        if(board1.getPiece("e4")!=new Bishop(board1, ChessPiece.Color.WHITE)){
            fail();
        }
    }
}