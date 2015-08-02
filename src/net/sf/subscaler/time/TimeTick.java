package net.sf.subscaler.time;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 02:28:02
 */
public class TimeTick extends TimeBase implements Comparable<TimeTick>, Scalable {

  public TimeTick(int hour, int min, int sec, int mili) {
    super(sec, mili, min, hour);
  }

  public int compareTo(TimeTick o) {
    return ((Long) getTickMili()).compareTo(o.getTickMili());
  }

  public boolean equals(Object obj) {
    if (obj != null && obj instanceof TimeTick) {
      return getTickMili() == ((TimeTick) obj).getTickMili();
    }
    return false;
  }

  public double getRatio(TimeTick base, TimeTick destination) {
    return new TimeDelta(base, this).getRatio(new TimeDelta(base, destination));
  }

  public void scale(TimeTick base, double ratio) {
    this.tickMili = base.tickMili + ((long) ((this.tickMili - base.tickMili) * ratio));
  }
}
