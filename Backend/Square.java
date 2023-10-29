package Backend;

// * Basic object to hold info about a sudoku square
public class Square {
  private byte value = 0;
  private boolean[] domain = { true, true, true, true, true, true, true, true, true };

  public Square() {
    
  }

  public Square(Square square) {
    this.value = square.value;
    this.domain = square.domain.clone();
  }

  public int getValue() {
    return ((int) this.value);
  }

  public void setValue(int value) {
    this.value = (byte) value;
    domain[value - 1] = false;
  }

  public boolean[] getDomain() {
    return (this.domain);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + value;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Square other = (Square) obj;
    if (value != other.value)
      return false;
    return true;
  }

}