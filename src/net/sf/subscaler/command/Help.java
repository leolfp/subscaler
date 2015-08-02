package net.sf.subscaler.command;

import net.sf.subscaler.sub.SubManager;

import java.io.PrintWriter;
import java.security.InvalidParameterException;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 22:34:28
 */
public class Help implements Command {
  public String getParametersHelp() {
    return "cmdName\tAn optional command to obtain detailed help";
  }

  public String getUsageHelp() {
    return "[cmdName]";
  }

  public String getShortHelp() {
    return "Prints this help";
  }

  public String getLongHelp() {
    return "Prints summary help or detailed help for a given command";
  }

  public String getCommandName() {
    return "help";
  }

  public void execute(SubManager subManager, String commandName, PrintWriter printWriter) throws InvalidParameterException {
    if (commandName == null || commandName.equals("")) {
      summaryHelp(printWriter);
    } else {
      commandName = commandName.trim().toLowerCase();
      detailedHelp(commandName, CommandFactory.getCommandFor(commandName), printWriter);
    }
  }

  private void detailedHelp(String commandName, Command command, PrintWriter printWriter) {
    if (command != null) {
      String commandHelp = command.getLongHelp();
      String usageHelp = command.getUsageHelp();
      if (commandHelp != null) {
        printWriter.println(commandHelp + ".");
        if (usageHelp != null) {
          printWriter.println("Usage: " + commandName + " " + usageHelp);
          printWriter.println(command.getParametersHelp());
        } else {
          printWriter.println("Usage: " + command.getCommandName());
        }
      } else {
        printWriter.println("No detailed help for command '" + commandName + "'.");
      }
    } else {
      printWriter.println("Unrecognized command '" + commandName + "'.");
    }
  }

  private void summaryHelp(PrintWriter printWriter) {
    Collection<? extends Command> commands = CommandFactory.getAllCommands();

    printWriter.println("Available commands:");
    for (Command command : commands) {
      printWriter.println(command.getCommandName() + "\t" + command.getShortHelp());
    }
  }
}
