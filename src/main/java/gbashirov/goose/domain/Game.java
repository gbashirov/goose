package gbashirov.goose.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
  
  private final List<Player> players;
  
  public Game() {
   this.players = new ArrayList<Player>();
  }
  
  public List<Player> players() { return Collections.unmodifiableList(players); }
  
  /**
   * @return <code>false</code> if player already exists
   */
  public boolean add(Player p) {
    if (players.contains(p)) {
      return false;
    }
    players.add(p);
    return true;
  }

}
