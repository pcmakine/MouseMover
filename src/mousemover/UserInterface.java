/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mousemover;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class UserInterface {

    private final static int WINDOW_WIDTH = 800;
    private final static int WINDOW_HEIGHT = 500;
    private int initialInterval;
    private JLabel time;

    public UserInterface(Notifier notifier, int initialInterval) {
        this.initialInterval = initialInterval;
        initUI(notifier);
    }

    //time in seconds
    public void updateTime(double elapsedTime) {
        this.time.setText(elapsedTime + " seconds elapsed");
    }

    private void initUI(Notifier notifier) {

        JFrame frame = new JFrame("Mouse Mover");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        JSlider slider = createSlider();
        slider.addChangeListener(makeIntervalChangeListener(notifier));

        JPanel topBar = new JPanel();
        topBar.setLayout(new BorderLayout());
        JLabel label = new JLabel("Time between mouse moves (seconds)", SwingConstants.CENTER);
        label.setLabelFor(slider);

        topBar.add(label, BorderLayout.CENTER);
        topBar.add(slider, BorderLayout.SOUTH);

        JPanel bottomBar = new JPanel();
        bottomBar.setLayout(new BorderLayout());

        JSlider quitAfterSlider = new JSlider(JSlider.HORIZONTAL, 1, 16, 16);
        quitAfterSlider.setMajorTickSpacing(1);
        quitAfterSlider.setPaintTicks(true);
        quitAfterSlider.setPaintLabels(true);
        Dictionary table = quitAfterSlider.createStandardLabels(1);
        table.put(16, new JLabel("∞"));
        quitAfterSlider.setLabelTable(table);
        quitAfterSlider.addChangeListener(this.makeAutomaticExitTimeChangeListener(notifier));

        JLabel quitAfterLabel = new JLabel("Automatically quit after x hours", SwingConstants.CENTER);
        quitAfterLabel.setLabelFor(quitAfterSlider);
        bottomBar.add(quitAfterLabel, BorderLayout.NORTH);
        bottomBar.add(quitAfterSlider, BorderLayout.CENTER);

        time = new JLabel("0", SwingConstants.CENTER);
        bottomBar.add(time, BorderLayout.SOUTH);


        container.add(topBar, BorderLayout.PAGE_START);
        container.add(quitButton, BorderLayout.CENTER);
        container.add(bottomBar, BorderLayout.SOUTH);
        frame.getContentPane().add(container);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private ChangeListener makeIntervalChangeListener(final Notifier notifier) {
        ChangeListener listener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider slider = (JSlider) e.getSource();
                int interval = slider.getValue();
                notifier.notifyIntervalChange(interval);
            }
        };
        return listener;
    }
    
        private ChangeListener makeAutomaticExitTimeChangeListener(final Notifier notifier) {
        ChangeListener listener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider slider = (JSlider) e.getSource();
                int interval = slider.getValue();
                notifier.notifyAutomaticExitTimeChange(interval);
            }
        };
        return listener;
    }

    private JSlider createSlider() {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 5, 300, initialInterval);
        slider.setMajorTickSpacing(20);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        Dictionary table = slider.createStandardLabels(20);
        table.put(300, new JLabel("300"));
        slider.setLabelTable(table);
        return slider;
    }
}