package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;

public class CurrentMatches extends RequestData{
    private String username;
    private String[] wGames;
    private String[] bGames;

    public boolean Get() throws RequestException {
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getStateTable();
        if(QueryBuilder.checkUsername(username)){
            String[][] opponents=QueryBuilder.getGameUsers(username);
            QueryBuilder.disconnectDb();
            wGames=opponents[0];
            bGames=opponents[1];
            return true;
        }
        else{
            QueryBuilder.disconnectDb();
            throw new RequestException();
        }
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        this.Get();
    }
}
