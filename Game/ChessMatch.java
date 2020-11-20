package Game;
import java.util.ArrayList;
import java.util.Random;

public class ChessMatch {
    public ChessBoard board;
    private User whiteUser;
    private User blackUser;
    private User turn;
    private ArrayList<Move> gameLog;
    private Random rand;


    public ChessMatch(){
        board=new ChessBoard();
        board.initialize();
        //whiteUser=new User(this);
        //blackUser=new User(this);
        rand = new Random();
        turn=whiteUser;
        gameLog=new ArrayList<Move>();
    }

    public void setUser(User user){
        int chooseColor = rand.nextInt();
        if(chooseColor % 2 == 0){
            user.setColor(ChessPiece.Color.WHITE);
            whiteUser=user;
        }
        else{
            user.setColor(ChessPiece.Color.BLACK);
        }
    }

    public void setUser2(User user){
        if(whiteUser.equals(null)){
            whiteUser=user;
            user.setColor(ChessPiece.Color.WHITE);
        }
        else{
            blackUser=user;
            user.setColor(ChessPiece.Color.BLACK);
        }
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

    public void addMove(Move move){
        gameLog.add(move);
        return;
    }

}
