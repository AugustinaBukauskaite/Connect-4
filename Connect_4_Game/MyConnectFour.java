import java.util.ArrayList;

public class MyConnectFour {
	ArrayList<Player> players;
	Board board;
	final int columns;
	final int rows;
	final int neededToWin;

	public MyConnectFour(int columns, int rows, int neededToWin){
		this.columns = columns;
		this.rows = rows;
		this.neededToWin = neededToWin;
		board = new Board(columns, rows);
		players = new ArrayList<>();
		players.add(new HumanPlayer('r'));
		players.add(new ComputerPlayer('y'));
		//players.add(new ComputerPlayer('b'));
	}

	//starts the game and asks the player whether to play again when the game is finished
	public void startGame(){
		boolean keepPlaying= true;
		InputOutput.printIntroduction();
		while (keepPlaying){
			playGame();
			keepPlaying = players.get(0).playAgain();
			board.clearBoard();
		}
	}


	//While the game is not won loops through players, gets their moves and places them on the board.
	//Then checks if the game is won and prints a message
	private void playGame(){
		InputOutput.printBoard(board);
		boolean win = false;
		char winningPlayer = ' ';

		while(!win){
			for (Player player : players){
				boolean placed = false;
				int move;
				int attemptsToGetMove = 0;
				while(!placed){
					if(attemptsToGetMove>0){
						InputOutput.displayInvalidMoveMessage();
					}
					move = player.getMove(players, board, neededToWin);
					placed = board.placeCounter(player.playerColour, move);
					attemptsToGetMove++;
				}
				InputOutput.printBoard(board);
				win = checkForConnected(player.playerColour)>0;
				winningPlayer = player.playerColour;
				if (win){break;}
			}
		}
		InputOutput.displayWinningMessage(winningPlayer);
	}

	//Scores the board based on how many connected sets there are
	//10000000 points if enough are connected to win the game
	//100 points for every connected neededToWin - 1
	//10 points for every connected neededToWin - 2
	public int boardScore(char player){
		if(checkForConnected(player)>0){
			return 1000000;
		}
		if(neededToWin-1 > 1 && checkForConnected(player ,neededToWin - 1)>0){
			return 1000*checkForConnected(player ,neededToWin - 1);
		}
		if(neededToWin-2 > 1 && checkForConnected(player,neededToWin - 2)>0){
			return 10*checkForConnected(player,neededToWin - 1);
		}
		else{
			return 0;
		}
	}

	//returns the amount of connected sets of counters, if not given the amountToCheckFor parameter it returns the amount of connected sets neededToWin
	//previously returned a boolean true to mark a win, but adapted to check for other amounts of connected counters, seems more fun to play.
	private int checkForConnected(char player, int... amountToCheckFor){
		int checkInARow = (amountToCheckFor.length > 0) ? amountToCheckFor[0] : neededToWin;
		int setsConnected = 0;
		int count = 0;
		// check horizontal
			for(int i=0; i<rows; i++){
				for(int j=0; j<columns; j++){
					if(board.getElement(i,j) == player){
						count = count + 1;
						if(count >= checkInARow){
							setsConnected++;
							//return true;
						}
					}
					else{
						count = 0;
					}
				}
				count = 0;
			}
			// check vertical
			count = 0;
			for(int i=0; i<columns; i++){
				for(int j=0; j<rows; j++){
					if(board.getElement(j,i) == player){
						count = count + 1;
						if(count >= checkInARow){
							setsConnected++;
							//return true;
						}
					}
					else{
						count = 0;
					}
				}
				count = 0;
			}
			
			// check diagonal
			count = 0;
			for(int i=0; i<= rows-checkInARow; i++){
				for(int j=0; j<=columns-checkInARow; j++){
					for(int k=0;k<checkInARow;k++){
						if(board.getElement(i+k,j+k) == player){
							count++;
							if(count >= checkInARow){
								setsConnected++;
								//return true;
							}
						}
					}
					count = 0;
				}
				count = 0;
			}
			// check reverse-diagonal
			count = 0;
			for(int i=0; i<=rows-checkInARow; i++){
				for(int j=columns-1; j>=checkInARow-1; j--){
					for(int k=0;k<checkInARow;k++){
						if(board.getElement(i+k,j-k) == player){
							count++;
							if(count >= checkInARow){
								setsConnected++;
								//return true;
							}
						}
					}
					count = 0;
				}
				count = 0;
			}
			
		return setsConnected;
	}
}
