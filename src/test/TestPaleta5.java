package test;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import brav.Brav5;
import brav.Paleta5;
import figura.Figura5;
import layoutkit.Layout;

@SuppressWarnings("serial")
public class TestPaleta5 extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestPaleta5 frame = new TestPaleta5();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestPaleta5() {
		super("Test paleta 5");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		Paleta5 mazo = new Paleta5();
		mazo.ordenar();
		
		Figura5.set(71, 51);
		
		int x, y;
		int i, j, k;
		int tam = mazo.tam() / 4;
		boolean base = true;
		y = 5;
		k = 0;
		for(i = 0; i < 4; i++) {
			x = 5;
			for(j = 0; j < tam; j++) {
				Figura5 c = new Figura5(base);
				base = !base;
				c.setLocation(x, y);
				c.setBrav((Brav5) mazo.brav(k++)) ;
				contentPane.add(c);
				x += Figura5.ancho + Layout.HALF_MARGIN;
			}
			Figura5 c = new Figura5(base);
			base = !base;
			c.setLocation(x, y);
			contentPane.add(c);
			y += Figura5.alto + Layout.HALF_MARGIN;
		}
		
		k = 0;
		for(i = 0; i < 4; i++) {
			x = 5;
			for(j = 0; j < tam; j++) {
				Figura5 c = new Figura5(base);
				base = !base;
				c.setLocation(x, y);
				c.setBrav((Brav5) mazo.brav(k++)) ;
				contentPane.add(c);
				x += Figura5.ancho + Layout.HALF_MARGIN;
			}
			Figura5 c = new Figura5(base);
			base = !base;
			c.setLocation(x, y);
			contentPane.add(c);
			y += Figura5.alto + Layout.HALF_MARGIN;
		}
	}

}
