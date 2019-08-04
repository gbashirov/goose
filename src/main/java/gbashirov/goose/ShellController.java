package gbashirov.goose;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import gbashirov.goose.domain.Game;
import gbashirov.goose.domain.Player;

public class ShellController {
  
  public static final String SEPARATOR = " ";
  public static final String SPACE = " ";
  public static final String COMMA = ",";
  public static final String EMPTY_STRING = "";
  
  public static final String CMD_ADD = "add";
  public static final String CMD_ADD_PLAYER = "player";
  public static final String CMD_MOVE = "move";
  
  public static final String MSG_PLAYERS = "players: ";
  public static final String MSG_PLAYER_EXISTS = "{0}: already existing player";
  public static final String MSG_MOVED = "{0} rolls {1}, {2}. {0} moves from {3} to {4}";
  public static final String MSG_WIN = " {0} Wins!!";
  
  private final Scanner in;
  private final PrintStream out;
  private final Game game;
  
  public ShellController(Game game, InputStream in, OutputStream out) {
    this.in = new Scanner(in);
    this.out = new PrintStream(out);
    this.game = game;
  }
  
  public final void execute() {
    while (in.hasNextLine()) {
      List<String> command =  splitCommand(in.nextLine());
      try {
        if (matchCommand(command.get(0), CMD_ADD) && matchCommand(command.get(1), CMD_ADD_PLAYER)) {
          Player p = new Player(command.get(2));
          boolean added = game.add(p);
          if (added) {
            printPlayers();
          } else {
            out.print(MessageFormat.format(MSG_PLAYER_EXISTS, p.name()));
          }
        } else if (matchCommand(command.get(0), CMD_MOVE)) {
          int d1 = Integer.parseInt(command.get(2));
          int d2 = Integer.parseInt(command.get(3));
          Player p = game.move(command.get(1), d1, d2);
          out.print(MessageFormat.format(MSG_MOVED, p.name(), d1, d2, p.previous(), p.space()));
        }
      } catch (IllegalArgumentException e) {
        e.printStackTrace(out);
      }
      Optional<Player> w = game.winner();
      if (w.isPresent()) {
        out.print(MessageFormat.format(MSG_WIN, w.get().name()));
      }
      out.print(System.lineSeparator());
    }
  }
  
  protected static final boolean matchCommand(String actual, String expected) {
    return actual != null && actual.trim().toUpperCase().startsWith(expected.trim().toUpperCase());
  }
  
  protected static final List<String> splitCommand(String command) {
    List<String> a = Arrays.<String>asList(command.split(SEPARATOR));
    a = a.stream()
      .filter(s -> !EMPTY_STRING.equals(s) && !COMMA.equals(s))
      .map(s -> s.endsWith(COMMA) ? s.substring(0, s.length()-1) : s)
      .collect(Collectors.toList());
    return a;
  }
  
  private final void printPlayers() {
    String players = game.players().stream().map(p -> p.name()).collect(Collectors.joining(", "));
    out.print(MSG_PLAYERS  + players);
  }

}
