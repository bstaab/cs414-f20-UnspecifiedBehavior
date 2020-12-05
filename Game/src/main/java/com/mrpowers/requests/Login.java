package com.mrpowers.requests;
import com.mrpowers.QueryBuilder;
import com.mrpowers.exceptions.IllegalMoveException;

public class Login extends RequestData{
    private String username;
    private String password;
    private Boolean valid;


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
            QueryBuilder.disconnectDb();
            System.out.println(results[2]);
            System.out.println(password);
            if(password.equals(results[2])){
                valid=true;
                return;
            }
            else{valid=false;}
        }
        else{
            valid=false;
        }
    }
    public static void main(String[] args){
        Login aLogin=new Login("user", "password");
        try{
            aLogin.buildResponse();
        }catch(IllegalMoveException e){

        }catch(RequestException e){

        }
    }
}
