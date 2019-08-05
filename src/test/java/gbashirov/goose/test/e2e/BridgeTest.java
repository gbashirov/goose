package gbashirov.goose.test.e2e;

import gbashirov.goose.ShellController;
import gbashirov.goose.domain.Game;
import gbashirov.goose.domain.Move;
import gbashirov.goose.domain.Player;

/**
 * The game starts with Pippo player on space 10.
 * Write "move pippo 1, 1" in console and verify that Pippo wins.
 */
public class BridgeTest {
  
  public static void main(String[] args) {
    Game g = new Game();
    Player p = g.add("Pippo");
    p.move(Move.BRIDGE_SPACE - 2);
    ShellController c = new ShellController(g, 1, System.in, System.out);
    c.execute();
  }

}
