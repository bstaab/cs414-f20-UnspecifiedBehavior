package com.mrpowers.chess;

import com.mrpowers.exceptions.IllegalPositionException;

import java.util.ArrayList;

public class King extends ChessPiece {

	public King(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if (this.color== Color.WHITE) {
			return "\u2654";
		} else {
			return "\u265A";
		}
	}

	@Override
	public ArrayList<String> legalMoves() {
		ArrayList<String> moves = new ArrayList<String>();
		
		String position = getPosition();
		int row = this.row + 1;
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
			
			
			// Check the right side
			newColumn = (char) (col + 1);
			for (int rowOffset = row - 1 ; rowOffset <= row + 1; ++rowOffset) {
				for(int colOffset = - 1; colOffset <= 1; ++colOffset) {
					newColumn = (char) (col + colOffset);
					move = "" + newColumn + rowOffset;
					
					if (move.equals(position)) {
						continue;
					}
					
					if(onBoard(move)) {
						piece = board.getPiece(move);
						if(piece == null) {
							if (move.equals(whitePortalLocation)) {
								if (onBlackPortal == null || onBlackPortal.color == attackColor) {
									if (!willBeAttacked(position, blackPortalLocation)) {
										moves.add(blackPortalLocation);
									}
								} else {
									if (!willBeAttacked(position, move)) {
										moves.add(move);
									}
								}
							} else if (move.equals(blackPortalLocation)) {
								if (onWhitePortal == null || onWhitePortal.color == attackColor) {
									if (!willBeAttacked(position, whitePortalLocation)) {
										moves.add(whitePortalLocation);
									}
								} else {
									if (!willBeAttacked(position, move)) {
										moves.add(move);
									}
								}
							} else {
								if (!willBeAttacked(position, move)) {
									moves.add(move);
								}
							}
						} else if (piece.color == attackColor){
							if (!willBeAttacked(position, move)) {
								moves.add(move);
							}
						}					
					}
				}
			}
		} catch (IllegalPositionException e)
		{
			System.out.println("Illegal position for a King");
		}
		
