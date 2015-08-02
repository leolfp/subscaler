package net.sf.subscaler.time;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 14:19:15
 */
public class TimeDelta extends TimeBase implements Comparable<TimeDelta> {
  public TimeDelta(TimeTick start, TimeTick end) {
    this(end.getTickMili() - start.getTickMili());
  }

  public TimeDelta(long mili) {
    super(mili);
  }

  public TimeDelta(int hour, int min, int sec, int mili) {
    super(sec, mili, min, hour);
  }

  public int compareTo(TimeDelta o) {
    return ((Long) getTickMili()).compareTo(o.getTickMili());
  }

  public boolean equals(Object obj) {
    if (obj != null && obj instanceof TimeDelta) {
      return getTickMili() == ((TimeDelta) obj).getTickMili();
    }
    return false;
  }

  public boolean isNegative() {
    return tickMili < 0;
  }

  public void scale(double ratio) {
    this.tickMili = (long) ((double) this.tickMili * ratio);
  }

  public double getRatio(TimeDelta destination) {
    return (double) destination.getTickMili() / this.getTickMili();
  }
}
