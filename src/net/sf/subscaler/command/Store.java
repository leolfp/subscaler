package net.sf.subscaler.command;

import net.sf.subscaler.sub.SubManager;

import java.io.PrintWriter;
import java.security.InvalidParameterException;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 22:34:38
 */
public class Store implements Command {
  public String getParametersHelp() {
    return "file\tOptional subtitles filename to save to";
  }

  public String getUsageHelp() {
    return "[file]";
  }

  public String getShortHelp() {
    return "Saves the subtitles as the given file";
  }

  public String getLongHelp() {
    return "Saves subtitles currently in use to the opened file, or to a new given file";
  }

  public String getCommandName() {
    return "store";
  }

  public void execute(SubManager subManager, String args, PrintWriter printWriter) throws InvalidParameterException {
    if (args == null || args.trim().equals("")) {
      subManager.storeDefaultSubCollection();
    } else {
      subManager.storeDefaultSubCollection(args.trim());
    }
  }
}
