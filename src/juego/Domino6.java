package juego;

import java.awt.EventQueue;

import brav.Brav6;
import figura.Figura;
import figura.Figura6;
import figura.Lateral6;
import figura.Nodo6;
import figura.Lateral.Lado;
import layoutkit.Layout;

public class Domino6 extends Domino {
	private static final long serialVersionUID = 1L;

	public Domino6(boolean giroLibre, String jugadorDerecha, String jugadorIzquierda) {
        super("Dominó de hexágonos", giroLibre, jugadorDerecha, jugadorIzquierda);
	}

	@Override
	protected void armarLaterales() {
        if(giroLibre) {
        	Figura6.set();
        } else {
        	Figura6.set(Figura.ANCHO, Figura.ANCHO + Layout.SINGLE_MARGIN);
        }
        
        Brav6 inicial = Lateral6.sacar();
        
        lateralIzq = new Lateral6(5, Lado.IZQUIERDO, jugadorIzquierda);
    	container.add(lateralIzq);
        lateralDer = new Lateral6(5, Lado.DERECHO, jugadorDerecha);
    	container.add(lateralDer);
    	
    	lateralIzq.setLocation(0, 0);
    	lateralDer.setLocation(getWidth() - lateralDer.getWidth() - Layout.SINGLE_MARGIN, 0);

    	int x = Layout.rightOf(lateralIzq);
    	tapete.setLocation(x, 0);
    	tapete.setSize(lateralDer.getX() - x, getHeight());
    	
	   	int y = (tapete.getHeight() - Figura6.alto) / 2;
	   	 x = (tapete.getWidth() - Figura6.ancho) / 2;
    	
    	Nodo6 nodoInicial= new Nodo6(x, y);
    	nodoInicial.setBrav(inicial);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Domino6(true, null, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
