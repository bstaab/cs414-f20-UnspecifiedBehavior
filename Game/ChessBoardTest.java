package a2;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ChessBoardTest{
  @Test
   public void testChessBoard(){
	  ChessBoard testBoard = new ChessBoard();
	  try {
		  assertEquals(null, testBoard.getPiece("a1"));
		  assertEquals(null, testBoard.getPiece("a8"));
		  assertEquals(null, testBoard.getPiece("h1"));
		  assertEquals(null, testBoard.getPiece("h8"));
		  
		  testBoard.initialize();
		  
		  // King
		  assertEquals("\u2654", testBoard.getPiece("d1").toString());
		  assertEquals("\u265A", testBoard.getPiece("d8").toString());
		  
		  // Queen
		  assertEquals("\u265B", testBoard.getPiece("e8").toString());
		  assertEquals("\u2655", testBoard.getPiece("e1").toString());
		  
		  // Bishop
		  assertEquals("\u265D", testBoard.getPiece("f8").toString());
		  assertEquals("\u265D", testBoard.getPiece("c8").toString());
		  assertEquals("\u2657", testBoard.getPiece("f1").toString());
		  assertEquals("\u2657", testBoard.getPiece("c1").toString());
		  
		  // Rook
		  assertEquals("\u265C", testBoard.getPiece("a8").toString());
		  assertEquals("\u2656", testBoard.getPiece("h1").toString());
		  assertEquals("\u265C", testBoard.getPiece("h8").toString());
		  assertEquals("\u2656", testBoard.getPiece("a1").toString());
		  
		  // Knight
		  assertEquals("\u265E", testBoard.getPiece("b8").toString());
		  assertEquals("\u265E", testBoard.getPiece("g8").toString());
		  assertEquals("\u2658", testBoard.getPiece("b1").toString());
		  assertEquals("\u2658", testBoard.getPiece("g1").toString());

		  // Pawn and empty squares
		  for(char i = 'a'; i <= 'h'; ++i) {
			  assertEquals("\u265F", testBoard.getPiece(i + "7").toString());
			  assertEquals("\u2659", testBoard.getPiece(i + "2").toString());
			  			  
			  assertEquals(null, testBoard.getPiece(i + "3"));
			  assertEquals(null, testBoard.getPiece(i + "4"));
			  assertEquals(null, testBoard.getPiece(i + "5"));
			  assertEquals(null, testBoard.getPiece(i + "6"));
		  }

	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @Test
  public void testIllegalPositionException() {
	  ChessBoard testBoard = new ChessBoard();

	  assertThrows(IllegalPositionException.class, () -> testBoard.getPiece("5"));
	  assertThrows(IllegalPositionException.class, () -> testBoard.getPiece("77"));
	  assertThrows(IllegalPositionException.class, () -> testBoard.getPiece("z9"));
	  assertThrows(IllegalPositionException.class, () -> testBoard.getPiece("a"));
	  assertThrows(IllegalPositionException.class, () -> testBoard.getPiece("B1"));
	  assertThrows(IllegalPositionException.class, () -> testBoard.getPiece("HH"));
	  assertThrows(IllegalPositionException.class, () -> testBoard.getPiece("2c"));
	  assertThrows(IllegalPositionException.class, () -> testBoard.getPiece("D33"));
	  assertThrows(IllegalPositionException.class, () -> testBoard.getPiece("EE4"));
  }
  
  @Test
  public void testIllegalMoveException() {
	  ChessBoard testBoard = new ChessBoard();

	  assertThrows(IllegalMoveException.class, () -> testBoard.move("5", "b"));
	  assertThrows(IllegalMoveException.class, () -> testBoard.move("a1", "B1"));
	  assertThrows(IllegalMoveException.class, () -> testBoard.move("A1", "b1"));
	  assertThrows(IllegalMoveException.class, () -> testBoard.move("aa", "bb"));
	  assertThrows(IllegalMoveException.class, () -> testBoard.move("CC", "44"));
	  assertThrows(IllegalMoveException.class, () -> testBoard.move("55", "33"));
	  assertThrows(IllegalMoveException.class, () -> testBoard.move("77", "99"));
	  assertThrows(IllegalMoveException.class, () -> testBoard.move("j9", "z0"));
  }
  
  @Test
  public void testPlacePiece() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  ChessPiece whitePiece = testBoard.getPiece("a2");
		  ChessPiece blackPiece = testBoard.getPiece("h7");

		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.placePiece(whitePiece, "a3"));
		  assertEquals("a3", whitePiece.getPosition());
		  
		  // Place piece on square occupied by same color, verify old position
		  assertEquals(false, testBoard.placePiece(whitePiece, "a1"));
		  assertEquals("a3", whitePiece.getPosition());
		  
		  // Verify piece at destination square, place piece on square occupied 
		  // by different color, verify new position
		  assertEquals("\u265F", testBoard.getPiece("a7").toString());
		  assertEquals(true, testBoard.placePiece(whitePiece, "a7"));
		  assertEquals("a7", whitePiece.getPosition());
		  assertEquals("\u2659", testBoard.getPiece("a7").toString());
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.placePiece(blackPiece, "h6"));
		  assertEquals("h6", blackPiece.getPosition());
		  
		  // Place piece on square occupied by same color, verify old position
		  assertEquals(false, testBoard.placePiece(blackPiece, "g7"));
		  assertEquals("h6", blackPiece.getPosition());
		  
		  // Verify piece at destination square, place piece on square occupied 
		  // by different color, verify new position
		  assertEquals("\u2659", testBoard.getPiece("h2").toString());
		  assertEquals(true, testBoard.placePiece(blackPiece, "h2"));
		  assertEquals("h2", blackPiece.getPosition());
		  assertEquals("\u265F", testBoard.getPiece("h2").toString());
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
}

