package gbashirov.goose.domain;

import java.text.MessageFormat;
import java.util.Random;

public class Move {
  
  public static final int FIRST_SPACE = 0;
  public static final int LAST_SPACE = 63;
  
  private static final int DICE_MIN = 1;
  private static final int DICE_MAX = 6;
  private static final String MSG_DICE = "Value {0} is not a valid dice outcome";
  
  private final int diceOne;
  private final int diceTwo;
  private final int val;
  private Boolean bounced;
  
  public Move(int d1, int d2) {
    this.diceOne = d1;
    this.diceTwo = d2;
    this.val = calVal();
  }
  public Move() {
    Random r = new Random();
    this.diceOne = r.nextInt(DICE_MAX-1)+1;
    this.diceTwo = r.nextInt(DICE_MAX-1)+1;
    this.val = calVal();
  }
  
  public int diceOne() { return diceOne; }
  public int diceTwo() { return diceTwo; }
  
  public final void apply(Player p) {
    int s = p.space() + val;
    if (p.space() + val > LAST_SPACE) {
      s = LAST_SPACE - (s - LAST_SPACE);
      bounced = true;
    }
    p.move(s);
  }
  
  public boolean bounced(Player p) {
    return bounced;
  }
  
  private static boolean checkValidDice(int d) {
    return d >= DICE_MIN && d <= DICE_MAX;
  }
  
  private int calVal() {
    if (!checkValidDice(diceOne)) {
      throw new IllegalArgumentException(MessageFormat.format(MSG_DICE, diceOne));
    }
    if (!checkValidDice(diceTwo)) {
      throw new IllegalArgumentException(MessageFormat.format(MSG_DICE, diceTwo));
    }
    return diceOne + diceTwo;
  }

}
