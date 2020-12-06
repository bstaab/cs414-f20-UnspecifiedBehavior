package com.mrpowers;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import com.mrpowers.chess.ChessBoard;
import com.mrpowers.chess.ChessPiece;
import com.mrpowers.exceptions.IllegalMoveException;
import com.mrpowers.exceptions.IllegalPositionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.mrpowers.chess.*;
import com.mrpowers.exceptions.IllegalMoveException;
import com.mrpowers.exceptions.IllegalPositionException;
//import static org.junit.Assert.assertThrows;

import static org.junit.Assert.assertTrue;

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
		  assertEquals("\u2654", testBoard.getPiece("e1").toString());
		  assertEquals("\u265A", testBoard.getPiece("e8").toString());
		  
		  // Queen
		  assertEquals("\u265B", testBoard.getPiece("d8").toString());
		  assertEquals("\u2655", testBoard.getPiece("d1").toString());
		  
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
  public void testPlacePiece1() {
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

  @Test
  public void testPlacePiece2() {
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
  
  @ Test
  public void testMoves1() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "a8"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(legalMoves.contains("a8"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves2() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "a7"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(legalMoves.contains("a7"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves3() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "b8"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "b3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("b2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(legalMoves.contains("b8"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves4() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "b7"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "b3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(legalMoves.contains("b7"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves5() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "c8"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(legalMoves.contains("c8"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
	  
  @ Test
  public void testMoves6() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "d8"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(legalMoves.contains("d8"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves7() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "e8"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(legalMoves.contains("e8"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves8() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "f8"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(legalMoves.contains("f8"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves9() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "g8"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(legalMoves.contains("g8"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves10() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "h8"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(legalMoves.contains("h8"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves11() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "b2"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("b2"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves12() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "c2"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("c2"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves13() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "d2"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("d2"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves14() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "e2"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("e2"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves15() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "f2"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("f2"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves16() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "g2"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("g2"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves17() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "h2"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("h2"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves18() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "a1"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("a1"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves19() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "a2"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("a2"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves20() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "b1"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("b1"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves21() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "b2"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("b2"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves22() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "c1"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("c1"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves23() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "c2"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("c2"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves24() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "d1"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("d1"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves25() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "d2"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("d2"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves26() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "e1"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("e1"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves27() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "e2"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("e2"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves28() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "f1"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("f1"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves29() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "f2"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("f2"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves30() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "g1"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("g1"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves31() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "g2"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("g2"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves32() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "h1"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("h1"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves33() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  
	  try {
		  // Verify initial portal location
		  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
		  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
		  
		  // Place piece on empty square and verify new position
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "h2"));
		  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a3"));
		  
		  // Get pawn located at a2
		  ChessPiece Piece = testBoard.getPiece("a2");
		  		  
		  // get valid moves
		  ArrayList<String> legalMoves = Piece.legalMoves();

		  // Verify it can move to the portal location
		  assertTrue(!legalMoves.contains("h2"));
		  
	  } catch (IllegalPositionException e) {
		  e.printStackTrace();
	  }
  }
  
  @ Test
  public void testMoves34() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
	  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
	  
	  // Place piece on empty square and verify new position
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "a1"));
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a1"));
  }
  
  @ Test
  public void testMoves35() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
	  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
	  
	  // Place piece on empty square and verify new position
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "a1"));
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a8"));
  }
  
  @ Test
  public void testMoves36() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
	  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
	  
	  // Place piece on empty square and verify new position
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "a2"));
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "a7"));
  }
  
  @ Test
  public void testMoves37() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
	  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
	  
	  // Place piece on empty square and verify new position
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "b1"));
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "b8"));
  }
  
  @ Test
  public void testMoves38() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
	  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
	  
	  // Place piece on empty square and verify new position
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "b2"));
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "b7"));
  }
  
  @ Test
  public void testMoves39() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
	  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
	  
	  // Place piece on empty square and verify new position
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "c1"));
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "c8"));
  }
  
  @ Test
  public void testMoves40() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
	  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
	  
	  // Place piece on empty square and verify new position
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "c2"));
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "c7"));
  }
  
  @ Test
  public void testMoves41() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
	  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
	  
	  // Place piece on empty square and verify new position
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "d1"));
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "d8"));
  }
  
  @ Test
  public void testMoves42() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
	  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
	  
	  // Place piece on empty square and verify new position
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "d2"));
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "d7"));
  }
  
  @ Test
  public void testMoves43() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
	  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
	  
	  // Place piece on empty square and verify new position
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "e1"));
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "e8"));
  }
  
  @ Test
  public void testMoves44() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
	  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
	  
	  // Place piece on empty square and verify new position
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "e2"));
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "e7"));
  }
  
  @ Test
  public void testMoves45() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
	  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
	  
	  // Place piece on empty square and verify new position
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "f1"));
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "f8"));
  }
  
  @ Test
  public void testMoves46() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
	  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
	  
	  // Place piece on empty square and verify new position
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "f2"));
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "f7"));
  }
  
  @ Test
  public void testMoves47() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
	  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
	  
	  // Place piece on empty square and verify new position
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "g1"));
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "g8"));
  }
  
  @ Test
  public void testMoves48() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
	  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
	  
	  // Place piece on empty square and verify new position
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "g2"));
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "g7"));
  }
  
  @ Test
  public void testMoves49() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
	  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
	  
	  // Place piece on empty square and verify new position
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "h1"));
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "h8"));
  }
  
  @ Test
  public void testMoves50() {
	  ChessBoard testBoard = new ChessBoard();
	  
	  testBoard.initialize();
	  assertEquals("a4", testBoard.getPortalLocation(ChessPiece.Color.WHITE));
	  assertEquals("a5", testBoard.getPortalLocation(ChessPiece.Color.BLACK));
	  
	  // Place piece on empty square and verify new position
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.WHITE, "h2"));
	  assertEquals(true, testBoard.setPortalLocation(ChessPiece.Color.BLACK, "h7"));
  }

  @Test
  public void testRowColToPosition() {
  	  ChessBoard testBoard = new ChessBoard();
  	  String test = testBoard.rowColToPosition(7,0);

  	  assertEquals(test, "a8");
  }

  @Test
  public void testInitialFen() {
  	ChessBoard testBoard = new ChessBoard();
  	testBoard.initialize();
	String initialPosition =  "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - - 0";

  	assertEquals(testBoard.getFenBoard(),initialPosition);
  }

	@Test
	public void testInitialEndGame() {
		ChessBoard testBoard = new ChessBoard();

		String endGamePosition =  "1k1r4/1p6/pb4p1/8/4N3/6P1/2P2P1P/4R1K1 b - - - 1";

		try {
			Pawn blackPawnOne = new Pawn(testBoard, ChessPiece.Color.BLACK);
			Pawn blackPawnTwo = new Pawn(testBoard, ChessPiece.Color.BLACK);
			Pawn blackPawnThree = new Pawn(testBoard, ChessPiece.Color.BLACK);
			Bishop blackBishop = new Bishop(testBoard, ChessPiece.Color.BLACK);
			Rook blackRook = new Rook(testBoard, ChessPiece.Color.BLACK);
			King blackKing = new King(testBoard, ChessPiece.Color.BLACK);

			Pawn whitePawnOne = new Pawn(testBoard, ChessPiece.Color.WHITE);
			Pawn whitePawnTwo = new Pawn(testBoard, ChessPiece.Color.WHITE);
			Pawn whitePawnThree = new Pawn(testBoard, ChessPiece.Color.WHITE);
			Pawn whitePawnFour = new Pawn(testBoard, ChessPiece.Color.WHITE);
			Knight whiteKnight = new Knight(testBoard, ChessPiece.Color.WHITE);
			Rook whiteRook = new Rook(testBoard, ChessPiece.Color.WHITE);
			King whiteKing = new King(testBoard, ChessPiece.Color.WHITE);

			testBoard.placePiece(blackPawnOne, "a6");
			testBoard.placePiece(blackPawnTwo, "b7");
			testBoard.placePiece(blackPawnThree, "g6");
			testBoard.placePiece(blackBishop, "b6");
			testBoard.placePiece(blackRook, "d8");
			testBoard.placePiece(blackKing, "b8");

			testBoard.placePiece(whitePawnOne, "c1");
			testBoard.placePiece(whitePawnTwo, "f2");
			testBoard.placePiece(whitePawnThree, "g3");
			testBoard.placePiece(whitePawnFour, "h2");
			testBoard.placePiece(whiteKnight, "e4");
			testBoard.placePiece(whiteRook, "e1");
			testBoard.placePiece(whiteKing, "g1");

			testBoard.move("c1","c2");

		} catch(Exception e) {}

		assertEquals(testBoard.getFenBoard(), endGamePosition);
	}
	
	@ Test
	public void testGetKing() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  		  
		ChessPiece Piece = testBoard.getKing(ChessPiece.Color.WHITE);
		assertEquals("e1", Piece.getPosition());
		
		Piece = testBoard.getKing(ChessPiece.Color.BLACK);
		assertEquals("e8", Piece.getPosition());
	}

	@ Test
	public void testIsCheck1() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece wKing = testBoard.getKing(ChessPiece.Color.WHITE);
		assertEquals("e1", wKing.getPosition());
		assertEquals(true, testBoard.placePiece(wKing, "e4"));
				
		assertEquals("", testBoard.isCheck());		
	}
		
	@ Test
	public void testIsCheck2() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece bKing = testBoard.getKing(ChessPiece.Color.BLACK);
		assertEquals("e8", bKing.getPosition());
		assertEquals(true, testBoard.placePiece(bKing, "e5"));
				
		assertEquals("", testBoard.isCheck());		
	}
	
	@ Test
	public void testIsCheck3() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece wKing = testBoard.getKing(ChessPiece.Color.WHITE);
		assertEquals("e1", wKing.getPosition());
		assertEquals(true, testBoard.placePiece(wKing, "e4"));
				
		ChessPiece bKing = testBoard.getKing(ChessPiece.Color.BLACK);
		assertEquals("e8", bKing.getPosition());
		assertEquals(true, testBoard.placePiece(bKing, "e5"));
		
		assertEquals("Black", testBoard.isCheck());		
	}
	
	@ Test
	public void testIsCheck4() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece wKing = testBoard.getKing(ChessPiece.Color.WHITE);
		assertEquals("e1", wKing.getPosition());
		assertEquals(true, testBoard.placePiece(wKing, "e4"));
				
		ChessPiece blackBishop = new Bishop(testBoard, ChessPiece.Color.BLACK);
		testBoard.placePiece(blackBishop,"c6");
				
		assertEquals("White", testBoard.isCheck());		
	}
	
	@ Test
	public void testIsCheck5() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece wKing = testBoard.getKing(ChessPiece.Color.WHITE);
		assertEquals("e1", wKing.getPosition());
		assertEquals(true, testBoard.placePiece(wKing, "e4"));
				
		ChessPiece blackBishop = new Bishop(testBoard, ChessPiece.Color.BLACK);
		testBoard.placePiece(blackBishop,"d6");
				
		assertEquals("", testBoard.isCheck());		
	}
	
    @ Test
	public void testIsCheck6() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece bKing = testBoard.getKing(ChessPiece.Color.BLACK);
		assertEquals("e8", bKing.getPosition());
		assertEquals(true, testBoard.placePiece(bKing, "e5"));
				
		ChessPiece whiteBishop = new Bishop(testBoard, ChessPiece.Color.WHITE);
		testBoard.placePiece(whiteBishop,"g3");
				
		assertEquals("Black", testBoard.isCheck());		
	}
   
	@Test
	public void testIsCheck7() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece bKing = testBoard.getKing(ChessPiece.Color.BLACK);
		assertEquals("e8", bKing.getPosition());
		assertEquals(true, testBoard.placePiece(bKing, "e5"));
				
		ChessPiece whiteBishop = new Bishop(testBoard, ChessPiece.Color.WHITE);
		testBoard.placePiece(whiteBishop,"g4");
				
		assertEquals("", testBoard.isCheck());		
	}
	
	@ Test
	public void testIsCheckmate1() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		assertEquals("", testBoard.isCheckmate());		
	}
	
	@ Test
	public void testIsCheckmate2() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece wKing = testBoard.getKing(ChessPiece.Color.WHITE);
		assertEquals("e1", wKing.getPosition());
		assertEquals(true, testBoard.placePiece(wKing, "e5"));
		
		ChessPiece Queen = new Queen(testBoard, ChessPiece.Color.BLACK);
		ChessPiece Rook1 = new Rook(testBoard, ChessPiece.Color.BLACK);
		ChessPiece Rook2 = new Rook(testBoard, ChessPiece.Color.BLACK);
		
		testBoard.placePiece(Queen,"d3");
		testBoard.placePiece(Rook1,"e3");
		testBoard.placePiece(Rook2,"f3");
				
		assertEquals("White", testBoard.isCheckmate());		
	}
	
	@ Test
	public void testIsCheckmate3() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece wKing = testBoard.getKing(ChessPiece.Color.WHITE);
		assertEquals("e1", wKing.getPosition());
		assertEquals(true, testBoard.placePiece(wKing, "e5"));
		
		ChessPiece Queen = new Queen(testBoard, ChessPiece.Color.BLACK);
		ChessPiece Rook1 = new Rook(testBoard, ChessPiece.Color.BLACK);
		ChessPiece Rook2 = new Rook(testBoard, ChessPiece.Color.BLACK);
		
		testBoard.placePiece(Queen,"c4");
		testBoard.placePiece(Rook1,"c5");
		testBoard.placePiece(Rook2,"c6");
				
		assertEquals("White", testBoard.isCheckmate());		
	}
	
	@ Test
	public void testIsCheckmate4() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece wKing = testBoard.getKing(ChessPiece.Color.WHITE);
		assertEquals("e1", wKing.getPosition());
		assertEquals(true, testBoard.placePiece(wKing, "e5"));
		
		ChessPiece Queen = new Queen(testBoard, ChessPiece.Color.BLACK);
		ChessPiece Rook1 = new Rook(testBoard, ChessPiece.Color.BLACK);
		ChessPiece Rook2 = new Rook(testBoard, ChessPiece.Color.BLACK);
		
		testBoard.placePiece(Queen,"g4");
		testBoard.placePiece(Rook1,"g5");
		testBoard.placePiece(Rook2,"g6");
				
		assertEquals("White", testBoard.isCheckmate());		
	}
	
	@ Test
	public void testIsCheckmate5() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece wKing = testBoard.getKing(ChessPiece.Color.WHITE);
		assertEquals("e1", wKing.getPosition());
		assertEquals(true, testBoard.placePiece(wKing, "e5"));
		
		ChessPiece whiteQueen = new Queen(testBoard, ChessPiece.Color.BLACK);
		ChessPiece whiteRook1 = new Rook(testBoard, ChessPiece.Color.BLACK);
		ChessPiece whiteRook2 = new Rook(testBoard, ChessPiece.Color.BLACK);
		
		testBoard.placePiece(whiteQueen,"g4");
		testBoard.placePiece(whiteRook1,"c5");
		testBoard.placePiece(whiteRook2,"g6");
				
		assertEquals("White", testBoard.isCheckmate());		
	}
	
	@ Test
	public void testIsCheckmate6() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece wKing = testBoard.getKing(ChessPiece.Color.WHITE);
		assertEquals("e1", wKing.getPosition());
		assertEquals(true, testBoard.placePiece(wKing, "e5"));
		
		ChessPiece Queen = new Queen(testBoard, ChessPiece.Color.BLACK);
		ChessPiece Rook1 = new Rook(testBoard, ChessPiece.Color.BLACK);
		ChessPiece Rook2 = new Rook(testBoard, ChessPiece.Color.BLACK);
		
		testBoard.placePiece(Queen,"c4");
		testBoard.placePiece(Rook1,"g5");
		testBoard.placePiece(Rook2,"c6");
				
		assertEquals("White", testBoard.isCheckmate());		
	}
	
	
	@ Test
	public void testIsCheckmate7() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		assertEquals("", testBoard.isCheckmate());		
	}
	
	@ Test
	public void testIsCheckmate8() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece bKing = testBoard.getKing(ChessPiece.Color.BLACK);
		assertEquals("e8", bKing.getPosition());
		assertEquals(true, testBoard.placePiece(bKing, "e5"));
		
		ChessPiece whiteQueen = new Queen(testBoard, ChessPiece.Color.WHITE);
		ChessPiece whiteRook1 = new Rook(testBoard, ChessPiece.Color.WHITE);
		ChessPiece whiteRook2 = new Rook(testBoard, ChessPiece.Color.WHITE);
		
		testBoard.placePiece(whiteQueen,"d3");
		testBoard.placePiece(whiteRook1,"e3");
		testBoard.placePiece(whiteRook2,"f3");
				
		assertEquals("Black", testBoard.isCheckmate());		
	}
	
	@ Test
	public void testIsCheckmate9() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece bKing = testBoard.getKing(ChessPiece.Color.BLACK);
		assertEquals("e8", bKing.getPosition());
		assertEquals(true, testBoard.placePiece(bKing, "e5"));
		
		ChessPiece whiteQueen = new Queen(testBoard, ChessPiece.Color.WHITE);
		ChessPiece whiteRook1 = new Rook(testBoard, ChessPiece.Color.WHITE);
		ChessPiece whiteRook2 = new Rook(testBoard, ChessPiece.Color.WHITE);
		
		testBoard.placePiece(whiteQueen,"c4");
		testBoard.placePiece(whiteRook1,"c5");
		testBoard.placePiece(whiteRook2,"c6");
				
		assertEquals("Black", testBoard.isCheckmate());		
	}
	
	@ Test
	public void testIsCheckmate10() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece bKing = testBoard.getKing(ChessPiece.Color.BLACK);
		assertEquals("e8", bKing.getPosition());
		assertEquals(true, testBoard.placePiece(bKing, "e5"));
		
		ChessPiece whiteQueen = new Queen(testBoard, ChessPiece.Color.WHITE);
		ChessPiece whiteRook1 = new Rook(testBoard, ChessPiece.Color.WHITE);
		ChessPiece whiteRook2 = new Rook(testBoard, ChessPiece.Color.WHITE);
		
		testBoard.placePiece(whiteQueen,"g4");
		testBoard.placePiece(whiteRook1,"g5");
		testBoard.placePiece(whiteRook2,"g6");
				
		assertEquals("Black", testBoard.isCheckmate());		
	}
	
	@ Test
	public void testIsCheckmate11() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece bKing = testBoard.getKing(ChessPiece.Color.BLACK);
		assertEquals("e8", bKing.getPosition());
		assertEquals(true, testBoard.placePiece(bKing, "e5"));
		
		ChessPiece whiteQueen = new Queen(testBoard, ChessPiece.Color.WHITE);
		ChessPiece whiteRook1 = new Rook(testBoard, ChessPiece.Color.WHITE);
		ChessPiece whiteRook2 = new Rook(testBoard, ChessPiece.Color.WHITE);
		
		testBoard.placePiece(whiteQueen,"g4");
		testBoard.placePiece(whiteRook1,"c5");
		testBoard.placePiece(whiteRook2,"g6");
				
		assertEquals("Black", testBoard.isCheckmate());		
	}
	
	@ Test
	public void testIsCheckmate12() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece bKing = testBoard.getKing(ChessPiece.Color.BLACK);
		assertEquals("e8", bKing.getPosition());
		assertEquals(true, testBoard.placePiece(bKing, "e5"));
		
		ChessPiece whiteQueen = new Queen(testBoard, ChessPiece.Color.WHITE);
		ChessPiece whiteRook1 = new Rook(testBoard, ChessPiece.Color.WHITE);
		ChessPiece whiteRook2 = new Rook(testBoard, ChessPiece.Color.WHITE);
		
		testBoard.placePiece(whiteQueen,"c4");
		testBoard.placePiece(whiteRook1,"g5");
		testBoard.placePiece(whiteRook2,"c6");
				
		assertEquals("Black", testBoard.isCheckmate());		
	}
	
	@ Test
	public void testChecCheckmate1() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece King = testBoard.getKing(ChessPiece.Color.BLACK);
		assertEquals("e8", King.getPosition());
		assertEquals(true, testBoard.placePiece(King, "e5"));
		
		ChessPiece Queen = new Queen(testBoard, ChessPiece.Color.WHITE);
		testBoard.placePiece(Queen,"c3");
				
		assertEquals("Black", testBoard.isCheck());
		assertEquals("", testBoard.isCheckmate());		
	}
	
	@ Test
	public void testChecCheckmate2() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece King = testBoard.getKing(ChessPiece.Color.BLACK);
		assertEquals("e8", King.getPosition());
		assertEquals(true, testBoard.placePiece(King, "e5"));
		
		ChessPiece Knight = new Knight(testBoard, ChessPiece.Color.WHITE);
		testBoard.placePiece(Knight,"f3");
				
		assertEquals("Black", testBoard.isCheck());
		assertEquals("", testBoard.isCheckmate());		
	}
	
	@ Test
	public void testChecCheckmate3() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece King = testBoard.getKing(ChessPiece.Color.BLACK);
		assertEquals("e8", King.getPosition());
		assertEquals(true, testBoard.placePiece(King, "e5"));
		
		ChessPiece Pawn = new Pawn(testBoard, ChessPiece.Color.WHITE);
		testBoard.placePiece(Pawn,"e4");
				
		assertEquals("Black", testBoard.isCheck());
		assertEquals("", testBoard.isCheckmate());		
	}
	
	@ Test
	public void testChecCheckmate4() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece King = testBoard.getKing(ChessPiece.Color.WHITE);
		assertEquals("e1", King.getPosition());
		assertEquals(true, testBoard.placePiece(King, "e5"));
		
		ChessPiece Queen = new Queen(testBoard, ChessPiece.Color.BLACK);
		testBoard.placePiece(Queen,"c3");
				
		assertEquals("White", testBoard.isCheck());
		assertEquals("", testBoard.isCheckmate());		
	}
	
	@ Test
	public void testChecCheckmate5() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece King = testBoard.getKing(ChessPiece.Color.WHITE);
		assertEquals("e1", King.getPosition());
		assertEquals(true, testBoard.placePiece(King, "e5"));
		
		ChessPiece Knight = new Knight(testBoard, ChessPiece.Color.BLACK);
		testBoard.placePiece(Knight,"f3");
				
		assertEquals("White", testBoard.isCheck());
		assertEquals("", testBoard.isCheckmate());		
	}
	
	@ Test
	public void testChecCheckmate6() {
		ChessBoard testBoard = new ChessBoard();
		  
		testBoard.initialize();
		  
		ChessPiece King = testBoard.getKing(ChessPiece.Color.WHITE);
		assertEquals("e1", King.getPosition());
		assertEquals(true, testBoard.placePiece(King, "e5"));
		
		ChessPiece Pawn = new Pawn(testBoard, ChessPiece.Color.BLACK);
		testBoard.placePiece(Pawn,"e4");
				
		assertEquals("White", testBoard.isCheck());
		assertEquals("", testBoard.isCheckmate());		
	}
}

