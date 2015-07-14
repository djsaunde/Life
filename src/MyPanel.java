/**
 * @author Dan Saunders
 * MyPanel.java
 * custom implementation of JPanel.java
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

class MyPanel extends JPanel {

	private static ArrayList<Cell> cells = new ArrayList<Cell>();
	private final int X_DIMENSION = 800, Y_DIMENSION = 800;
	
	public MyPanel(int n) {
		Dimension d = getPreferredSize();
		
		for (int i = 0; i < d.getWidth(); i += d.getWidth() / n) {
			for (int k = 0; k < d.getHeight(); k += d.getHeight() / n) {
				cells.add(new Cell(i, k, n, d));
			}
		}
		
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(X_DIMENSION, Y_DIMENSION);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Cell cell : cells) {
			if (Life.getBoard()[cell.getXBoard()][cell.getYBoard()]) {
				cell.paintSquare(g);
			}
		}
	}
}