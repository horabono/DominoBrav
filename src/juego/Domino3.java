package juego;

import java.awt.EventQueue;

import brav.Brav3;
import figura.Figura;
import figura.Figura3;
import figura.Lateral3;
import figura.Nodo3;
import figura.Lateral.Lado;
import layoutkit.Layout;

public class Domino3 extends Domino {
	private static final long serialVersionUID = 1L;

	public Domino3(boolean giroLibre, String jugadorDerecha, String jugadorIzquierda) {
        super("Dominó de triángulos", giroLibre, jugadorDerecha, jugadorIzquierda);
//		System.out.println("Domino3(" + giroLibre + ")");
	}

	@Override
	protected void armarLaterales() {
//		System.out.println("Domino3.giroLibre = " + giroLibre);
		
        if(giroLibre) {
        	Figura3.set();
        } else {
        	Figura3.set(Figura.ANCHO, Figura.ANCHO + Layout.SINGLE_MARGIN);
        }
        
        Brav3 inicial = Lateral3.sacar();

        lateralIzq = new Lateral3(3, Lado.IZQUIERDO, jugadorIzquierda);
    	container.add(lateralIzq);
        lateralDer = new Lateral3(3, Lado.DERECHO, jugadorDerecha);
    	container.add(lateralDer);
    	
    	lateralIzq.setLocation(0, 0);
    	lateralDer.setLocation(getWidth() - lateralDer.getWidth() - Layout.SINGLE_MARGIN, 0);

    	int x = Layout.rightOf(lateralIzq);
    	tapete.setLocation(x, 0);
    	tapete.setSize(lateralDer.getX() - x, getHeight());
    	
	   	int y = (tapete.getHeight() - Figura3.alto) / 2;
	   	 x = (tapete.getWidth() - Figura3.ancho) / 2;
    	
    	Nodo3 nodoInicial= new Nodo3(x, y, true);
    	nodoInicial.setBrav(inicial);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Domino3(true, null, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
