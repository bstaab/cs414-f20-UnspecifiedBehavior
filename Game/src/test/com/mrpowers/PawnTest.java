package com.mrpowers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.mrpowers.chess.*;
import com.mrpowers.exceptions.IllegalPositionException;

import static org.junit.Assert.assertThrows;

public class PawnTest {

  @Test
  public void testLegalMoves() {
    ChessBoard testBoard = new ChessBoard();
    Pawn blackPawn     = new Pawn(testBoard, ChessPiece.Color.BLACK);
    Pawn testPawnWhite = new Pawn(testBoard, ChessPiece.Color.WHITE);
    Pawn whitePawn     = new Pawn(testBoard, ChessPiece.Color.WHITE);
    
    Pawn whitePawn1      = new Pawn(testBoard, ChessPiece.Color.WHITE);
    Pawn blackPawn1      = new Pawn(testBoard, ChessPiece.Color.BLACK);
    Pawn testPawnBlackc  = new Pawn(testBoard, ChessPiece.Color.BLACK);
    
    testBoard.placePiece(blackPawn, "e3");
    testBoard.placePiece(testPawnWhite, "d2");
    testBoard.placePiece(whitePawn, "c3");

    testBoard.placePiece(whitePawn1, "c6");
    testBoard.placePiece(testPawnBlackc, "d7");
    testBoard.placePiece(blackPawn1, "e6");

    ArrayList<String> legalMovesWhite = testPawnWhite.legalMoves();

    assertEquals(3,legalMovesWhite.size());
    assertTrue(legalMovesWhite.contains("d3"));
    assertTrue(legalMovesWhite.contains("d4"));
    assertTrue(legalMovesWhite.contains("e3"));

    ArrayList<String> legalMovesBlack = testPawnBlackc.legalMoves();
    assertEquals(3,legalMovesWhite.size());
    assertTrue(legalMovesBlack.contains("c6"));
    assertTrue(legalMovesBlack.contains("d6"));
    assertTrue(legalMovesBlack.contains("d5"));
  }
  
  @Test
  public void testLegalMovesWithPortals1() {
    ChessBoard testBoard = new ChessBoard();
    Pawn blackPawn     = new Pawn(testBoard, ChessPiece.Color.BLACK);
    Pawn testPawnWhite = new Pawn(testBoard, ChessPiece.Color.WHITE);
        
    testBoard.placePiece(blackPawn,     "c7");
    testBoard.placePiece(testPawnWhite, "c2");
    testBoard.setPortalLocation(ChessPiece.Color.BLACK, "c4");
    testBoard.setPortalLocation(ChessPiece.Color.WHITE, "c7");
    
    ArrayList<String> legalMovesWhite = testPawnWhite.legalMoves();

    assertEquals(4,legalMovesWhite.size());
    assertTrue(legalMovesWhite.contains("c3"));
    assertTrue(legalMovesWhite.contains("c7"));
    assertTrue(legalMovesWhite.contains("b3"));
    assertTrue(legalMovesWhite.contains("d3"));
  }
  
  @Test
  public void testLegalMovesWithPortals2() {
    ChessBoard testBoard = new ChessBoard();
    Pawn blackPawn1     = new Pawn(testBoard, ChessPiece.Color.BLACK);
    Pawn blackPawn2     = new Pawn(testBoard, ChessPiece.Color.BLACK);
        
    testBoard.placePiece(blackPawn1, "c7");
    testBoard.placePiece(blackPawn2, "c2");
    testBoard.setPortalLocation(ChessPiece.Color.BLACK, "c2");
    testBoard.setPortalLocation(ChessPiece.Color.WHITE, "c5");
    
    ArrayList<String> legalMovesBlack1 = blackPawn1.legalMoves();

    assertEquals(4,legalMovesBlack1.size());
    assertTrue(legalMovesBlack1.contains("c6"));
    assertTrue(legalMovesBlack1.contains("c5"));
    assertTrue(legalMovesBlack1.contains("d6"));
    assertTrue(legalMovesBlack1.contains("b6"));
  }
  
  @Test
  public void testIllegalPositionException(){
	  ChessBoard testBoard = new ChessBoard();
	  Pawn whitePawn = new Pawn(testBoard, ChessPiece.Color.WHITE);
  	  Pawn blackPawn = new Pawn(testBoard, ChessPiece.Color.BLACK);
  	  
  	  assertThrows(IllegalPositionException.class, () -> whitePawn.setPosition("G5"));
  	  assertThrows(IllegalPositionException.class, () -> whitePawn.setPosition("5"));
	  assertThrows(IllegalPositionException.class, () -> whitePawn.setPosition("77"));
	  assertThrows(IllegalPositionException.class, () -> whitePawn.setPosition("z9"));
	  assertThrows(IllegalPositionException.class, () -> whitePawn.setPosition("a"));
	  assertThrows(IllegalPositionException.class, () -> blackPawn.setPosition("B1"));
	  assertThrows(IllegalPositionException.class, () -> blackPawn.setPosition("HH"));
	  assertThrows(IllegalPositionException.class, () -> blackPawn.setPosition("2c"));
	  assertThrows(IllegalPositionException.class, () -> blackPawn.setPosition("D33"));
	  assertThrows(IllegalPositionException.class, () -> blackPawn.setPosition("EE4"));
  }
  
  @Test
  public void testGetColorMethod() {
	  ChessBoard testBoard = new ChessBoard();
	  Pawn whitePawn = new Pawn(testBoard, ChessPiece.Color.WHITE);
	  Pawn blackPawn = new Pawn(testBoard, ChessPiece.Color.BLACK);
  	  
  	  assertEquals(ChessPiece.Color.WHITE, whitePawn.getColor());
  	  assertEquals(ChessPiece.Color.BLACK, blackPawn.getColor());
  }
  
  @Test
  public void testGetPosition() {
	  ChessBoard testBoard = new ChessBoard();
	  Pawn whitePawn = new Pawn(testBoard, ChessPiece.Color.WHITE);
	  Pawn blackPawn = new Pawn(testBoard, ChessPiece.Color.BLACK);
  	  
  	  testBoard.placePiece(whitePawn, "b6");
  	  testBoard.placePiece(blackPawn, "a7");
  	  
  	  assertEquals("b6", whitePawn.getPosition());
  	  assertEquals("a7", blackPawn.getPosition());
  }
  
  @Test
  public void testToString() {
	  ChessBoard testBoard = new ChessBoard();
	  Pawn whitePawn = new Pawn(testBoard, ChessPiece.Color.WHITE);
	  Pawn blackPawn = new Pawn(testBoard, ChessPiece.Color.BLACK);
  	  
  	  assertEquals("\u2659", whitePawn.toString());
  	  assertEquals("\u265F", blackPawn.toString());
  }
  
}
