package gbashirov.goose;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import gbashirov.goose.domain.Event;
import gbashirov.goose.domain.Game;
import gbashirov.goose.domain.Move;
import gbashirov.goose.domain.PlayerAddedEvent;
import gbashirov.goose.domain.PlayerMovedEvent;
import gbashirov.goose.domain.PlayerNotAddedEvent;
import gbashirov.goose.domain.PlayerRolledEvent;
import gbashirov.goose.domain.PlayerWinsEvent;

public class ShellController {
  
  public static final String SEPARATOR = " ";
  public static final String SPACE = " ";
  public static final String COMMA = ",";
  public static final String DOT = ".";
  public static final String EMPTY_STRING = "";
  
  public static final String CMD_ADD = "add";
  public static final String CMD_ADD_PLAYER = "player";
  public static final String CMD_MOVE = "move";
  
  public static final String MSG_PLAYERS = "players: ";
  public static final String MSG_PLAYER_EXISTS = "{0}: already existing player.";
  public static final String MSG_ROLL= "{0} rolls {1}, {2}.";
  public static final String MSG_MOVED = "{0} moves from {1} to {2}.";
  public static final String MSG_BOUNCE = "{0} bounces! {0} returns to {1}.";
  
  public static final String MSG_WIN = "{0} Wins!!";
  
  private final Scanner in;
  private final PrintStream out;
  private final Game game;
  private int gameOffset;
  
  public ShellController(Game game, InputStream in, OutputStream out) {
    this(game, 0, in, out);
  }
  public ShellController(Game game, int gameOffset, InputStream in, OutputStream out) {
    this.in = new Scanner(in);
    this.out = new PrintStream(out);
    this.game = game;
    this.gameOffset = gameOffset;
  }
  
  public final void execute() {
    while (in.hasNextLine()) {
      List<String> command =  splitCommand(in.nextLine());
      try {
        if (matchCommand(command, 0, CMD_ADD) && matchCommand(command, 1, CMD_ADD_PLAYER)) {
          game.add(command.get(2));
        } else if (matchCommand(command, 0, CMD_MOVE)) {
          if (command.size() > 2) {
            int d1 = Integer.parseInt(command.get(2));
            int d2 = Integer.parseInt(command.get(3));
            game.move(command.get(1), d1, d2);
          }
          game.move(command.get(1));
        }
      } catch (IllegalArgumentException e) {
        e.printStackTrace(out);
      }
      for (Event e : game.events(gameOffset)) {
        gameOffset = gameOffset + 1;
        out.print(message(e));
        out.print(SPACE);
        if (e instanceof PlayerWinsEvent) {
           System.exit(0);
        }
      }
      out.print(System.lineSeparator());
    }
  }
  
  private String message(Event e) {
    if (e instanceof PlayerAddedEvent) {
      String players = game.players().stream().map(p -> p.name()).collect(Collectors.joining(", "));
      return MSG_PLAYERS  + players;
    } else if (e instanceof PlayerNotAddedEvent) {
      return MessageFormat.format(MSG_PLAYER_EXISTS, ((PlayerNotAddedEvent) e).player());
    } else if (e instanceof PlayerRolledEvent) {
      PlayerRolledEvent pe = (PlayerRolledEvent) e;
      return MessageFormat.format(MSG_ROLL, e.player(), pe.diceOne(), pe.diceTwo());
    } else if (e instanceof PlayerMovedEvent) {
      PlayerMovedEvent pe = (PlayerMovedEvent) e;
      if (pe.bounce()) {
        return MessageFormat.format(MSG_BOUNCE, e.player(), pe.end());
      }
      return MessageFormat.format(MSG_MOVED, e.player(), pe.start() == Move.FIRST_SPACE ? "Start" : pe.start(), pe.end());
    } else if (e instanceof PlayerWinsEvent) {
      return MessageFormat.format(MSG_WIN, e.player());
    } else {
      throw new UnsupportedOperationException(e.getClass().toString());
    }
  }
  
  protected static final boolean matchCommand(List<String> actual, int n, String expected) {
    return n < actual.size() && actual.get(n) != null && actual.get(n).trim().toUpperCase().startsWith(expected.trim().toUpperCase());
  }
  
  protected static final List<String> splitCommand(String command) {
    List<String> a = Arrays.<String>asList(command.split(SEPARATOR));
    a = a.stream()
      .filter(s -> !EMPTY_STRING.equals(s) && !COMMA.equals(s))
      .map(s -> s.endsWith(COMMA) ? s.substring(0, s.length()-1) : s)
      .collect(Collectors.toList());
    return a;
  }
  
}
