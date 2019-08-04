package gbashirov.goose.test.e2e;

import gbashirov.goose.ShellController;
import gbashirov.goose.domain.Game;
import gbashirov.goose.domain.Move;
import gbashirov.goose.domain.Player;

/**
 * The game starts with Pippo player on space 61.
 * Write "move pippo 1, 1" in console and verify that Pippo wins.
 */
public class WinnerTest {
  
  public static void main(String[] args) {
    Game g = new Game();
    Player p = new Player("Pippo");
    g.add(p);
    p.move(Move.LAST_SPACE - 2);
    ShellController c = new ShellController(g, System.in, System.out);
    c.execute();
  }

}
