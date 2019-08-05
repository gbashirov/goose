package gbashirov.goose.domain;

public class PlayerMovedEvent implements PlayerEvent {
  
  private final String player;
  private final int start;
  private final int end;
  
  public PlayerMovedEvent(Player p) {
    this.player = p.name();
    this.start = p.previous();
    this.end = p.space();
  }
  
  public String player() { return player; }
  public int start() { return start; }
  public int end() { return end; }

}
