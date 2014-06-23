/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mousemover;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.Date;
import java.util.TimeZone;
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

        Date date = new Date(System.currentTimeMillis() - this.startTime - TimeZone.getDefault().getOffset(System.currentTimeMillis() - this.startTime ));
        ui.updateTime(date);
        prevLoc = currentLoc;
    }

    public void reset() {
        this.startTime = System.currentTimeMillis();
    }

    private double nanosToSeconds(long nanoTime) {
        return nanoTime / 1000000000;
    }
}
