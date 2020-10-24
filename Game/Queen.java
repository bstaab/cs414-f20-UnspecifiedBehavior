package Game;

import java.util.ArrayList;

public class Queen extends ChessPiece{

	public Queen(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if (this.color == Color.WHITE) {
			return "\u2655";
		} else {
			return "\u265B";
		}
	}

	@Override
	// The queen can be moved any number of unoccupied squares 
	// in a straight line vertically, horizontally
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

}
