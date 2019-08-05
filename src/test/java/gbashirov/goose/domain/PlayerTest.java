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
    pippo.move(PIPPO_SPACE);
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
  
  @Test(expected=IllegalArgumentException.class)
  public void moveOutsideStart() {
    pippo.move(0);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void moveOutsideEnd() {
    pippo.move(Move.LAST_SPACE+1);
  }

}
