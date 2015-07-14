/**
 *   @author Dan Saunders
 *   Cell.java
 *   representation of a cell in the game grid
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

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
    
    /**
     * getter for cell's x position on the game board
     * @return int xBoard
     */
    public int getXBoard() {
    	return xBoard;
    }
    
    /**
     * getter for cell's y position on the game board
     * @return int yBoard
     */
    public int getYBoard() {
    	return yBoard;
    }

    /**
     * paints this cell on the GUI
     * @param g - Graphics object associated with GUI
     */
    public void paintSquare(Graphics g){
    	g.setColor(getColor(Life.getAge(xBoard*Life.getDimension()+yBoard)));
        g.fillRect(xPos, yPos, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(xPos, yPos, width, height);
    }
    
    /**
     * returns appropriate Color object based on age logic
     * @param color - integer which tells us which color to return
     * @return Color object
     */
    public Color getColor(int color) {
    	if (color > 51) {
    		return Color.BLACK;
    	}
    	return new Color(190, 100, color*5);
    }
}
