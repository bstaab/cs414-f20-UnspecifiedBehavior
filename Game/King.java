package Game;

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
		
		try{
			// Check the right side
			newColumn = (char) (col + 1);
			for (int i = row - 1 ; i <= row + 1; ++i) {
				move = "" + newColumn + i;
				if(onBoard(move) && board.getPiece(move) == null || this.color != board.getPiece(move).color) {
					if (!willBeAttacked(position, move)) {
						moves.add(move);
					}
				}
			}
			
			// Check the left side
			newColumn = (char) (col - 1);
			for (int i = row - 1 ; i <= row + 1; ++i) {
				move = "" + newColumn + i;
				if(onBoard(move) && board.getPiece(move) == null || this.color != board.getPiece(move).color) {
					if (!willBeAttacked(position, move)) {
						moves.add(move);
					} 
				}
			}
						
			// Check up
			move = "" + col + (row + 1);
			if(onBoard(move) && board.getPiece(move) == null || this.color != board.getPiece(move).color ) {
				if (!willBeAttacked(position, move)) {
					moves.add(move);
				}
			}
			
			// Check down
			move = "" + col + (row - 1);
			if(onBoard(move) && board.getPiece(move) == null || this.color != board.getPiece(move).color) {
				if (!willBeAttacked(position, move)) {
					moves.add(move);
				}
			}
		
		} catch (IllegalPositionException e) 
		{
			System.out.println("Illegal position for a King");
		}

		return moves;
	}

	private boolean onBoard(String position) {
		if (position.length() == 2  &&
			Character.isLetter(position.charAt(0)) && 
			Character.isDigit(position.charAt(1))) {
			
			char column = position.charAt(0);
			int row = Integer.valueOf(position.charAt(1) + "");
			if (row >= 1 && row <= 8) {
				if(column >= 'a' && column <= 'h') {
					if (position.length() == 2) {
						return true;
					}
				}
			}
		}
		return false;
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

