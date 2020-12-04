package com.mrpowers.chess;

import com.mrpowers.exceptions.IllegalMoveException;
import com.mrpowers.exceptions.IllegalPositionException;

public class ChessBoard {

	private ChessPiece[][] board;
	private String whitePortal;
	private String blackPortal;
	private String fenBoard; //FEN notation of the board state
	private int totalMoves;
	
	public ChessBoard(){
		this.board = new ChessPiece[8][8];
		
		// Track the portals separately because the chess board 
		// can't track two pieces at the same location.
		this.whitePortal = "";
		this.blackPortal = "";
		this.fenBoard = "";
		this.totalMoves = 0;
	}

	public String getFenBoard() {
		return this.fenBoard;
	}

	public void initialize() {
		ChessPiece whiteRook1   = new Rook(this,   ChessPiece.Color.WHITE);
		ChessPiece whiteKnight1 = new Knight(this, ChessPiece.Color.WHITE);
		ChessPiece whiteBishop1 = new Bishop(this, ChessPiece.Color.WHITE);
		ChessPiece whiteKing    = new King(this,   ChessPiece.Color.WHITE);
		ChessPiece whiteQueen   = new Queen(this,  ChessPiece.Color.WHITE);
		ChessPiece whiteBishop2 = new Bishop(this, ChessPiece.Color.WHITE);
		ChessPiece whiteKnight2 = new Knight(this, ChessPiece.Color.WHITE);
		ChessPiece whiteRook2   = new Rook(this,   ChessPiece.Color.WHITE);
		ChessPiece whitePawn1   = new Pawn(this,   ChessPiece.Color.WHITE);
		ChessPiece whitePawn2   = new Pawn(this,   ChessPiece.Color.WHITE);
		ChessPiece whitePawn3   = new Pawn(this,   ChessPiece.Color.WHITE);
		ChessPiece whitePawn4   = new Pawn(this,   ChessPiece.Color.WHITE);
		ChessPiece whitePawn5   = new Pawn(this,   ChessPiece.Color.WHITE);
		ChessPiece whitePawn6   = new Pawn(this,   ChessPiece.Color.WHITE);
		ChessPiece whitePawn7   = new Pawn(this,   ChessPiece.Color.WHITE);
		ChessPiece whitePawn8   = new Pawn(this,   ChessPiece.Color.WHITE);
		
		ChessPiece blackRook1   = new Rook  (this, ChessPiece.Color.BLACK);
		ChessPiece blackKnight1 = new Knight(this, ChessPiece.Color.BLACK);
		ChessPiece blackBishop1 = new Bishop(this, ChessPiece.Color.BLACK);
		ChessPiece blackKing    = new King  (this, ChessPiece.Color.BLACK);
		ChessPiece blackQueen   = new Queen (this, ChessPiece.Color.BLACK);
		ChessPiece blackBishop2 = new Bishop(this, ChessPiece.Color.BLACK);
		ChessPiece blackKnight2 = new Knight(this, ChessPiece.Color.BLACK);
		ChessPiece blackRook2   = new Rook  (this, ChessPiece.Color.BLACK);
		ChessPiece blackPawn1   = new Pawn  (this, ChessPiece.Color.BLACK);
		ChessPiece blackPawn2   = new Pawn  (this, ChessPiece.Color.BLACK);
		ChessPiece blackPawn3   = new Pawn  (this, ChessPiece.Color.BLACK);
		ChessPiece blackPawn4   = new Pawn  (this, ChessPiece.Color.BLACK);
		ChessPiece blackPawn5   = new Pawn  (this, ChessPiece.Color.BLACK);
		ChessPiece blackPawn6   = new Pawn  (this, ChessPiece.Color.BLACK);
		ChessPiece blackPawn7   = new Pawn  (this, ChessPiece.Color.BLACK);
		ChessPiece blackPawn8   = new Pawn  (this, ChessPiece.Color.BLACK);

		placePiece(whiteRook1, 	"a1");
		placePiece(whiteKnight1,"b1");
		placePiece(whiteBishop1,"c1");
		placePiece(whiteKing, 	"e1");
		placePiece(whiteQueen,  "d1");
		placePiece(whiteBishop2,"f1");
		placePiece(whiteKnight2,"g1");
		placePiece(whiteRook2, 	"h1");
		placePiece(whitePawn1, 	"a2");
		placePiece(whitePawn2, 	"b2");
		placePiece(whitePawn3, 	"c2");
		placePiece(whitePawn4, 	"d2");
		placePiece(whitePawn5, 	"e2");
		placePiece(whitePawn6, 	"f2");
		placePiece(whitePawn7, 	"g2");
		placePiece(whitePawn8, 	"h2");
		
		placePiece(blackRook1, 	"a8");
		placePiece(blackKnight1,"b8");
		placePiece(blackBishop1,"c8");
		placePiece(blackKing, 	"e8");
		placePiece(blackQueen,  "d8");
		placePiece(blackBishop2,"f8");
		placePiece(blackKnight2,"g8");
		placePiece(blackRook2, 	"h8");
		placePiece(blackPawn1, 	"a7");
		placePiece(blackPawn2, 	"b7");
		placePiece(blackPawn3, 	"c7");
		placePiece(blackPawn4, 	"d7");
		placePiece(blackPawn5, 	"e7");
		placePiece(blackPawn6, 	"f7");
		placePiece(blackPawn7, 	"g7");
		placePiece(blackPawn8, 	"h7");
		
		this.whitePortal = "a4";
		this.blackPortal = "a5";

		this.fenBoard = this.toFen();
	}

