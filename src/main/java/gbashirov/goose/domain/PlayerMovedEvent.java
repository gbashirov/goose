package gbashirov.goose.domain;

public class PlayerMovedEvent implements Event {
  
  private final String player;
  private final int start;
  private final int end;
  private boolean bounce;
  
  public PlayerMovedEvent(Player p) {
    this.player = p.name();
    this.start = p.previous();
    this.end = p.space();
    this.bounce = false;
  }
  
  public PlayerMovedEvent(Player p, boolean bounced) {
    this.player = p.name();
    if (!bounced) {
      start = p.previous();
      end = Move.LAST_SPACE;
      bounce = false;
    } else {
      start = Move.LAST_SPACE;
      end = p.space();
      bounce = true;
    }
  }
  
  public String player() { return player; }
  public int start() { return start; }
  public int end() { return end; }
  public boolean bounce() { return bounce; }

}
