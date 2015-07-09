/**
 * @author Dan Saunders
 * BoardListener.java, GuiUpdater.java
 * Observer pattern interface and class which will tell 
 * the GUI to redraw certain cells in grid
 */

public interface BoardListener {
	
	void boardChanged();
}

class GuiUpdater implements BoardListener {

	private int x, y, n;
	private MyPanel panel;
	
	public GuiUpdater(int x, int y, int n, MyPanel panel) {
		this.panel = panel;
		this.x = x; this.y = y;
	}

	@Override
	public void boardChanged() {
		panel.paintCell(x*n+y);
	}
}
