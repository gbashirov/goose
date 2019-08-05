package gbashirov.goose.domain;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GameTest {
  
  private static final String PIPPO_NAME = "Pippo";
  private static final String PLUTO_NAME = "Pluto";
  private static final int PIPPO_SPACE = 5;
  
  private Player pippo;
  private Game game;
  
  @Before
  public void setup() {
    game = new Game();
    pippo = game.add(PIPPO_NAME);
    pippo.move(PIPPO_SPACE);
  }
  
  @Test
  public void addNewPlayer() {
    game.add(PLUTO_NAME);
    assertTrue(game.events(0).get(0) instanceof PlayerAddedEvent);
  }
  
  @Test
  public void preventDuplicatePlayer() {
    game.add(PIPPO_NAME);
    List<Event> es = game.events(1);
    assertTrue(es.get(0) instanceof PlayerNotAddedEvent);
  }
  
  @Test
  public void winnerNotPresent() {
    assertFalse(game.winner().isPresent());
  }
  
  @Test
  public void winnerPresent() {
    pippo.move(Move.LAST_SPACE);
    assertTrue(game.winner().isPresent());
  }

}
