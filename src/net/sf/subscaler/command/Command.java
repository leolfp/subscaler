package net.sf.subscaler.command;

import net.sf.subscaler.sub.SubManager;

import java.io.PrintWriter;
import java.security.InvalidParameterException;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 22:24:15
 */
public interface Command {
  public String getParametersHelp();

  public String getUsageHelp();

  public String getShortHelp();

  public String getLongHelp();

  public String getCommandName();

  public void execute(SubManager subManager, String args, PrintWriter printWriter) throws InvalidParameterException;
}
