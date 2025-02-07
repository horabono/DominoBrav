package figura;

import brav.Brav;
import figura.Figura.Estado;
import layoutkit.Layout;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

import javax.swing.JComponent;

public abstract class Nodo implements FiguraListener {
	protected static int ID = 0;
	
	protected static JComponent component;
	protected static int maxX, maxY;
	
	public static void setComponent(JComponent c) {
		component = c;
	}
    
    protected static Polygon getPolygon(int x, int y, Figura figura) {
    	Polygon p = figura.getPoligono();
    	int tam = p.npoints;
    	int[] xPos = new int[tam];
    	int[] yPos = new int[tam];
    	
    	for(int i = 0; i < tam; i++) {
    		xPos[i] = p.xpoints[i] + x;
    		yPos[i] = p.ypoints[i] + y;
    	}
    	
    	return new Polygon(xPos, yPos, tam);
    }
	
	protected static boolean sePisan(Polygon a, Polygon b) {
		Point p = new Point();
		Point q = new Point();
		boolean pisa = false;
		
		for(int i = 0; i < a.npoints && !pisa; i++) {
			p.x = a.xpoints[i];
			p.y = a.ypoints[i];
			q.x = b.xpoints[i];
			q.y = b.ypoints[i];

			pisa = b.contains(p) || a.contains(q);
		}
		
		return pisa;
	}
	
	protected static boolean entre(int i, int min, int max) {
		return i > min && i < max;
	}
	
	protected static boolean cabe(int posX, int posY, int ancho, int alto) {
		return entre(posX, 0, maxX - ancho) && entre(posY, 0, maxY - alto);
	}
	
	protected static boolean casiIgual(int a, int b, int margen) {
		return entre(a-b, -margen, margen);
	}
	
	protected static boolean casiIgual(int a, int b) {
		return casiIgual(a, b, 5);
	}
	
	protected static boolean casiIgual(Polygon p, int i, Polygon q, int j) {
		return casiIgual(p.xpoints[i], q.xpoints[j]) && casiIgual(p.ypoints[i], q.ypoints[j]);
	}
    
    protected static ArrayList<NodoListener> listeners = new ArrayList<>();
  
    public static void addNodoListener(NodoListener listener) {
    	listeners.add(listener);
    }
	
	protected final int id;
	protected final Figura figura;
	protected final Polygon poligono;
	protected final int x, y;
	protected int ancho, alto; 
	protected final boolean base;
	protected final Nodo[] vecinos;
		
    protected Nodo(int x, int y, Figura figura) {
    	this.id = ++ID;
		this.x = x;
		this.y = y;
    	this.figura = figura;
    	figura.setLocation(x, y);
		if(!figura.isEmpty()) {
			figura.setEstado(Estado.JUGADO);
		}
		vecinos = new Nodo[figura.getLados()];
    	base = figura.isBase();
		poligono = getPolygon(x, y, figura);
		maxX = component.getWidth();
		maxY = component.getHeight() - Layout.DOUBLE_MARGIN;
		component.add(figura);
		Figura.addFiguraListener(this);
    }
    
    public Nodo getVecino(int i) {
		return vecinos[i];
    }
	
	public Figura getFigura() {
		return figura;
	}
	
	protected boolean isEmpty() {
		return figura.isEmpty();
	}

	public void setBrav(Brav brav) {
		if(figura.isBase() == brav.isBase()) {
			figura.setBrav(brav);
			figura.setEstado(brav == null ? Estado.VACIO : Estado.JUGADO);
			if(figura.getEstado() == Estado.JUGADO) {
				calcularPuntos();
			}
			crearVecinos();
			component.repaint();
		}
	}
		
	protected void crearVecinos() {
		for(int i = 0; i < vecinos.length; i++) {
			if(vecinos[i] == null) {
				crearVecino(i);
			}
		}
	}
	
	protected void enlazar(Nodo vecino, int i) {
		vecinos[i] = vecino;
		vecino.vecinos[i] = this;
	}
	
	protected boolean casiIgual(Polygon p, int i, int j) {
		return casiIgual(p, i, poligono, j);
	}
	
	protected abstract void crearVecino(int i);
	protected abstract int[] getLado(int lado);
	protected abstract void calcularPuntos();

	@Override
	public boolean clic(Figura figura) {
		if(figura == this.figura && figura.getEstado() == Estado.VACIO && Lateral.figuraActiva != null) {
			setBrav(Lateral.figuraActiva.getBrav());
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Nodo " + id + " " + figura.toString();
	}

}
