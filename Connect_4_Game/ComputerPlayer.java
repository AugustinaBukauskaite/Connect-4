import java.util.ArrayList;

class ComputerPlayer extends Player {
    final int analysisDepth = 2;

    public ComputerPlayer(char playerColour){
        super(playerColour);
        this.playerType = "Computer";
    }

    public int getMove(ArrayList<Player> players, Board board, int neededToWin){
        int move;
        move = generateComputerMove(playerColour, players, board, analysisDepth, neededToWin).move;
        if(move == -1){
            System.out.println("No more valid moves, it's a tie");
            return - 1;
        }
        return move;
    }

    //Recursive method to generate a computer move. Base case if analysisDepth < 1 returns score 0.
    //Loops through all columns and places the counter on the test board, evaluates the move for each
    //and subtracts other player scores by calling itself. Returns a random move from the highest ranking moves.
	private Move generateComputerMove(char playerColour, ArrayList<Player> players, Board board, int analysisDepth, int neededToWin){
		ArrayList<Move> bestMoveList= new ArrayList<>();
        Move bestMove = new Move(-1,-10000001);
		if (analysisDepth < 1){
			return new Move(0, 0);
		}
		else {
			for(int testColumn = 1; testColumn<= board.columns; testColumn++){
                Move testMove = new Move(testColumn);
                MyConnectFour testGame = new MyConnectFour(board.columns, board.rows, neededToWin);
                testGame.board.setBoard(board);
				boolean placed = testGame.board.placeCounter(playerColour, testMove.move);
				if(placed){
                    int otherPlayerScores  = 0;
                    for(Player testPlayer :players){
                        if (testPlayer.playerColour != playerColour){

                            //Method calls itself to estimate the best score for other players after the computer move is made.
                            //The score is divided by the amount of players to prioritise personal win over predicted "loss" after the winning move is made.
                            otherPlayerScores += (generateComputerMove(testPlayer.playerColour, players, testGame.board, analysisDepth-1, neededToWin).moveScore / players.size());
                        }
                    }
					testMove.moveScore = testGame.boardScore(playerColour) - otherPlayerScores;
					if (testMove.moveScore > bestMove.moveScore){
						bestMove.setTo(testMove);
                        bestMoveList.clear();
                        bestMoveList.add(testMove);
					}
                    else if(bestMoveList.size() - 1 >= 0 && bestMoveList.get(bestMoveList.size()-1).moveScore == testMove.moveScore){
                        bestMoveList.add(testMove);
                    }
				}
			}
            bestMove = getRandomMoveFromList(bestMoveList);
			return bestMove;
		}
	}

    private Move getRandomMoveFromList (ArrayList<Move> moveList){
        int randomInt = (int) (Math.random() * (moveList.size()));
        Move randomMove = new Move(moveList.get(randomInt));
        return randomMove;
    }

    public boolean playAgain(){
        return false;
    }
}
