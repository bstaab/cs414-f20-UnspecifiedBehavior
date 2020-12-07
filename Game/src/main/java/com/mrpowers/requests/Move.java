package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.chess.*;
import com.mrpowers.exceptions.IllegalMoveException;
import com.mrpowers.exceptions.IllegalPositionException;

import java.lang.*;

public class Move extends RequestData {
    private String whiteUser;
    private String blackUser;
    private String from;
    private String to;
    private Boolean valid;
    private String turn;
    private String check;
    private String checkmate;
    private String fen;
    private String username;
    private String err;

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

    public Boolean Do() throws IllegalPositionException, IllegalMoveException {
        System.out.println(to+" "+from);
        valid=false;
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getStateTable();
        QueryBuilder.getMessagesTable();
        fen=QueryBuilder.getState(whiteUser, blackUser);
        ChessBoard board;
        ChessPiece p;
        turn=QueryBuilder.getTurn(whiteUser, blackUser);
        try {
            board = makeBoard(fen);
            p = board.getPiece(from);
            if(!(board.getPiece(from).getColor().equals(ChessPiece.Color.WHITE)&&whiteUser==username)){
                QueryBuilder.disconnectDb();
                return valid;
            }
            else if(!(board.getPiece(from).getColor().equals(ChessPiece.Color.BLACK)&&blackUser==username)){
                QueryBuilder.disconnectDb();
                return valid;
            }
            board.move(from, to);
            check = board.isCheck();
            checkmate = board.isCheckmate();
            fen = board.toFen();
        }catch(Exception e) {
            err="board/move/position";
            QueryBuilder.disconnectDb();
            return valid;
        }
        turn=QueryBuilder.getTurn(whiteUser, blackUser);
        if(turn.equals("White")){
            turn=whiteUser;
        }
        else if(turn.equals("Black")){
            turn=blackUser;
        }
        else{
            turn="corrupted";
            QueryBuilder.disconnectDb();
            return valid;
        }
        try{
            if(turn.equals(username)){
                QueryBuilder.updateState(whiteUser, blackUser, fen, "White");
            }
            if(checkmate.equals("White")){
                QueryBuilder.updateMatches(blackUser, true);
                QueryBuilder.updateMatches(whiteUser, false);
                QueryBuilder.addMessage(whiteUser, blackUser, "LOST");
                QueryBuilder.addMessage(blackUser, whiteUser, "WON");
                QueryBuilder.removeGame(whiteUser, blackUser);
            }
            else if(checkmate.equals(("Black"))){
                QueryBuilder.updateMatches(blackUser, false);
                QueryBuilder.updateMatches(whiteUser, true);
                QueryBuilder.addMessage(whiteUser, blackUser, "WON");
                QueryBuilder.addMessage(blackUser, whiteUser, "LOST");
                QueryBuilder.removeGame(whiteUser, blackUser);
            }
            QueryBuilder.disconnectDb();
            valid=true;
        }catch(Exception e){
            valid=false;
            QueryBuilder.disconnectDb();
            return valid;
        }
        QueryBuilder.disconnectDb();
        return valid;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException, IllegalPositionException {
        this.Do();
    }
}
