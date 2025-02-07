package figura;

import java.awt.Color;

import javax.swing.JPanel;

public class Tapete extends JPanel {
	private static final long serialVersionUID = 1L;

	public Tapete() {
        setOpaque(true);
        setLayout(null);
//        this.setBackground(Color.ORANGE);
        this.setBackground(new Color(0, 192, 0));
	}

}
