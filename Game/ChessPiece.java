package Game;

import java.util.ArrayList;

public abstract class ChessPiece {
	public enum Color {WHITE, BLACK}
	protected ChessBoard board; // The board it belongs to, default null.
	protected int row;          // The index of the horizontal rows in the range 0..7.
	protected int column;       // The index of the vertical column in the range 0..7.
	protected Color color;      // The color of the piece.
	
	public ChessPiece(ChessBoard board, Color color) {
		this.board = board;
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public String getPosition() {
		// Returns the position of the piece in a two-character string: 
		// a single letter (a..h) followed by a single digit (1..8)
		return String.format("%c%d", (char)('a' + this.column), this.row + 1);
	}
	
	public void setPosition(String position) throws IllegalPositionException {
		if (position.length() != 2) {
			throw new IllegalPositionException();
		}
		
		char col_char = position.charAt(0);
		char row_char = position.charAt(1);
		
		if (row_char < '1' || row_char > '8') {
			throw new IllegalPositionException();
		}
		
		if (col_char < 'a' || col_char > 'h') {
			throw new IllegalPositionException();
		}
				
		this.row = row_char - '1';
		this.column = col_char - 'a';
	}
	protected boolean validateNextMove(String nextMove) {
		if(nextMove.length()!=2) {
			return false;
		}
		if(nextMove.charAt(0)>=97&&nextMove.charAt(0)<=104&&nextMove.charAt(1)<=56&&nextMove.charAt(1)>=49) {
			return true;
		}
		else {
			return false;
		}
	}
	abstract public String toString();
	abstract public ArrayList<String> legalMoves();
	
}