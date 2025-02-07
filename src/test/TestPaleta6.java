package test;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import brav.Brav6;
import brav.Paleta6;
import figura.Figura6;
import layoutkit.Layout;

@SuppressWarnings("serial")
public class TestPaleta6 extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestPaleta6 frame = new TestPaleta6();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestPaleta6() {
		super("Test paleta 6");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		Paleta6 mazo = new Paleta6();
//		mazo.ordenar();
		
		int x, y;
		int i, j, k;
		int tam = mazo.tam() / 6;

		y = 20;
		k = 0;
		for(i = 0; i < 6; i++) {
			x = 20;
			for(j = 0; j < tam; j++) {
				Figura6 c = new Figura6();
				c.setLocation(x, y);
				c.setBrav((Brav6) mazo.brav(k++));
				contentPane.add(c);
				x += Figura6.ancho + Layout.HALF_MARGIN;
			}
			Figura6 c = new Figura6();
			c.setLocation(x, y);
			contentPane.add(c);
			y += Figura6.alto + Layout.HALF_MARGIN;
		}

		y += 5;;
		k = 0;
		tam++;
		for(i = 0; i < 2; i++) {
			x = 20;
			for(j = 0; j < tam; j++) {
				Figura6 c = new Figura6();
				c.setLocation(x, y);
				c.setBrav((Brav6) mazo.brav(k++));
				contentPane.add(c);
				x += Figura6.ancho + Layout.HALF_MARGIN;
			}
			Figura6 c = new Figura6();
			c.setLocation(x, y);
			contentPane.add(c);
			y += Figura6.alto + Layout.HALF_MARGIN;
		}
	}

}
