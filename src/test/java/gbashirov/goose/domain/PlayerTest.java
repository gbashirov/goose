package gbashirov.goose.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
  
  private static final int PIPPO_SPACE = 5;
  
  private Player pippo;
  
  @Before
  public void setup() {
    pippo = new Player("Pippo");
    pippo.move(PIPPO_SPACE - 1);
  }

  @Test
  public void playerNameIsTheID() {
    assertEquals(new Player("Pippo"), pippo);
    assertNotEquals(new Player("Pluto"), pippo);
  }
  
  @Test
  public void playerNameDoIgnoreCase() {
    assertEquals(new Player("PiPPo"), pippo);
  }
  
  @Test
  public void moveBackward() {
    final int n = -3;
    pippo.move(n);
    assertEquals(PIPPO_SPACE + n, pippo.space());
  }
  
  @Test
  public void moveTwoTimes() {
    final int n = 3;
    final int m = 2;
    pippo.move(n);
    assertEquals(PIPPO_SPACE + n, pippo.space());
    pippo.move(m);
    assertEquals(PIPPO_SPACE + n + m, pippo.space());
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void moveOutsideStart() {
    pippo.move(-1 - PIPPO_SPACE);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void moveOutsideEnd() {
    pippo.move(Player.END_SPACE);
  }

}
