package net.sf.subscaler.command;

import net.sf.subscaler.sub.SubCollection;
import net.sf.subscaler.sub.SubManager;

import java.io.PrintWriter;
import java.security.InvalidParameterException;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 15/11/2008
 * Time: 22:01:41
 * To change this template use File | Settings | File Templates.
 */
public class Index implements Command {
  public String getParametersHelp() {
    return "sign\t+ or - to indicate that the direction is a delta\n" +
        "index\tThe first subtitle index or the delta";
  }

  public String getUsageHelp() {
    return "[sign]<index>";
  }

  public String getShortHelp() {
    return "Reindexes subtitles";
  }

  public String getLongHelp() {
    return "Redefines the first subtitle index, useful for appending files";
  }

  public String getCommandName() {
    return "index";
  }

  public void execute(SubManager subManager, String args, PrintWriter printWriter) throws InvalidParameterException {
    if (args == null || args.trim().equals("")) {
      throw new InvalidParameterException();
    }

    String[] argsA = args.split(" ");
    String time = argsA[0];

    int delta = argsA.length > 0 ? Integer.parseInt(argsA[0]) : 0;

    SubCollection collection = subManager.getDefaultSubCollection();

    if (TimeView.isDelta(time)) {
      collection.setIndex(collection.getIndex() + delta);
    } else {
      collection.setIndex(delta);
    }
  }
}
