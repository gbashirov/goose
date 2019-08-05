package gbashirov.goose.domain;

public class PlayerMovedEvent implements Event {
  
  private final String player;
  private final int diceOne;
  private final int diceTwo;
  private final int start;
  private final int end;
  
  public PlayerMovedEvent(Player p, int d1, int d2) {
    this.player = p.name();
    this.diceOne = d1;
    this.diceTwo = d2;
    this.start = p.space();
    this.end = p.previous();
  }
  
  public String player() { return player; }
  public int diceOne() { return diceOne; }
  public int diceTwo() { return diceTwo; }
  public int start() { return start; }
  public int end() { return end; }
  

}
