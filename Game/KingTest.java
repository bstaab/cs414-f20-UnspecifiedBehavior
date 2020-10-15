package Game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KingTest {

  @Test
  public void testLegalMoves1(){
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
    assertEquals(3, legalMovesWhite.size());
    assertTrue(legalMovesWhite.contains("c1"));
    assertTrue(legalMovesWhite.contains("c2"));
    assertTrue(legalMovesWhite.contains("c3"));
    
    ArrayList<String> legalMovesBlack = blackKing.legalMoves();
    assertEquals(3, legalMovesBlack.size());
    assertTrue(legalMovesBlack.contains("f3"));
    assertTrue(legalMovesBlack.contains("f2"));
    assertTrue(legalMovesBlack.contains("f1"));
  }
  
  @Test
  public void testLegalMoves2(){
    ChessBoard testBoard = new ChessBoard();
    King   whiteKing   = new King(testBoard, ChessPiece.Color.WHITE);
    Queen  blackQueen  = new Queen(testBoard, ChessPiece.Color.BLACK);
    Rook   blackRook   = new Rook(testBoard, ChessPiece.Color.BLACK);
    Knight blackKnight = new Knight(testBoard, ChessPiece.Color.BLACK);
    Bishop blackBishop = new Bishop(testBoard, ChessPiece.Color.BLACK);
    

    testBoard.placePiece(whiteKing,   "d4");
    testBoard.placePiece(blackQueen,  "d6");
    testBoard.placePiece(blackRook,   "a4");
    testBoard.placePiece(blackKnight, "f5");
    testBoard.placePiece(blackBishop, "h6");
        
    ArrayList<String> legalMovesWhite = whiteKing.legalMoves();
    assertEquals(1, legalMovesWhite.size());
    assertTrue(legalMovesWhite.contains("c3"));
  }
  
  @Test
  public void testLegalMoves3(){
    ChessBoard testBoard = new ChessBoard();
    King   blackKing   = new King(testBoard, ChessPiece.Color.BLACK);
    Rook   whiteRook   = new Rook(testBoard, ChessPiece.Color.WHITE);
    Bishop whtieBishop = new Bishop(testBoard, ChessPiece.Color.WHITE);
    Pawn   whitePawn   = new Pawn(testBoard, ChessPiece.Color.WHITE);
    

    testBoard.placePiece(blackKing,   "d5");
    testBoard.placePiece(whitePawn,   "c6");
    testBoard.placePiece(whiteRook,   "d6");
    testBoard.placePiece(whtieBishop, "e6");
    
        
    ArrayList<String> legalMovesBlack = blackKing.legalMoves();
    assertEquals(4, legalMovesBlack.size());
    assertTrue(legalMovesBlack.contains("d6"));
    assertTrue(legalMovesBlack.contains("c5"));
    assertTrue(legalMovesBlack.contains("e4"));
    assertTrue(legalMovesBlack.contains("e5"));
  }
  
  @Test
  public void testLegalMoves4(){
    ChessBoard testBoard = new ChessBoard();
    King whiteKing = new King(testBoard, ChessPiece.Color.WHITE);
    Knight blackcKnight1 = new Knight(testBoard, ChessPiece.Color.BLACK);
    Knight blackcKnight2 = new Knight(testBoard, ChessPiece.Color.BLACK);
    Knight blackcKnight3 = new Knight(testBoard, ChessPiece.Color.BLACK);
    Knight blackcKnight4 = new Knight(testBoard, ChessPiece.Color.BLACK);
    Knight blackcKnight5 = new Knight(testBoard, ChessPiece.Color.BLACK);
    Knight blackcKnight6 = new Knight(testBoard, ChessPiece.Color.BLACK);
    Knight blackcKnight7 = new Knight(testBoard, ChessPiece.Color.BLACK);
    Knight blackcKnight8 = new Knight(testBoard, ChessPiece.Color.BLACK);

    testBoard.placePiece(whiteKing, "d4");
    testBoard.placePiece(blackcKnight1, "c1");
    testBoard.placePiece(blackcKnight2, "c2");
    testBoard.placePiece(blackcKnight3, "a3");
    testBoard.placePiece(blackcKnight4, "b5");
    testBoard.placePiece(blackcKnight5, "b6");
    testBoard.placePiece(blackcKnight6, "d7");
    testBoard.placePiece(blackcKnight7, "g4");
    testBoard.placePiece(blackcKnight8, "f2");
        
    ArrayList<String> legalMovesWhite = whiteKing.legalMoves();
    assertEquals(0, legalMovesWhite.size());
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