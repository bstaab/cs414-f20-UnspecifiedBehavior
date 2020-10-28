package Game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KnightTest {

	
	@Test
	public void testLegalMoves() {
		ChessBoard testBoard = new ChessBoard();
		
	    Knight blackKnight1 = new Knight(testBoard, ChessPiece.Color.BLACK);
	    Knight blackKnight2 = new Knight(testBoard, ChessPiece.Color.BLACK);
	    Knight whiteKnight1 = new Knight(testBoard, ChessPiece.Color.WHITE);
	    Knight whiteKnight2 = new Knight(testBoard, ChessPiece.Color.WHITE);
	    
	    testBoard.placePiece(blackKnight1, "b8");
	    testBoard.placePiece(whiteKnight1, "d4");
	    testBoard.placePiece(blackKnight2, "a1");
	    testBoard.placePiece(whiteKnight2, "b1");
	    
	    ArrayList<String> legalMovesBlackKnight1 = blackKnight1.legalMoves();
	    assertEquals(3, legalMovesBlackKnight1.size());
	    assertTrue(legalMovesBlackKnight1.contains("a6"));
	    assertTrue(legalMovesBlackKnight1.contains("c6"));
	    assertTrue(legalMovesBlackKnight1.contains("d7"));
	    	    
	    ArrayList<String> legalMovesWhiteKnight1 = whiteKnight1.legalMoves();
	    assertEquals(8, legalMovesWhiteKnight1.size());
	    assertTrue(legalMovesWhiteKnight1.contains("c2"));
	    assertTrue(legalMovesWhiteKnight1.contains("b3"));
	    assertTrue(legalMovesWhiteKnight1.contains("b5"));
	    assertTrue(legalMovesWhiteKnight1.contains("c6"));
	    assertTrue(legalMovesWhiteKnight1.contains("e6"));
	    assertTrue(legalMovesWhiteKnight1.contains("f5"));
	    assertTrue(legalMovesWhiteKnight1.contains("f3"));
	    assertTrue(legalMovesWhiteKnight1.contains("e2"));
	    
	    ArrayList<String> legalMovesBlackKnight2 = blackKnight2.legalMoves();
	    assertEquals(2, legalMovesBlackKnight2.size());
	    assertTrue(legalMovesBlackKnight2.contains("b3"));
	    assertTrue(legalMovesBlackKnight2.contains("c2"));
	    
	    ArrayList<String> legalMovesWhiteKnight2 = whiteKnight2.legalMoves();
	    assertEquals(3, legalMovesWhiteKnight2.size());
	    assertTrue(legalMovesWhiteKnight2.contains("a3"));
	    assertTrue(legalMovesWhiteKnight2.contains("c3"));
	    assertTrue(legalMovesWhiteKnight2.contains("d2"));
	}
	
	@Test
	public void testLegalMovesPortal() {
		ChessBoard testBoard = new ChessBoard();
		
	    Knight blackKnight1 = new Knight(testBoard, ChessPiece.Color.BLACK);
	    
	    testBoard.placePiece(blackKnight1, "d4");
	    testBoard.setPortalLocation(ChessPiece.Color.BLACK, "b5");
	    testBoard.setPortalLocation(ChessPiece.Color.WHITE, "a8");
	    	   	    
	    ArrayList<String> legalMovesBlackKnight1 = blackKnight1.legalMoves();
	    assertEquals(8, legalMovesBlackKnight1.size());
	    assertTrue(legalMovesBlackKnight1.contains("c2"));
	    assertTrue(legalMovesBlackKnight1.contains("b3"));
	    assertTrue(legalMovesBlackKnight1.contains("a8"));
	    assertTrue(legalMovesBlackKnight1.contains("c6"));
	    assertTrue(legalMovesBlackKnight1.contains("e6"));
	    assertTrue(legalMovesBlackKnight1.contains("f5"));
	    assertTrue(legalMovesBlackKnight1.contains("f3"));
	    assertTrue(legalMovesBlackKnight1.contains("e2"));
	}

	@Test
	public void KnightTestEmpty() {
		ChessBoard myBoard=new ChessBoard();
		Knight myKnight=new Knight(myBoard, ChessPiece.Color.WHITE);
		myBoard.placePiece(myKnight, "d4");
		
		assertEquals(myKnight.legalMoves().size(), 8);
		
		try {
			myBoard.move("d4", "f5");
		}catch(IllegalMoveException e) {
			fail();
		}
		
		try {
			assertEquals(myKnight, myBoard.getPiece("f5"));
		}catch(IllegalPositionException e) {
			fail();
		}
	}
	
	@Test
	public void KnightTestCapture() {
		ChessBoard myBoard=new ChessBoard();
		Knight myKnight=new Knight(myBoard, ChessPiece.Color.WHITE);
		myBoard.placePiece(myKnight, "d4");
		Knight enemyKnight=new Knight(myBoard, ChessPiece.Color.BLACK);
		myBoard.placePiece(enemyKnight, "f5");
		assertEquals(myKnight.legalMoves().size(), 8);
		try {
			myBoard.move("d4", "f5");
		}catch(IllegalMoveException e) {
			fail();
		}
		try {
			assertEquals(myKnight, myBoard.getPiece("f5"));
		}catch(IllegalPositionException e) {
			fail();
		}
	}
	
	@Test
	public void KnightTestFriendly() {
		ChessBoard myBoard=new ChessBoard();
		Knight myKnight=new Knight(myBoard, ChessPiece.Color.WHITE);
		myBoard.placePiece(myKnight, "d4");
		Knight enemyKnight=new Knight(myBoard, ChessPiece.Color.WHITE);
		myBoard.placePiece(enemyKnight, "f5");
		assertEquals(myKnight.legalMoves().size(), 7);
		try {
			myBoard.move("d4", "f5");
		}catch(IllegalMoveException e) {
			assertEquals(1, 1);
		}
		try {
			assertEquals(myKnight, myBoard.getPiece("d4"));
		}catch(IllegalPositionException e) {
			fail();
		}
	}
	@Test
	public void KnightTestInitial() {
		ChessBoard myBoard=new ChessBoard();
		Knight myKnight=new Knight(myBoard, ChessPiece.Color.WHITE);
		myBoard.placePiece(myKnight, "c1");
		assertEquals(myKnight.legalMoves().size(), 4);
		try {
			myBoard.move("c1", "d3");
		}catch(IllegalMoveException e) {
			fail();
		}
		try {
			assertEquals(myKnight, myBoard.getPiece("d3"));
		}catch(IllegalPositionException e) {
			fail();
		}
	}
	@Test
	public void testIllegalPositionException() {
		ChessBoard testBoard = new ChessBoard();
		Knight whiteKnight = new Knight(testBoard, ChessPiece.Color.WHITE);
		Knight blackKnight = new Knight(testBoard, ChessPiece.Color.BLACK);
	  	  
		assertThrows(IllegalPositionException.class, () -> whiteKnight.setPosition("G5"));
		assertThrows(IllegalPositionException.class, () -> whiteKnight.setPosition("5"));
		assertThrows(IllegalPositionException.class, () -> whiteKnight.setPosition("77"));
		assertThrows(IllegalPositionException.class, () -> whiteKnight.setPosition("z9"));
		assertThrows(IllegalPositionException.class, () -> whiteKnight.setPosition("a"));
		assertThrows(IllegalPositionException.class, () -> blackKnight.setPosition("B1"));
		assertThrows(IllegalPositionException.class, () -> blackKnight.setPosition("HH"));
		assertThrows(IllegalPositionException.class, () -> blackKnight.setPosition("2c"));
		assertThrows(IllegalPositionException.class, () -> blackKnight.setPosition("D33"));
		assertThrows(IllegalPositionException.class, () -> blackKnight.setPosition("EE4"));
	}	
}




