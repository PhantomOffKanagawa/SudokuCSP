import Backend.Board;
import Backend.CSP;

public class Main {
	public static void main(String[] args) {
        // * Initialize Board
        Board boardA = new Board();

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
        boardAStrategy.printInfo();

        
        // * Initialize Board
        Board boardB = new Board();

        // * Initialize Board B
        boardB.setSquare(3, 1, 5);
        boardB.setSquare(5, 1, 1);
        boardB.setSquare(3, 2, 2);
        boardB.setSquare(6, 2, 4);
        boardB.setSquare(8, 2, 3);
        boardB.setSquare(1, 3, 1);
        boardB.setSquare(3, 3, 9);
        boardB.setSquare(7, 3, 2);
        boardB.setSquare(9, 3, 6);

        boardB.setSquare(1, 4, 2);
        boardB.setSquare(5, 4, 3);
        boardB.setSquare(2, 5, 4);
        boardB.setSquare(7, 5, 7);
        boardB.setSquare(1, 6, 5);
        boardB.setSquare(6, 6, 7);
        boardB.setSquare(9, 6, 1);

        boardB.setSquare(4, 7, 6);
        boardB.setSquare(6, 7, 3);
        boardB.setSquare(2, 8, 6);
        boardB.setSquare(4, 8, 1);
        boardB.setSquare(5, 9, 7);
        boardB.setSquare(8, 9, 5);


        // * Run the strategy
        CSP boardBStrategy = new CSP(boardB);
        boardBStrategy.run();
        boardBStrategy.printSquare();
        boardBStrategy.printInfo();

        
        // * Initialize Board
        Board boardC = new Board();

        // * Initialize Board C
        boardC.setSquare(1, 1, 6);
        boardC.setSquare(2, 1, 7);
        boardC.setSquare(2, 2, 2);
        boardC.setSquare(3, 2, 5);
        boardC.setSquare(2, 3, 9);
        boardC.setSquare(4, 3, 5);
        boardC.setSquare(5, 3, 6);
        boardC.setSquare(7, 3, 2);

        boardC.setSquare(1, 4, 3);
        boardC.setSquare(5, 4, 8);
        boardC.setSquare(7, 4, 9);
        boardC.setSquare(7, 5, 8);
        boardC.setSquare(9, 5, 1);
        boardC.setSquare(4, 6, 4);
        boardC.setSquare(5, 6, 7);

        boardC.setSquare(3, 7, 8);
        boardC.setSquare(4, 7, 6);
        boardC.setSquare(8, 7, 9);
        boardC.setSquare(8, 8, 1);
        boardC.setSquare(1, 9, 1);
        boardC.setSquare(3, 9, 6);
        boardC.setSquare(5, 9, 5);
        boardC.setSquare(8, 9, 7);


        // * Run the strategy
        CSP boardCStrategy = new CSP(boardC);
        boardCStrategy.run();
        boardCStrategy.printSquare();
        boardCStrategy.printInfo();
    }
}