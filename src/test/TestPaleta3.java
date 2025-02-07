package test;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import brav.Paleta3;
import figura.Figura3;

@SuppressWarnings("serial")
public class TestPaleta3 extends JFrame {
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestPaleta3 frame = new TestPaleta3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestPaleta3() {
		super("Test paleta 3");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		Paleta3 mazo =  new Paleta3();
		
		int x, y;
		int i, j, k;
		int tam = mazo.tam() / 4;
		boolean base = true;
		k = 0;
		y = 20;
		for(i = 0; i < 4; i++) {
			x = 20;
			for(j = 0; j < tam; j++) {
				Figura3 c = new Figura3(base);
				base = !base;
				c.setLocation(x, y);
				c.setBrav(mazo.brav(k++));
				contentPane.add(c);
				x += 75;
			}
			Figura3 c = new Figura3(base);
			base = !base;
			c.setLocation(x, y);
			contentPane.add(c);
			y += 75;
		}

		k = 0;
		for(i = 0; i < 4; i++) {
			x = 20;
			for(j = 0; j < tam; j++) {
				Figura3 c = new Figura3(base);
				base = !base;
				c.setLocation(x, y);
				c.setBrav(mazo.brav(k++));
				contentPane.add(c);
				x += 75;
			}
			Figura3 c = new Figura3(true);
			base = !base;
			c.setLocation(x, y);
			contentPane.add(c);
			y += 79;
		}
	}

}
