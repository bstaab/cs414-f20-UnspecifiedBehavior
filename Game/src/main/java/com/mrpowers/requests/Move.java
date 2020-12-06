package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.chess.*;
import com.mrpowers.exceptions.IllegalMoveException;
import java.lang.*;

public class Move extends RequestData {
    private String whiteUser;
    private String blackUser;
    private String from;
    private String to;
    private Boolean valid;
    private String check;
    private String checkmate;
    private String fen;

    public Move(String whiteUser, String blackUser, String from, String to){
        this.whiteUser=whiteUser;
        this.blackUser=blackUser;
        this.from=from;
        this.to=to;
    }
    public Move(){}

    public String getFrom(){
        return from;
    }
    public String getTo(){
        return to;
    }
    public String getFen(){
        return fen;
    }

    private Boolean makePiece(char pieceChar, ChessBoard board, int row, int col){
        row=7-row;
        ChessPiece piece;
        if(Character.isUpperCase(pieceChar)){
            switch(pieceChar){
                case 'P':
                    piece=new Pawn(board, ChessPiece.Color.WHITE);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
                case 'B':
                    piece = new Bishop(board, ChessPiece.Color.WHITE);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
                case 'R':
                    piece = new Rook(board, ChessPiece.Color.WHITE);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
                case 'K':
                    piece = new King(board, ChessPiece.Color.WHITE);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
                case 'Q':
                    piece = new Queen(board, ChessPiece.Color.WHITE);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
                case 'N':
                    piece = new Knight(board, ChessPiece.Color.WHITE);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
            }

        }
        else{
            switch(pieceChar){
                case 'p':
                    piece=new Pawn(board, ChessPiece.Color.BLACK);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
                case 'b':
                    piece = new Bishop(board, ChessPiece.Color.BLACK);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
                case 'r':
                    piece = new Rook(board, ChessPiece.Color.BLACK);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
                case 'k':
                    piece = new King(board, ChessPiece.Color.BLACK);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
                case 'q':
                    piece = new Queen(board, ChessPiece.Color.BLACK);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
                case 'n':
                    piece = new Knight(board, ChessPiece.Color.BLACK);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
            }
        }
        return false;
    }

    public ChessBoard makeBoard(String fen){
        ChessBoard board = new ChessBoard();
        int row=0;
        int column=0;
        Boolean complete=false;
        for(int i=0;i<fen.length();i++){
            Character c=fen.charAt(i);
            if(!complete){
                if(Character.isSpaceChar(c)){
                    complete=true;
                }
                else if(makePiece(c, board, row, column)){
                    column++;
                }
                else if(Character.isDigit(c)){
                    int s=Character.getNumericValue(c);
                    for(int j=0;j<s;j++){
                        column++;
                    }
                }
                else if(c.equals('/')){
                    row++;
                    column=0;
                }
            }
        }
        return board;
    }

    public Boolean Do(){
        valid=false;
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getStateTable();
        fen=QueryBuilder.getState(whiteUser, blackUser);
        ChessBoard board=makeBoard(fen);
        try {
            board.move(from, to);
            check=board.isCheck();
            checkmate=board.isCheckmate();
            fen=board.toFen();
            if(QueryBuilder.getTurn(whiteUser, blackUser).equals("White")){
                QueryBuilder.updateState(whiteUser, blackUser, fen, "Black");
            }
            if(QueryBuilder.getTurn(whiteUser, blackUser).equals("Black")){
                QueryBuilder.updateState(whiteUser, blackUser, fen, "White");
            }
            QueryBuilder.disconnectDb();
            valid=true;
        }catch(IllegalMoveException e){
            valid=false;
            QueryBuilder.disconnectDb();
        }
        return valid;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        this.Do();
    }
}
