package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;

public class UserData extends RequestData{
    private String username;
    private String email;
    private int gamesWon;
    private int gamesLost;

    public UserData(String username, String email, int gamesWon, int gamesLost){
        this.username=username;
        this.email=email;
        this.gamesWon=gamesWon;
        this.gamesLost=gamesLost;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        if(QueryBuilder.checkUsername(username)){
            String[] results=QueryBuilder.searchUser(username);
            int [] r = QueryBuilder.getMatches(username);
            gamesWon=r[0]-r[1];
            gamesLost=r[1];
            email="";
            QueryBuilder.disconnectDb();
        }
        else{
            throw new RequestException();
        }
    }
}
