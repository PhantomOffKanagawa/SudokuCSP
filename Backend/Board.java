package Backend;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class Board {
  // * Initialize Variables to track: Board itself
  private final Square[] sudokuBoard;
  private final PriorityQueue<Variable> remainingVariables;

  // * Initialize a new board with blank sets
  public Board() {
    sudokuBoard = new Square[81];
    for (int i = 0; i < 81; i++) {
      sudokuBoard[i] = new Square();
    }

    remainingVariables = new PriorityQueue<Variable>();
  }

  public Board(Board board, int index, int v) {
    sudokuBoard = new Square[81];
    for (var i = 0; i < 81; i++) {
      sudokuBoard[i] = new Square(board.getSquare(i));
    }

    this.remainingVariables = new PriorityQueue<>(board.remainingVariables);

    setSquare(index, v);
  }

  public int remainingVariablesCount() {
    return remainingVariables.size();
  }

  protected void initializeRemainingVariables() {
    for (int i = 0; i < 81; i++) {
      if (!hasSet(i))
        remainingVariables.add(new Variable(i, getSquare(i).getDomain().length));
    }
  }

  protected Variable nextVariable() {
    return remainingVariables.poll();
  }

  private boolean isConsistent(int index, int value, int indexToCompare) {
    if (!hasSet(indexToCompare))
      return true;
    if (getSquare(indexToCompare).getValue() == value)
      return false;
    return true;
  }

  protected boolean consistencyCheck(int index, int value) {
    // * Check Vertical Line Constraints
    for (int i = 1; i < yFromIndex(index); i++) {
      int compareIndex = indexFromCoords(xFromIndex(index), i);
      if (!isConsistent(index, value, compareIndex))
        return false;
    }

    for (int i = yFromIndex(index) + 1; i <= 9; i++) {
      int compareIndex = indexFromCoords(xFromIndex(index), i);
      if (!isConsistent(index, value, compareIndex))
        return false;
    }

    // * Check Horiztonal Line Constraints
    for (int i = 1; i < xFromIndex(index); i++) {
      int compareIndex = indexFromCoords(i, yFromIndex(index));
      if (!isConsistent(index, value, compareIndex))
        return false;
    }
    for (int i = xFromIndex(index) + 1; i <= 9; i++) {
      int compareIndex = indexFromCoords(i, yFromIndex(index));
      if (!isConsistent(index, value, compareIndex))
        return false;
    }

    // Check In Suqare Constraints
    // TODO optimize to remove horizontal and vertical repeats

    int squareStartY = index / 27 * 3 + 1;
    int squareStartX = index % 9 / 3 * 3 + 1;

    for (int x = 0; x < 3; x++) {
      for (int y = 0; y < 3; y++) {
        int compareIndex = indexFromCoords(squareStartX + x, squareStartY + y);
        if (!isConsistent(index, value, compareIndex))
          return false;
      }
    }

    return true;
  }

  // * Sets the value of a square and related info
  public void setSquare(int x, int y, int v) {
    sudokuBoard[indexFromCoords(x, y)].setValue(v);
  }

  public void setSquare(int i, int v) {
    sudokuBoard[i].setValue(v);
  }

  public void testCoords() {
    System.out.printf("%d, %d Expected, %d, %d Actual %n", 0, 0, indexFromCoords(2, 2) / 27,
        indexFromCoords(2, 2) % 9 / 3);
    System.out.printf("%d, %d Expected, %d, %d Actual %n", 3, 3, (indexFromCoords(1, 4)) / 27 * 3,
        indexFromCoords(9, 4) % 9 / 3 * 3);
    // System.out.printf("%d Expected, %d Actual %n", 8,
    // yFromIndex(indexFromCoords(4, 9)));
    // System.out.printf("%d Expected, %d Actual %n", 3,
    // xFromIndex(indexFromCoords(4, 9)));
  }

  protected int indexFromCoords(int x, int y) {
    return 9 * (y - 1) + (x - 1);
  }

  protected int yFromIndex(int i) {
    return i / 9 + 1;
  }

  protected int xFromIndex(int i) {
    return i % 9 + 1;
  }

  // * Get the set value of a passed square
  protected Square getSquare(int x, int y) {
    return sudokuBoard[indexFromCoords(x, y)];
  }

  protected Square getSquare(int i) {
    return sudokuBoard[i];
  }

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