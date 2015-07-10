/**
 * @author Dan Saunders
 * Cell.java
 * representation of a cell in the game grid
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
