package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;

public class Concede extends RequestData{
    private String whiteUser;
    private String blackUser;
    private String loser;
    private Boolean valid;

    public Concede(String whiteUser, String blackUser, String loser){
        this.whiteUser=whiteUser;
        this.blackUser=blackUser;
        this.loser=loser;
    }
    public Concede(String loser){
        this.loser=loser;
    }

    public boolean RemoveAllGames(){
        valid=false;
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getStateTable();
        try{
            String[][] p=QueryBuilder.getGameUsers(loser);
            for(int i=0;i<p[0].length;i++){
                QueryBuilder.removeGame(p[0][i], loser);
            }
            for(int i=0;i<p[1].length;i++){
                QueryBuilder.removeGame(loser, p[1][i]);
            }
            valid=true;
        }catch(Exception e){
            valid=false;
        }
        QueryBuilder.disconnectDb();
        return valid;
    }

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
