package Game;

public class Move {
    private String from;
    private String to;

    public Move(String from, String to){
        this.from=from;
        this.to=to;
    }
    public String getFrom(){
        return from;
    }
    public String getTo(){
        return to;
    }
}
