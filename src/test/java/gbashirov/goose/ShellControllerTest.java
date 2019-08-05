package gbashirov.goose;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class ShellControllerTest {
  
  @Test
  public void commandSplitIgnoreSpace() {
    List<String> list = ShellController.splitCommand(" add   player Jim  ");
    assertEquals(3, list.size());
    assertEquals("add", list.get(0));
  }
  
  @Test
  public void commandMatchIgnoreCase() {
    List<String> command = ShellController.splitCommand("add plAyer  ");
    assertTrue(ShellController.matchCommand(command, 0, "ADD"));
    assertTrue(ShellController.matchCommand(command, 1, "player"));
  }
  
}
