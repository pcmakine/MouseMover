/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mousemover;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Pete
 */
public class SliderListener implements ChangeListener {

    private int interval;
    private final Object monitor;

    public SliderListener(Object monitor) {
        this.monitor = monitor;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider slider = (JSlider) e.getSource();
        interval = slider.getValue();
        synchronized (monitor) {
            monitor.notify();
        }
        MouseMover.changeInterval(interval);
    }
}
