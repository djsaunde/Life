
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
