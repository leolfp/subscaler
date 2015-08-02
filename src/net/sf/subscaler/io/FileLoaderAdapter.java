package net.sf.subscaler.io;

import net.sf.subscaler.sub.SubCollection;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 12:59:59
 */
public abstract class FileLoaderAdapter implements FileLoader {
  public final SubCollection load(File file) {
    FileReader fr = null;
    LineNumberReader ln = null;

    try {
      fr = new FileReader(file);
      ln = new LineNumberReader(fr);

      return load(ln);
    } catch (FileNotFoundException e) {
      e.printStackTrace(System.err);
    } finally {
      try {
        if (ln != null) {
          ln.close();
        }
        if (fr != null) {
          fr.close();
        }
      } catch (IOException e) {
        e.printStackTrace(System.err);
      }
    }

    return null; // TODO Exception
  }

  protected abstract SubCollection load(LineNumberReader lineReader);

  public final void store(SubCollection collection, File file) {
    FileWriter fw = null;
    BufferedWriter bw = null;
    PrintWriter pw = null;

    try {
      fw = new FileWriter(file);
      bw = new BufferedWriter(fw);
      pw = new PrintWriter(bw);

      store(collection, pw);

      pw.flush();
      bw.flush();
      fw.flush();
    } catch (IOException e) {
      e.printStackTrace(System.err);
    } finally {
      try {
        if (pw != null) {
          pw.close();
        }
        if (bw != null) {
          bw.close();
        }
        if (fw != null) {
          fw.close();
        }
      } catch (IOException e) {
        e.printStackTrace(System.err);
      }
    }
  }

  protected abstract void store(SubCollection collection, PrintWriter writer);
}
