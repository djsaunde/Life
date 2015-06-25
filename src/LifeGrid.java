package src;
/**
 * @author Dan Saunders
 * LifeGrid.java
 * sister class to Life.java which renders the Game of Life in a GUI
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.JPanel;

public class LifeGrid {

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Conway's Game of Life");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(dim.width/2, dim.height/2);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Display the window.
        frame.setVisible(true);
    }

    public LifeGrid(int n) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    public static void main (String[] args) {
    	LifeGrid g = new LifeGrid(10);
    }
}