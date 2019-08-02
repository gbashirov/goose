package gbashirov.goose.domain;

public class Player {
  
  private static final String ERR_EMPTY_NAME = "Player name may not be empty";

  private final String name;
  
  public Player(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException(ERR_EMPTY_NAME);
    }
    this.name = name;
  }
  
  public String name() { return name; }
  
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
