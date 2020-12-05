package com.mrpowers;

public class QueryBuilderDemo {
    public static void main(String[] args){
     QueryBuilder.connectDb();
//        QueryBuilder.getDBTable();
//        QueryBuilder.getStateTable();
        QueryBuilder.getMessagesTable();
        int c = QueryBuilder.getListSize("user1");
        String [] usernames = QueryBuilder.getUsernamesFrom("user1");
        for(int i = 0; i < c; i++) {
            System.out.println(usernames[i]);
        }
//        QueryBuilder.addMessage("fella","lass","hey there");
//        QueryBuilder.removeMessage("fella","lass","hey there");
        /*
        String s = QueryBuilder.getMessage("user1","user2");
        String s = QueryBuilder.getState("user1","user2");
        QueryBuilder.addGameState("user1","user2","woooooooooBlahhhhh");
        QueryBuilder.updateState("codeuser","testuser","state is changed again");
        int [] i = QueryBuilder.getMatches("testuser");
        System.out.println("Matches:"+ i[0] + " Matches Won:" + i[1]);
        QueryBuilder.updateMatches("testuser",false);
        int [] r = QueryBuilder.getMatches("testuser");
        System.out.println("Matches:"+ r[0] + " Matches Won:" + r[1]);
        QueryBuilder.createUser("tuser","test@ruser","123test");
        String [] s = QueryBuilder.searchUser("testuser");
        QueryBuilder.changePassword("testuser","wuuuuuuuuuu");*/
        QueryBuilder.disconnectDb();
    }
}
