package gbashirov.goose.domain;

public class PlayerNotAddedEvent implements PlayerEvent {

  private final String player;
  
  public PlayerNotAddedEvent(Player p) {
    this.player = p.name();
  }
  
  public String player() { return player; }

}
