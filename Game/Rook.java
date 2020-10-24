package Game;

import java.util.ArrayList;

public class Rook extends ChessPiece {

	public Rook(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if (this.color == Color.WHITE) {
			return "\u2656";
		} else {
			return "\u265C";
		}
	}

	public ArrayList<String> legalMoves() {
		ArrayList<String> moves = new ArrayList<String>();

		String position = getPosition();
		int row = this.row + 1;
		char col = position.charAt(0);
		String move = "";
		int newRow = row;
		
		try{
			// Move down
			newRow = row - 1;
			move = "" + col + newRow;
			while(onBoard(move)) {
				if(board.getPiece(move) == null) {
					moves.add(move);
				} else {
					if(color == board.getPiece(move).color) {
						break;
					} else {
						moves.add(move);
						break;
					}
				}
				newRow -= 1;
				move = "" + col + newRow;
			}
						
			// Move up
			newRow = row + 1;
			move = "" + col + newRow;
			while(onBoard(move)) {	
				if(board.getPiece(move) == null)
				{
					moves.add(move);
				} else {
					if(color == board.getPiece(move).color)	{
						break;
					} else {
						moves.add(move);
						break;
					}
				}
				newRow += 1;
				move = "" + col + newRow;
			}
			
			// Move left
			row = this.row + 1;
			col	 = position.charAt(0);
			while(true) {
				col -= 1;
				if(col == 'a' - 1) {
					break;
				}
				
				move = "" + col + row;
				if (board.getPiece(move) == null ) {
					moves.add(move);
				}
				
				if (board.getPiece(move) != null )	{
					if(color == board.getPiece(move).color) {
						break;
					} else {
						moves.add(move);
						break;
					}
				}
			}
			
			// Move right
			row = this.row + 1;
			col	 = position.charAt(0);
			while(true) {
				col += 1;
				if(col > 'h') {
					break;
				}
				
				move ="" + col + row;
				if(board.getPiece(move) == null ) {
					moves.add(move);
				} else {
					if(color == board.getPiece(move).color) {
						break;
					} else {
						moves.add(move);
						break;
					}
				}
			}
		}catch(IllegalPositionException e) {
			System.out.println("Invalid position for a Rook");
		}
		
		return moves;
	}

}
