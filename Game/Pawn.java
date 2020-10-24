package Game;

import java.util.ArrayList;

public class Pawn extends ChessPiece {
	
	public Pawn(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if (this.color == Color.WHITE) {
			return "\u2659";
		} else {
			return "\u265F";
		}
	}

	@Override
	public ArrayList<String> legalMoves() {
		ArrayList<String> moves =  new ArrayList<String>(); 
		
		String position = getPosition();
		int row = this.row + 1;
		char col = position.charAt(0);
		String move = "";
		int newRow = row;
		char newCol;

		try{
			// Moves for BLACK piece
			if(color == Color.BLACK) {
				// First move can be two spaces
				if(row == 7 ) {
					newRow = row - 2;
					move = "" + col + newRow;
					if(board.getPiece(move) == null) {
						moves.add(move);
					}
				}
				
				// Subsequent moves can only be one space
				newRow = row - 1;
				move = "" + col + newRow;
				if(onBoard(move) && board.getPiece(move) == null) {
					moves.add(move);
				}
				
				// Capture piece
				newRow = row - 1;
				newCol = col;
				newCol -= 1;
				move = "" + newCol + newRow;
				if(onBoard(move) && board.getPiece(move) != null) {
					if(!(position.equals("h")) && board.getPiece(move).color.equals(Color.WHITE) ) {
						moves.add(move);
					}
				}
				
				// Capture piece
				newCol = col;
				newCol += 1;
				move = "" + newCol + newRow;
				if(onBoard(move) && board.getPiece(move) != null) {
					if(position != "a" && board.getPiece(move).color.equals(Color.WHITE)) {
						moves.add(move);
					}
				}				
			}
			
			// Moves for WHITE piece
			if(color == Color.WHITE){
				// First move can be two spaces
				if(row == 2 ){
					newRow = row + 2;
					move = "" + col + newRow;
					if(onBoard(move) && board.getPiece(move) == null) {
						moves.add(move);
					}
				}
				
				// Subsequent moves can only be one space
				newRow = row + 1;
				move = "" + col + newRow;
				if(onBoard(move) && board.getPiece(move) == null) {
					moves.add(move);
				}

				// Capture piece
				newCol = col;
				newCol += 1;
				move = "" + newCol + newRow;
				if(onBoard(move) && board.getPiece(move) != null) {
					if(position != "a" && board.getPiece(move).color.equals(Color.BLACK)) {
						moves.add(move);
					}
				}
				
				// Capture piece
				newRow = row + 1;
				newCol = col;
				newCol -= 1;
				move = "" + newCol + newRow;
				if(onBoard(move) && board.getPiece(move) != null) {
					if(!(position.equals("h")) && board.getPiece(move).color.equals(Color.BLACK)) {
						moves.add(move);
					}
				}
				
			}
		}catch(IllegalPositionException e) {
			System.out.println("Illegal position for a Pawn");
		}
		
		return moves;
	}

	

}
