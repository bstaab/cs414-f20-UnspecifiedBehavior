package Game;

import java.util.ArrayList;

public class Portal extends ChessPiece {

	public Portal(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if (this.color== Color.WHITE) {
			return "\u26AA";
		} else {
			return "\u26AB";
		}
	}

	@Override
	public ArrayList<String> legalMoves() {
		ArrayList<String> moves = new ArrayList<String>();

		String position = getPosition();
		int row = this.row + 1;
		char col = position.charAt(0);
		String move = "";
		int newRow = row;
		char newCol;
		
		try{
			// Move down
			newRow = row - 1;
			move = "" + col + newRow;
			while(onBoard(move)) {
				if(board.getPiece(move) == null) {
					moves.add(move);
				} else {
					if(color != board.getPiece(move).color) {
						moves.add(move);
					} 
					break;
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
					if(color != board.getPiece(move).color)	{
						moves.add(move);
					} 					
					break;
				}
				newRow += 1;
				move = "" + col + newRow;
			}
			
			// Move left
			newCol	 = (char) (col - 1);
			move = "" + newCol + row;
			while(onBoard(move)) {
				if(board.getPiece(move) == null)
				{
					moves.add(move);
				} else {
					if(color != board.getPiece(move).color)	{
						moves.add(move);
					} 					
					break;
				}
				newCol -= 1;
				move = "" + newCol + row;
			}
			
			// Move right
			col = position.charAt(0);
			newCol = (char) (col + 1);
			move = "" + newCol + row;
			while(onBoard(move)) {
				if(board.getPiece(move) == null)
				{
					moves.add(move);
				} else {
					if(color != board.getPiece(move).color)	{
						moves.add(move);
					} 					
					break;
				}
				newCol += 1;
				move = "" + newCol + row;
			}
			
			// Move lower-left
			col = position.charAt(0);
			newCol = (char) (col - 1);
			newRow = row - 1;
			move = "" + newCol + newRow;
			while(onBoard(move)) {
				if(board.getPiece(move) == null)
				{
					moves.add(move);
				} else {
					if(color != board.getPiece(move).color)	{
						moves.add(move);
					} 					
					break;
				}
				newCol -= 1;
				newRow -= 1;
				move = "" + newCol + newRow;
			}
			
			// Move lower-right
			col = position.charAt(0);
			newCol = (char) (col + 1);
			newRow = row - 1;
			move = "" + newCol + newRow;
			while(onBoard(move)) {
				if(board.getPiece(move) == null)
				{
					moves.add(move);
				} else {
					if(color != board.getPiece(move).color)	{
						moves.add(move);
					} 					
					break;
				}
				newCol += 1;
				newRow -= 1;
				move = "" + newCol + newRow;
			}
			
			// Move upper-right
			col = position.charAt(0);
			newCol = (char) (col + 1);
			newRow = row + 1;
			move = "" + newCol + newRow;
			while(onBoard(move)) {
				if(board.getPiece(move) == null)
				{
					moves.add(move);
				} else {
					if(color != board.getPiece(move).color)	{
						moves.add(move);
					} 					
					break;
				}
				newCol += 1;
				newRow += 1;
				move = "" + newCol + newRow;
			}
			
			// Move upper-left
			col = position.charAt(0);
			newCol = (char) (col - 1);
			newRow = row + 1;
			move = "" + newCol + newRow;
			while(onBoard(move)) {
				if(board.getPiece(move) == null)
				{
					moves.add(move);
				} else {
					if(color != board.getPiece(move).color)	{
						moves.add(move);
					} 					
					break;
				}
				newCol -= 1;
				newRow += 1;
				move = "" + newCol + newRow;
			}
			
		}catch(IllegalPositionException e) {
			System.out.println("Invalid position for a Rook");
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

