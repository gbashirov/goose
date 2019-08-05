package gbashirov.goose.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Game {
  
  private static final String ERR_WINNER = "More than one winner";
  
  private final List<Player> players;
  private final List<Event> events;
  
  public Game() {
   this.players = new ArrayList<Player>();
   this.events = new ArrayList<Event>();
  }
  
  public List<Event> events(int offset) {
    List<Event> es;
    if (offset >= events.size()) {
      es = Collections.emptyList();
    } else {
      es = events.subList(offset, events.size());
    }
    return es;
  }
  
  public List<Player> players() { return Collections.unmodifiableList(players); }
  
  /**
   * @return <code>null</code> if player already exists
   */
  public Player add(String name) {
    Player p = new Player(name);
    if (players.contains(p)) {
      events.add(new PlayerNotAddedEvent(p));
      return null;
    } else {
      players.add(p);
      events.add(new PlayerAddedEvent(p));
      return p;
    }
  }
  
  private Player player(String name) {
    return players.stream().filter(p -> p.name().equalsIgnoreCase(name)).findFirst().get();
  }
  
  public void move(String player, int diceOne, int diceTwo) {
    Move m = new Move(diceOne, diceTwo);
    move(player, m);
  }
  public void move(String player) {
    move(player, new Move());
  }
  private void move(String player, Move m) {
    Player p = player(player);
    events.add(new PlayerRolledEvent(p, m));
    events.addAll(m.apply(p));
    Optional<Player> w = winner();
    if (w.isPresent()) {
      events.add(new PlayerWinsEvent(p));
    }
  }
  
  public Optional<Player> winner() {
    List<Player> ws = players.stream().filter(p -> p.space() == Move.LAST_SPACE).collect(Collectors.toList());
    if (ws.size() > 1) {
      throw new IllegalStateException(ERR_WINNER);
    }
    return ws.size() > 0 ? Optional.of(ws.get(0)) : Optional.empty();
  }
  
}
