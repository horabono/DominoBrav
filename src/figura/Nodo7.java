package figura;

import java.awt.Polygon;
import java.util.ArrayList;

import brav.Brav;

public class Nodo7 extends Nodo {
	
	public static final ArrayList<Nodo> nodos = new ArrayList<>();
	
	private final static int[][] inicio = { 
		{6, 2, 5, 1, 4, 0, 3}, 
		{5, 1, 4, 0, 3, 6, 2}, 
		{4, 0, 3, 6, 2, 5, 1}, 
		{3, 6, 2, 5, 1, 4, 0}, 
		{2, 5, 1, 4, 0, 3, 6}, 
		{1, 4, 0, 3, 6, 2, 5}, 
		{0, 3, 6, 2, 5, 1, 4} 
	};

	public Nodo7(int x, int y, boolean base) {
		super(x, y, new Figura7(base));
		alto = Figura7.alto;
		ancho = Figura7.ancho;
	}
	
	public Nodo7(int x, int y, Figura7 fig) {
		super(x, y, fig);
		alto = Figura7.alto;
		ancho = Figura7.ancho;
	}

	@Override
	protected void crearVecinos() {
		int j = figura.isBase() ? 0 : 1;
		for(int i = 0; i < vecinos.length; i++) {
			if(vecinos[j] == null) {
				crearVecino(j);
			}
			j = (j+1) % 7;
		}
	}

	@Override
	protected void crearVecino(int i) {
		int posX = x;
		int posY = y;
		int b3 = Figura7.b3 + 1;
		int w3 = ancho - Figura7.b1;
		int w2 = ancho - Figura7.b2; //Figura7.w2 + 1;
		int h3 = Figura7.h3 + 1;
		
		switch(i) {
		case 0:
			if(base) {
				posX -= b3; //Figura7.b3;
				posY -= Figura7.hi;
			} else {
				posX += b3; //Figura7.b3;
				posY += Figura7.hi;
			}
			break;
		case 1:
			if(base) {
				posX -= w2; //Figura7.w2;
				posY -= h3; //Figura7.h1;
			} else {
				posX += w2; //Figura7.w2;
				posY += h3; //Figura7.h1;
			}
			break;
		case 2:
			if(base) {
				posX -= w3; //Figura7.b1;
				posY += Figura7.hs;
			} else {
				posX += w3; //Figura7.b1;
				posY -= Figura7.hs;
			}
			break;
		case 3:
			if(base) {
				posY += alto;
			} else {
				posY -= alto;
			}
			break;
		case 4:
			if(base) {
				posX += w3; //Figura7.b1;
				posY += Figura7.hs;
			} else {
				posX -= w3; //Figura7.b1;
				posY -= Figura7.hs;
			}
			break;
		case 5:
			if(base) {
				posX += w2; //Figura7.w2;
				posY -= h3; //Figura7.h1;
			} else {
				posX -= w2; //Figura7.w2;
				posY += h3; //Figura7.h1;
			}
			break;
		case 6:
			if(base) {
				posX += b3; //Figura7.b3;
				posY -= Figura7.hi;
			} else {
				posX -= b3; //Figura7.b3;
				posY += Figura7.hi;
			}
			break;
		}

		boolean exito = cabe(posX, posY, Figura7.ancho, Figura7.alto);
		
		if(exito) {
			Figura7 nueva = new Figura7(!base);
			Polygon p = getPolygon(posX, posY, nueva);
			
			for(Nodo n : nodos) {
				if(n.id != this.id && sePisan(p, ((Nodo7)n).poligono)) {
					exito = false;
					break;
				}
			}

			if(exito) {
				Nodo7 nuevo = new Nodo7(posX, posY, nueva);
				for(Nodo n : nodos) {
					if(n.id != this.id && nuevo.base != n.base) {
						nuevo.enlazar((Nodo7)n);
					}
				}
				nodos.add(nuevo);
				enlazar(nuevo, i);
			}
		}
	}
	
	private void enlazar(Nodo7 otro) {
		Polygon p = otro.poligono;
		int i, j, k;
		
		if(base) {
			j = 3;
			k = 0;
		} else {
			j = 0;
			k = 3;
		}
		
		i = 0;
		while(i < 7 && !casiIgual(p, j, k)) {
			i++;
			j = (j + 6) % 7;
			k = (k + 6) % 7;
		}
		
		if(i < 7) {
			enlazar(otro, i);
		}
	}

	@Override
	protected int[] getLado(int lado) {
		Brav b = figura.getBrav();
		int j  = b.getOrientacion().getValor() % 7;
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
