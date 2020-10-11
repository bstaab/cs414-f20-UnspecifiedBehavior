package Game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class PortalTest {

  @Test
  public void testLegalMoves() {
    ChessBoard testBoard = new ChessBoard();
    
    Portal whitePortal = new Portal(testBoard, ChessPiece.Color.WHITE);
    
    testBoard.placePiece(whitePortal, "e3");
    
    ArrayList<String> legalMovesWhite = whitePortal.legalMoves();

    assertEquals(25,legalMovesWhite.size());
    assertTrue(legalMovesWhite.contains("e2"));
    assertTrue(legalMovesWhite.contains("e1"));
    assertTrue(legalMovesWhite.contains("e4"));
    assertTrue(legalMovesWhite.contains("e5"));
    assertTrue(legalMovesWhite.contains("e6"));
    assertTrue(legalMovesWhite.contains("e7"));
    assertTrue(legalMovesWhite.contains("e8"));
    assertTrue(legalMovesWhite.contains("a3"));
    assertTrue(legalMovesWhite.contains("b3"));
    assertTrue(legalMovesWhite.contains("c3"));
    assertTrue(legalMovesWhite.contains("d3"));
    assertTrue(legalMovesWhite.contains("f3"));
    assertTrue(legalMovesWhite.contains("g3"));
    assertTrue(legalMovesWhite.contains("h3"));
    assertTrue(legalMovesWhite.contains("d2"));
    assertTrue(legalMovesWhite.contains("c1"));
    assertTrue(legalMovesWhite.contains("f2"));
    assertTrue(legalMovesWhite.contains("g1"));
    assertTrue(legalMovesWhite.contains("f4"));
    assertTrue(legalMovesWhite.contains("g5"));
    assertTrue(legalMovesWhite.contains("h6"));
    assertTrue(legalMovesWhite.contains("d4"));
    assertTrue(legalMovesWhite.contains("c5"));
    assertTrue(legalMovesWhite.contains("b6"));
    assertTrue(legalMovesWhite.contains("a7"));
  }
  
  //@Test
  public void testLegalMoves2() {
    ChessBoard testBoard = new ChessBoard();
    
    
    Portal blackPortal = new Portal(testBoard, ChessPiece.Color.BLACK);
    Portal whitePortal = new Portal(testBoard, ChessPiece.Color.BLACK);
    
    Queen blackQueen = new Queen(testBoard, ChessPiece.Color.BLACK);
    Pawn blackPawn1  = new Pawn(testBoard, ChessPiece.Color.BLACK);
    Pawn blackPawn2  = new Pawn(testBoard, ChessPiece.Color.BLACK);
    Pawn blackPawn3  = new Pawn(testBoard, ChessPiece.Color.BLACK);
    Pawn blackPawn4  = new Pawn(testBoard, ChessPiece.Color.BLACK);
    Pawn blackPawn5  = new Pawn(testBoard, ChessPiece.Color.BLACK);
    Queen whiteQueen = new Queen(testBoard, ChessPiece.Color.WHITE);
    Pawn whitePawn1  = new Pawn(testBoard, ChessPiece.Color.WHITE);
    Pawn whitePawn2  = new Pawn(testBoard, ChessPiece.Color.WHITE);
    Pawn whitePawn3  = new Pawn(testBoard, ChessPiece.Color.WHITE);
    Pawn whitePawn4  = new Pawn(testBoard, ChessPiece.Color.WHITE);
    Pawn whitePawn5  = new Pawn(testBoard, ChessPiece.Color.WHITE);
        
    testBoard.placePiece(blackPortal, "e4");
    testBoard.placePiece(whitePortal, "e4");
    
    testBoard.placePiece(blackQueen, "e4");
    testBoard.placePiece(blackPawn1, "d4");
    testBoard.placePiece(blackPawn2, "d5");
    testBoard.placePiece(blackPawn3, "e5");
    testBoard.placePiece(blackPawn4, "f5");
    testBoard.placePiece(blackPawn5, "f4");
    testBoard.placePiece(whiteQueen, "e3");
    testBoard.placePiece(whitePawn1, "d3");
    testBoard.placePiece(whitePawn2, "d2");
    testBoard.placePiece(whitePawn3, "e2");
    testBoard.placePiece(whitePawn4, "f2");
    testBoard.placePiece(whitePawn5, "f3");

    ArrayList<String> legalMovesBlack = blackQueen.legalMoves();

    assertEquals(3,legalMovesBlack.size());
    assertTrue(legalMovesBlack.contains("d3"));
    assertTrue(legalMovesBlack.contains("e3"));
    assertTrue(legalMovesBlack.contains("f3"));
    
    ArrayList<String> legalMovesWhite = whiteQueen.legalMoves();

    assertEquals(3,legalMovesWhite.size());
    assertTrue(legalMovesWhite.contains("d4"));
    assertTrue(legalMovesWhite.contains("e4"));
    assertTrue(legalMovesWhite.contains("f4"));
  }
  
  @Test
  public void testIllegalPositionException() {
	  ChessBoard testBoard = new ChessBoard();
	  Portal whitePortal = new Portal(testBoard, ChessPiece.Color.WHITE);
	  Portal blackPortal = new Portal(testBoard, ChessPiece.Color.BLACK);
  	  
  	  assertThrows(IllegalPositionException.class, () -> whitePortal.setPosition("G5"));
  	  assertThrows(IllegalPositionException.class, () -> whitePortal.setPosition("5"));
	  assertThrows(IllegalPositionException.class, () -> whitePortal.setPosition("77"));
	  assertThrows(IllegalPositionException.class, () -> whitePortal.setPosition("z9"));
	  assertThrows(IllegalPositionException.class, () -> whitePortal.setPosition("a"));
	  assertThrows(IllegalPositionException.class, () -> blackPortal.setPosition("B1"));
	  assertThrows(IllegalPositionException.class, () -> blackPortal.setPosition("HH"));
	  assertThrows(IllegalPositionException.class, () -> blackPortal.setPosition("2c"));
	  assertThrows(IllegalPositionException.class, () -> blackPortal.setPosition("D33"));
	  assertThrows(IllegalPositionException.class, () -> blackPortal.setPosition("EE4"));
  }
  
  @Test
  public void testGetColorMethod() {
	  ChessBoard testBoard = new ChessBoard();
	  Portal whitePortal = new Portal(testBoard, ChessPiece.Color.WHITE);
	  Portal blackPortal = new Portal(testBoard, ChessPiece.Color.BLACK);
  	  
  	  assertEquals(ChessPiece.Color.WHITE, whitePortal.getColor());
  	  assertEquals(ChessPiece.Color.BLACK, blackPortal.getColor());
  }
  
  @Test
  public void testGetPosition() {
	  ChessBoard testBoard = new ChessBoard();
	  Portal whitePortal = new Portal(testBoard, ChessPiece.Color.WHITE);
	  Portal blackPortal = new Portal(testBoard, ChessPiece.Color.BLACK);
  	  
  	  testBoard.placePiece(whitePortal, "h3");
  	  testBoard.placePiece(blackPortal, "g1");
  	  
  	  assertEquals("h3", whitePortal.getPosition());
  	  assertEquals("g1", blackPortal.getPosition());
  }
  
  @Test
  public void testToString() {
	  ChessBoard testBoard = new ChessBoard();
	  Portal whitePortal = new Portal(testBoard, ChessPiece.Color.WHITE);
	  Portal blackPortal = new Portal(testBoard, ChessPiece.Color.BLACK);
  	  
  	  assertEquals("\u26AA", whitePortal.toString());
  	  assertEquals("\u26AB", blackPortal.toString());
  }
  
}
