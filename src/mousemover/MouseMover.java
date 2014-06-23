/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mousemover;

import java.awt.Robot;
import java.util.Date;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JSlider;
import java.util.Timer;
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
    private static Timer timer;
    private static UserInterface ui;
    private static SecondsUpdater updater;
    private static QuitterTask quitter;
    private static boolean exitAfter = false;
    private static final Notifier notifier = new Notifier(monitor);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ui = new UserInterface(notifier, interval);
                timer = new Timer();
                updater = new SecondsUpdater(ui, notifier);
                timer.scheduleAtFixedRate(updater, 0, 1000);
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

    public static void startSleepingOver() {
        notified = true;
    }

    public static void changeInterval(int changedInterval) {
        interval = changedInterval;
        notified = true;
    }

    public static void changeAutomaticExitTime(long exitAfter) {
        try {
            quitter.cancel();   //stop countdown to quitting
        } catch (NullPointerException e) {
            //if an automatic shutdown has not been scheduled
        }
        updater.cancel();   //start the timer over
        updater = new SecondsUpdater(ui, notifier);
        timer.scheduleAtFixedRate(updater, 0, 1000);
        if (notifier.automaticQuittingScheduled()) {
            scheduleQuitting(exitAfter);
        }
        notified = true;
    }

    private static void scheduleQuitting(long exitAfter) {
        Date date = new Date(System.currentTimeMillis() + exitAfter * 1000);
        quitter = new QuitterTask();
        timer.schedule(quitter, date);
    }
}
