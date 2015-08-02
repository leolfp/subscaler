package net.sf.subscaler.sub;

import net.sf.subscaler.io.FileLoader;
import net.sf.subscaler.io.FileLoaderFactory;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 20:50:31
 */
public class SubManager {
  private SubCollection defaultSubCollection;
  private String fileName;

  public String getFileName() {
    return fileName;
  }

  public SubCollection getDefaultSubCollection() {
    return defaultSubCollection;
  }

  public void loadDefaultSubCollection(String fileName) {
    File file = new File(fileName);
    FileLoader loader = FileLoaderFactory.getFileLoaderFor(file);
    this.defaultSubCollection = loader.load(file);
    if (defaultSubCollection != null) {
      this.fileName = fileName;
    }
  }

  public void storeDefaultSubCollection() {
    storeDefaultSubCollection(this.fileName);
  }

  public void storeDefaultSubCollection(String fileName) {
    File file = new File(fileName);
    FileLoader loader = FileLoaderFactory.getFileLoaderFor(file);
    loader.store(this.defaultSubCollection, file);
  }
}
