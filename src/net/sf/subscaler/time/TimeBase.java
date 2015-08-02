package net.sf.subscaler.time;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 14:20:26
 */
public abstract class TimeBase implements Movable {
  protected long tickMili;

  protected TimeBase(long tickMili) {
    this.tickMili = tickMili;
  }

  protected TimeBase(int sec, int mili, int min, int hour) {
    this((long) mili + sec * 1000 + min * 60000 + hour * 3600000);
  }

  public short getHour() {
    return (short) (tickMili / 3600000);
  }

  public short getMin() {
    return (short) ((tickMili / 60000) % 60);
  }

  public short getSec() {
    return (short) ((tickMili / 1000) % 60);
  }

  public short getMili() {
    return (short) (tickMili % 1000);
  }

  long getTickMili() {
    return tickMili;
  }

  public void displace(TimeDelta delta) {
    this.tickMili += delta.getTickMili();
  }
}
