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
        //System.out.println("db");
        //System.out.println(username);
        try {
            String insertQuery = "INSERT INTO registry (Username,Email,PASSWORD,Matches_Won,Matches) VALUES (?,?,?,?,?)";
            prepObj = connObj.prepareStatement(insertQuery);
            prepObj.setString(1,username);
            prepObj.setString(2,email);
            prepObj.setString(3,password);
            prepObj.setInt(4,0);
            prepObj.setInt(5,0);
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

    public static void updateState(String white,String black,String state,String turn){
        String updateQuery = "UPDATE game_state SET State = ?, Turn = ? WHERE WhiteUser = ? AND BlackUser = ?";
        try{
            prepObj = connObj.prepareStatement(updateQuery);
            prepObj.setString(1,state);
            prepObj.setString(2,turn);
            prepObj.setString(3,white);
            prepObj.setString(4,black);
            prepObj.executeUpdate();
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public static boolean addGame(String white, String black,String state){
        try {
            String insertQuery = "INSERT INTO game_state (State,WhiteUser,BlackUser,Turn) VALUES (?,?,?,?)";
            prepObj = connObj.prepareStatement(insertQuery);
            prepObj.setString(1,state);
            prepObj.setString(2,white);
            prepObj.setString(3,black);
            prepObj.setString(4,"White");
            prepObj.executeUpdate();
            return true;
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    public static void removeGame(String white, String black){
        String deleteQuery = "DELETE FROM game_state WHERE WhiteUser = ? AND BlackUser = ?";
        try {
            prepObj = connObj.prepareStatement(deleteQuery);
            prepObj.setString(1,white);
            prepObj.setString(2,black);
            prepObj.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static String getState(String white, String black){
        String updateQuery = "SELECT State From game_state WHERE WhiteUser = ? AND BlackUser = ?";
        String state = "";
        try{
            prepObj = connObj.prepareStatement(updateQuery);
            prepObj.setString(1,white);
            prepObj.setString(2,black);
            ResultSet rs = prepObj.executeQuery();
            while(rs.next()){
                state = rs.getString("State");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return state;
    }

    public static String getTurn(String white, String black){
        String updateQuery = "SELECT Turn From game_state WHERE WhiteUser = ? AND BlackUser = ?";
        String turn = "";
        try{
            prepObj = connObj.prepareStatement(updateQuery);
            prepObj.setString(1,white);
            prepObj.setString(2,black);
            ResultSet rs = prepObj.executeQuery();
            while(rs.next()){
                turn = rs.getString("Turn");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return turn;
    }

    private static int[] getGameCount(String username){
        int[] c = {0,0};
        try{
            String countQuery = "SELECT COUNT(BlackUser) FROM game_state WHERE WhiteUser = ?";
            prepObj = connObj.prepareStatement(countQuery);
            prepObj.setString(1,username);
            ResultSet rs = prepObj.executeQuery();
            rs.next();
            c[0] = rs.getInt(1);
            countQuery = "SELECT COUNT(WhiteUser) FROM game_state WHERE BlackUser = ?";
            prepObj = connObj.prepareStatement(countQuery);
            prepObj.setString(1,username);
             rs = prepObj.executeQuery();
            rs.next();
            c[1] = rs.getInt(1);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return c;
    }

    public static String[][] getGameUsers(String user){
        int[] size = getGameCount(user);
        String selectQuery = "SELECT BlackUser FROM game_state WHERE WhiteUser = ?";
        String[] wGames = new String[size[0]+1];
        String[] bGames = new String[size[1]+1];
        //wGames[0] ="WHITE";
        //bGames[0] = "BLACK";
        try{
            prepObj = connObj.prepareStatement(selectQuery);
            prepObj.setString(1,user);
            ResultSet rs = prepObj.executeQuery();
            int i = 0;
            while(rs.next()){
                String player = rs.getString("BlackUser");
                wGames[i] = player;
                i++;
            }
            selectQuery = "SELECT WhiteUser FROM game_state WHERE BlackUser = ?";
            prepObj = connObj.prepareStatement(selectQuery);
            prepObj.setString(1,user);
            rs = prepObj.executeQuery();
            i = 0;
            while(rs.next()){
                String player = rs.getString("WhiteUser");
                bGames[i] = player;
                i++;
            }
        }catch (Exception sqlException){
            sqlException.printStackTrace();
        }
        String [][] games = {wGames,bGames};
        return games;
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

    public static String[] getUsernamesFromMessages(String username){
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
}
