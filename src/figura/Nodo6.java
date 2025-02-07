package figura;

import java.awt.Polygon;
import java.util.ArrayList;

import brav.Brav;

public class Nodo6 extends Nodo {
	
	public static final ArrayList<Nodo> nodos = new ArrayList<>();
	
	private final static int[][] inicio = { 
		{0, 1, 2, 3, 4, 5}, 
		{1, 2, 3, 4, 5, 0}, 
		{2, 3, 4, 5, 0, 1}, 
		{3, 4, 5, 0, 1, 2}, 
		{4, 5, 0, 1, 2, 3}, 
		{5, 0, 1, 2, 3, 4} 
	};

	public Nodo6(int x, int y) {
		super(x, y, new Figura6());
		alto = Figura6.alto;
		ancho = Figura6.ancho;
	}

	@Override
	protected void crearVecino(int i) {
		int posX = x;
		int posY = y;
		int w3 = (ancho + Figura6.lado) / 2;
		
		switch(i) {
		case 0:
			posY -= alto;
			break;
		case 1:
			posX += w3;
			posY -= Figura6.h2;
			break;
		case 2:
			posX += w3;
			posY += Figura6.h2;
			break;
		case 3:
			posY += alto;
			break;
		case 4:
			posX -= w3;
			posY += Figura6.h2;
			break;
		case 5:
			posX -= w3;
			posY -= Figura6.h2;
			break;
		}
		
		if(cabe(posX, posY, Figura6.ancho, Figura6.alto)) {
			Nodo6 nuevo = new Nodo6(posX, posY);
			
			for(Nodo n : nodos) {
				if(n.id != this.id) {
					nuevo.enlazar((Nodo6)n);
				}
			}
			
			nodos.add(nuevo);
			enlazar(nuevo, i);
		}
	}
	
	private void enlazar(Nodo6 otro) {
		Polygon p = otro.poligono;
		int i, j, k;

		j = 4;
		k = 0;
		i = 0;
		while(i < 6 && !casiIgual(p, j, k)) {
			i++;
			j = (j + 1) % 6;
			k = (k + 1) % 6;
		}
		
		if(i < 6) {
			enlazar(otro, i);
		}
	}
	
	@Override
	protected void enlazar(Nodo vecino, int i) {
		vecinos[i] = vecino;
		vecino.vecinos[(i+3)%6] = this;
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
				int[] otro = vecinos[i].getLado((i+3) % 6);
				if(este[0] == otro[1]) puntos++;
				if(este[1] == otro[0]) puntos++;
			}
		}
		
		for(NodoListener listener : listeners) {
			listener.jugada(puntos);
		}
	}

}
