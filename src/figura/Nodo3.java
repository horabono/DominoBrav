package figura;

import java.awt.Polygon;
import java.util.ArrayList;

import brav.Brav;

public class Nodo3 extends Nodo {
	
	public static final ArrayList<Nodo> nodos = new ArrayList<>();
	
	private final static int[][] inicio = { 
		{2, 0, 1}, 
		{1, 2, 0}, 
		{0, 1, 2} 
	};

	public Nodo3(int x, int y, boolean base) {
		super(x, y, new Figura3(base));
		alto = Figura3.alto;
		ancho = Figura3.ancho;
	}
	
	protected void setParams() {
	}
	
	@Override
	protected void crearVecino(int i) {
		int posX = x;
		int posY = y;

		switch(i) {
		case 0:
			if(base) {
				posX -= Figura3.w2+1;
			} else {
				posX += Figura3.w2+1;
			}
			break;
		case 1:
			if(base) {
				posY += Figura3.alto+1;
			} else {
				posY -= Figura3.alto+1;
			}
			break;
		case 2:
			if(base) {
				posX += Figura3.w2+1;
			} else {
				posX -= Figura3.w2+1;
			}
			break;
		}
		
		boolean exito = cabe(posX, posY, Figura3.ancho, Figura3.alto);
		
		if(exito) {
			Nodo3 nuevo = new Nodo3(posX, posY, !base);
			for(Nodo n : nodos) {
				if(n.id != this.id && nuevo.base != n.base) {
					nuevo.enlazar((Nodo3)n);
				}
			}
			nodos.add(nuevo);
			enlazar(nuevo, i);
		}
	}
	
	private void enlazar(Nodo3 otro) {
		Polygon p = otro.poligono;
		int i, j, k;
		
		if(base) {
			j = 1;
			k = 0;
		} else {
			j = 0;
			k = 1;
		}
		
		i = 0;
		while(i < 3 && !casiIgual(p, j, k)) {
			i++;
			j = (j + 2) % 3;
			k = (k + 2) % 3;
		}
		
		if(i < 3) {
			enlazar(otro, i);
		}
	}
	
	@Override
	protected int[] getLado(int lado) {
		Brav b = figura.getBrav();
		int j  = b.getOrientacion().getValor() % 3;
		int i = inicio[lado][j];
		int si = b.sig(i);
		return new int[] { b.getColor(i).valor, b.getColor(si).valor };
	}
	
	@Override
	protected void calcularPuntos() {
		int puntos = 0;
		for(int i = 0; i < vecinos.length; i++) {
			Nodo vecino = vecinos[i];
			if(vecino != null && !vecino.isEmpty()) {
				int[] este = getLado(i);
				int[] otro = vecino.getLado(i);
				if(este[0] == otro[1]) puntos++;
				if(este[1] == otro[0]) puntos++;
			}
		}
	
		for(NodoListener listener : listeners) {
			listener.jugada(puntos);
		}
	}

}
