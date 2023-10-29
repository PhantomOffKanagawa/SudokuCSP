package Backend;

// * Basic object to hold info about a sudoku square
public class Variable implements Comparable<Variable> {
  private final byte index;
  private byte value;
  
  public Variable(int index, int value) {
    this.index = (byte) index;
    this.value = (byte) value;
  }

  public byte getIndex() {
    return index;
  }

  public byte getValue() {
    return value;
  }

  public void setValue(byte value) {
    this.value = value;
  }

  
    // Overriding compareTo() method
    @Override public int compareTo(Variable o)
    {
        if (this.value > o.value) {
 
            return -1;
        }
        else if (this.value < o.value) {
 
            return 1;
        }
        else {
 
            return 0;
        }
    }

}