package gbashirov.goose;

public class App {

  public static void main(String[] args) {
      ShellController c = new ShellController(System.in, System.out);
      c.execute();
  }

}
