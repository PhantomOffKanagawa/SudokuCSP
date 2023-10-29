import Backend.Board;
import Backend.CSP;

public class Main {
	public static void main(String[] args) {
        // * Initialize Board
        Board boardA = new Board();

        // boardA.testCoords();

        // * Initialize Board A
        boardA.setSquare(3, 1, 1);
        boardA.setSquare(6, 1, 2);
        boardA.setSquare(3, 2, 5);
        boardA.setSquare(6, 2, 6);
        boardA.setSquare(8, 2, 3);
        boardA.setSquare(1, 3, 4);
        boardA.setSquare(2, 3, 6);
        boardA.setSquare(6, 3, 5);

        boardA.setSquare(4, 4, 1);
        boardA.setSquare(6, 4, 4);
        boardA.setSquare(1, 5, 6);
        boardA.setSquare(4, 5, 8);
        boardA.setSquare(7, 5, 1);
        boardA.setSquare(8, 5, 4);
        boardA.setSquare(9, 5, 3);
        boardA.setSquare(5, 6, 9);
        boardA.setSquare(7, 6, 5);
        boardA.setSquare(9, 6, 8);

        boardA.setSquare(1, 7, 8);
        boardA.setSquare(5, 7, 4);
        boardA.setSquare(6, 7, 9);
        boardA.setSquare(8, 7, 5);
        boardA.setSquare(1, 8, 1);
        boardA.setSquare(4, 8, 3);
        boardA.setSquare(5, 8, 2);
        boardA.setSquare(3, 9, 9);
        boardA.setSquare(7, 9, 3);


        // * Run the strategy
        CSP boardAStrategy = new CSP(boardA);
        boardAStrategy.run();
        boardAStrategy.printSquare();
    }
}