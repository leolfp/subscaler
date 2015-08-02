package net.sf.subscaler.time;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 02:28:28
 */
public class TimeSpan implements Comparable<TimeSpan>, Movable, Scalable {
  private TimeTick start;
  private TimeTick finish;

  public TimeSpan(TimeTick start, TimeTick finish) {
    this.start = start;
    this.finish = finish;
  }

  public TimeTick getStart() {
    return start;
  }

  public TimeTick getFinish() {
    return finish;
  }

  public int compareTo(TimeSpan o) {
    int comp = this.getStart().compareTo(o.getStart());
    if (comp != 0) {
      return comp;
    }
    return this.getFinish().compareTo(o.getFinish());
  }

  public boolean equals(Object obj) {
    if (obj != null && obj instanceof TimeSpan) {
      TimeSpan ts = (TimeSpan) obj;
      return getStart().equals(ts.getStart()) && getFinish().equals(ts.getFinish());
    }
    return false;
  }

  public TimeDelta getDelta() {
    return new TimeDelta(this.finish, this.start);
  }

  public void displace(TimeDelta delta) {
    this.start.displace(delta);
    this.finish.displace(delta);
  }

  public void scale(TimeTick base, double ratio) {
    this.start.scale(base, ratio);
    this.finish.scale(base, ratio);
  }
}
