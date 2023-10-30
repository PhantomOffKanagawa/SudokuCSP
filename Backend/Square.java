package Backend;

// * Basic object to hold info about a sudoku square
public class Square implements Comparable<Square> {
  private byte value = 0;
  private boolean[] domain = { true, true, true, true, true, true, true, true, true };
  private byte domainCount = 9;
  private byte degree = 20;
  private final byte index;

  public Square(int index) {
    this.index = (byte) index;
  }

  public Square(Square square) {
    this.value = square.value;
    this.domain = square.domain.clone();
    this.domainCount = square.domainCount;
    this.degree = square.degree;
    this.index = square.index;
  }

  public void constrain(int c) {
    if (domain[c - 1]) {
      domainCount--;
    }
    domain[c - 1] = false;
  }

  public void removeDegree() {
    degree--;
  }

  public int getDegree() {
    return degree;
  }

  public int getIndex() {
    return this.index;
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

  public int getDomainCount() {
    return this.domainCount;
  }

  // Overriding compareTo() method
  @Override
  public int compareTo(Square o) {
    if (this.domainCount < o.domainCount) {
      return -1;
    } else if (this.domainCount > o.domainCount) {
      return 1;
    } else {
      if (this.degree > o.degree) {
        return -1;
      } else if (this.degree < o.degree) {
        return 1;
      } else {
        if (this.index < o.index) {
          return -1;
        } else if (this.index > o.index) {
          return 1;
        } else {
          return 0;
        }
      }
    }
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