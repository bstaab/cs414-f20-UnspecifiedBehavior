package com.mrpowers.requests;
import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;

public class Login extends RequestData{
    private String username;
    private String password;


    public Login(String username, String password){
        this.username=username;
        this.password=password;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException {
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        if(QueryBuilder.checkUsername(username)){
            String[] results=QueryBuilder.searchUser(username);
            if(password==results[2]){
                QueryBuilder.disconnectDb();
                return;
            }
            else{throw new RequestException();}
        }
        else{
            QueryBuilder.disconnectDb();
            throw new RequestException();
        }
    }
}
