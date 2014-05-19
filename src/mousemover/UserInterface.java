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
import javax.swing.event.ChangeListener;

public class UserInterface {

    private final static int WINDOW_WIDTH = 800;
    private final static int WINDOW_HEIGHT= 500;
    private int initialInterval;

    public UserInterface(ChangeListener listener, int initialInterval) {
        this.initialInterval = initialInterval;
        initUI(listener);
    }

    private void initUI(ChangeListener listener) {

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
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 5, 300, initialInterval);
        slider.setMajorTickSpacing(20);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        Dictionary table = slider.createStandardLabels(20);
        table.put(300, new JLabel("300"));
        slider.setLabelTable(table);  
        slider.addChangeListener(listener);
        
        JPanel topBar = new JPanel();
        topBar.setLayout(new BorderLayout());
        JLabel label = new JLabel("Time between mouse moves", SwingConstants.CENTER);
        label.setLabelFor(slider);
        
        topBar.add(label, BorderLayout.CENTER);
        topBar.add(slider, BorderLayout.SOUTH);
        
        container.add(topBar, BorderLayout.PAGE_START);
        container.add(quitButton, BorderLayout.CENTER);
        frame.getContentPane().add(container);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}