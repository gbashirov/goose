package gbashirov.goose.domain;

import java.text.MessageFormat;

public class Player {
  
  private static final String ERR_EMPTY_NAME = "Player name may not be empty";
  private static final String ERR_SPACE_OUTSIDE = "Player {0} moved to the invalid space {1}";

  private final String name;
  private int space;
  private int spaceOld;
  
  public Player(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException(ERR_EMPTY_NAME);
    }
    this.name = name.trim();
    this.space = Move.FIRST_SPACE;
    this.spaceOld = Move.FIRST_SPACE;
  }
  
  public String name() { return name; }
  public int space() { return space; }
  public int previous() { return spaceOld; }
  
  /** 
   * @param n Destination space
   */
  public void move(int n) {
    if (n < Move.FIRST_SPACE || n > Move.LAST_SPACE) {
      throw new IllegalArgumentException(MessageFormat.format(ERR_SPACE_OUTSIDE, name, n));
    }
    spaceOld = space;
    space = n;
  }
  
  @Override
  public String toString() {
    return "Player [name=" + name + "]";
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.toUpperCase().hashCode());
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
    Player other = (Player) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equalsIgnoreCase(other.name))
      return false;
    return true;
  }

}
