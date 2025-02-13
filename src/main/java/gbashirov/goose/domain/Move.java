package gbashirov.goose.domain;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Move {
  
  public static final int FIRST_SPACE = 0;
  public static final int LAST_SPACE = 63;
  public static final int BRIDGE_SPACE = 6;
  public static final List<Integer> GOOSE_SPACES = Arrays.asList(5, 9, 14, 18, 23, 27);
  
  private static final int DICE_MIN = 1;
  private static final int DICE_MAX = 6;
  private static final String MSG_DICE = "Value {0} is not a valid dice outcome";
  
  private final int diceOne;
  private final int diceTwo;
  private final int val;
  
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
  
  public final List<PlayerMovedEvent> apply(Player p) {
    List<PlayerMovedEvent> es = new ArrayList<PlayerMovedEvent>();
    int s = p.space() + val;
    // No goose space if close to the end
    if (p.space() + val > LAST_SPACE) {
      p.move(LAST_SPACE);
      es.add(new PlayerMovedEvent(p));
      p.move(LAST_SPACE - (s - LAST_SPACE));
      es.add(new PlayerMovedEvent(p));
    } else {
      p.move(s);
      es.add(new PlayerMovedEvent(p));
    }
    while (GOOSE_SPACES.contains(s)) {
      s = p.space() + val;
      p.move(s);
      es.add(new PlayerMovedEvent(p));
    }
    if (p.space() == BRIDGE_SPACE) {
      p.move(BRIDGE_SPACE * 2);
      es.add(new PlayerMovedEvent(p));
    }
    return es;
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
