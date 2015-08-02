package net.sf.subscaler.command;

import net.sf.subscaler.sub.SubManager;

import java.io.PrintWriter;
import java.security.InvalidParameterException;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 22:34:32
 */
public class Load implements Command {
  public String getParametersHelp() {
    return "file\tSubtitles filename to load";
  }

  public String getUsageHelp() {
    return "<file>";
  }

  public String getShortHelp() {
    return "Loads the given subtitle file";
  }

  public String getLongHelp() {
    return "Loads a given subtitle file to work with";
  }

  public String getCommandName() {
    return "load";
  }

  public void execute(SubManager subManager, String fileName, PrintWriter printWriter) throws InvalidParameterException {
    if (fileName == null || fileName.trim().equals("")) {
      throw new InvalidParameterException();
    }

    subManager.loadDefaultSubCollection(fileName);
  }
}
