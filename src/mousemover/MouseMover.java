/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mousemover;

import java.awt.Robot;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Pete
 */
public class MouseMover {

    private static int interval = 60; //seconds
    private static final int MAX_Y = 250;
    private static final int MAX_X = 400;
    private static final Object monitor = new Object();
    private static boolean notified;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        final SliderListener listener = new SliderListener(monitor);
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UserInterface ui = new UserInterface(listener, interval);
            }
        });

        waitInterval();

    }

    public static void waitInterval() throws Exception {
        Robot robot = new Robot();
        Random random = new Random();

        while (true) {
            synchronized (monitor) {
                monitor.wait(interval * 1000);
            }
            if (!notified) {
                robot.mouseMove(random.nextInt(MAX_X), random.nextInt(MAX_Y));
            }
            notified = false;
        }
    }

    public static void changeInterval(int changedInterval) {
        interval = changedInterval;
        notified = true;
    }
}
