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
		int[] two_arr= {2, -2};
		int[] one_arr= {1, -1};
		String my_position=this.getPosition();
		String nextMove="";
		for(int i=0;i<2;i++){
			for(int j=0;j<2;j++) {
				nextMove=""+(char)(my_position.charAt(0)+two_arr[i])+(char)(my_position.charAt(1)+one_arr[j]);
				moves=checkMove(moves, nextMove);
				nextMove=""+(char)(my_position.charAt(0)+one_arr[j])+(char)(my_position.charAt(1)+two_arr[i]);
				moves=checkMove(moves, nextMove);
			}
		}
		return moves;
	}

	private ArrayList<String> checkMove(ArrayList<String> legalMoves, String nextMove){
		ChessPiece nextPiece;
		if(this.validateNextMove(nextMove)) {
			try {
				nextPiece=board.getPiece(nextMove);
				if(nextPiece==null) {
					legalMoves.add(nextMove);
				}
				else if(nextPiece.color!=this.color) {
					legalMoves.add(nextMove);
				}
			} catch (IllegalPositionException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		return legalMoves;
	}
}

