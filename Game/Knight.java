package a2;

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
		// Per A2 instructions, Knights cannot move
		ArrayList<String> moves = new ArrayList<String>(); 
		return moves;
	}
}

