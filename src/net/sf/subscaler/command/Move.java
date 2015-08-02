package net.sf.subscaler.command;

import net.sf.subscaler.sub.SubCollection;
import net.sf.subscaler.sub.SubManager;
import net.sf.subscaler.time.TimeDelta;
import net.sf.subscaler.time.TimeTick;

import java.io.PrintWriter;
import java.security.InvalidParameterException;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 22:34:56
 */
public class Move implements Command {
  public String getParametersHelp() {
    return "sign\t+ or - to indicate that time is delta\n" +
        "time\tThe time to start the first sub or the delta, hh:mm:ss.nnn\n" +
        "first\tThe optional first sub key, inclusive, to move\n" +
        "last\tThe optional last sub key, inclusive, to move\n" +
        "sort\tA boolean to force resort of subs";
  }

  public String getUsageHelp() {
    return "[sign]<time> [first] [last] [sort]";
  }

  public String getShortHelp() {
    return "Moves subtitles linearly";
  }

  public String getLongHelp() {
    return "Moves the selected subs to start at the given position";
  }

  public String getCommandName() {
    return "move";
  }

  public void execute(SubManager subManager, String args, PrintWriter printWriter) throws InvalidParameterException {
    if (args == null || args.trim().equals("")) {
      throw new InvalidParameterException();
    }

    String[] argsA = args.split(" ");
    String time = argsA[0];

    Integer first = argsA.length > 1 ? Integer.parseInt(argsA[1]) : null;
    Integer last = argsA.length > 2 ? Integer.parseInt(argsA[2]) : null;
    boolean sort = argsA.length > 3 && Boolean.parseBoolean(argsA[3]);

    if (TimeView.isDelta(time)) {
      executeDelta(subManager.getDefaultSubCollection(), time, first, last, sort);
    } else {
      executeTick(subManager.getDefaultSubCollection(), time, first, last, sort);
    }
  }

  private void executeDelta(SubCollection subs, String time, Integer first, Integer last, boolean sort) throws InvalidParameterException {
    TimeDelta delta = TimeView.stringToDelta(time);

    if (first == null && last == null) {
      subs.displace(delta, sort);
    } else if (last == null) {
      subs.displace(delta, first, sort);
    } else {
      subs.displace(delta, first, last, sort);
    }
  }

  private void executeTick(SubCollection subs, String time, Integer first, Integer last, boolean sort) throws InvalidParameterException {
    TimeTick tick = TimeView.stringToTick(time);

    if (first == null && last == null) {
      subs.displace(tick, sort);
    } else if (last == null) {
      subs.displace(tick, first, sort);
    } else {
      subs.displace(tick, first, last, sort);
    }
  }
}
