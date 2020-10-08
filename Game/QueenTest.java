package a2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QueenTest {

  @Test
  public void testLegalMoves() {
    ChessBoard testBoard = new ChessBoard();
    
    Queen blackQueen1 = new Queen(testBoard, ChessPiece.Color.BLACK);
    Queen whiteQueen1 = new Queen(testBoard, ChessPiece.Color.WHITE);
    Queen blackQueen2 = new Queen(testBoard, ChessPiece.Color.BLACK);
    Queen whiteQueen2 = new Queen(testBoard, ChessPiece.Color.WHITE);
    
    testBoard.placePiece(blackQueen1, "e8");
    testBoard.placePiece(whiteQueen1, "e1");
    testBoard.placePiece(blackQueen2, "a5");
    testBoard.placePiece(whiteQueen2, "b3");

    assertTrue(blackQueen1.legalMoves().isEmpty());
    assertTrue(blackQueen2.legalMoves().isEmpty());
    assertTrue(whiteQueen1.legalMoves().isEmpty());
    assertTrue(whiteQueen2.legalMoves().isEmpty());
  }
  
  @Test
  public void testIllegalPositionException() {
	  ChessBoard testBoard = new ChessBoard();
	  Queen whiteQueen = new Queen(testBoard, ChessPiece.Color.WHITE);
  	  Queen blackQueen = new Queen(testBoard, ChessPiece.Color.BLACK);
  	  
  	  assertThrows(IllegalPositionException.class, () -> whiteQueen.setPosition("G5"));
  	  assertThrows(IllegalPositionException.class, () -> whiteQueen.setPosition("5"));
	  assertThrows(IllegalPositionException.class, () -> whiteQueen.setPosition("77"));
	  assertThrows(IllegalPositionException.class, () -> whiteQueen.setPosition("z9"));
	  assertThrows(IllegalPositionException.class, () -> whiteQueen.setPosition("a"));
	  assertThrows(IllegalPositionException.class, () -> blackQueen.setPosition("B1"));
	  assertThrows(IllegalPositionException.class, () -> blackQueen.setPosition("HH"));
	  assertThrows(IllegalPositionException.class, () -> blackQueen.setPosition("2c"));
	  assertThrows(IllegalPositionException.class, () -> blackQueen.setPosition("D33"));
	  assertThrows(IllegalPositionException.class, () -> blackQueen.setPosition("EE4"));
  }
  
  @Test
  public void testGetColorMethod() {
	  ChessBoard testBoard = new ChessBoard();
	  Queen whiteQueen = new Queen(testBoard, ChessPiece.Color.WHITE);
	  Queen blackQueen = new Queen(testBoard, ChessPiece.Color.BLACK);
  	  
  	  assertEquals(ChessPiece.Color.WHITE, whiteQueen.getColor());
  	  assertEquals(ChessPiece.Color.BLACK, blackQueen.getColor());
  }
  
  @Test
  public void testGetPosition() {
	  ChessBoard testBoard = new ChessBoard();
	  Queen whiteQueen = new Queen(testBoard, ChessPiece.Color.WHITE);
	  Queen blackQueen = new Queen(testBoard, ChessPiece.Color.BLACK);
  	  
  	  testBoard.placePiece(whiteQueen, "h3");
  	  testBoard.placePiece(blackQueen, "g1");
  	  
  	  assertEquals("h3", whiteQueen.getPosition());
  	  assertEquals("g1", blackQueen.getPosition());
  }
  
  @Test
  public void testToString() {
	  ChessBoard testBoard = new ChessBoard();
	  Queen whiteQueen = new Queen(testBoard, ChessPiece.Color.WHITE);
	  Queen blackQueen = new Queen(testBoard, ChessPiece.Color.BLACK);
  	  
  	  assertEquals("\u2655", whiteQueen.toString());
  	  assertEquals("\u265B", blackQueen.toString());
  }
  
}
