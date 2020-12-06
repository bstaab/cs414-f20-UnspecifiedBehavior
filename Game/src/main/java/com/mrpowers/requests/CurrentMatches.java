package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;

public class CurrentMatches extends RequestData{
    private String username;
    private String[] wGames;
    private String[] bGames;
    private String message;

    public boolean Get() throws RequestException {
        message="INVALID";
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getStateTable();
        if(QueryBuilder.checkUsername(username)){
            try{
                String[][] opponents=QueryBuilder.getGameUsers(username);
                wGames=opponents[0];
                bGames=opponents[1];
                QueryBuilder.disconnectDb();
                message="ACTIVE MATCHES";
                return true;
            }catch(Exception e){
                QueryBuilder.disconnectDb();
                wGames= new String[]{};
                bGames= new String[]{};
                message="NO ACTIVE MATCHES";
                return true;
            }
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
