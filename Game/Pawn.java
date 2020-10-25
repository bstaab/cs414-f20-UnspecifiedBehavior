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

		ChessPiece piece;
		int initialRow = 2;
		int moveDir = 1;
		Color attackColor = Color.BLACK;
		if (color == Color.BLACK) {
			initialRow = 7;
			moveDir = -1;
			attackColor = Color.WHITE;			
		}
		
		// Get location of portals
		String whitePortalLocation = board.getPortalLocation(Color.WHITE);
		String blackPortalLocation = board.getPortalLocation(Color.BLACK);
		ChessPiece onWhitePortal = null;
		ChessPiece onBlackPortal = null;
		try {
			// Get the piece on top of the portals
			if (onBoard(whitePortalLocation)) {
				onWhitePortal = board.getPiece(whitePortalLocation);
			}
			if (onBoard(blackPortalLocation)) {
				onBlackPortal = board.getPiece(blackPortalLocation);
			}
			
			// Check move forward
			newRow = row + moveDir;
			move = "" + col + newRow;
			int maxMoves = (row == initialRow) ? 2 : 1;
			for (int moveIdx = 0; moveIdx < maxMoves; ++moveIdx) {
 				piece = board.getPiece(move);
 				if(piece == null) {
					if (move.equals(whitePortalLocation)) {
						if (onBlackPortal == null || onBlackPortal.color == attackColor) {
							moves.add(blackPortalLocation);
						} else {
							moves.add(move);
							break;
						}
					} else if (move.equals(blackPortalLocation)) {
						if (onWhitePortal == null || onWhitePortal.color == attackColor) {
							moves.add(whitePortalLocation);
						} else {
							moves.add(move);
							break;
						}
					} else {
						moves.add(move);
					}
				} else if (piece.color == attackColor){
					moves.add(move);
					break;
				}
 				
 				move = moves.get(moves.size() - 1);
 				newCol = move.charAt(0);
 				newRow = move.charAt(1) - '0' + moveDir;
 				move = "" + newCol + newRow;
 			}
			
			// Check to the right
			newRow = row + moveDir;
			newCol = (char) (col + 1);
			move = "" + newCol + newRow;
			piece = board.getPiece(move);
			if(piece == null) {
				if (move.equals(whitePortalLocation)) {
					if (onBlackPortal == null || onBlackPortal.color == attackColor) {
						moves.add(blackPortalLocation);
					} else {
						moves.add(move);
					}
				} else if (move.equals(blackPortalLocation)) {
					if (onWhitePortal == null || onWhitePortal.color == attackColor) {
						moves.add(whitePortalLocation);
					} else {
						moves.add(move);
					}
				} else {
					moves.add(move);
				}
			} else if (piece.color == attackColor){
				moves.add(move);
			}
			
			// Check to the left
			newRow = row + moveDir;
			newCol = (char) (col - 1);
			move = "" + newCol + newRow;
			piece = board.getPiece(move);
			if(piece == null) {
				if (move.equals(whitePortalLocation)) {
					if (onBlackPortal == null || onBlackPortal.color == attackColor) {
						moves.add(blackPortalLocation);
					} else {
						moves.add(move);
					}
				} else if (move.equals(blackPortalLocation)) {
					if (onWhitePortal == null || onWhitePortal.color == attackColor) {
						moves.add(whitePortalLocation);
					} else {
						moves.add(move);
					}
				} else {
					moves.add(move);
				}
			} else if (piece.color == attackColor){
				moves.add(move);
			}
						
		}catch(IllegalPositionException e) {
			System.out.println("Illegal position for a Pawn");
		}
		
		return moves;
	}
}
