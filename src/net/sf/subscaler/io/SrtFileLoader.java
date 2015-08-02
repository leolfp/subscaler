package net.sf.subscaler.io;

import net.sf.subscaler.sub.Sub;
import net.sf.subscaler.sub.SubCollection;
import net.sf.subscaler.time.TimeSpan;
import net.sf.subscaler.time.TimeTick;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 12:11:15
 */
public class SrtFileLoader extends FileLoaderAdapter {
  protected SubCollection load(LineNumberReader lineReader) {
    SubCollection subCollection = new SubCollection();
    Sub sub;

    try {
      while ((sub = readSub(lineReader)) != null) {
        subCollection.add(sub);
      }
    } catch (IOException e) {
      e.printStackTrace(System.err);
    }

    return subCollection;
  }

  protected void store(SubCollection subCollection, PrintWriter writer) {
    Iterator<Sub> iter = subCollection.iterator();
    int i = subCollection.getIndex();

    while (iter.hasNext()) {
      Sub sub = iter.next();
      writeSub(++i, sub, writer);
    }
  }

  private Sub readSub(LineNumberReader lineReader) throws IOException {
    String line;

    while ((line = lineReader.readLine()) != null && line.trim().equals("")) {
      // Does nothing
    }

    if (line == null) {
      return null;
    }

    // TODO int subNumber = Integer.parseInt(line); // Ignored
    TimeSpan timeSpan = readTimeSpan(lineReader.readLine());
    Sub sub = new Sub(timeSpan);

    while ((line = lineReader.readLine()) != null && !line.trim().equals("")) {
      sub.appendText(line);
    }

    return sub;
  }

  private TimeSpan readTimeSpan(String line) {
    // Format: hh:mm:ss,nnn --> hh:mm:ss,nnn
    String[] timeSpanStr = line.split(" --> ");
    TimeTick start = readTimeTick(timeSpanStr[0]);
    TimeTick finish = readTimeTick(timeSpanStr[1]);

    return new TimeSpan(start, finish);
  }

  private TimeTick readTimeTick(String tickStr) {
    // Format: hh:mm:ss,nnn

    String[] p = tickStr.split(":|,");

    return new TimeTick(
        Integer.parseInt(p[0]),
        Integer.parseInt(p[1]),
        Integer.parseInt(p[2]),
        Integer.parseInt(p[3])
    );
  }

  private String serializeTimeSpan(TimeSpan timeSpan) {
    // Format: hh:mm:ss,nnn --> hh:mm:ss,nnn
    return serializeTimeTick(timeSpan.getStart()) + " --> " + serializeTimeTick(timeSpan.getFinish());
  }

  private String serializeTimeTick(TimeTick timeTick) {
    // Format: hh:mm:ss,nnn
    return String.format("%02d:%02d:%02d,%03d",
        timeTick.getHour(), timeTick.getMin(), timeTick.getSec(), timeTick.getMili());
  }

  private void writeSub(int n, Sub sub, PrintWriter writer) {
    writer.println(n);
    writer.println(serializeTimeSpan(sub.getTimeSpan()));
    writer.println(sub.getText());
    writer.println();
  }
}
