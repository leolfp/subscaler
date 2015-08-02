package net.sf.subscaler.io;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 12:08:08
 */
public class FileLoaderFactory {
  private static Map<String, FileLoader> loaders;

  static {
    loaders = new HashMap<String, FileLoader>();
    loaders.put("srt", new SrtFileLoader());
  }

  public static FileLoader getFileLoaderFor(File file) {
    String type = extractFileType(file.getName());
    return loaders.get(type);
  }

  private static String extractFileType(String fileName) {
    return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
  }
}
