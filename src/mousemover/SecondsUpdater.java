/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mousemover;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.TimerTask;

/**
 *
 * @author Pete
 */
public class SecondsUpdater extends TimerTask {

    private long startTime;
    private UserInterface ui;
    private Point prevLoc;
    private Notifier notifier;

    public SecondsUpdater(UserInterface ui, Notifier notifier) {
        this.reset();
        this.ui = ui;
        this.notifier = notifier;
        prevLoc = MouseInfo.getPointerInfo().getLocation();
    }

    public void run() {
        
        Point currentLoc = MouseInfo.getPointerInfo().getLocation();
        if (!prevLoc.equals(currentLoc)) {
            notifier.notifyToStartSleepingOver();
        }
        ui.updateTime(this.nanosToSeconds(System.nanoTime() - this.startTime));
        prevLoc = currentLoc;
    }

    public void reset() {
        this.startTime = System.nanoTime();
    }

    private double nanosToSeconds(long nanoTime) {
        return nanoTime / 1000000000;
    }
}
