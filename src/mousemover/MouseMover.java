/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mousemover;

import java.awt.Robot;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author Pete
 */
public class MouseMover {

    public static final int MINUTE = 60000;
    public static final int MAX_Y = 250;
    public static final int MAX_X = 400;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UserInterface ui = new UserInterface();
            }
        });

        Robot robot = new Robot();
        Random random = new Random();

        while (true) {
            robot.mouseMove(random.nextInt(MAX_X), random.nextInt(MAX_Y));
            Thread.sleep(MINUTE);
        }
    }
}
