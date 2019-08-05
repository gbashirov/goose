package gbashirov.goose.domain;

public class PlayerAddedEvent implements Event {

  private final String player;
  
  public PlayerAddedEvent(Player p) {
    this.player = p.name();
  }
  
  public String player() { return player; }

}
