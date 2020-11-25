package com.mrpowers;

import com.mrpowers.ChessBoard;
import com.mrpowers.ChessPiece;
import com.mrpowers.IllegalPositionException;

import java.util.ArrayList;

public class Rook extends ChessPiece {

	public Rook(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if (this.color == Color.WHITE) {
			return "\u2656";
		} else {
			return "\u265C";
		}
	}

	public ArrayList<String> legalMoves() {
		ArrayList<String> moves = new ArrayList<String>();

		String position = getPosition();
		int row = this.row + 1;
		int newRow = row;
		char col = position.charAt(0);
		char newColumn;
		String move = "";
		
		ChessPiece piece;
		Color attackColor = Color.BLACK;
		if (color == Color.BLACK) {
			attackColor = Color.WHITE;			
		}
		
		// Get location of portals
		String whitePortalLocation = board.getPortalLocation(Color.WHITE);
		String blackPortalLocation = board.getPortalLocation(Color.BLACK);
		ChessPiece onWhitePortal = null;
		ChessPiece onBlackPortal = null;
		
		try{
			// Get the piece on top of the portals
			if (onBoard(whitePortalLocation)) {
				onWhitePortal = board.getPiece(whitePortalLocation);
			}
			if (onBoard(blackPortalLocation)) {
				onBlackPortal = board.getPiece(blackPortalLocation);
			}
			// Move down and up
			for(int moveDir = -1; moveDir <= 1; ++moveDir) {
				if (moveDir == 0) {
					continue;
				}
				row = this.row + 1;
				newRow = row + moveDir;
				col = position.charAt(0);
				move = "" + col + newRow;
				while(onBoard(move)) {
					piece = board.getPiece(move);
					if(piece == null) {
						if (move.equals(whitePortalLocation)) {
							if (onBlackPortal == null) {
								moves.add(blackPortalLocation);
							} else {
								if (onBlackPortal.color == attackColor) {
									moves.add(blackPortalLocation);
								} else {
									moves.add(move);
								}
								break;
							} 
						} else if (move.equals(blackPortalLocation)) {
							if (onWhitePortal == null) {
								moves.add(whitePortalLocation);
							} else {
								if (onWhitePortal.color == attackColor) {
									moves.add(whitePortalLocation);
								} else {
									moves.add(move);
								}
								break;
							}
						} else {
							moves.add(move);
						}
					} else {
						if (piece.color == attackColor){
							moves.add(move);
						}
						break;
					}
					
					move = moves.get(moves.size() - 1);
	 				col = move.charAt(0);
	 				newRow = (move.charAt(1) - '0') + moveDir;
	 				move = "" + col + newRow;
				}
			}
			
			// Move down and up
			row = this.row + 1;
			for(int moveDir = -1; moveDir <= 1; ++moveDir) {
				if (moveDir == 0) {
					continue;
				}
				row = this.row + 1;
				col = position.charAt(0);
				newColumn = (char)(col + moveDir);
				move = "" + newColumn + row;
				while(onBoard(move)) {
					piece = board.getPiece(move);
					if(piece == null) {
						if (move.equals(whitePortalLocation)) {
							if (onBlackPortal == null) {
								moves.add(blackPortalLocation);
							} else {
								if (onBlackPortal.color == attackColor) {
									moves.add(blackPortalLocation);
								} else {
									moves.add(move);
								}
								break;
							} 
						} else if (move.equals(blackPortalLocation)) {
							if (onWhitePortal == null) {
								moves.add(whitePortalLocation);
							} else {
								if (onWhitePortal.color == attackColor) {
									moves.add(whitePortalLocation);
								} else {
									moves.add(move);
								}
								break;
							}
						} else {
							moves.add(move);
						}
					} else {
						if (piece.color == attackColor){
							moves.add(move);
						}
						break;
					}
					move = moves.get(moves.size() - 1);
					newRow = move.charAt(1);
					col = move.charAt(0);
					newColumn = (char)(col + moveDir);
					move = "" + newColumn + row;
				}
			}
		}catch(IllegalPositionException e) {
			System.out.println("Invalid position for a Rook");
		}
		
		return moves;
	}

}
