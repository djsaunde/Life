/**
 *   @author Dan Saunders
 *   Cell.java
 *   representation of a cell in the game grid
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Map;
import java.util.HashMap;

class Cell {

	private int xPos;
    private int yPos;
    private int width;
    private int height;
    private int xBoard;
    private int yBoard;
    private static final Map<Integer, Color> colorMap;
    	static {
    		colorMap = new HashMap<Integer, Color>();
    		colorMap.put(1, Color.BLUE); colorMap.put(2, Color.CYAN); colorMap.put(3, Color.GREEN);
    		colorMap.put(4, Color.YELLOW); colorMap.put(5, Color.ORANGE); colorMap.put(6, Color.RED);
    		colorMap.put(7, Color.PINK); colorMap.put(8, Color.MAGENTA);
    	}
    
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
    	g.setColor(getColor(Life.getAge(xBoard*Life.getDimension()+yBoard)));
        g.fillRect(xPos, yPos, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(xPos, yPos, width, height);
    }
    
    public Color getColor(int color) {
    	if (color > 8) {
    		return Color.BLACK;
    	}
    	return colorMap.get(color);
    }
}
