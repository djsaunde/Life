/**
 *   @author Dan Saunders
 *   LifeGrid.java
 *   class responsible for handling the Game of Life's GUI
 */

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

public class LifeGrid implements ActionListener {
	private static JFrame f;
	private static GridPanel grid;
	private static JPanel ui;
	private JButton start, stop;
	
	/**
	 *   constructor for LifeGrid which sets up class variables and sets up GUI
	 *   @param board - Game of Life board
	 *   @param n - numbers of rows, columns in grid
	 *   @throws InterruptedException 
	 *   @throws InvocationTargetException 
	 */
	public LifeGrid() throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	private JButton makeButton(String caption) {
	    JButton b = new JButton(caption);
	    b.setActionCommand(caption);
	    b.addActionListener(this);
	    return b;
	}
	
	/**
	 *   called from constructor, creates and shows the GUI
	 */
	private void createAndShowGUI() {
		grid = new GridPanel(Life.getDimension());
		ui = new JPanel();
		
		start = makeButton("Start");
		stop = makeButton("Stop");
		stop.setEnabled(false);
		
		ui.add(start); 
		ui.add(stop);
		
		f = new JFrame("Conway's Game of Life");
		f.setLayout(new FlowLayout());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(grid);
		f.add(ui);
		f.setResizable(false);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	
	/**
	 *   calls the repaint() function on the panel which holds the game's grid components
	 */
	public void draw(int iteration) {
		f.setTitle("Conway's Game of Life: Generation " + (iteration+1));
		grid.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}