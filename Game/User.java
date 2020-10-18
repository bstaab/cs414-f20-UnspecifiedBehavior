package Game;

public class User {
    ChessMatch myGame;

    public User(ChessMatch myGame){
        this.myGame=myGame;
    }
    //probably have to parse a json file or something to get the move from the client
    public void Move(String fromPosition, String toPosition){
        try{
            myGame.board.move(fromPosition, toPosition);
        }catch(IllegalMoveException e){
            e.printStackTrace();
        }
        myGame.addMove(fromPosition, toPosition);
    }
}
