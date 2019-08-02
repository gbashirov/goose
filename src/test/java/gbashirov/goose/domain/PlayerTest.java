package gbashirov.goose.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
  
  private Player pippo;
  
  @Before
  public void setup() {
    pippo = new Player("Pippo");
  }

  @Test
  public void playerNameIsTheID() {
    assertEquals(new Player("Pippo"), pippo);
    assertNotEquals(new Player("Pluto"), pippo);
  }
  
  @Test
  public void playerNameIgnoreCase() {
    assertEquals(new Player("PiPPo"), pippo);
  }

}
