package Backend;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Board {
  // * Initialize Variables to track: Board itself
  private final Square[] sudokuBoard;
  // private final PriorityQueue<Variable> remainingVariables;
  public final LinkedList<Square> remainingVariables;
  public int lastIndex, lastValue;

  // * Initialize a new board with blank sets
  public Board() {
    sudokuBoard = new Square[81];
    for (int i = 0; i < 81; i++) {
      sudokuBoard[i] = new Square(i);
    }

    remainingVariables = new LinkedList<Square>();

  }

  public Board(Board board) {
    sudokuBoard = new Square[81];
    for (var i = 0; i < 81; i++) {
      sudokuBoard[i] = new Square(board.getSquare(i));
    }

    this.remainingVariables = new LinkedList<>(board.remainingVariables);
  }

  public Board(Board board, int index, int v) {
    sudokuBoard = new Square[81];
    for (var i = 0; i < 81; i++) {
      sudokuBoard[i] = new Square(board.getSquare(i));
    }

    this.remainingVariables = new LinkedList<>(board.remainingVariables);

    setSquare(index, v);
  }

  public int remainingVariablesCount() {
    return remainingVariables.size();
  }

  // * Sets remainingVariables to their initial
  protected void initializeRemainingVariables() {
    for (int i = 0; i < 81; i++) {
      if (!hasSet(i))
        remainingVariables.add(getSquare(i));
    }
  }

  /*
   * FUNCTIONS NEEDED FOR CSP
   */

  // * Function to handle returning the next best variable to set
  protected Square nextVariable() {
    // * remainingVariables is a stack with all remaining empty variables
    // * The compareTo function of the Variable object handles selecting the best
    // variable
    Collections.sort(remainingVariables);
    return remainingVariables.poll();
  }

  // * Helper function to consistencyCheck that returns if a variable will work
  private boolean isConsistent(int index, int value, int indexToCompare) {
    if (!hasSet(indexToCompare))
      return true;
    if (getSquare(indexToCompare).getValue() == value)
      return false;
    return true;
  }

  // * Function to check if a value at an index is consistent and set domains
  protected boolean forwardCheck(int index, int value) {
    // * Check Vertical Line Constraints
    for (int i = 1; i < yFromIndex(index); i++) {
      int compareIndex = indexFromCoords(xFromIndex(index), i);
      constrainSquare(compareIndex, value);
      if (!isConsistent(index, value, compareIndex) || getSquare(index).getDomainCount() == 0)
        return false;
    }

    for (int i = yFromIndex(index) + 1; i <= 9; i++) {
      int compareIndex = indexFromCoords(xFromIndex(index), i);
      constrainSquare(compareIndex, value);
      if (!isConsistent(index, value, compareIndex) || getSquare(index).getDomainCount() == 0)
        return false;
    }

    // * Check Horiztonal Line Constraints
    for (int i = 1; i < xFromIndex(index); i++) {
      int compareIndex = indexFromCoords(i, yFromIndex(index));
      constrainSquare(compareIndex, value);
      if (!isConsistent(index, value, compareIndex) || getSquare(index).getDomainCount() == 0)
        return false;
    }
    for (int i = xFromIndex(index) + 1; i <= 9; i++) {
      int compareIndex = indexFromCoords(i, yFromIndex(index));
      constrainSquare(compareIndex, value);
      if (!isConsistent(index, value, compareIndex) || getSquare(index).getDomainCount() == 0)
        return false;
    }

    // Check In Suqare Constraints
    int squareStartY = index / 27 * 3 + 1;
    int squareStartX = index % 9 / 3 * 3 + 1;

    for (int x = 0; x < 3; x++) {
      for (int y = 0; y < 3; y++) {
        if (squareStartX + x == xFromIndex(index) || squareStartY + y == yFromIndex(index)) continue;
        int compareIndex = indexFromCoords(squareStartX + x, squareStartY + y);
        constrainSquare(compareIndex, value);
        if (!isConsistent(index, value, compareIndex) || getSquare(index).getDomainCount() == 0)
          return false;
      }
    }

    return true;
  }

  /*
   * BOARD GETTER SETTERS
   */

  // * Get the set value of a passed square
  protected Square getSquare(int x, int y) {
    return sudokuBoard[indexFromCoords(x, y)];
  }

  protected Square getSquare(int i) {
    return sudokuBoard[i];
  }

  // * Sets the value of a square and related info
  public boolean setSquare(int x, int y, int v) {
    sudokuBoard[indexFromCoords(x, y)].setValue(v);
    lastIndex = indexFromCoords(x, y);
    lastValue = v;

    return forwardCheck(indexFromCoords(x, y), v);
  }

  public boolean setSquare(int i, int v) {
    sudokuBoard[i].setValue(v);
    lastIndex = i;
    lastValue = v;

    return forwardCheck(i, v);
  }

  /*
   * INDEX MANIPULATION FOR 1-BASED ORDERED PAIR TO 0-BASED ARRAY INDEX
   */

  protected int indexFromCoords(int x, int y) {
    return 9 * (y - 1) + (x - 1);
  }

  protected int yFromIndex(int i) {
    return i / 9 + 1;
  }

  protected int xFromIndex(int i) {
    return i % 9 + 1;
  }

  /*
   * HELPER FUNCTIONS FOR SQUARE AND BOARD DATA
   */

  // * Check if the board has a square set
  protected boolean hasSet(int x, int y) {
    return (sudokuBoard[indexFromCoords(x, y)].getValue() != 0);
  }

  protected boolean hasSet(int i) {
    return (sudokuBoard[i].getValue() != 0);
  }

  protected boolean isFull() {
    return (remainingVariables.size() == 0);
  }

  protected void constrainSquare(int index, int value) {
    // test.remove(getSquare(index));
    getSquare(index).constrain(value);
    getSquare(index).removeDegree();
    // test.add(getSquare(index));
  }

  /*
   * PRINT FUNCTIONS
   */

  protected void printSquare() {
    System.out.println("-------------------------------------------------------");
    for (int i = 1; i <= 9; i++) {
      System.out.printf("|");
      for (int j = 1; j <= 9; j++) {
        System.out.printf("  %d  |", getSquare(indexFromCoords(j, i)).getValue());
      }
      System.out.printf("%n-------------------------------------------------------%n");
    }
  }
}