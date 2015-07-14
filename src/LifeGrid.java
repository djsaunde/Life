/**
 *   @author Dan Saunders
 *   LifeGrid.java
 *   class responsible for handling the Game of Life's GUI
 */

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import java.lang.reflect.InvocationTargetException;

public class LifeGrid {
	private GridPanel panel;
	private JFrame f;
	
	/**
	 *   constructor for LifeGrid which sets up class variables and sets up GUI
	 *   @param board - Game of Life board
	 *   @param n - numbers of rows, columns in grid
	 *   @throws InterruptedException 
	 *   @throws InvocationTargetException 
	 */
	public LifeGrid(int n) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	/**
	 *   called from constructor, creates and shows the GUI
	 */
	private void createAndShowGUI() {
		panel = new GridPanel(Life.getDimension());
		
		f = new JFrame("Conway's Game of Life");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(panel);
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
		panel.repaint();
	}
}