package net.sf.subscaler;

import net.sf.subscaler.command.CommandController;
import net.sf.subscaler.sub.SubManager;

/**
 * Created by IntelliJ IDEA.
 * User: Leonardo
 * Date: 08/07/2007
 * Time: 20:57:53
 * To change this template use File | Settings | File Templates.
 */
public class SubScalerMain {
  public static void main(String[] args) {
    CommandController controller = new CommandController(new SubManager(), System.console());
    controller.executeCommandLoop();
  }
}
