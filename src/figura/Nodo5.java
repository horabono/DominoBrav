package figura;

import java.awt.Polygon;
import java.util.ArrayList;

import brav.Brav;

public class Nodo5 extends Nodo {
	
	public static final ArrayList<Nodo> nodos = new ArrayList<>();
	
	private final static int[][] inicio = { 
		{4, 1, 3, 0, 2}, 
		{3, 0, 2, 4, 1}, 
		{2, 4, 1, 3, 0}, 
		{1, 3, 0, 2, 4}, 
		{0, 2, 4, 1, 3} 
	};
	
	public Nodo5(int x, int y, boolean base) {
		super(x, y, new Figura5(base));		// instancia la figura por defecto
		alto = Figura5.alto;
		ancho = Figura5.ancho;
	}
	
	private Nodo5(int x, int y, Figura5 fig) {
		super(x, y, fig);
		alto = Figura5.alto;
		ancho = Figura5.ancho;
	}

	@Override
	protected void crearVecino(int i) {
		int posX = x;
		int posY = y;
		int w3 = ancho - Figura5.b1 + 1;
		int w2 = Figura5.w2 + 1;
		
				
		switch(i) {
		case 0:
			if(base) {
				posX -= w2; //Figura5.w2 ;
				posY -= Figura5.h1;
			} else {
				posX += w2; //Figura5.w2;
				posY += Figura5.h1;
			}
			break;
		case 1:
			if(base) {
				posX -= w3; //Figura5.b1;
				posY += Figura5.h2;
			} else {
				posX += w3; //Figura5.b1;
				posY -= Figura5.h2;
			}
			break;
		case 2:
			if(base) {
				posY += alto;
			} else {
				posY -= alto;
			}
			break;
		case 3:
			if(base) {
				posX += w3; //Figura5.b1;
				posY += Figura5.h2;
			} else {
				posX -= w3; //Figura5.b1;
				posY -= Figura5.h2;
			}
			break;
		case 4:
			if(base) {
				posX += w2; //Figura5.w2;
				posY -= Figura5.h1;
			} else {
				posX -= w2; //Figura5.w2;
				posY += Figura5.h1;
			}
			break;
		}

		boolean exito = cabe(posX, posY, Figura5.ancho, Figura5.alto);
		
		if(exito) {
			Figura5 nueva = new Figura5(!base);
			Polygon p = getPolygon(posX, posY, nueva);
			
			for(Nodo n : nodos) {
				if(n.id != this.id && sePisan(p, ((Nodo5)n).poligono)) {
					exito = false;
					break;
				}
			}

			if(exito) {
				Nodo5 nuevo = new Nodo5(posX, posY, nueva);
				for(Nodo n : nodos) {
					if(n.id != this.id && nuevo.base != n.base) {
						nuevo.enlazar((Nodo5)n);
					}
				}
				nodos.add(nuevo);
				enlazar(nuevo, i);
			}
		}
	}
	
	private void enlazar(Nodo5 otro) {
		Polygon p = otro.poligono;
		int i, j, k;
		
		if(base) {
			j = 2;
			k = 0;
		} else {
			j = 0;
			k = 2;
		}
		
		i = 0;
		while(i < 5 && !casiIgual(p, j, k)) {
			i++;
			j = (j + 4) % 5;
			k = (k + 4) % 5;
		}
		
		if(i < 5) {
			enlazar(otro, i);
		}
	}
	
	@Override
	protected int[] getLado(int lado) {
		Brav b = figura.getBrav();
		int j  = b.getOrientacion().getValor() % 5;
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
				int[] otro = vecinos[i].getLado(i);
				if(este[0] == otro[1]) puntos++;
				if(este[1] == otro[0]) puntos++;
			}
		}
		
		for(NodoListener listener : listeners) {
			listener.jugada(puntos);
		}
	}

}
