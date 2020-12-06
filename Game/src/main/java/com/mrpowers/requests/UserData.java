package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;

public class UserData extends RequestData{
    private String username;
    private String email;
    private Integer gamesWon;
    private Integer gamesLost;
    private Boolean valid;

    public UserData(String username){
        this.username=username;

    }

    public Integer getGamesWon(){
        return gamesWon;
    }
    public Integer getGamesLost(){
        return gamesLost;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        if(QueryBuilder.checkUsername(username)){
            String[] results=QueryBuilder.searchUser(username);
            try{
                int [] r = QueryBuilder.getMatches(username);
                gamesWon=r[0]-r[1];
                gamesLost=r[1];
            }
            catch(Exception e){
                gamesWon=0;
                gamesLost=0;
            }
            email=results[1];
            valid=true;
        }
        else{
            valid=false;
        }
        QueryBuilder.disconnectDb();
    }
}
