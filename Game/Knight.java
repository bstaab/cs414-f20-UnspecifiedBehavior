package Game;

import java.util.ArrayList;

public class Knight extends ChessPiece {

	public Knight(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		Color color = this.color;
		if (color == Color.WHITE) {
			return "\u2658";
		} else {
			return "\u265E";
		}
	}
	@Override
	public ArrayList<String> legalMoves() {
		ArrayList<String> moves = new ArrayList<String>();
				
		String position = getPosition();
		int row = this.row + 1;
		char col = position.charAt(0);
		String move = "";
		
		int[] two_arr= {2, -2};
		int[] one_arr= {1, -1};
		
		for(int i=0;i<2;i++){
			for(int j=0;j<2;j++) {
				move=""+(char)(col + two_arr[i]) + (row + one_arr[j]);
				moves=checkMove(moves, move);
				move=""+(char)(col + one_arr[j]) + (row + two_arr[i]);
				moves=checkMove(moves, move);
			}
		}
		return moves;
	}

	private ArrayList<String> checkMove(ArrayList<String> legalMoves, String move){
		// Get location of portals
		String whitePortalLocation = board.getPortalLocation(Color.WHITE);
		String blackPortalLocation = board.getPortalLocation(Color.BLACK);
		ChessPiece onWhitePortal = null;
		ChessPiece onBlackPortal = null;
		
		ChessPiece piece;
		Color attackColor = Color.BLACK;
		if (color == Color.BLACK) {
		attackColor = Color.WHITE;			
		}
		
		try{
			// Get the piece on top of the portals
			if (onBoard(whitePortalLocation)) {
				onWhitePortal = board.getPiece(whitePortalLocation);
			}
			if (onBoard(blackPortalLocation)) {
				onBlackPortal = board.getPiece(blackPortalLocation);
			}
		
			if(onBoard(move)) {
				piece = board.getPiece(move);
				if(piece == null) {
					if (move.equals(whitePortalLocation)) {
						if (onBlackPortal == null || onBlackPortal.color == attackColor) {
							legalMoves.add(blackPortalLocation);
						} else {
							legalMoves.add(move);
						}
					} else if (move.equals(blackPortalLocation)) {
						if (onWhitePortal == null || onWhitePortal.color == attackColor) {
							legalMoves.add(whitePortalLocation);
						} else {
							legalMoves.add(move);
						}
					} else {
						legalMoves.add(move);
					}
				} else if (piece.color == attackColor){
					legalMoves.add(move);
				}					
			}	
		} catch (IllegalPositionException e) 
		{
			System.out.println("Illegal position for a Knight");
		}
		return legalMoves;
	}
}

