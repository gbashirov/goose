package gbashirov.goose.domain;

public class PlayerNotAddedEvent implements Event {

  private final String player;
  
  public PlayerNotAddedEvent(Player p) {
    this.player = p.name();
  }
  
  public String player() { return player; }

}
