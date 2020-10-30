package com.jcg.jdbc.sql.query.builder;

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
            prepObj.close();
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

    public static boolean createUser(String username, String email, String password){
        try {
            String insertQuery = "INSERT INTO registry VALUES (?,?,?)";
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
}
