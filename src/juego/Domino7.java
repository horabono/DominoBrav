package juego;

import java.awt.EventQueue;

import brav.Brav7;
import figura.Figura;
import figura.Figura7;
import figura.Lateral7;
import figura.Nodo7;
import figura.Lateral.Lado;
import layoutkit.Layout;

public class Domino7 extends Domino {
	private static final long serialVersionUID = 1L;

	public Domino7(boolean giroLibre, String jugadorDerecha, String jugadorIzquierda) {
        super("Dominó de heptágonos", giroLibre, jugadorDerecha, jugadorIzquierda);
	}

	@Override
	protected void armarLaterales() {
        if(giroLibre) {
            Figura7.set();
        } else {
        	Figura7.set(Figura.ANCHO, Figura.ANCHO - 42, Figura.ANCHO + Layout.SINGLE_MARGIN);
        }
        
        Brav7 inicial = Lateral7.sacar();

        lateralIzq = new Lateral7(5, Lado.IZQUIERDO, jugadorIzquierda);
    	container.add(lateralIzq);
        lateralDer = new Lateral7(5, Lado.DERECHO, jugadorDerecha);
    	container.add(lateralDer);
    	
    	lateralIzq.setLocation(0, 0);
    	lateralDer.setLocation(getWidth() - lateralDer.getWidth() - Layout.SINGLE_MARGIN, 0);

    	int x = Layout.rightOf(lateralIzq);
    	tapete.setLocation(x, 0);
    	tapete.setSize(lateralDer.getX() - x, getHeight());
    	
	   	int y = (tapete.getHeight() - Figura7.alto) / 2;
	   	 x = (tapete.getWidth() - Figura7.ancho) / 2;
    	
    	Nodo7 nodoInicial= new Nodo7(x, y, true);
    	Nodo7.nodos.add(nodoInicial);
    	nodoInicial.setBrav(inicial);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Domino7(true, null, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
