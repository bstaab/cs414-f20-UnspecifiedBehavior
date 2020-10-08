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
	public ArrayList<String> legalMoves() {
		// Per A2 instructions, Queens cannot move
		ArrayList<String> moves= new ArrayList<String>(); 
		return moves;
	}

}
