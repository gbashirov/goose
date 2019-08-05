package gbashirov.goose.domain;

public class PlayerWinsEvent implements PlayerEvent {
  
  private final String player;
  
  public PlayerWinsEvent(Player p) {
    this.player = p.name();
  }
  
  public String player() { return player; }

}
