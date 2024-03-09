import java.io.BufferedReader;
import java.io.InputStreamReader;


public class InputOutput {
	    private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        //ANSI colour escape codes
        static String reset = "\u001B[0m";
        static String Red = "\u001B[31m";
        static String Green = "\u001B[32m";
        static String Yellow = "\u001B[33m";
        static String Blue = "\u001B[34m";
        static String Magenta = "\u001B[35m";
        static String Cyan = "\u001B[36m";

	    public static void printIntroduction(){
		System.out.println("Welcome to Connect 4");
		System.out.println("There are 2 players red and yellow");
		System.out.println("Player 1 is Red, Player 2 is Yellow");
		System.out.println("To play the game type in the number of the column you want to drop you counter in");
		System.out.println("A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally");
		System.out.println("");
	}

    //method to print the board
	public static void printBoard(Board board){
		final char empty = '\u0000';
		final String separator = "|";
		final String space = " ";

		for(int i=0; i<board.rows; i++){
			for(int j=0; j<board.columns; j++){
				if(board.getElement(i, j) == empty){
					System.out.print(separator+ space+ space+ space);
				}
				else{
						System.out.print(separator+space+getColour(board.getElement(i, j))+board.getElement(i, j)+reset+space);
					}
			}
			System.out.println(separator);
		}
		for (int i = 1; i <= board.columns;i++){
			System.out.print(space+space+i+space);
		}
		System.out.print("\n");
	}

    public static String getColour (char playerColour){
        switch (playerColour){
            case 'r':
                return Red;
            case 'g':
                return Green;
            case 'y':
                return Yellow;
            case 'b':
                return Blue;
            case 'm':
                return Magenta;
            case 'c':
                return Cyan;
            default:
                return reset;
        }
    }

    public static void displayMessage(String message){
        System.out.print(message);
    }

    public static char getFirstCharacterInput(){
        char returnChar = ' ';
        while (returnChar == ' ' ){
            try{
                returnChar  = input.readLine().charAt(0);
            }
            catch(Exception e){
                displayInvalidDecisionInput();
            }
        }
        return returnChar;
    }

    public static int getInteger(){
        String returnLine = "";
        int returnInt = 0;
        try{
            returnLine= input.readLine();
            returnInt = Integer.parseInt(returnLine);
        }
        catch(Exception e){
            displayInvalidColumnInput();
        }
        return returnInt;
    }

    public static void displayInvalidColumnInput(){
        System.out.println("Invalid input, please input the number of the column.");
    }

    public static void displayInvalidDecisionInput(){
        System.out.println("Invalid input, please press Y to play again or N to exit");
    }

    public static void displayDecisionInputPrompt(){
        System.out.println("Please press Y to play again or N to exit");
    }

    public static void displayWinningMessage(char winningPlayer){
		System.out.println(winningPlayer + " player has won!!!");
    }

    public static void displayInvalidMoveMessage(){
        System.out.println("invalid move, please select another move");
    }

    public static void displayTieMessage(){
        System.out.println("It's a tie");
    }
}
