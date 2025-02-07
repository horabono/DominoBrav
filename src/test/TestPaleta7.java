package test;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import brav.Brav7;
import brav.Paleta7;
import figura.Figura7;
import layoutkit.Layout;

@SuppressWarnings("serial")
public class TestPaleta7 extends JFrame{

	private JPanel contentPane;

	public TestPaleta7() {
		super("Test paleta 5");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		Paleta7 mazo = new Paleta7();
		mazo.ordenar();
	
//		Figura7.set(75, 35, 85);
		
		int x, y;
		int i, j, k;
		int tam = mazo.tam() / 8;
		int filas = 4; // mazo.tam() / tam;
		boolean base = true;

		y = 20;
		k = 0;
		for(i = 0; i < filas; i++) {
			x = 10;
			for(j = 0; j < tam; j++) {
				Figura7 c = new Figura7(base);
				base = !base;
				c.setLocation(x, y);
				c.setBrav((Brav7) mazo.brav(k++));
				contentPane.add(c);
				x += Figura7.ancho + Layout.HALF_MARGIN;
			}
			Figura7 c = new Figura7(base);
			base = !base;
			c.setLocation(x, y);
			contentPane.add(c);
			y += Figura7.alto + Layout.HALF_MARGIN;
		}
		
		k = 0;
		for(i = 0; i < filas; i++) {
			x = 10;
			for(j = 0; j < tam; j++) {
				Figura7 c = new Figura7(base);
				base = !base;
				c.setLocation(x, y);
				c.setBrav((Brav7) mazo.brav(k++));
				contentPane.add(c);
				x += Figura7.ancho + Layout.HALF_MARGIN;
			}
			Figura7 c = new Figura7(base);
			base = !base;
			c.setLocation(x, y);
			contentPane.add(c);
			y += Figura7.alto + Layout.HALF_MARGIN;
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestPaleta7 frame = new TestPaleta7();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
