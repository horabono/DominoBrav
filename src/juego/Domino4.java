package juego;

import java.awt.EventQueue;

import brav.Brav4;
import figura.Figura;
import figura.Figura4;
import figura.Lateral4;
import figura.Nodo4;
import figura.Lateral.Lado;
import layoutkit.Layout;

public class Domino4 extends Domino {
	private static final long serialVersionUID = 1L;
	
	public Domino4(boolean giroLibre, String jugadorDerecha, String jugadorIzquierda) {
        super("Dominó de rectángulos", giroLibre, jugadorDerecha, jugadorIzquierda);
	}

	@Override
	protected void armarLaterales() {
        if(giroLibre) {
        	Figura4.set();
        } else {
        	Figura4.set(Figura.ANCHO, Figura.ANCHO + Layout.SINGLE_MARGIN);
        }
        
        Brav4 inicial = Lateral4.sacar(new Brav4(new int[] {0, 1, 2, 3}));

        lateralIzq = new Lateral4(5, Lado.IZQUIERDO, jugadorIzquierda);
    	container.add(lateralIzq);
        lateralDer = new Lateral4(5, Lado.DERECHO, jugadorDerecha);
    	container.add(lateralDer);
    	
    	lateralIzq.setLocation(0, 0);
    	lateralDer.setLocation(getWidth() - lateralDer.getWidth() - Layout.SINGLE_MARGIN, 0);

    	int x = Layout.rightOf(lateralIzq);
    	tapete.setLocation(x, 0);
    	tapete.setSize(lateralDer.getX() - x, getHeight());
    	
	   	int y = (tapete.getHeight() - Figura4.alto) / 2;
	   	 x = (tapete.getWidth() - Figura4.ancho) / 2;
    	
    	Nodo4 nodoInicial= new Nodo4(x, y);
    	nodoInicial.setBrav(inicial);
	}

    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Domino4(true, null, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }

}
