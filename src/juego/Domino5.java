package juego;

import java.awt.EventQueue;

import brav.Brav5;
import figura.Figura;
import figura.Figura5;
import figura.Lateral5;
import figura.Nodo5;
import figura.Lateral.Lado;
import layoutkit.Layout;

public class Domino5 extends Domino {
	private static final long serialVersionUID = 1L;

	public Domino5(boolean giroLibre, String jugadorDerecha, String jugadorIzquierda) {
        super("Dominó de pentágonos", giroLibre, jugadorDerecha, jugadorIzquierda);
	}

	@Override
	protected void armarLaterales() {
        if(giroLibre) {
    		Figura5.set();
        } else {
//    		Figura5.set(71, 51);
        	Figura5.set(Figura.ANCHO, Figura.ANCHO - Layout.SINGLE_MARGIN);
        }
        
        Brav5 inicial = Lateral5.sacar();

        lateralIzq = new Lateral5(5, Lado.IZQUIERDO, jugadorIzquierda);
    	container.add(lateralIzq);
        lateralDer = new Lateral5(5, Lado.DERECHO, jugadorDerecha);
    	container.add(lateralDer);
    	
    	lateralIzq.setLocation(0, 0);
    	lateralDer.setLocation(getWidth() - lateralDer.getWidth() - Layout.SINGLE_MARGIN, 0);

    	int x = Layout.rightOf(lateralIzq);
    	tapete.setLocation(x, 0);
    	tapete.setSize(lateralDer.getX() - x, getHeight());
    	
	   	int y = (tapete.getHeight() - Figura5.alto) / 2;
	   	 x = (tapete.getWidth() - Figura5.ancho) / 2;
    	
    	Nodo5 nodoInicial= new Nodo5(x, y, true);
    	nodoInicial.setBrav(inicial);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Domino5(true, null, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
