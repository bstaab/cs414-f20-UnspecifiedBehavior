package com.mrpowers;
import com.mrpowers.exceptions.IllegalMoveException;
import com.mrpowers.exceptions.IllegalPositionException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.mrpowers.chess.*;
import com.mrpowers.requests.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RequestTests {

/*

    @BeforeAll

    public void Before() {
        Boolean CI = System.getenv("CI").equals("true");
        if (CI) {
            System.out.println(CI);
        }

    }
*/
/*
    @Test
    public void NewUserTest(){
        NewUser aUser=new NewUser("user5", "password", "user@mail.com");
        try {
            aUser.buildResponse();
        }catch(RequestException | IllegalMoveException e){
            System.out.println("error");
        }
        UserData d=new UserData("user5");
        try{
            d.buildResponse();
            if(d.getGamesLost()!=0){
                fail();
            }
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        } catch (RequestException e) {
            e.printStackTrace();
        }
        Unregister r=new Unregister("user5", d.getPassword());
        r.Delete();
    }
    @Test
    public void MoveTest() throws IllegalPositionException {
        Move aMove=new Move();
        String fen0 = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        String fen1 = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
        ChessBoard board0=aMove.makeBoard(fen0);
        ChessBoard board1=aMove.makeBoard(fen1);
        if(board0.getPiece("c2").legalMoves().size()!=4){
            fail();
        }
        if(board1.getPiece("e4").legalMoves().size()!=3){
            fail();
        }
    }
    @Test
    public void MessagesTest() throws RequestException {
        NewUser aUser=new NewUser("to", "password", "to@mail.com");
        try {
            aUser.buildResponse();
        }catch(RequestException | IllegalMoveException e){
            System.out.println("error");
        }
        aUser=new NewUser("from", "password", "from@mail.com");
        try {
            aUser.buildResponse();
        }catch(RequestException | IllegalMoveException e){
            System.out.println("error");
        }
        try{
            DeleteMessage del=new DeleteMessage("to");
            assertTrue(del.DeleteAll());
        }catch (Exception e){
            fail();
        }
        DeleteMessage d=new DeleteMessage("to");
        d.DeleteAll();
        MatchRequest invite=new MatchRequest("to", "from");
        System.out.println(invite.getErr());
        invite.Do();
        CheckMessages cm=new CheckMessages("to");
        Boolean check=cm.Check();
        if(!check){fail();}
        GetMessage m = new GetMessage("from", "to");
        try{
            String message=m.Get();
            if(!message.equals("INVITATION")){
                fail();
            }
            else{System.out.println("test");}
        }
        catch(RequestException e){
            fail();
        }
    }
    @Test
    public void MessageTest2() throws IllegalMoveException, RequestException {
        NewUser aUser=new NewUser("to2", "password", "to@mail.com");
        NewUser bUser=new NewUser("from0", "password", "to@mail.com");
        NewUser cUser=new NewUser("from1", "password", "to@mail.com");
        try {
            aUser.buildResponse();
            bUser.buildResponse();
            cUser.buildResponse();
        }catch(RequestException | IllegalMoveException e){
            System.out.println("error");
        }
        DeleteMessage d=new DeleteMessage("to2");
        d.DeleteAll();
        MatchRequest m=new MatchRequest("to2", "from0");
        m.buildResponse();
        m=new MatchRequest("to2", "from1");
        m.buildResponse();
        GetAllMessages gal=new GetAllMessages("to2");
        gal.buildResponse();
        String[][] mur=gal.GetData();
        assertTrue(mur[1][0].equals("INVITATION"));
        assertTrue(mur[0][0].equals("from1")||mur[0][1].equals("from1"));
    }
    @Test
    public void NewMatchTest() throws IllegalMoveException, RequestException {
        NewUser aUser=new NewUser("to2", "password", "to@mail.com");
        NewUser bUser=new NewUser("from0", "password", "to@mail.com");
        try {
            aUser.buildResponse();
            bUser.buildResponse();
        }catch(RequestException | IllegalMoveException e){
            System.out.println("error");
        }
        Concede c = new Concede("to2");
        assertTrue(c.RemoveAllGames());
        NewChessMatch ncm=new NewChessMatch("to2", "from0");
        try{
            assertTrue(ncm.TestDo());
            assertTrue(ncm.getFen().equals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - - 0w"));
        }catch(Exception e){
            fail();
        }
        c=new Concede("to2");
        assertTrue(c.RemoveAllGames());
    }
    @Test
    public void dbtest(){
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        assertTrue(QueryBuilder.createUser("user9", "user9@mail", "password"));
        assertTrue(QueryBuilder.createUser("user8", "user9@mail", "password"));
        assertTrue(QueryBuilder.addGame("user9", "user8", "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - - 0"));
        System.out.println(QueryBuilder.getState("user9", "user8"));
        assertTrue(QueryBuilder.getState("user9", "user8").equals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - - 0"));
        QueryBuilder.removeGame("user9", "user8");
        assertFalse(QueryBuilder.getState("user9", "user8").equals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - - 0"));
        assertTrue(QueryBuilder.deleteUser("user9"));
        assertTrue(QueryBuilder.deleteUser("user8"));
        QueryBuilder.disconnectDb();
    }

    @Test
    public void dbTest2() throws IllegalMoveException, RequestException {
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getStateTable();
        ChessBoard board=new ChessBoard();
        board.initialize();
        QueryBuilder.addGame("h1", "h2", board.toFen());
        String f=QueryBuilder.getTurn("h1", "h2");
        System.out.println(f.length());
        QueryBuilder.disconnectDb();
    }

    @Test
    public void moveTest() throws IllegalPositionException, IllegalMoveException {
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getStateTable();
        QueryBuilder.removeGame("paul", "art");
        QueryBuilder.addGame("paul", "art", "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - - 0w");
        QueryBuilder.disconnectDb();
        Move a=new Move("paul", "art", "c2", "c4", "paul");
        a.Do();
        System.out.println(a.getFen());
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getStateTable();
        QueryBuilder.removeGame("paul", "art");
        QueryBuilder.disconnectDb();
    }*/

}