package Backend;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class CSP {

    // * Declare variables for keeping an active board, tracking execution time,
    // tracking generated nodes, and tracking which player went last
    private Board board;
    long time;
    int assignmentsPrinted = 5;

    private final Stack<Board> tree = new Stack<Board>();

    public CSP(Board board) {
        this.board = board;
    }

    // * Function to run search on board
    public void run() {
        time = System.currentTimeMillis();
        board.initializeRemainingVariables();
        while (!RecursiveBacktracking())
            ;
        time = System.currentTimeMillis() - time;
    }

    private boolean RecursiveBacktracking() {
        if (assignmentsPrinted > 0) {
            if (assignmentsPrinted != 5)
                System.out.printf("Square %d, %d has a domain size of %d and degree of %d and is being assigned %d%n",
                        board.xFromIndex(board.lastIndex), board.yFromIndex(board.lastIndex),
                        board.getSquare(board.lastIndex).getDomainCount(), board.getSquare(board.lastIndex).getDegree(),
                        board.lastValue);
            assignmentsPrinted--;
        }

        // * if assignment is complete then return assignment
        if (board.isFull())
            return true;

        // * select unassigned variable
        int varIndex = SelectUnassignedVariable();
        // System.out.printf(" Trying to set variable %d, tree length %d, current value
        // %d%n", varIndex, tree.size(), board.getSquare(varIndex).getValue());

        // ! Debug ****
        // if (varIndex <= 72) printSquare();

        // * for each value in order-domain-values
        for (int value : OrderDomainValues(varIndex)) {
            Board newBoard = new Board(board);
            if (newBoard.setSquare(varIndex, value)) {
                // System.out.printf(" Value %d works%n", value);
                tree.add(newBoard);
            }
        }

        board = tree.pop();

        return false;
    }

    private int SelectUnassignedVariable() {
        return board.nextVariable().getIndex();
    }

    private LinkedList<Integer> OrderDomainValues(int index) {
        boolean[] domain = board.getSquare(index).getDomain();
        LinkedList<Integer> domainValues = new LinkedList<>();

        for (int i = 9; i >= 1; i--) {
            if (domain[i - 1] == true)
                domainValues.add(i);
        }

        return domainValues;
    }

    public void printInfo() {
        System.out.printf("This took %.3f seconds%n", ((float) time) / 1000);
    }

    public void printSquare() {
        board.printSquare();
    }

}
