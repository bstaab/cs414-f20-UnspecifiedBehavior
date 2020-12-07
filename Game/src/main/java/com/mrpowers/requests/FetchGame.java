package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;

public class FetchGame extends RequestData{
    private String fen;
    private String whiteUser;
    private String blackUser;
    private String turn;
    private Boolean valid;

    public String Get() throws RequestException {
        valid=false;
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getStateTable();
        fen = QueryBuilder.getState(whiteUser, blackUser);
        turn=QueryBuilder.getTurn(whiteUser, blackUser);
        QueryBuilder.disconnectDb();
        valid=true;
        return fen;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        this.Get();
    }
}
