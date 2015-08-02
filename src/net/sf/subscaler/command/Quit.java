package net.sf.subscaler.command;

import net.sf.subscaler.sub.SubManager;

import java.io.PrintWriter;
import java.security.InvalidParameterException;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 22:35:40
 */
public class Quit implements Command {
  public String getParametersHelp() {
    return null;
  }

  public String getUsageHelp() {
    return null;
  }

  public String getShortHelp() {
    return "Terminates this shell";
  }

  public String getLongHelp() {
    return "Terminates this SubScaler shell, losing non-saved changes.";
  }

  public String getCommandName() {
    return "quit";
  }

  public void execute(SubManager subManager, String args, PrintWriter printWriter) throws InvalidParameterException {
    System.exit(0);
  }
}