		return moves;
	}


	// Function to see if the king will be attacked by an enemy piece
	// at the new location
	private boolean willBeAttacked(String currentPosition, String newPosition) {
		char col = newPosition.charAt(0);
		int row = Integer.valueOf(newPosition.charAt(1) + "");
		
		char newCol;
		int  newRow;
		String move;
		Color attackColor = (this.color == Color.WHITE) ? Color.BLACK : Color.WHITE;
		int count;
		ChessPiece piece;
					
		// Check left
		newCol = (char) (col - 1);
		move = "" + newCol + row;
		count = 1;
		while(onBoard(move)) {
			try {
				if (!move.equals(currentPosition)) {
					piece = board.getPiece(move);
					if(piece != null) {
						if (piece.color == attackColor) {
	 						if (piece instanceof Rook || piece instanceof Queen ||
	 							(count == 1 && piece instanceof King)) {
	 							return true;
	 						}
						}
						break;
					}
				}
			} catch (IllegalPositionException e) {

			}
			
			count += 1;
			newCol = (char) (newCol - 1);
			move = "" + newCol + row;
		}
		
		// Check right
		newCol = (char) (col + 1);
		move = "" + newCol + row;
		count = 1;
		while(onBoard(move)) {
			try {
				if (!move.equals(currentPosition)) {
					piece = board.getPiece(move);
					if(piece != null) {
						if (piece.color == attackColor) {
							if (piece instanceof Rook || piece instanceof Queen ||
								(count == 1 && piece instanceof King)) {
								return true;
							}
						}
						break;
					}
				}
			} catch (IllegalPositionException e) {

			}
			
			count += 1;	
			newCol = (char) (newCol + 1);
			move = "" + newCol + row;
		}
		
		// Check up
		newRow = row + 1;
		move = "" + col + newRow;
		count = 1;
		while(onBoard(move)) {
			try {
				if (!move.equals(currentPosition)) {
					piece = board.getPiece(move);
					if(piece != null) {
						if (piece.color == attackColor) {
							if (piece instanceof Rook || piece instanceof Queen) {
								return true;
							}
							if (count == 1 && piece instanceof King) {
									return true;
							}
							if (count <= 2 && attackColor == Color.BLACK && piece instanceof Pawn) {
								return true;
							}
						}
						break;
					}
				}
			} catch (IllegalPositionException e) {
				
			}
			
			count += 1;	
			newRow += 1;
			move = "" + col + newRow;
		}
		
		// Check down
		newRow = row - 1;
		move = "" + col + newRow;
		count = 1;
		while(onBoard(move)) {
			try {
				if (!move.equals(currentPosition)) {
					piece = board.getPiece(move);
					if(piece != null) {
						if (piece.color == attackColor) {
							if (piece instanceof Rook || piece instanceof Queen) {
								return true;
							}
							if (count == 1 && piece instanceof King) {
									return true;
							}
							if (count <= 2 && attackColor == Color.WHITE && piece instanceof Pawn) {
								return true;
							}
						}
						break;
					}
				}
			} catch (IllegalPositionException e) {
			
			}
			
			count += 1;	
			newRow -= 1;
			move = "" + col + newRow;
		}
			
		// Check diagonals lower left 
		newRow = row - 1;
		newCol = (char) (col - 1);
		move = "" + newCol + newRow;
		count = 1;
		while(onBoard(move)) {
			try {
				if (!move.equals(currentPosition)) {
					piece = board.getPiece(move);
					if(piece != null) {
						if (piece.color == attackColor) {
							if (piece instanceof Bishop || piece instanceof Queen) {
								return true;
							}
							if (count == 1 && piece instanceof King) {
									return true;
							}
							if (count == 1 && attackColor == Color.WHITE && piece instanceof Pawn) {
								return true;
							}
						}
						break;
					}
				}
			} catch (IllegalPositionException e) {
				
			}
			
			count += 1;
			newRow -= 1;
			newCol = (char) (newCol - 1);
			move = "" + newCol + newRow;
		}
			
		// Check diagonals lower right 
		newRow = row - 1;
		newCol = (char) (col + 1);
		move = "" + newCol + newRow;
		count = 1;
		while(onBoard(move)) {
			try {
				if (!move.equals(currentPosition)) {
					piece = board.getPiece(move);
					if(piece != null) {
						if (piece.color == attackColor) {
							if (piece instanceof Bishop || piece instanceof Queen) {
								return true;
							}
							if (count == 1 && piece instanceof King) {
									return true;
							}
							if (count == 1 && attackColor == Color.WHITE && piece instanceof Pawn) {
								return true;
							}
						}
						break;
					}
				}
			} catch (IllegalPositionException e) {
				
			}
			
			count += 1;
			newRow -= 1;
			newCol = (char) (newCol + 1);
			move = "" + newCol + newRow;
		}
		
		// Check diagonals upper right 
		newRow = row + 1;
		newCol = (char) (col + 1);
		move = "" + newCol + newRow;
		count = 1;
		while(onBoard(move)) {
			try {
				if (!move.equals(currentPosition)) {
					piece = board.getPiece(move);
					if(piece != null) {
						if (piece.color == attackColor) {
							if (piece instanceof Bishop || piece instanceof Queen) {
								return true;
							}
							if (count == 1 && piece instanceof King) {
									return true;
							}
							if (count == 1 && attackColor == Color.BLACK && piece instanceof Pawn) {
								return true;
							}
						}
						break;
					}
				}
			} catch (IllegalPositionException e) {
				
			}
			
			count += 1;
			newRow += 1;
			newCol = (char) (newCol + 1);
			move = "" + newCol + newRow;
		}
			
		// Check diagonals upper left 
		newRow = row + 1;
		newCol = (char) (col - 1);
		move = "" + newCol + newRow;
		count = 1;
		while(onBoard(move)) {
			try {
				if (!move.equals(currentPosition)) {
					piece = board.getPiece(move);
					if(piece != null) {
						if (piece.color == attackColor) {
							if (piece instanceof Bishop || piece instanceof Queen) {
								return true;
							}
							if (count == 1 && piece instanceof King) {
									return true;
							}
							if (count == 1 && attackColor == Color.BLACK && piece instanceof Pawn) {
								return true;
							}
						}
						break;
					}
				}
			} catch (IllegalPositionException e) {
				
			}
			
			count += 1;
			newRow += 1;
			newCol = (char) (newCol - 1);
			move = "" + newCol + newRow;
		}	
		
		// Check for knights
		try {
			move = "" + (char) (col - 2) + (row + 1);
			if (!move.equals(currentPosition)) {
				if (onBoard(move)) {
					piece = board.getPiece(move);
					if (piece != null && piece.color == attackColor && piece instanceof Knight) {
						return true;
					}
				}
			}
			
			move = "" + (char) (col - 2) + (row - 1);
			if (!move.equals(currentPosition)) {
				if (onBoard(move)) {
					piece = board.getPiece(move);
					if (piece != null && piece.color == attackColor && piece instanceof Knight) {
							return true;
					}
				}
			}
			
			move = "" + (char) (col - 1) + (row - 2);
			if (!move.equals(currentPosition)) {
				if (onBoard(move)) {
					piece = board.getPiece(move);
					if (piece != null && piece.color == attackColor && piece instanceof Knight) {
							return true;
					}
				}
			}
			
			move = "" + (char) (col - 1) + (row + 2);
			if (!move.equals(currentPosition)) {
				if (onBoard(move)) {
					piece = board.getPiece(move);
					if (piece != null && piece.color == attackColor && piece instanceof Knight) {
							return true;
					}
				}
			}
			
			move = "" + (char) (col + 1) + (row + 2);
			if (!move.equals(currentPosition)) {
				if (onBoard(move)) {
					piece = board.getPiece(move);
					if (piece != null && piece.color == attackColor && piece instanceof Knight) {
							return true;
					}
				}
			}
			
			move = "" + (char) (col + 1) + (row - 2);
			if (!move.equals(currentPosition)) {
				if (onBoard(move)) {
					piece = board.getPiece(move);
					if (piece != null && piece.color == attackColor && piece instanceof Knight) {
							return true;
					}
				}
			}
			
			move = "" + (char) (col + 2) + (row - 1);
			if (!move.equals(currentPosition)) {
				if (onBoard(move)) {
					piece = board.getPiece(move);
					if (piece != null && piece.color == attackColor && piece instanceof Knight) {
							return true;
					}
				}
			}
			
			move = "" + (char) (col + 2) + (row + 1);
			if (!move.equals(currentPosition)) {
				if (onBoard(move)) {
					piece = board.getPiece(move);
					if (piece != null && piece.color == attackColor && piece instanceof Knight) {
							return true;
					}
				}
			}
		} catch (IllegalPositionException e) {
				
		}
		
		return false;
	}
}

