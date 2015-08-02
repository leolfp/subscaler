package net.sf.subscaler.io;

import net.sf.subscaler.sub.SubCollection;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 12:05:43
 */
public interface FileLoader {
  public SubCollection load(File file);

  public void store(SubCollection collection, File file);
}
