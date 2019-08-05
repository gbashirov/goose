package gbashirov.goose.domain;

public class PlayerRolledEvent implements PlayerEvent {
  
  private final String player;
  private final int diceOne;
  private final int diceTwo;
  
  public PlayerRolledEvent(Player p, Move m) {
    this.player = p.name();
    this.diceOne = m.diceOne();
    this.diceTwo = m.diceTwo();
  }
  
  public String player() { return player; }
  public int diceOne() { return diceOne; }
  public int diceTwo() { return diceTwo; }
  

}
