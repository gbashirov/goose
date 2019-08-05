package gbashirov.goose;

import gbashirov.goose.domain.Game;

public class App {

  public static void main(String[] args) {
    ShellController c = new ShellController(new Game(), System.in, System.out);
    c.execute();
  }

}
