package net.sf.subscaler.command;

import net.sf.subscaler.sub.Sub;
import net.sf.subscaler.sub.SubManager;

import java.io.PrintWriter;
import java.security.InvalidParameterException;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 09/07/2007
 * Time: 00:07:58
 */
public class Info implements Command {
  public String getParametersHelp() {
    return "subKey\tAn optional key that identifies a sub to get info";
  }

  public String getUsageHelp() {
    return "[subKey]";
  }

  public String getShortHelp() {
    return "Show informations about loaded subtitles";
  }

  public String getLongHelp() {
    return "Show informations about loaded subtitles, like fileName and number of subs";
  }

  public String getCommandName() {
    return "info";
  }

  public void execute(SubManager subManager, String args, PrintWriter printWriter) throws InvalidParameterException {
    if (subManager.getFileName() == null) {
      printWriter.println("No loaded subtitles file.");
    } else if (args == null || args.trim().equals("")) {
      printWriter.println("Loaded file: " + subManager.getFileName());
      printWriter.println("(" + subManager.getDefaultSubCollection().getSize() + " subs loaded)");
    } else {
      int key = Integer.parseInt(args.trim());
      if (key >= 0 && key < subManager.getDefaultSubCollection().getSize()) {
        Sub sub = subManager.getDefaultSubCollection().getSub(key);
        printWriter.println("Start:  " + TimeView.tickToString(sub.getTimeSpan().getStart()));
        printWriter.println("Finish: " + TimeView.tickToString(sub.getTimeSpan().getFinish()));
        printWriter.println("Text:   " + sub.getReplacedText());
      } else {
        printWriter.println("No sub identified by '" + key + "'.");
      }
    }
  }
}
