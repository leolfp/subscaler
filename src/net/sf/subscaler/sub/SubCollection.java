package net.sf.subscaler.sub;

import net.sf.subscaler.time.TimeDelta;
import net.sf.subscaler.time.TimeTick;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 12:05:58
 */
public class SubCollection {
  private List<Sub> subtitleList = new ArrayList<Sub>();
  private int index;

  public Iterator<Sub> iterator() {
    return subtitleList.iterator();
  }

  public void add(Sub sub) {
    this.subtitleList.add(sub);
  }

  public void displace(TimeTick tick, boolean sort) {
    displace(tick, 0, subtitleList.size(), sort);
  }

  public void displace(TimeDelta delta, boolean sort) {
    displace(delta, 0, subtitleList.size(), sort);
  }

  public void displace(TimeTick tick, int from, boolean sort) {
    displace(tick, from, subtitleList.size(), sort);
  }

  public void displace(TimeDelta delta, int from, boolean sort) {
    displace(delta, from, subtitleList.size(), sort);
  }

  public void displace(TimeTick tick, int from, int to, boolean sort) {
    TimeDelta delta = new TimeDelta(subtitleList.get(from).getTimeSpan().getStart(), tick);
    displace(delta, from, to, sort);
  }

  public void displace(TimeDelta delta, int from, int to, boolean sort) {
    List<Sub> subList = subtitleList.subList(from, to);

    for (Sub sub : subList) {
      sub.getTimeSpan().displace(delta);
    }

    if (sort) {
      Collections.sort(subtitleList);
    }
  }

  public void scale(int pivot, int moving, TimeTick dest) {
    TimeTick movingStart = this.subtitleList.get(moving).getTimeSpan().getStart();
    TimeTick pivotStart = this.subtitleList.get(pivot).getTimeSpan().getStart();

    double ratio = movingStart.getRatio(pivotStart, dest);

    List<Sub> subList = subtitleList.subList(pivot, moving + 1);

    for (Sub sub : subList) {
      sub.getTimeSpan().scale(pivotStart, ratio);
    }
  }

  public void scale(int pivot, int moving, TimeDelta delta) {
    TimeTick start = this.subtitleList.get(moving).getTimeSpan().getStart();
    TimeTick dest = new TimeTick(start.getHour(), start.getMin(), start.getSec(), start.getMili());
    dest.displace(delta);
    scale(pivot, moving, dest);
  }

  public Map<Integer, Sub> search(String text) {
    Map<Integer, Sub> pos = new TreeMap<Integer, Sub>();

    for (int i = 0; i < subtitleList.size(); ++i) {
      if (subtitleList.get(i).contains(text)) {
        pos.put(i, subtitleList.get(i));
      }
    }

    return pos;
  }

  public int getSize() {
    return subtitleList.size();
  }

  public Sub getSub(int i) {
    return subtitleList.get(i);
  }

  public void setIndex(int i) {
    this.index = i;
  }

  public int getIndex() {
    return this.index;
  }
}
