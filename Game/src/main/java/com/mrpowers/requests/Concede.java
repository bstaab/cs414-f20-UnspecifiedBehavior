package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;

public class Concede extends RequestData{
    private String whiteUser;
    private String blackUser;
    private String loser;
    private Boolean valid;

    public Boolean Do(){
        valid=false;
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getStateTable();
        if(QueryBuilder.checkUsername(loser)&&QueryBuilder.checkUsername(whiteUser)&&QueryBuilder.checkUsername(blackUser)){
            QueryBuilder.updateMatches(loser, false);
            if(whiteUser.equals(loser)){
                QueryBuilder.updateMatches(blackUser, true);
            }
            else if(blackUser.equals(loser)){
                QueryBuilder.updateMatches(whiteUser, true);
            }
            valid=true;
            QueryBuilder.removeGame(whiteUser, blackUser);
        }
        QueryBuilder.disconnectDb();
        return valid;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        this.Do();
    }
}
