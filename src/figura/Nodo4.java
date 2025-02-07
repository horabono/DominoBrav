package figura;

import java.awt.Polygon;
import java.util.ArrayList;

import brav.Brav;

public class Nodo4 extends Nodo {
	
	public static final ArrayList<Nodo> nodos = new ArrayList<>();
	
	private final static int[][] inicio = { 
		{0, 1, 2, 3}, 
		{1, 2, 3, 0}, 
		{2, 3, 0, 1}, 
		{3, 0, 1, 2} 
	};

	public Nodo4(int x, int y) {
		super(x, y, new Figura4());
		ancho = Figura4.ancho;
		alto = Figura4.alto;
	}

	@Override
	protected void crearVecino(int i) {
		int posX = x;
		int posY = y;
		
		switch(i) {
		case 0:
			posY -= alto;
			break;
		case 1:
			posX += ancho;
			break;
		case 2:
			posY += alto;
			break;
		case 3:
			posX -= ancho;
			break;
		}
		
		if(cabe(posX, posY, Figura4.ancho, Figura4.alto)) {
			Nodo4 nuevo = new Nodo4(posX, posY);
			
			for(Nodo n : nodos) {
				if(n.id != this.id) {
					nuevo.enlazar((Nodo4)n);
				}
			}
			
			nodos.add(nuevo);
			enlazar(nuevo, i);
		}
	}
	
	private void enlazar(Nodo4 otro) {
		Polygon p = otro.poligono;
		int i, j, k;

		j = 3;
		k = 0;
		i = 0;
		while(i < 4 && !casiIgual(p, j, k)) {
			i++;
			j = (j + 1) % 4;
			k = (k + 1) % 4;
		}
		
		if(i < 4) {
			enlazar(otro, i);
		}
	}
	
	@Override
	protected void enlazar(Nodo vecino, int i) {
		vecinos[i] = vecino;
		vecino.vecinos[(i+2)%4] = this;
	}
	
	@Override
	protected int[] getLado(int lado) {
		Brav b = figura.getBrav();
		int j  = b.getOrientacion().getValor();
		int i = inicio[lado][j];
		int si = b.sig(i);
		return new int[] { b.getColor(i).valor, b.getColor(si).valor };
	}
	
	@Override
	protected void calcularPuntos() {
		int puntos = 0;
		for(int i = 0; i < vecinos.length; i++) {
			if(vecinos[i] != null && !vecinos[i].isEmpty()) {
				int[] este = getLado(i);
				int[] otro = vecinos[i].getLado((i+2) % 4);
				if(este[0] == otro[1]) puntos++;
				if(este[1] == otro[0]) puntos++;
			}
		}
		
		for(NodoListener listener : listeners) {
			listener.jugada(puntos);
		}
	}
		
}


