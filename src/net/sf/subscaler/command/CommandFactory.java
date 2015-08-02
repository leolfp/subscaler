package net.sf.subscaler.command;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 22:24:09
 */
public class CommandFactory {
  private static Map<String, Command> commands;

  static {
    commands = new TreeMap<String, Command>();
    commands.put("index", new Index());
    commands.put("move", new Move());
    commands.put("help", new Help());
    commands.put("load", new Load());
    commands.put("quit", new Quit());
    commands.put("scale", new Scale());
    commands.put("search", new Search());
    commands.put("store", new Store());
    commands.put("info", new Info());
  }

  public static Command getCommandFor(String command) {
    return commands.get(command);
  }

  public static Collection<? extends Command> getAllCommands() {
    return Collections.unmodifiableCollection(commands.values());
  }
}
