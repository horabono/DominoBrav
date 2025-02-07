package juego;

import java.awt.Container;

import javax.swing.JFrame;

import figura.Lateral;
import figura.Nodo;
import figura.NodoListener;
import figura.Tapete;

public class Domino extends JFrame implements NodoListener {
	private static final long serialVersionUID = 1L;
	
	public static Domino crearJuego(int lados, boolean giroLibre, String jugadorDerecha, String jugadorIzquierda) {
		Domino domino = null;
		switch(lados) {
		case 3:
			domino = new Domino3(giroLibre, jugadorDerecha, jugadorIzquierda);
			break;
		case 4:
			domino = new Domino4(giroLibre, jugadorDerecha, jugadorIzquierda);
			break;
		case 5:
			domino = new Domino5(giroLibre, jugadorDerecha, jugadorIzquierda);
			break;
		case 6:
			domino = new Domino6(giroLibre, jugadorDerecha, jugadorIzquierda);
			break;
		case 7:
			domino = new Domino7(giroLibre, jugadorDerecha, jugadorIzquierda);
			break;
		}
		return domino;
	}
	
	protected Container container;
	protected Lateral lateralIzq;
	protected Lateral lateralDer;
	protected Lateral ladoActivo;
	protected Tapete tapete;
	protected final boolean giroLibre;
	protected String jugadorDerecha;
	protected String jugadorIzquierda;
	
	protected Domino(String titulo, boolean giroLibre, String jugadorDerecha, String jugadorIzquierda) {
		super(titulo);
	
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

        container = getContentPane();
        container.setLayout(null);
    	setVisible(true);
    	
    	tapete = new Tapete();
    	container.add(tapete);
    	Nodo.setComponent(tapete);
    	Nodo.addNodoListener(this);

    	this.giroLibre= giroLibre;
    	this.jugadorDerecha = jugadorDerecha;
    	this.jugadorIzquierda = jugadorIzquierda;
    	
    	ladoActivo = null;
    	armarLaterales();
    	lateralIzq.alternar();
	}
	
	protected void armarLaterales() {}

	@Override
	public void jugada(int puntos) {
		lateralDer.alternar();
		lateralIzq.alternar();
	}

}
