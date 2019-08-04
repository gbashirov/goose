package gbashirov.goose;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import gbashirov.goose.domain.Game;
import gbashirov.goose.domain.Player;

public class ShellController {
  
  public static final String SEPARATOR = " ";
  public static final String SPACE = " ";
  public static final String EMPTY_STRING = "";
  
  public static final String CMD_ADD = "add player";
  
  public static final String MSG_PLAYERS = "players: ";
  
  private final Scanner in;
  private final PrintStream out;
  private final Game game;
  
  public ShellController(InputStream in, OutputStream out) {
    this.in = new Scanner(in);
    this.out = new PrintStream(out);
    this.game = new Game();
  }
  
  public final void execute() {
    while (in.hasNextLine()) {
      List<String> command =  splitCommand(in.nextLine());
      if (matchCommand(command.get(0) + SEPARATOR + command.get(1), CMD_ADD)) {
        game.add(new Player(command.get(2)));
        printPlayers();
      }
    }
  }
  
  protected static final boolean matchCommand(String actual, String expected) {
    return actual != null && actual.trim().toUpperCase().startsWith(expected.trim().toUpperCase());
  }
  
  protected static final List<String> splitCommand(String command) {
    List<String> a = Arrays.<String>asList(command.split(SEPARATOR));
    a = a.stream().filter(s -> !EMPTY_STRING.equals(s)).collect(Collectors.toList());
    return a;
  }
  
  private final void printPlayers() {
    String players = game.players().stream().map(p -> p.name()).collect(Collectors.joining(", "));
    out.println(MSG_PLAYERS + SPACE + players);
  }

}
