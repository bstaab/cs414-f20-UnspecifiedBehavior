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
				if(!(board.getPiece(move) instanceof Portal)) {
					moves.add(move);
				}
				
				newRow -= 1;
				move = "" + col + newRow;
				
				// restrict to one move for now
				break;
			}
						
			// Move up
			newRow = row + 1;
			move = "" + col + newRow;
			while(onBoard(move)) {	
				if(!(board.getPiece(move) instanceof Portal)) {
					moves.add(move);
				}
				
				newRow += 1;
				move = "" + col + newRow;
				
				// restrict to one move for
				break;
			}
			
			// Move left
			newCol	 = (char) (col - 1);
			move = "" + newCol + row;
			while(onBoard(move)) {
				if(!(board.getPiece(move) instanceof Portal)) {
					moves.add(move);
				}
				
				newCol -= 1;
				move = "" + newCol + row;
				
				// restrict to one move for
				break;
			}
			
			// Move right
			col = position.charAt(0);
			newCol = (char) (col + 1);
			move = "" + newCol + row;
			while(onBoard(move)) {
				if(!(board.getPiece(move) instanceof Portal)) {
					moves.add(move);
				}
				
				newCol += 1;
				move = "" + newCol + row;
				
				// restrict to one move for
				break;
			}
			
			// Move lower-left
			col = position.charAt(0);
			newCol = (char) (col - 1);
			newRow = row - 1;
			move = "" + newCol + newRow;
			while(onBoard(move)) {
				if(!(board.getPiece(move) instanceof Portal)) {
					moves.add(move);
				}
				
				newCol -= 1;
				newRow -= 1;
				move = "" + newCol + newRow;
				
				// restrict to one move for
				break;
			}
			
			// Move lower-right
			col = position.charAt(0);
			newCol = (char) (col + 1);
			newRow = row - 1;
			move = "" + newCol + newRow;
			while(onBoard(move)) {
				if(!(board.getPiece(move) instanceof Portal)) {
					moves.add(move);
				}
				
				newCol += 1;
				newRow -= 1;
				move = "" + newCol + newRow;
				
				// restrict to one move for
				break;
			}
			
			// Move upper-right
			col = position.charAt(0);
			newCol = (char) (col + 1);
			newRow = row + 1;
			move = "" + newCol + newRow;
			while(onBoard(move)) {
				if(!(board.getPiece(move) instanceof Portal)) {
					moves.add(move);
				}
				
				newCol += 1;
				newRow += 1;
				move = "" + newCol + newRow;
				
				// restrict to one move for
				break;
			}
			
			// Move upper-left
			col = position.charAt(0);
			newCol = (char) (col - 1);
			newRow = row + 1;
			move = "" + newCol + newRow;
			while(onBoard(move)) {
				if(!(board.getPiece(move) instanceof Portal)) {
					moves.add(move);
				}
				
				newCol -= 1;
				newRow += 1;
				move = "" + newCol + newRow;
				
				// restrict to one move for
				break;
			}
			
		}catch(IllegalPositionException e) {
			System.out.println("Invalid position for a Rook");
		}
		
		return moves;
	}

}

