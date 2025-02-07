package figura;

import java.awt.Point;
import java.awt.Polygon;

import brav.Orientable;
import brav.Brav7.Orientacion7;
import brav.Brav7.Giro7;

public class Figura7 extends Figura {
	private static final long serialVersionUID = 1L;
	
	private static final double RECTO = Math.PI/2;
	private static final double PI14 = Math.PI/14;
	private static final double ALFA1 = 4*PI14;
	private static final double ALFA3 = 1*PI14;
	private static final double ALFA6 = 2*PI14;

	public static int ancho, lado, alto;
	public static int w2, b1, b2, b3;
	public static int h1, h2, h3;
	public static int hi, hs;
    private static boolean giroLibre;

    private static Polygon[] sectorBase;
    private static Polygon[] sectorInv;
    private static Polygon figuraBase;
    private static Polygon figuraInv;
	private static Point centroBase;
	private static Point centroInv;
	
	static {
		sectorBase = new Polygon[7];
		sectorInv = new Polygon[7];
	}
	
	// 1. Heptágono libre en función del ancho, el lado y el alto
	public static void set(int _ancho, int _lado, int _alto) {
		ancho = impar(_ancho);
		lado = impar(_lado);
		alto = _alto;
		w2 = ancho / 2;
		b1 = (ancho - lado) / 2; // ancho y lado son impares, entonces su diferencia es par
		
		double a1 = Math.acos((double)b1 / lado);
		
		h1 = (int)(lado * Math.sin(a1) + .5);

		int h = alto - h1;
		double d = Math.sqrt(h*h + w2*w2);
		double g1 = Math.acos(w2 / d);
		double g2 = Math.acos(d / (2*lado));
		double a3 = RECTO - g1 - g2;

		b2 = (int)(lado * Math.sin(a3) + .5);
		b3 = w2 - b2;
		h2 = (int)(lado * Math.cos(a3) + .5);
		h3 = alto - h1 - h2;
		hi = h1 + h2;
		hs = h2 + h3;
		giroLibre = lado == impar((int)(ancho / (1 + 2*Math.cos(ALFA1)))); 
		armar();
	}
	
	// 2. Heptágono regular en función del ancho.
	public static void set(int _ancho) {
		ancho = impar(_ancho);
		lado = impar((int)(ancho / (1 + 2*Math.cos(ALFA1))));
		w2 = ancho / 2;
		b1 = (ancho - lado) / 2; // ancho y lado son impares, entonces su diferencia es par
		b2 = (int)(lado * Math.sin(ALFA3) + .5);
		b3 = w2 - b2;
		h1 = (int)(lado * Math.sin(ALFA1) + .5);
		h2 = (int)(lado * Math.cos(ALFA3) + .5);
		h3 = (int)(lado * Math.sin(ALFA6) + .5);
		hi = h1 + h2;
		hs = h2 + h3;
		alto = h1 + h2 + h3;
		giroLibre = true;
		armar();
	}
	
	// 3. Heptágono por defecto. Llama a 2.
	public static void set() {
		set(ANCHO);
	}
	
	private static void armar() {
		figuraBase = new Polygon();
		figuraInv = new Polygon();

		Point[] v = new Point[7];
		Point[] m = new Point[7];
		Point c;

		v[0] = new Point(w2, 0);
		v[1] = new Point(ancho - b2, h3);
		v[2] = new Point(ancho - 1, h2 + h3);
		v[3] = new Point(ancho - b1, alto - 1);
		v[4] = new Point(b1, alto - 1);
		v[5] = new Point(0, h2 + h3);
		v[6] = new Point(b2, h3);

		for(int i = 0; i < 7; i++) {
			m[i] = mitad(v[i], v[(i+1) % 7]);
		}
		
		int x0 = v[3].x;
		int y0 = v[3].y;
		int x1 = m[6].x;
		int y1 = m[6].y;
		double r = (double)(y1-y0)/(x1-x0);
		int a = (int)(r*(w2 - x0)) + y0;

		c = new Point(w2+1, a);
		centroBase = c;
		
		for(int i = 0; i < 7; i++) {
			figuraBase.addPoint(v[i].x, v[i].y);
			int j = (i+6) % 7;
			int[] x = new int[] { v[i].x, m[i].x, c.x, m[j].x };
			int[] y = new int[] { v[i].y, m[i].y, c.y, m[j].y };
			sectorBase[i] = new Polygon(x, y, 4);
		}

		v[0] = new Point(b1, 0);
		v[1] = new Point(ancho - b1, 0);
		v[2] = new Point(ancho - 1, h1);
		v[3] = new Point(ancho - b2, h1 + h2);
		v[4] = new Point(w2, alto - 1);
		v[5] = new Point(b2, h1 + h2);
		v[6] = new Point(0, h1);

		for(int i = 0; i < 7; i++) {
			m[i] = mitad(v[i], v[(i+1) % 7]);
		}

		c = new Point(w2+1, alto-a);
		centroInv = c;
		
		for(int i = 0; i < 7; i++) {
			figuraInv.addPoint(v[i].x, v[i].y);
			int j = (i+6) % 7;
			int[] x = new int[] { v[i].x, m[i].x, c.x, m[j].x };
			int[] y = new int[] { v[i].y, m[i].y, c.y, m[j].y };
			sectorInv[i] = new Polygon(x, y, 4);
		}
	}
	
	public Figura7(boolean base) {
		setTam(ancho, alto);
		setBase(base);
	}

	@Override
	public void girar() {
		brav.girar(giroLibre? Giro7.GIRO_DER: Giro7.GIRO_INV);
		setBase(brav.isBase());
        repaint();
	}

	@Override
	public Orientable getOrientacion() {
		Orientable orientable;
		
		if(brav == null) {
			orientable = (base ? Orientacion7.NORTE : Orientacion7.NORNORESTE);
		} else {
			orientable = brav.getOrientacion();
		}
		
		return orientable;
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
