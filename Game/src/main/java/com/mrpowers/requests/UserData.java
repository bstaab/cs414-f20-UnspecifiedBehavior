package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;

public class UserData extends RequestData{
    private String username;
    private String email;
    private Integer gamesWon;
    private Integer gamesLost;
    private Boolean valid;

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
            QueryBuilder.disconnectDb();
            gamesWon=r[0]-r[1];
            gamesLost=r[1];
            email=results[1];
            valid=true;
        }
        else{
            valid=false;
        }
    }
}
