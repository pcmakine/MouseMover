/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mousemover;

/**
 *
 * @author Pete
 */
public class Notifier {
    private Object monitor;
    private long max = 16;
    private long currentQuitTime;

    public Notifier(Object monitor) {
        this.monitor = monitor;
    }

    public boolean automaticQuittingScheduled() {
        return currentQuitTime != max;
    }
    
    public void notifyToStartSleepingOver(){
        MouseMover.startSleepingOver();
        notifyMonitor();
    }

    public void notifyIntervalChange(int interval) {
        MouseMover.changeInterval(interval);
        notifyMonitor();
    }

    public void notifyAutomaticExitTimeChange(int exitAfter) {
        this.currentQuitTime = exitAfter;
        MouseMover.changeAutomaticExitTime(this.hoursToMilliseconds(exitAfter));
        notifyMonitor();
    }

    public void notifyMonitor() {
        synchronized (monitor) {
            monitor.notify();
        }
    }

    private long hoursToMilliseconds(int hrs) {
        return 1000 * 60 * 60 * hrs;
    }
}
