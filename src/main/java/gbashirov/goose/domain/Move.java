package gbashirov.goose.domain;

import java.text.MessageFormat;

public class Move {
  
  public static final int FIRST_SPACE = 1;
  public static final int LAST_SPACE = 63;
  
  private static final int DICE_MIN = 1;
  private static final int DICE_MAX = 6;
  private static final String MSG_DICE = "Value {0} is not a valid dice outcome";
  
  private final int val;
  
  public Move(int d1, int d2) {
    if (!checkValidDice(d1)) {
      throw new IllegalArgumentException(MessageFormat.format(MSG_DICE, d1));
    }
    if (!checkValidDice(d2)) {
      throw new IllegalArgumentException(MessageFormat.format(MSG_DICE, d2));
    }
    this.val = d1 + d2;
  }
  
  public final void apply(Player p) {
    int s = p.space() + val;
    if (s > LAST_SPACE) {
      s = LAST_SPACE - (s - LAST_SPACE);
    }
    p.move(s);
  }
  
  private static boolean checkValidDice(int d) {
    return d >= DICE_MIN && d <= DICE_MAX;
  }

}
