package gbashirov.goose.domain;

public class PlayerRolledEvent implements Event {
  
  private final String player;
  private final int diceOne;
  private final int diceTwo;
  
  public PlayerRolledEvent(Player p, int d1, int d2) {
    this.player = p.name();
    this.diceOne = d1;
    this.diceTwo = d2;
  }
  
  public String player() { return player; }
  public int diceOne() { return diceOne; }
  public int diceTwo() { return diceTwo; }
  

}
