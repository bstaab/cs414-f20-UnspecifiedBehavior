package a2;

import java.util.ArrayList;

public class Bishop extends ChessPiece {

	public Bishop(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if (this.color == Color.WHITE) {
			return "\u2657";
		} else {
			return "\u265D";
		}
	}

	@Override
	public ArrayList<String> legalMoves() {
		ArrayList<String> moves = new ArrayList<String>();
		
		String position = getPosition();
		int row = this.row + 1;
		char col = position.charAt(0);
		String move = "";

		try{
			// Move up and left
			row = this.row + 1;
			col	 = (char) (position.charAt(0));
			while(true) {
				row += 1;
				col = (char) (col - 1);
				
				if(row > 8 || col < 'a') {
					break;
				}
				
				move ="" + col + row;
				if(board.getPiece(move) == null ) {
					moves.add(move);
				} else  {
					if(color == board.getPiece(move).color) {
						break;
					}
					if(color != board.getPiece(move).color)	{
						moves.add(move);
						break;
					}	
				}
			}
			
			// Move up and right
			row = this.row + 1;
			col	 = (char) (position.charAt(0));
			while(true){
				row += 1;
				col = (char) (col + 1);
				if(row > 8 || col > 'h') {
					break;
				}
				
				move ="" + col + row;
				if(board.getPiece(move) == null)
				{
					moves.add(move);
				} else {
					if(color == board.getPiece(move).color)	{
						break;
					}
					if(color != board.getPiece(move).color) {
						moves.add(move);
						break;
					}
				}
			}

			// Move down and left
			row = this.row + 1;
			col	= (position.charAt(0));
			while(true) {
				row -= 1;
				col = (char) (col - 1);
				
				if(row < 1 || col < 'a') {
					break;
				}
				
				move ="" + col + row;
				if(board.getPiece(move) == null ) {
					moves.add(move);
				} else {
					if(color == board.getPiece(move).color) {
						break;
					} else  {
						moves.add(move);
						break;
					}	
				}
			}
			
			// Move down and right
			row = this.row + 1;
			col	 = (char) (position.charAt(0));
			while(true) {
				row -= 1;
				col = (char) (col + 1);
				if(row < 1 || col > 'h') {
					break;
				}
				 
				move ="" + col + row;
				if(board.getPiece(move) == null ) {
					moves.add(move);
				} else {
					if(color == board.getPiece(move).color) {
						break;
					}
					if(color != board.getPiece(move).color) {
						moves.add(move);
						break;
					}	
				}
			}
						
		} catch (IllegalPositionException e) {
			System.out.println("Illegal position for a Bishop");
		}
		
		return moves;
	}
}
