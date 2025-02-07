package test;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import brav.Brav4;
import brav.Paleta4;
import figura.Figura4;
import figura.Nodo;
import figura.Nodo4;
import layoutkit.Layout;

@SuppressWarnings("serial")
public class TestPaleta4 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestPaleta4 frame = new TestPaleta4();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestPaleta4() {
		super("Test paleta 4");
		
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		Nodo.setComponent(contentPane);
		
		Paleta4 mazo = new Paleta4();
		mazo.ordenar();
		
		int x, y;
		int i, j, k;
		int tam = mazo.tam() / 5;

		k = 0;
		y = 20;
		for(i = 0; i < 5; i++) {
			x = 20;
			for(j = 0; j < tam; j++) {
				Figura4 c = new Figura4();
				c.setLocation(x, y);
				c.setBrav((Brav4) mazo.brav(k++));
				contentPane.add(c);
				x += Figura4.ancho + Layout.HALF_MARGIN;
			}
			Nodo4 c = new Nodo4(x, y);
			contentPane.add(c.getFigura());
			y += Figura4.alto + Layout.HALF_MARGIN;
		}
		
//		Figura4.set(51, 73);

		k = 0;
		for(i = 0; i < 5; i++) {
			x = 20;
			for(j = 0; j < tam; j++) {
				Figura4 c = new Figura4();
				c.setLocation(x, y);
				c.setBrav((Brav4) mazo.brav(k++));
				contentPane.add(c);
				x += Figura4.ancho + Layout.HALF_MARGIN;
			}
			Nodo4 c = new Nodo4(x, y);
			contentPane.add(c.getFigura());
			y += Figura4.alto + Layout.HALF_MARGIN;
		}
	}

}
