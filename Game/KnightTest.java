package Game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class KnightTest {
/*
	@Test
	public void testLegalMoves() {
		ChessBoard testBoard = new ChessBoard();
		
	    Knight blackKnight1 = new Knight(testBoard, ChessPiece.Color.BLACK);
	    Knight blackKnight2 = new Knight(testBoard, ChessPiece.Color.BLACK);
	    Knight whiteKnight1 = new Knight(testBoard, ChessPiece.Color.WHITE);
	    Knight whiteKnight2 = new Knight(testBoard, ChessPiece.Color.WHITE);
	    
	    testBoard.placePiece(blackKnight2, "a1");
	    testBoard.placePiece(whiteKnight2, "b1");
	    testBoard.placePiece(blackKnight1, "b8");
	    testBoard.placePiece(whiteKnight1, "d4");
	    	    
	    assertTrue(blackKnight1.legalMoves().isEmpty());
	    assertTrue(blackKnight2.legalMoves().isEmpty());
	    assertTrue(whiteKnight1.legalMoves().isEmpty());
	    assertTrue(whiteKnight2.legalMoves().isEmpty());
	}
*/
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




