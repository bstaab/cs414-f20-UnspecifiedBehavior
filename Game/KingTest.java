package a2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KingTest {

  @Test
  public void testLegalMoves(){
    ChessBoard testBoard = new ChessBoard();
    King whiteKing = new King(testBoard, ChessPiece.Color.WHITE);
    Pawn whitePawn = new Pawn(testBoard, ChessPiece.Color.WHITE);
    King blackKing = new King(testBoard, ChessPiece.Color.BLACK);
    Pawn blackPawn = new Pawn(testBoard, ChessPiece.Color.BLACK);

    testBoard.placePiece(whiteKing, "d2");
    testBoard.placePiece(whitePawn, "d1");
    testBoard.placePiece(blackKing, "e2");
    testBoard.placePiece(blackPawn, "e3");
        
    ArrayList<String> legalMovesWhite = whiteKing.legalMoves();
    assertEquals(7, legalMovesWhite.size());
    assertTrue(legalMovesWhite.contains("c1"));
    assertTrue(legalMovesWhite.contains("c2"));
    assertTrue(legalMovesWhite.contains("c3"));
    assertTrue(legalMovesWhite.contains("d3"));
    assertTrue(legalMovesWhite.contains("e3"));
    assertTrue(legalMovesWhite.contains("e2"));
    assertTrue(legalMovesWhite.contains("e1"));

    ArrayList<String> legalMovesBlack = blackKing.legalMoves();
    assertEquals(7, legalMovesBlack.size());
    assertTrue(legalMovesBlack.contains("d1"));
    assertTrue(legalMovesBlack.contains("d2"));
    assertTrue(legalMovesBlack.contains("d3"));
    assertTrue(legalMovesBlack.contains("f3"));
    assertTrue(legalMovesBlack.contains("f2"));
    assertTrue(legalMovesBlack.contains("f1"));
    assertTrue(legalMovesBlack.contains("e1"));
  }
  
  @Test
  public void testIllegalPositionException() {
	  ChessBoard testBoard = new ChessBoard();
	  King whiteKing = new King(testBoard, ChessPiece.Color.WHITE);
  	  King blackKing = new King(testBoard, ChessPiece.Color.BLACK);
  	  
  	  assertThrows(IllegalPositionException.class, () -> whiteKing.setPosition("G5"));
  	  assertThrows(IllegalPositionException.class, () -> whiteKing.setPosition("5"));
	  assertThrows(IllegalPositionException.class, () -> whiteKing.setPosition("77"));
	  assertThrows(IllegalPositionException.class, () -> whiteKing.setPosition("z9"));
	  assertThrows(IllegalPositionException.class, () -> whiteKing.setPosition("a"));
	  assertThrows(IllegalPositionException.class, () -> blackKing.setPosition("B1"));
	  assertThrows(IllegalPositionException.class, () -> blackKing.setPosition("HH"));
	  assertThrows(IllegalPositionException.class, () -> blackKing.setPosition("2c"));
	  assertThrows(IllegalPositionException.class, () -> blackKing.setPosition("D33"));
	  assertThrows(IllegalPositionException.class, () -> blackKing.setPosition("EE4"));
  }
  
  @Test
  public void testGetColorMethod() {
	  ChessBoard testBoard = new ChessBoard();
	  King whiteKing = new King(testBoard, ChessPiece.Color.WHITE);
	  King blackKing = new King(testBoard, ChessPiece.Color.BLACK);
  	  
  	  assertEquals(ChessPiece.Color.WHITE, whiteKing.getColor());
  	  assertEquals(ChessPiece.Color.BLACK, blackKing.getColor());
  }
  
  @Test
  public void testGetPosition() {
	  ChessBoard testBoard = new ChessBoard();
	  King whiteKing = new King(testBoard, ChessPiece.Color.WHITE);
	  King blackKing = new King(testBoard, ChessPiece.Color.BLACK);
  	  
  	  testBoard.placePiece(whiteKing, "a2");
  	  testBoard.placePiece(blackKing, "h7");
  	  
  	  assertEquals("a2", whiteKing.getPosition());
  	  assertEquals("h7", blackKing.getPosition());
  }
  
  @Test
  public void testToString() {
	  ChessBoard testBoard = new ChessBoard();
	  King whiteKing = new King(testBoard, ChessPiece.Color.WHITE);
	  King blackKing = new King(testBoard, ChessPiece.Color.BLACK);
  	  
  	  assertEquals("\u2654", whiteKing.toString());
  	  assertEquals("\u265A", blackKing.toString());
  }
  
}