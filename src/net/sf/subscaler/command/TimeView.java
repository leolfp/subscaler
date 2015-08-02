package net.sf.subscaler.command;

import net.sf.subscaler.time.TimeDelta;
import net.sf.subscaler.time.TimeTick;

import java.security.InvalidParameterException;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 09/07/2007
 * Time: 08:54:23
 * To change this template use File | Settings | File Templates.
 */
public class TimeView {
  public static String deltaToString(TimeDelta delta) {
    if (delta.isNegative()) {
      return String.format("-%02d:%02d:%02d.%03d", -delta.getHour(), -delta.getMin(), -delta.getSec(), -delta.getMili());
    } else {
      return String.format("+%02d:%02d:%02d.%03d", delta.getHour(), delta.getMin(), delta.getSec(), delta.getMili());
    }
  }

  public static String tickToString(TimeTick tick) {
    return String.format("%02d:%02d:%02d.%03d", tick.getHour(), tick.getMin(), tick.getSec(), tick.getMili());
  }

  public static TimeTick stringToTick(String time) throws InvalidParameterException {
    int[] times = parseTime(time);
    return new TimeTick(times[0], times[1], times[2], times[3]);
  }

  public static TimeDelta stringToDelta(String oldTime) throws InvalidParameterException {
    String newTime = oldTime;
    if (isDelta(oldTime)) {
      newTime = oldTime.substring(1);
    }
    int[] times = parseTime(newTime);
    if (oldTime.startsWith("-")) {
      return new TimeDelta(-times[0], -times[1], -times[2], -times[3]);
    } else {
      return new TimeDelta(times[0], times[1], times[2], times[3]);
    }
  }

  private static int[] parseTime(String time) {
    String[] timesS = time.split(":|\\.");
    int[] times = new int[4];

    if (timesS.length != 4) {
      throw new InvalidParameterException();
    }

    try {
      for (int i = 0; i < 4; ++i) {
        times[i] = Integer.parseInt(timesS[i]);
      }
    } catch (NumberFormatException e) {
      throw new InvalidParameterException();
    }

    return times;
  }

  public static boolean isDelta(String time) {
    return time.startsWith("+") || time.startsWith("-");
  }
}
