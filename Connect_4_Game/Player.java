import java.util.ArrayList;

abstract class Player {
    char playerColour;
    String playerType;
    
    public Player(char playerColour){
        this.playerColour = playerColour;
    }

    public char playerColour(){
        return playerColour;
    }

    public String playerType(){
        return playerType;
    }

    abstract int getMove(ArrayList<Player> players, Board board, int neededToWin);

    abstract boolean playAgain();
}
