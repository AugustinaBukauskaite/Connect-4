import java.util.Arrays;

class Board {
	final int columns;
	final int rows;
	private char[][] board;

    //Two constructors that allow me to instantiate a board two ways
    Board (int columns, int rows){
        this.columns = columns;
		this.rows = rows;
		this.board = new char[rows][columns];
    }

    Board (Board board){
        this.columns = board.columns;
		this.rows = board.rows;
		this.board = new char[rows][columns];
    }

    public void setBoard(Board board){
        for (int i = 0; i < rows; i++) {
            this.board[i] = Arrays.copyOf(board.board[i], columns);
        }
	}

	public char[][] getBoard(){
		return board;
	}

    public void setElement(int i, int j, char value){
            board[i][j] = value;
	}

    public char getElement(int i, int j){
            return board[i][j];
	}

	public boolean placeCounter(char player, int position){
        final char empty = '\u0000';
		boolean placed = false;
			for(int i = rows-1; i >= 0; i--){
				boolean fieldAvailable = board[i][position-1] == empty;
				if(!placed && fieldAvailable){
                    board[i][position-1] = player;
                    placed = true;
				}
			}
		return placed;
	}

    public void clearBoard(){
        final char empty = '\u0000';
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				board[i][j] = empty;
			}
		}
	}
}
