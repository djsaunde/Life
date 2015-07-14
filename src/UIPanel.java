import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

/**
 * @author Dan Saunders
 * UIPanel.java
 * custom implementation of JPanel.java for user interface
 */

public class UIPanel extends JPanel {

	/**
	 * auto-generated serial version ID
	 */
	private static final long serialVersionUID = -5744478982613790558L;
	
	public UIPanel() {
		this.setLayout(new FlowLayout());
		this.add(new Button("Start"));
		this.add(new Button("Stop"));
	}

}
