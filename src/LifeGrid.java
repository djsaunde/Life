/**
 * @author Dan Saunders
 * LifeGrid.java
 * GUI handling class
 */

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

public class LifeGrid {
	private int n;
	private boolean[][] board;
	private MyPanel panel;
	
	public LifeGrid(boolean[][] board, int n) {
		this.board = board;
		this.n = n;
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	private void createAndShowGUI() {
		panel = new MyPanel(board, n);
		JFrame f = new JFrame("Conway's Game of Life");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(panel);
		f.setResizable(false);
		f.pack();
		f.setVisible(true);
	}
	
	public void draw() {
		panel.repaint();
	}
	
	public MyPanel getPanel() {
		return panel;
	}
}
	
class MyPanel extends JPanel {

	private ArrayList<Cell> cells = new ArrayList<Cell>();
	private Dimension d;
	private int X_DIMENSION = 800, Y_DIMENSION = 600;
	private Graphics g;
	
	public MyPanel(boolean[][] board, int n) {
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
		this.g = g;
		super.paintComponent(g);
	}
	
	protected void paintCell(int index) {
		cells.get(index).paintSquare(g);
	}
}

class Cell {

	private int xPos;
    private int yPos;
    private int width;
    private int height;
    private int xBoard;
    private int yBoard;
    
    public Cell(int xPos, int yPos, int n, Dimension d) {
    	this.xPos = xPos;
    	this.yPos = yPos;
    	width = (int) (d.getWidth() / n);
    	height = (int) (d.getHeight() / n);
    	xBoard = xPos / width;
    	yBoard = yPos / height;
    }
    
    public int getXBoard() {
    	return xBoard;
    }
    
    public int getYBoard() {
    	return yBoard;
    }

    public void paintSquare(Graphics g){
    	g.setColor(Color.RED);
        g.fillRect(xPos,yPos,width,height);
        g.setColor(Color.BLACK);
        g.drawRect(xPos,yPos,width,height);   
    }
}
