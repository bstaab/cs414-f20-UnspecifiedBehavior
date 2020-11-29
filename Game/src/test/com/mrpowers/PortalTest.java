package com.mrpowers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.mrpowers.chess.*;
import com.mrpowers.exceptions.IllegalPositionException;

import static org.junit.Assert.assertThrows;

class PortalTest {

  @Test
  public void testLegalMoves() {
    ChessBoard testBoard = new ChessBoard();
    
    Portal whitePortal = new Portal(testBoard, ChessPiece.Color.WHITE);
    Portal blackPortal = new Portal(testBoard, ChessPiece.Color.WHITE);
    
    testBoard.placePiece(whitePortal, "e4");
    testBoard.placePiece(blackPortal, "h5");
    
    ArrayList<String> legalMovesWhite = whitePortal.legalMoves();

    assertEquals(8,legalMovesWhite.size());
    assertTrue(legalMovesWhite.contains("d5"));
    assertTrue(legalMovesWhite.contains("e5"));
    assertTrue(legalMovesWhite.contains("f5"));
    assertTrue(legalMovesWhite.contains("f4"));
    assertTrue(legalMovesWhite.contains("f3"));
    assertTrue(legalMovesWhite.contains("e3"));
    assertTrue(legalMovesWhite.contains("d3"));
    assertTrue(legalMovesWhite.contains("d4"));
    
    ArrayList<String> legalMovesBlack = blackPortal.legalMoves();

    assertEquals(5,legalMovesBlack.size());
    assertTrue(legalMovesBlack.contains("g6"));
    assertTrue(legalMovesBlack.contains("h6"));
    assertTrue(legalMovesBlack.contains("g5"));
    assertTrue(legalMovesBlack.contains("g4"));
    assertTrue(legalMovesBlack.contains("h4"));    
  }
  
  //@Test
  public void testLegalMoves2() {
    ChessBoard testBoard = new ChessBoard();
    
    
    Portal blackPortal = new Portal(testBoard, ChessPiece.Color.BLACK);
    Portal whitePortal = new Portal(testBoard, ChessPiece.Color.BLACK);
    
    Pawn blackPawn1  = new Pawn(testBoard, ChessPiece.Color.BLACK);
    Pawn blackPawn2  = new Pawn(testBoard, ChessPiece.Color.BLACK);
    Pawn blackPawn3  = new Pawn(testBoard, ChessPiece.Color.BLACK);
    Pawn blackPawn4  = new Pawn(testBoard, ChessPiece.Color.BLACK);
    Pawn blackPawn5  = new Pawn(testBoard, ChessPiece.Color.BLACK);
    Pawn whitePawn1  = new Pawn(testBoard, ChessPiece.Color.WHITE);
    Pawn whitePawn2  = new Pawn(testBoard, ChessPiece.Color.WHITE);
    Pawn whitePawn3  = new Pawn(testBoard, ChessPiece.Color.WHITE);
    Pawn whitePawn4  = new Pawn(testBoard, ChessPiece.Color.WHITE);
    Pawn whitePawn5  = new Pawn(testBoard, ChessPiece.Color.WHITE);
        
    testBoard.placePiece(blackPortal, "e5");
    testBoard.placePiece(whitePortal, "e4");
    
    testBoard.placePiece(blackPawn1, "d5");
    testBoard.placePiece(blackPawn2, "d6");
    testBoard.placePiece(blackPawn3, "e6");
    testBoard.placePiece(blackPawn4, "f6");
    testBoard.placePiece(blackPawn5, "f5");
    
    testBoard.placePiece(whitePawn1, "d4");
    testBoard.placePiece(whitePawn2, "d3");
    testBoard.placePiece(whitePawn3, "e3");
    testBoard.placePiece(whitePawn4, "f3");
    testBoard.placePiece(whitePawn5, "f4");

    ArrayList<String> legalMovesBlack = blackPortal.legalMoves();

    assertEquals(7,legalMovesBlack.size());
    assertTrue(legalMovesBlack.contains("d6"));
    assertTrue(legalMovesBlack.contains("e6"));
    assertTrue(legalMovesBlack.contains("f6"));
    assertTrue(legalMovesBlack.contains("f5"));
    assertTrue(legalMovesBlack.contains("f4"));
    assertTrue(legalMovesBlack.contains("d4"));
    assertTrue(legalMovesBlack.contains("d5"));
    
    
    ArrayList<String> legalMovesWhite = whitePortal.legalMoves();

    assertEquals(7,legalMovesWhite.size());
    assertTrue(legalMovesWhite.contains("d5"));
    assertTrue(legalMovesWhite.contains("f5"));
    assertTrue(legalMovesWhite.contains("f4"));
    assertTrue(legalMovesWhite.contains("f3"));
    assertTrue(legalMovesWhite.contains("e3"));
    assertTrue(legalMovesWhite.contains("d3"));
    assertTrue(legalMovesWhite.contains("d4"));
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
