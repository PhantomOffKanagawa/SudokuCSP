package Backend;

import java.util.LinkedList;
import java.util.Stack;

public class CSP {

    // * Declare variables for keeping an active board, tracking execution time, tracking generated nodes, and tracking which player went last
    private Board board;
    long time;

    private final Stack<Board> tree = new Stack<Board>();

    public CSP(Board board) {
        this.board = board;
    }

    // * Function to run search on board
    public void run() {
        board.initializeRemainingVariables();
        while (!BacktrackingSearch());
    }

    private boolean BacktrackingSearch() {
        System.out.printf("%d remaining%n", board.remainingVariablesCount());
        return RecursiveBacktracking();
    }

    private boolean RecursiveBacktracking() {
        // * if assignment is complete then return assignment
        if (board.isFull()) return true;

        // * select unassigned variable
        int varIndex = SelectUnassignedVariable();
        System.out.printf(" Trying to set variable %d, tree length %d, current value %d%n", varIndex, tree.size(), board.getSquare(varIndex).getValue());

        // ! Debug ****
        // if (varIndex <= 72) printSquare();

        // * for each value in order-domain-values
        for (int value : OrderDomainValues(varIndex)) {
            if (consistencyCheck(varIndex, value)) {
                // System.out.printf("     Value %d works%n", value);
                tree.add(new Board(board, varIndex, value));
            }
        }

        // TODO inferences <- INFERENCE etc.

        board = tree.pop();

        return false;
    }

    private int SelectUnassignedVariable() {
        return board.nextVariable().getIndex();
    }

    private LinkedList<Integer> OrderDomainValues(int index) {
        boolean[] domain = board.getSquare(index).getDomain();
        LinkedList<Integer> domainValues = new LinkedList<>();

        for (int i = 1; i <= 9; i++) {
            if (domain[i - 1] == true) domainValues.add(i);
        }

        return domainValues;
    }

    private boolean consistencyCheck(int index, int value) {
        return board.consistencyCheck(index, value);
    }

    public void printSquare() {
        board.printSquare();
    }

}
