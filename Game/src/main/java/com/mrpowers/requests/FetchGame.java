package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;

public class FetchGame extends RequestData{
    private String fen;
    private String whiteUser;
    private String blackUser;
    private String turn;

    public String Get() throws RequestException {
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getStateTable();
        fen = "";
        fen = QueryBuilder.getState(whiteUser, blackUser);
        turn=QueryBuilder.getTurn(whiteUser, blackUser);
        if(fen.length()==0){
            throw new RequestException();
        }
        QueryBuilder.disconnectDb();
        return fen;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        this.Get();
    }
}
