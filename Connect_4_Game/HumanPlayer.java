import java.util.ArrayList;

class HumanPlayer extends Player {

    public HumanPlayer(char playerColour){
        super(playerColour);
        this.playerType = "Human";
    }

    public int getMove(ArrayList<Player> players, Board board, int neededToWin){
        int move = 0;
		while (move == 0 ){
            move = InputOutput.getInteger();
            if (move < 1  || move > board.columns){
                InputOutput.displayInvalidColumnInput();;
                move = 0;
            }
		}
		return move;
    }

    //Method to check if the player wants to play a new game
    public boolean playAgain(){
        final char yes = 'Y';
        final char no = 'N';
        final char blank = ' ';
        char decision = blank;

        InputOutput.displayDecisionInputPrompt();
        while (decision == blank ){
            decision = InputOutput.getFirstCharacterInput();
            if (decision != yes && decision != no){
                InputOutput.displayInvalidDecisionInput();
                decision = blank;
            }
        }

        return decision == yes;
    }

}
