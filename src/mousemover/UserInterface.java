/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mousemover;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class UserInterface {
    
    public UserInterface(){
        initUI();
    }
    
    private void initUI() {

       JFrame frame = new JFrame("Mouse Mover");
       
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       JButton quitButton = new JButton("Quit");
       quitButton.setBounds(50, 60, 80, 30);
       quitButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent event) {
               System.exit(0);
          }
       });

       frame.getContentPane().add(quitButton);
       frame.setSize(800, 500);
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
    }

}