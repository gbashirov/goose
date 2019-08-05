package gbashirov.goose.domain;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MoveTest {
  
private static final int PIPPO_SPACE = 5;
  
  private Player pippo;
  
  @Before
  public void setup() {
    pippo = new Player("Pippo");
    pippo.move(PIPPO_SPACE);
  }
  
  @Test
  public void moveBackwardWhenExeeded() {
    pippo.move(Move.LAST_SPACE - 1);
    List<PlayerMovedEvent> es = new Move(2,2).apply(pippo);
    assertEquals(Move.LAST_SPACE - 3, pippo.space());
    assertEquals(Move.LAST_SPACE, es.get(0).end());
    assertEquals(Move.LAST_SPACE, es.get(1).start());
  }
  
  @Test
  public void moveTwoTimes() {
    new Move(2,1).apply(pippo);
    assertEquals(PIPPO_SPACE + 3, pippo.space());
    new Move(1,1).apply(pippo);
    assertEquals(PIPPO_SPACE + 3 + 2, pippo.space());
  }
  
  
  @Test
  public void moveForewardOnBridge() {
    pippo.move(Move.BRIDGE_SPACE-2);
    List<PlayerMovedEvent> es = new Move(1,1).apply(pippo);
    assertEquals(Move.BRIDGE_SPACE * 2, pippo.space());
    assertEquals(Move.BRIDGE_SPACE, es.get(0).end());
    assertEquals(Move.BRIDGE_SPACE, es.get(1).start());
  }

}
