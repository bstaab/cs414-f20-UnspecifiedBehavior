package com.mrpowers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import com.mrpowers.chess.*;
import com.mrpowers.exceptions.IllegalPositionException;

import static org.junit.Assert.assertThrows;

public class RookTest {
  @Test
  public void testLegalMoves() {
    ChessBoard testBoard = new ChessBoard();
    Rook testRookWhite = new Rook(testBoard, ChessPiece.Color.WHITE);
    Pawn whitePawn = new Pawn(testBoard, ChessPiece.Color.WHITE);
    Pawn blackPawn = new Pawn(testBoard, ChessPiece.Color.BLACK);
    Rook testRookBlack = new Rook(testBoard, ChessPiece.Color.BLACK);
    
    testBoard.placePiece(testRookWhite, "d4");
    testBoard.placePiece(testRookBlack, "f6");
    testBoard.placePiece(whitePawn, "d6");
    testBoard.placePiece(blackPawn, "f4");
    
    ArrayList<String> legalMovesWhite = testRookWhite.legalMoves();

    assertEquals(9, legalMovesWhite.size());
    assertTrue(legalMovesWhite.contains("d5"));
    assertTrue(legalMovesWhite.contains("e4"));
    assertTrue(legalMovesWhite.contains("f4"));
    assertTrue(legalMovesWhite.contains("a4"));
    assertTrue(legalMovesWhite.contains("b4"));
    assertTrue(legalMovesWhite.contains("c4"));
    assertTrue(legalMovesWhite.contains("d1"));
    assertTrue(legalMovesWhite.contains("d2"));
    assertTrue(legalMovesWhite.contains("d3"));
    
    ArrayList<String> legalMovesBlack = testRookBlack.legalMoves();

    assertEquals(7, legalMovesBlack.size());
    assertTrue(legalMovesBlack.contains("f5"));
    assertTrue(legalMovesBlack.contains("e6"));
    assertTrue(legalMovesBlack.contains("d6"));
    assertTrue(legalMovesBlack.contains("f7"));
    assertTrue(legalMovesBlack.contains("f8"));
    assertTrue(legalMovesBlack.contains("g6"));
    assertTrue(legalMovesBlack.contains("h6"));
  }
  
  @Test
  public void testLegalMovesPortals() {
    ChessBoard testBoard = new ChessBoard();
    Rook testRookBlack = new Rook(testBoard, ChessPiece.Color.BLACK);
        
    testBoard.placePiece(testRookBlack, "c6");
    testBoard.setPortalLocation(ChessPiece.Color.BLACK, "c4");
    testBoard.setPortalLocation(ChessPiece.Color.WHITE, "a8");
    
    ArrayList<String> legalMovesWhite = testRookBlack.legalMoves();

    assertEquals(18, legalMovesWhite.size());
    assertTrue(legalMovesWhite.contains("c5"));
    assertTrue(legalMovesWhite.contains("a8"));
    assertTrue(legalMovesWhite.contains("a7"));
    assertTrue(legalMovesWhite.contains("a6"));
    assertTrue(legalMovesWhite.contains("a5"));
    assertTrue(legalMovesWhite.contains("a4"));
    assertTrue(legalMovesWhite.contains("a3"));
    assertTrue(legalMovesWhite.contains("a2"));
    assertTrue(legalMovesWhite.contains("a1"));
    assertTrue(legalMovesWhite.contains("c7"));
    assertTrue(legalMovesWhite.contains("c8"));
    assertTrue(legalMovesWhite.contains("b6"));
    assertTrue(legalMovesWhite.contains("a6"));
    assertTrue(legalMovesWhite.contains("d6"));
    assertTrue(legalMovesWhite.contains("e6"));
    assertTrue(legalMovesWhite.contains("f6"));
    assertTrue(legalMovesWhite.contains("g6"));
    assertTrue(legalMovesWhite.contains("h6"));
  }
  
  @Test
  public void testIllegalPositionException() {
	  ChessBoard testBoard = new ChessBoard();
	  Rook whiteRook = new Rook(testBoard, ChessPiece.Color.WHITE);
  	  Rook blackRook = new Rook(testBoard, ChessPiece.Color.BLACK);
  	  
  	  assertThrows(IllegalPositionException.class, () -> whiteRook.setPosition("G5"));
  	  assertThrows(IllegalPositionException.class, () -> whiteRook.setPosition("5"));
	  assertThrows(IllegalPositionException.class, () -> whiteRook.setPosition("77"));
	  assertThrows(IllegalPositionException.class, () -> whiteRook.setPosition("z9"));
	  assertThrows(IllegalPositionException.class, () -> whiteRook.setPosition("a"));
	  assertThrows(IllegalPositionException.class, () -> blackRook.setPosition("B1"));
	  assertThrows(IllegalPositionException.class, () -> blackRook.setPosition("HH"));
	  assertThrows(IllegalPositionException.class, () -> blackRook.setPosition("2c"));
	  assertThrows(IllegalPositionException.class, () -> blackRook.setPosition("D33"));
	  assertThrows(IllegalPositionException.class, () -> blackRook.setPosition("EE4"));
  }
  
  @Test
  public void testGetColorMethod() {
	  ChessBoard testBoard = new ChessBoard();
	  Rook whiteRook = new Rook(testBoard, ChessPiece.Color.WHITE);
	  Rook blackRook = new Rook(testBoard, ChessPiece.Color.BLACK);
  	  
  	  assertEquals(ChessPiece.Color.WHITE, whiteRook.getColor());
  	  assertEquals(ChessPiece.Color.BLACK, blackRook.getColor());
  }
  
  @Test
  public void testGetPosition() {
	  ChessBoard testBoard = new ChessBoard();
	  Rook whiteRook = new Rook(testBoard, ChessPiece.Color.WHITE);
	  Rook blackRook = new Rook(testBoard, ChessPiece.Color.BLACK);
  	  
  	  testBoard.placePiece(whiteRook, "b8");
  	  testBoard.placePiece(blackRook, "h8");
  	  
  	  assertEquals("b8", whiteRook.getPosition());
  	  assertEquals("h8", blackRook.getPosition());
  }
  
  @Test
  public void testToString() {
	  ChessBoard testBoard = new ChessBoard();
	  Rook whiteRook = new Rook(testBoard, ChessPiece.Color.WHITE);
	  Rook blackRook = new Rook(testBoard, ChessPiece.Color.BLACK);
  	  
  	  assertEquals("\u2656", whiteRook.toString());
  	  assertEquals("\u265C", blackRook.toString());
  }
  
}

