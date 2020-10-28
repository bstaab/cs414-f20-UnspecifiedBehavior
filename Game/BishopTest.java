package Game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class BishopTest {

  @Test
  public void testLegalMoves1() {
    ChessBoard testBoard = new ChessBoard();
    Bishop whiteBishop = new Bishop(testBoard, ChessPiece.Color.WHITE);
    Bishop blackBishop = new Bishop(testBoard, ChessPiece.Color.BLACK);
       
    testBoard.placePiece(whiteBishop, "d4");
    testBoard.placePiece(blackBishop, "d5");
    
    ArrayList<String> legalMovesWhite = whiteBishop.legalMoves();
    assertEquals(13, legalMovesWhite.size());
    assertTrue(legalMovesWhite.contains("a1"));
    assertTrue(legalMovesWhite.contains("b2"));
    assertTrue(legalMovesWhite.contains("c3"));
    assertTrue(legalMovesWhite.contains("e5"));
    assertTrue(legalMovesWhite.contains("f6"));
    assertTrue(legalMovesWhite.contains("g7"));
    assertTrue(legalMovesWhite.contains("h8"));
    assertTrue(legalMovesWhite.contains("g1"));
    assertTrue(legalMovesWhite.contains("f2"));
    assertTrue(legalMovesWhite.contains("e3"));
    assertTrue(legalMovesWhite.contains("c5"));
    assertTrue(legalMovesWhite.contains("b6"));
    assertTrue(legalMovesWhite.contains("a7"));

    ArrayList<String> legalMovesBlack = blackBishop.legalMoves();
    assertEquals(13, legalMovesBlack.size());
    assertTrue(legalMovesBlack.contains("a2"));
    assertTrue(legalMovesBlack.contains("b3"));
    assertTrue(legalMovesBlack.contains("c4"));
    assertTrue(legalMovesBlack.contains("e6"));
    assertTrue(legalMovesBlack.contains("f7"));
    assertTrue(legalMovesBlack.contains("g8"));
    assertTrue(legalMovesBlack.contains("a8"));
    assertTrue(legalMovesBlack.contains("b7"));
    assertTrue(legalMovesBlack.contains("c6"));
    assertTrue(legalMovesBlack.contains("e4"));
    assertTrue(legalMovesBlack.contains("f3"));
    assertTrue(legalMovesBlack.contains("g2"));
  } 	
	
  /*
  @Test
  public void testLegalMoves2() {
    ChessBoard testBoard = new ChessBoard();
    Bishop whiteBishop = new Bishop(testBoard, ChessPiece.Color.WHITE);
    Pawn whitePawn = new Pawn(testBoard, ChessPiece.Color.WHITE);
    Bishop blackBishop = new Bishop(testBoard, ChessPiece.Color.BLACK);
    Pawn blackPawn = new Pawn(testBoard, ChessPiece.Color.BLACK);
       
    testBoard.placePiece(whiteBishop, "d4");
    testBoard.placePiece(whitePawn,   "b6");
    testBoard.placePiece(blackBishop, "f6");
    testBoard.placePiece(blackPawn,   "e7");

    ArrayList<String> legalMovesWhite = whiteBishop.legalMoves();
    assertEquals(9, legalMovesWhite.size());
    assertTrue(legalMovesWhite.contains("a1"));
    assertTrue(legalMovesWhite.contains("b2"));
    assertTrue(legalMovesWhite.contains("c3"));
    assertTrue(legalMovesWhite.contains("c5"));
    assertTrue(legalMovesWhite.contains("e3"));
    assertTrue(legalMovesWhite.contains("f2"));
    assertTrue(legalMovesWhite.contains("g1"));
    assertTrue(legalMovesWhite.contains("e5"));
    assertTrue(legalMovesWhite.contains("f6"));

    ArrayList<String> legalMovesBlack = blackBishop.legalMoves();
    assertEquals(6, legalMovesBlack.size());
    assertTrue(legalMovesBlack.contains("d4"));
    assertTrue(legalMovesBlack.contains("e5"));
    assertTrue(legalMovesBlack.contains("g5"));
    assertTrue(legalMovesBlack.contains("g7"));
    assertTrue(legalMovesBlack.contains("h4"));
    assertTrue(legalMovesBlack.contains("h8"));
  } 
  */
  
  @Test
  public void testLegalMovesPortal1() {
    ChessBoard testBoard = new ChessBoard();
    Bishop whiteBishop = new Bishop(testBoard, ChessPiece.Color.WHITE);
       
    testBoard.placePiece(whiteBishop, "d4");
    testBoard.setPortalLocation(ChessPiece.Color.BLACK, "g8");
    testBoard.setPortalLocation(ChessPiece.Color.WHITE, "a1");
    
    ArrayList<String> legalMovesWhite = whiteBishop.legalMoves();
    assertEquals(19, legalMovesWhite.size());
    assertTrue(legalMovesWhite.contains("c3"));
    assertTrue(legalMovesWhite.contains("b2"));
    assertTrue(legalMovesWhite.contains("g8"));
    assertTrue(legalMovesWhite.contains("f7"));
    assertTrue(legalMovesWhite.contains("e6"));
    assertTrue(legalMovesWhite.contains("d5"));
    assertTrue(legalMovesWhite.contains("c4"));
    assertTrue(legalMovesWhite.contains("b3"));
    assertTrue(legalMovesWhite.contains("a2"));
    assertTrue(legalMovesWhite.contains("e3"));
    assertTrue(legalMovesWhite.contains("f2"));
    assertTrue(legalMovesWhite.contains("g1"));
    assertTrue(legalMovesWhite.contains("c5"));
    assertTrue(legalMovesWhite.contains("b6"));
    assertTrue(legalMovesWhite.contains("a7"));
    assertTrue(legalMovesWhite.contains("e5"));
    assertTrue(legalMovesWhite.contains("f6"));
    assertTrue(legalMovesWhite.contains("g7"));
    assertTrue(legalMovesWhite.contains("h8"));
    
  }
  
  @Test
  public void testIllegalPositionException() {
	  ChessBoard testBoard = new ChessBoard();
	  Bishop whiteBishop = new Bishop(testBoard, ChessPiece.Color.WHITE);
  	  Bishop blackBishop = new Bishop(testBoard, ChessPiece.Color.BLACK);
  	  
  	  assertThrows(IllegalPositionException.class, () -> whiteBishop.setPosition("G5"));
  	  assertThrows(IllegalPositionException.class, () -> whiteBishop.setPosition("5"));
	  assertThrows(IllegalPositionException.class, () -> whiteBishop.setPosition("77"));
	  assertThrows(IllegalPositionException.class, () -> whiteBishop.setPosition("z9"));
	  assertThrows(IllegalPositionException.class, () -> whiteBishop.setPosition("a"));
	  assertThrows(IllegalPositionException.class, () -> blackBishop.setPosition("B1"));
	  assertThrows(IllegalPositionException.class, () -> blackBishop.setPosition("HH"));
	  assertThrows(IllegalPositionException.class, () -> blackBishop.setPosition("2c"));
	  assertThrows(IllegalPositionException.class, () -> blackBishop.setPosition("D33"));
	  assertThrows(IllegalPositionException.class, () -> blackBishop.setPosition("EE4"));
  }
  
  @Test
  public void testGetColorMethod() {
	  ChessBoard testBoard = new ChessBoard();
	  Bishop whiteBishop = new Bishop(testBoard, ChessPiece.Color.WHITE);
  	  Bishop blackBishop = new Bishop(testBoard, ChessPiece.Color.BLACK);
  	  
  	  assertEquals(ChessPiece.Color.WHITE, whiteBishop.getColor());
  	  assertEquals(ChessPiece.Color.BLACK, blackBishop.getColor());
  }
  
  @Test
  public void testGetPosition() {
	  ChessBoard testBoard = new ChessBoard();
	  Bishop whiteBishop = new Bishop(testBoard, ChessPiece.Color.WHITE);
  	  Bishop blackBishop = new Bishop(testBoard, ChessPiece.Color.BLACK);
  	  
  	  testBoard.placePiece(whiteBishop, "d4");
  	  testBoard.placePiece(blackBishop, "f6");
  	  
  	  assertEquals("d4", whiteBishop.getPosition());
  	  assertEquals("f6", blackBishop.getPosition());
  }
  
  @Test
  public void testToString() {
	  ChessBoard testBoard = new ChessBoard();
	  Bishop whiteBishop = new Bishop(testBoard, ChessPiece.Color.WHITE);
  	  Bishop blackBishop = new Bishop(testBoard, ChessPiece.Color.BLACK);
  	  
  	  assertEquals("\u2657", whiteBishop.toString());
  	  assertEquals("\u265D", blackBishop.toString());
  }
}
