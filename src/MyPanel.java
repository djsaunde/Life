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

	private ArrayList<Cell> cells = new ArrayList<Cell>();
	private Dimension d;
	private boolean[][] board;
	private int n;
	private int X_DIMENSION = 800, Y_DIMENSION = 800;
	
	public MyPanel(boolean[][] board, int n) {
		this.n = n;
		this.board = board;
		d = getPreferredSize();
		
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
			if (board[cell.getXBoard()][cell.getYBoard()]) {
				cell.paintSquare(g);
			}
		}
	}
}