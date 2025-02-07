package figura;

import java.awt.Point;
import java.awt.Polygon;

import brav.Orientable;
import brav.Brav5.Orientacion5;
import brav.Brav5.Giro5;

public class Figura5 extends Figura {
	private static final long serialVersionUID = 1L;

	private static final double PI10 = Math.PI/10;
	private static final double ALFA1 = 4*PI10;
	
	public static int ancho, lado, alto;
	public static int w2, b1;
	public static int h1, h2;
    private static boolean giroLibre;

    private static Polygon[] sectorBase;
    private static Polygon[] sectorInv;
    private static Polygon figuraBase;
    private static Polygon figuraInv;
	private static Point centroBase;
	private static Point centroInv;
	
	static {
		sectorBase = new Polygon[5];
		sectorInv = new Polygon[5];
//		set();
	}
	
	// 1. Pentágono regular por defecto.
	public static void set() {
		set(ANCHO);
	}
	
	// 2. Pentágono regular en función del ancho.
	public static void set(int _ancho) {
		_ancho = impar(_ancho);
		set(_ancho, (int)(_ancho / (1 + 2 * Math.cos(ALFA1))));
	}
	
	// 3. Pentágono libre en función del ancho y el lado
	public static void set(int _ancho, int _lado) {
		ancho = impar(_ancho);
		lado = impar(_lado);
		w2 = ancho / 2;
		b1 = (ancho - lado) / 2; // ancho y lado son impares, entonces su diferencia es par
		
		double a1 = Math.acos((double)b1 / lado);
		double a3 = Math.asin((double)w2 / lado);
		
		h1 = (int)(lado * Math.sin(a1) + .5);
		h2 = (int)(lado * Math.cos(a3) + .5);
		alto = h1 + h2;
		giroLibre = lado == impar((int)(ancho / (1 + 2 * Math.cos(ALFA1))));
		armar();
	}
	
	private static void armar() {
		figuraBase = new Polygon();
		figuraInv = new Polygon();

		Point[] v = new Point[5];
		Point[] m = new Point[5];
		Point c;

		v[0] = new Point(w2, 0);
		v[1] = new Point(ancho - 1, h2);
		v[2] = new Point(ancho - b1, alto - 1);
		v[3] = new Point(b1, alto - 1);
		v[4] = new Point(0, h2);

		for(int i = 0; i < 5; i++) {
			m[i] = mitad(v[i], v[(i+1) % 5]);
		}
		
		int x0 = v[2].x;
		int y0 = v[2].y;
		int x1 = m[4].x;
		int y1 = m[4].y;
		double r = (double)(y1-y0)/(x1-x0);
		int a = (int)(r*(w2 - x0)) + y0;

		c = new Point(w2+1, a);
		centroBase = c;
		
		for(int i = 0; i < 5; i++) {
			figuraBase.addPoint(v[i].x, v[i].y);
			int j = (i+4) % 5;
			int[] x = new int[] { v[i].x, m[i].x, c.x, m[j].x };
			int[] y = new int[] { v[i].y, m[i].y, c.y, m[j].y };
			sectorBase[i] = new Polygon(x, y, 4);
		}

		v[0] = new Point(b1, 0);
		v[1] = new Point(ancho - b1, 0);
		v[2] = new Point(ancho - 1, h1);
		v[3] = new Point(w2, alto - 1);
		v[4] = new Point(0, h1);

		for(int i = 0; i < 5; i++) {
			m[i] = mitad(v[i], v[(i+1) % 5]);
		}

		c = new Point(w2+1, alto-a);
		centroInv = c;
		
		for(int i = 0; i < 5; i++) {
			figuraInv.addPoint(v[i].x, v[i].y);
			int j = (i+4) % 5;
			int[] x = new int[] { v[i].x, m[i].x, c.x, m[j].x };
			int[] y = new int[] { v[i].y, m[i].y, c.y, m[j].y };
			sectorInv[i] = new Polygon(x, y, 4);
		}
	}
	
	public Figura5(boolean base) {
		setTam(ancho, alto);
		setBase(base);
	}
	
	@Override
	public Orientable getOrientacion() {
		Orientable orientable;
		
		if(brav == null) {
			orientable = (base ? Orientacion5.NORTE : Orientacion5.NORNORESTE);
		} else {
			orientable = brav.getOrientacion();
		}
		
		return orientable;
	}
	
	@Override
	public void girar() {
		brav.girar(giroLibre? Giro5.GIRO_DER: Giro5.GIRO_INV);
		setBase(brav.isBase());
        repaint();
	}
	
	private void setBase(boolean base) {
		this.base = base;
        if(base) {
			poligono = figuraBase;
			sectores = sectorBase;
			centro = centroBase;
        } else {
			poligono = figuraInv;
			sectores = sectorInv;
			centro = centroInv;
        }
	}

}
