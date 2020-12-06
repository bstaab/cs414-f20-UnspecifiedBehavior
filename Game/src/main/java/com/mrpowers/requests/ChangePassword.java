package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;

public class ChangePassword extends RequestData{
    private String username;
    private String password;
    private String newPassword;
    private Boolean valid;

    public boolean Change(){
        valid=false;
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        if(QueryBuilder.checkUsername(username)){
            String[] results=QueryBuilder.searchUser(username);

            if(password.equals(results[2])){
                valid=true;
                QueryBuilder.changePassword(username, newPassword);
            }
        }
        QueryBuilder.disconnectDb();
        return valid;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        this.Change();
    }
}
