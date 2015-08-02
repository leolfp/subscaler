package net.sf.subscaler.command;

import net.sf.subscaler.sub.SubManager;

import java.io.Console;
import java.security.InvalidParameterException;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 20:58:08
 */
public class CommandController {
  private SubManager subManager;
  private Console console;

  public CommandController(SubManager subManager, Console console) {
    this.subManager = subManager;
    this.console = console;
  }

  public void executeCommandLoop() {
    String fullCommand;

    console.writer().println("SubScaler - a subtitle tool shell.\nType help for command explanations.");
    while ((fullCommand = console.readLine("> ")) != null) {
      fullCommand = fullCommand.trim();
      int pos = fullCommand.indexOf(' ');
      String commandName, args = null;
      if (pos != -1) {
        commandName = fullCommand.substring(0, pos);
        args = fullCommand.substring(pos + 1);
      } else {
        commandName = fullCommand;
      }

      Command command = CommandFactory.getCommandFor(commandName);
      if (command != null) {
        try {
          command.execute(this.subManager, args, console.writer());
        } catch (InvalidParameterException e) {
          console.writer().println("Invalid parameter(s) for command '" + commandName + "'. See usage on help.");
        }
      } else {
        console.writer().println("Unrecognized command '" + commandName + "'.");
      }
    }
  }
}
