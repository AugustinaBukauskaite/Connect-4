//import java.util.ArrayList;

class Main {
  static int neededToWin = 4; //InputOutput.getInteger();
  static int columns = 7;
  static int rows = 6;
  public static void main(String[] args) {
    MyConnectFour newGame = new MyConnectFour(columns,rows,neededToWin);
    newGame.startGame();
  }
}
