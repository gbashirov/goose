package gbashirov.goose.domain;

import static org.junit.Assert.*;

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
    pippo = new Player(PIPPO_NAME);
    pippo.move(PIPPO_SPACE);
    game = new Game();
    game.add(pippo);
  }
  
  @Test
  public void addNewPlayer() {
    assertTrue(game.add(new Player(PLUTO_NAME)));
  }
  
  @Test
  public void preventDuplicatePlayer() {
    assertFalse(game.add(new Player(PIPPO_NAME)));
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
