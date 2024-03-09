public class Move {
    int move;
    int moveScore;
    
    public Move (int move, int moveScore){
        this.move = move;
        this.moveScore = moveScore;
    }

    public Move (int move){
        this.move = move;
    }

    public Move(Move move){
        this.move = move.move;
        this.moveScore = move.moveScore;
    }

    public void setTo(Move move){
        this.move = move.move;
        this.moveScore = move.moveScore;
    }
}
