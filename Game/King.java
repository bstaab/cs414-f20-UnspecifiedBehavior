package Game;

import java.util.ArrayList;

public class King extends ChessPiece {

	public King(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if (this.color== Color.WHITE) {
			return "\u2654";
		} else {
			return "\u265A";
		}
	}

	@Override
	public ArrayList<String> legalMoves() {
		ArrayList<String> moves = new ArrayList<String>();
		
		String position = getPosition();
		int row = this.row + 1;
		char col = position.charAt(0);
		char newColumn;
		String move = "";
		
		try{
			// Check the right side
			newColumn = (char) (col + 1);
			for (int i = row - 1 ; i <= row + 1; ++i) {
				move = "" + newColumn + i;
				if(onBoard(move) && board.getPiece(move) == null || this.color != board.getPiece(move).color) {
					moves.add(move);
				}
			}
			
			// Check the left side
			newColumn = (char) (col - 1);
			for (int i = row - 1 ; i <= row + 1; ++i) {
				move = "" + newColumn + i;
				if(onBoard(move) && board.getPiece(move) == null || this.color != board.getPiece(move).color) {
					moves.add(move);
				}
			}
						
			// Check up
			move = "" + col + (row + 1);
			if(onBoard(move) && board.getPiece(move) == null || this.color != board.getPiece(move).color ) {
				moves.add(move);
			}
			
			// Check down
			move = "" + col + (row - 1);
			if(onBoard(move) && board.getPiece(move) == null || this.color != board.getPiece(move).color) {
				moves.add(move);
			}
		
		} catch (IllegalPositionException e) 
		{
			System.out.println("Illegal position for a King");
		}

		return moves;
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
}

