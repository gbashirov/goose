package gbashirov.goose.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Game {
  
  private static final String ERR_WINNER = "More than one winner";
  
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
  
  private Player player(String name) {
    return players.stream().filter(p -> p.name().equalsIgnoreCase(name)).findFirst().get();
  }
  
  public Player move(String player, int diceOne, int diceTwo) {
    Player p = player(player);
    new Move(diceOne, diceTwo).apply(p);
    return p;
  }
  
  public Optional<Player> winner() {
    List<Player> ws = players.stream().filter(p -> p.space() == Move.LAST_SPACE).collect(Collectors.toList());
    if (ws.size() > 1) {
      throw new IllegalStateException(ERR_WINNER);
    }
    return ws.size() > 0 ? Optional.of(ws.get(0)) : Optional.empty();
  }

}
