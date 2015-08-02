package net.sf.subscaler.sub;

import net.sf.subscaler.time.TimeSpan;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 02:27:29
 */
public class Sub implements Comparable<Sub> {
  private TimeSpan timeSpan;
  private StringBuffer text = new StringBuffer();
  private String replacedText;

  public String getReplacedText() {
    if (replacedText == null) {
      replacedText = text.toString().replaceAll(" ++|\\t++|\\n++", " ");
    }
    return replacedText;
  }

  public Sub(TimeSpan timeSpan) {
    this.timeSpan = timeSpan;
  }

  public Sub(TimeSpan timeSpan, String text) {
    this(timeSpan);
    setText(text);
  }

  public TimeSpan getTimeSpan() {
    return timeSpan;
  }

  public void setText(String text) {
    this.replacedText = null;
    this.text = new StringBuffer(text);
  }

  public void appendText(String text) {
    this.replacedText = null;
    if (this.text.length() != 0) {
      this.text.append("\n");
    }
    this.text.append(text);
  }

  public String getText() {
    return text.toString();
  }

  public int compareTo(Sub o) {
    return this.getTimeSpan().compareTo(o.getTimeSpan());
  }

  public boolean contains(String subText) {
    return getReplacedText().contains(subText);
  }

  public boolean equals(Object obj) {
    if (obj != null && obj instanceof Sub) {
      Sub sub = (Sub) obj;
      return getTimeSpan().equals(sub.getTimeSpan());
    }
    return false;
  }
}
