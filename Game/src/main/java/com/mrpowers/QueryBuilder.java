package com.mrpowers;

import java.sql.*;


public class QueryBuilder {
    static ResultSet resObj;
    static Statement stmtObj;
    static Connection connObj;
    static PreparedStatement prepObj;

    static String table_name;
    static String column_1, column_2, column_3;

    public static void connectDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connObj = DriverManager.getConnection("jdbc:mysql://cs414-db.cuboxgv8ds8p.us-east-1.rds.amazonaws.com:3306/db",
                    "admin", "rams2020");
            stmtObj = connObj.createStatement();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static void disconnectDb() {
        try {
            resObj.close();
//            prepObj.close();
            stmtObj.close();
            connObj.close();
            System.out.println("\n=======Database Connection Closed=======\n");
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static void getDBTable() {
        Statement stmt = null;
        try {
            DatabaseMetaData md = connObj.getMetaData();
            resObj = md.getTables(null,null,"%",null);
            while ((resObj.next())){
                table_name = resObj.getString(3);
            }
            resObj = stmtObj.executeQuery("SELECT * FROM registry");
            ResultSetMetaData rsm = resObj.getMetaData();
            column_1 = rsm.getColumnName(1);
            column_2 = rsm.getColumnName(2);
            column_3 = rsm.getColumnName(3);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void getStateTable() {
        Statement stmt = null;
        try {
            DatabaseMetaData md = connObj.getMetaData();
            resObj = md.getTables(null,null,"%",null);
            while ((resObj.next())){
                table_name = resObj.getString(3);
            }
            resObj = stmtObj.executeQuery("SELECT * FROM game_state");
            ResultSetMetaData rsm = resObj.getMetaData();
            column_1 = rsm.getColumnName(1);
            column_2 = rsm.getColumnName(2);
            column_3 = rsm.getColumnName(3);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void getMessagesTable() {
        Statement stmt = null;
        try {
            DatabaseMetaData md = connObj.getMetaData();
            resObj = md.getTables(null,null,"%",null);
            while ((resObj.next())){
                table_name = resObj.getString(3);
            }
            resObj = stmtObj.executeQuery("SELECT * FROM messages");
            ResultSetMetaData rsm = resObj.getMetaData();
            column_1 = rsm.getColumnName(1);
            column_2 = rsm.getColumnName(2);
            column_3 = rsm.getColumnName(3);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static boolean createUser(String username, String email, String password){
        System.out.println("db");
        System.out.println(username);
        try {
            String insertQuery = "INSERT INTO registry (Username,Email,PASSWORD) VALUES (?,?,?)";
            prepObj = connObj.prepareStatement(insertQuery);
            prepObj.setString(1,username);
            prepObj.setString(2,email);
            prepObj.setString(3,password);
            prepObj.executeUpdate();
            return true;
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    //If no user is found it will return null in string[0]
    public static String[] searchUser(String username){
        String selectQuery = "SELECT * FROM registry WHERE Username=?";
        String[] results = new String[3];
        try {
            prepObj = connObj.prepareStatement(selectQuery);
            prepObj.setString(1,username);
            ResultSet rs = prepObj.executeQuery();
            while(rs.next()){
                results[0] = rs.getString("Username");
                results[1] = rs.getString("Email");
                results[2] = rs.getString("PASSWORD");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return results;
    }

    public static boolean deleteUser(String username){
        if(checkUsername(username)){
            String deleteQuery = "DELETE FROM registry WHERE Username=?";
            try {
                prepObj = connObj.prepareStatement(deleteQuery);
                prepObj.setString(1,username);
                prepObj.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if(!checkUsername(username)) return true;
        }
        return false;
    }

    public static boolean checkUsername(String username){
        String[] s = searchUser(username);
        if(s[0] != null) return true;
        return false;
    }

    public static boolean checkEmail(String email){
        String selectQuery = "SELECT * FROM registry WHERE Email=?";
        String results = "";
        try {
            prepObj = connObj.prepareStatement(selectQuery);
            prepObj.setString(1,email);
            ResultSet rs = prepObj.executeQuery();
            while(rs.next()){
                results = rs.getString("Email");
            }
            if(!results.isEmpty()) return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static void changePassword(String username,String newPassword){
        String updateQuery = "UPDATE registry SET PASSWORD = ? WHERE Username = ?";
        try {
            prepObj = connObj.prepareStatement(updateQuery);
            prepObj.setString(1,newPassword);
            prepObj.setString(2,username);
            prepObj.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //array[0] returns match count array[1] returns matches won
    public static int[] getMatches(String username){
        int[] matches = new int[2];
        String updateQuery = "SELECT Matches_Won,Matches FROM registry WHERE Username = ?";
        String match = "";
        String match_won = "";
        try{
            prepObj = connObj.prepareStatement(updateQuery);
            prepObj.setString(1,username);
            ResultSet rs = prepObj.executeQuery();
            while(rs.next()){
                match = rs.getString("Matches_won");
                match_won = rs.getString("Matches");
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        matches[0] = Integer.parseInt(match);
        matches[1] = Integer.parseInt(match_won);
        return matches;
    }

    //updates user based on whether they won or not
    public static void updateMatches(String username,boolean gameWon){
        String updateQuery;
        if(gameWon){
            updateQuery = "UPDATE registry SET Matches_Won = Matches_Won + 1, Matches = Matches + 1 WHERE Username = ?";
        }else{
            updateQuery = "UPDATE registry SET Matches = Matches + 1 WHERE Username = ?";
        }
        try {
            prepObj = connObj.prepareStatement(updateQuery);
            prepObj.setString(1,username);
            prepObj.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateState(String user1,String user2,String state){
        String updateQuery = "UPDATE game_state SET State = ? WHERE Player_1 = ? AND Player_2 = ?";
        try{
            prepObj = connObj.prepareStatement(updateQuery);
            prepObj.setString(1,state);
            prepObj.setString(2,user1);
            prepObj.setString(3,user2);
            prepObj.executeUpdate();
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public static boolean addGameState(String user1, String user2, String state){
        try {
            String insertQuery = "INSERT INTO game_state (State,Player_1,Player_2) VALUES (?,?,?)";
            prepObj = connObj.prepareStatement(insertQuery);
            prepObj.setString(1,state);
            prepObj.setString(2,user1);
            prepObj.setString(3,user2);
            prepObj.executeUpdate();
            return true;
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    public static void removeGameState(String user1, String user2, String state){
        String deleteQuery = "DELETE FROM game_state WHERE State = ? AND Player_1 = ? AND Player_2 = ?";
        try {
            prepObj = connObj.prepareStatement(deleteQuery);
            prepObj.setString(1,state);
            prepObj.setString(2,user1);
            prepObj.setString(3,user2);
            prepObj.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static String getState(String user1, String user2){
        String updateQuery = "SELECT State From game_state WHERE Player_1 = ? AND Player_2 = ?";
        String state = "";
        try{
            prepObj = connObj.prepareStatement(updateQuery);
            prepObj.setString(1,user1);
            prepObj.setString(2,user2);
            ResultSet rs = prepObj.executeQuery();
            while(rs.next()){
                state = rs.getString("State");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return state;
    }

    public static String getMessage(String to, String from){
        String updateQuery = "SELECT message FROM messages WHERE toUser = ? AND fromUser = ?";
        String msg = "";
        try{
            prepObj = connObj.prepareStatement(updateQuery);
            prepObj.setString(1,to);
            prepObj.setString(2,from);
            ResultSet rs = prepObj.executeQuery();
            while(rs.next()){
                msg = rs.getString("message");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return msg;
    }

    public static boolean addMessage(String to, String from, String msg){
        try {
            String insertQuery = "INSERT INTO messages (toUser,message,fromUser) VALUES (?,?,?)";
            prepObj = connObj.prepareStatement(insertQuery);
            prepObj.setString(1,to);
            prepObj.setString(2,msg);
            prepObj.setString(3,from);
            prepObj.executeUpdate();
            return true;
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }
    public static int getListSize(String username){
        int c = 0;
        try{
            String countQuery ="SELECT COUNT(fromUser) FROM messages WHERE toUser = ?";
            prepObj = connObj.prepareStatement(countQuery);
            prepObj.setString(1,username);
            ResultSet rs = prepObj.executeQuery();
            rs.next();
            c = rs.getInt(1);
        }catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return c;
    }

    public static String[] getUsernamesFrom(String username){
        String [] userList;
        int listSize;
        listSize = getListSize(username);
        userList = new String[listSize];
        try{
            String updateQuery ="SELECT fromUser FROM messages WHERE toUser = ?";
            prepObj = connObj.prepareStatement(updateQuery);
            prepObj.setString(1,username);
            ResultSet rs = prepObj.executeQuery();
            int i = 0;
            while(rs.next()){
                String user = rs.getString("fromUser");
                userList[i] = user;
                i++;
            }
        }catch (Exception sqlException){
            sqlException.printStackTrace();
        }
        return userList;
    }


    public static void removeMessage(String to, String from, String msg){
        String deleteQuery = "DELETE FROM messages WHERE message = ? AND toUser = ? AND fromUser = ?";
        try {
            prepObj = connObj.prepareStatement(deleteQuery);
            prepObj.setString(1,msg);
            prepObj.setString(2,to);
            prepObj.setString(3,from);
            prepObj.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
