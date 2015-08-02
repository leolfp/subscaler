package net.sf.subscaler.command;

import net.sf.subscaler.sub.SubManager;

import java.io.PrintWriter;
import java.security.InvalidParameterException;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 22:35:00
 */
public class Scale implements Command {
  public String getParametersHelp() {
    return "pivot\tThe base of the scale, a sub key that will remain intact\n" +
        "sel\tThe sub key to be moved\n" +
        "sign\t+ or - to indicate that time is delta\n" +
        "time\tThe time to start the first sub or the delta, hh:mm:ss.nnn";
  }

  public String getUsageHelp() {
    return "<pivot> <sel> [sign]<time>";
  }

  public String getShortHelp() {
    return "Changes subtitles' placement scale";
  }

  public String getLongHelp() {
    return "Scales subtitles' placement, from pivot moving selection to the given time";
  }

  public String getCommandName() {
    return "scale";
  }

  public void execute(SubManager subManager, String args, PrintWriter printWriter) throws InvalidParameterException {
    if (args == null || args.trim().equals("")) {
      throw new InvalidParameterException();
    }

    String[] argsA = args.split(" ");
    if (argsA.length != 3) {
      throw new InvalidParameterException();
    }

    int pivot = Integer.parseInt(argsA[0]);
    int moving = Integer.parseInt(argsA[1]);

    if (TimeView.isDelta(argsA[2])) {
      subManager.getDefaultSubCollection().scale(pivot, moving, TimeView.stringToDelta(argsA[2]));
    } else {
      subManager.getDefaultSubCollection().scale(pivot, moving, TimeView.stringToTick(argsA[2]));
    }
  }
}
