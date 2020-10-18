package Game;
import java.util.ArrayList;

public class ChessMatch {
    public ChessBoard board;
    private User whiteUser;
    private User blackUser;
    private User turn;
    private ArrayList<String> gameLog;


    public ChessMatch(){
        board=new ChessBoard();
        board.initialize();
        whiteUser=new User(this);
        blackUser=new User(this);
        turn=whiteUser;
        gameLog=new ArrayList<String>();
    }

    public void nextTurn(){
        if(turn.equals(whiteUser)){
            turn=blackUser;
            return;
        }
        else if(turn.equals(blackUser)){
            turn=whiteUser;
            return;
        }

    }

    public void addMove(String fromPosition, String toPosition){
        gameLog.add(""+fromPosition+","+"toPosition");
        return;
    }

}
