package gbashirov.goose.domain;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MoveTest {
  
  private Player pippo;
  
  @Before
  public void setup() {
    pippo = new Player("Pippo");
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
    assertEquals(3, pippo.space());
    new Move(1,3).apply(pippo);
    assertEquals(3 + 4, pippo.space());
  }
  
  
  @Test
  public void moveForewardOnBridge() {
    pippo.move(Move.BRIDGE_SPACE-2);
    List<PlayerMovedEvent> es = new Move(1,1).apply(pippo);
    assertEquals(Move.BRIDGE_SPACE * 2, pippo.space());
    assertEquals(Move.BRIDGE_SPACE, es.get(0).end());
    assertEquals(Move.BRIDGE_SPACE, es.get(1).start());
  }
  
  @Test
  public void moveForewardOnGoose() {
    pippo.move(3);
    List<PlayerMovedEvent> es = new Move(1,1).apply(pippo);
    assertEquals(3 + 2 * 2, pippo.space());
    assertEquals(2, es.size());
  }
  
  @Test
  public void moveForewardOnTwoGoose() {
    pippo.move(10);
    List<PlayerMovedEvent> es = new Move(2,2).apply(pippo);
    assertEquals(10 + 4 * 3, pippo.space());
    assertEquals(3, es.size());
  }
  
}
