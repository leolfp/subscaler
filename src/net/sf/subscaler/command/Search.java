package net.sf.subscaler.command;

import net.sf.subscaler.sub.Sub;
import net.sf.subscaler.sub.SubManager;

import java.io.PrintWriter;
import java.security.InvalidParameterException;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 22:34:49
 */
public class Search implements Command {
  public String getParametersHelp() {
    return "text\tA text to be searched in all subs";
  }

  public String getUsageHelp() {
    return "<text>";
  }

  public String getShortHelp() {
    return "Finds subtitles that matches the given text";
  }

  public String getLongHelp() {
    return "Finds subtitles that matches text, listing their keys and contents";
  }

  public String getCommandName() {
    return "search";
  }

  public void execute(SubManager subManager, String args, PrintWriter printWriter) throws InvalidParameterException {
    if (args == null || args.trim().equals("")) {
      throw new InvalidParameterException();
    }

    Map<Integer, Sub> subs = subManager.getDefaultSubCollection().search(args);
    Set<Integer> keys = subs.keySet();

    for (Integer key : keys) {
      Sub sub = subs.get(key);
      printWriter.println(key + ": " + sub.getReplacedText());
    }
  }
}