	private boolean onBoard(String position) {
		if (position.length() == 2  &&
			Character.isLetter(position.charAt(0)) && 
			Character.isDigit(position.charAt(1))) {
			
			char column = position.charAt(0);
			int row = Integer.valueOf(position.charAt(1) + "");
			if (row >= 1 && row <= 8) {
				if(column >= 'a' && column <= 'h') {
					if (position.length() == 2) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	public ChessPiece getPiece(String position) throws IllegalPositionException {
		if(!onBoard(position)) {
			throw new IllegalPositionException(position);
		}

		int row = (Integer.parseInt(position.substring(1))) - 1;
		int column = position.charAt(0) - 'a';
		
		return this.board[row][column];
	}
	
	public String getPortalLocation(ChessPiece.Color color) {
		if (color == ChessPiece.Color.WHITE) {
			return this.whitePortal;
		} else {
			return this.blackPortal;
		}
	}
		
	public boolean setPortalLocation(ChessPiece.Color color, String position) {
		if (onBoard(position)) {
			if (color == ChessPiece.Color.WHITE) {
				whitePortal = position;
			} else {
				blackPortal = position;
			}
			return true;
		}
		return false;
	}
	
	public boolean placePiece(ChessPiece piece, String position) {
		try {
			if (getPiece(position) == null || piece.color != getPiece(position).color) {
				piece.setPosition(position);
				board[piece.row][piece.column] = piece;
				return true;
			}
		}catch(IllegalPositionException e ){
			System.out.println("Illegal position");
		}
		return false;
	}

	
	public void move(String fromPosition, String toPosition) throws IllegalMoveException {
		try {
			if (getPiece(fromPosition).legalMoves().contains(toPosition)) {
				int row = getPiece(fromPosition).row;
				int col = getPiece(fromPosition).column;
				
				placePiece(board[row][col], toPosition);
				board[row][col] = null;
				this.totalMoves++;
				this.fenBoard = this.toFen();
			} else {
				throw new IllegalMoveException();
			}
		}catch(IllegalPositionException e){
			throw new IllegalMoveException();
		}catch(IllegalMoveException f){
			throw new IllegalMoveException();
		}catch(NullPointerException n) {
			throw new IllegalMoveException();
		}
	}
	
	public String toString() {
	    String chess="";
	    String upperLeft = "\u250C";
	    String upperRight = "\u2510";
	    String horizontalLine = "\u2500";
	    String horizontal3 = horizontalLine + "\u3000" + horizontalLine;
	    String verticalLine = "\u2502";
	    String upperT = "\u252C";
	    String bottomLeft = "\u2514";
	    String bottomRight = "\u2518";
	    String bottomT = "\u2534";
	    String plus = "\u253C";
	    String leftT = "\u251C";
	    String rightT = "\u2524";

	    String topLine = upperLeft;
	    for (int i = 0; i < 7; i++){
	        topLine += horizontal3 + upperT;
	    }
	    topLine += horizontal3 + upperRight;

	    String bottomLine = bottomLeft;
	    for (int i = 0; i<7; i++){
	        bottomLine += horizontal3 + bottomT;
	    }
	    bottomLine += horizontal3 + bottomRight;
	    chess+=topLine + "\n";

	    for (int row = 7; row >=0; row--){
	        String midLine = "";
	        for (int col = 0; col < 8; col++){
	            if(board[row][col]==null) {
	                midLine += verticalLine + " \u3000 ";
	            } else {midLine += verticalLine + " "+board[row][col]+" ";}
	        }
	        midLine += verticalLine;
	        String midLine2 = leftT;
	        for (int i = 0; i<7; i++){
	            midLine2 += horizontal3 + plus;
	        }
	        midLine2 += horizontal3 + rightT;
	        chess+=midLine+ "\n";
	        if(row>=1)
	            chess+=midLine2+ "\n";
	    }

	    chess+=bottomLine;
	    return chess;
	}

	public String rowColToPosition(int row, int col) {
		String ret = "";

		switch (col) {
			case 0: ret += 'a';break;
			case 1: ret += 'b';break;
			case 2: ret += 'c';break;
			case 3: ret += 'd';break;
			case 4: ret += 'e';break;
			case 5: ret += 'f';break;
			case 6: ret += 'g';break;
			case 7: ret += 'h';break;
		}
		ret += Integer.toString(row + 1);
		return ret;
	}

	public String toFen() {
		String ret = "";
		for (int rank = 7; rank >= 0; rank--) {
			int empty = 0;
			for (int file = 0; file < 8; file++) {
				try {
					String position = this.rowColToPosition(rank, file);
					char piece = this.getPiece(position).getFenChar();
					if (empty > 0) {
						ret += empty;
					}
					ret += piece;
					empty = 0;
				} catch (Exception e){
					empty++;
					if (empty == 8 || file == 7) {
						ret += empty;
					}
				}
			}
			if (rank != 0) {
				ret += '/';
			}
		}

		if (this.totalMoves % 2 == 0) {
			ret += " w";
		} else {
			ret += " b";
		}

		ret += " - - - ";
		ret += this.totalMoves;

		return ret;
	}

	public static void main(String[] args) {
		ChessBoard board = new ChessBoard();
		board.initialize();
		System.out.println(board);
	}
}
