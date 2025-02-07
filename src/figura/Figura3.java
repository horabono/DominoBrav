package figura;

import java.awt.Point;
import java.awt.Polygon;

import brav.Orientable;
import brav.Brav3.Giro3;
import brav.Brav3.Orientacion3;

public class Figura3 extends Figura {
	private static final long serialVersionUID = 1L;

	public static int ancho, alto;
	public static int w2, h2;
    private static boolean giroLibre;
	
	private static Polygon[] sectorBase;
	private static Polygon[] sectorInv;
	private static Polygon figuraBase;
	private static Polygon figuraInv;
	private static Point centroBase;
	private static Point centroInv;
	
	static {
		sectorBase = new Polygon[3];
		sectorInv = new Polygon[3];
//		set();
	}
	
	// 1. Triángulo libre en función del ancho y del alto.
	public static void set(int _ancho, int _alto) {
		ancho = impar(_ancho);
		alto = impar(_alto);
		giroLibre = alto == impar((int)(ancho * COS30));
		armar();
	}
	
	// 2. Triángulo regular en función del ancho.
	public static void set(int _ancho) {
		ancho = impar(_ancho);
		alto = impar((int)(ancho * COS30));
		giroLibre = true;
		armar();
	}
	
	// 3. Triángulo regular por defecto. Llama a 2.
	public static void set() {
		set(ANCHO);
	}
	
	private static void armar() {
		figuraBase = new Polygon();
		figuraInv = new Polygon();
		w2 = ancho / 2;
		h2 = alto / 2;

		int w4 = ancho / 4;
		double d = Math.sqrt(w2 * w2 + alto * alto) / 2;
		double a = Math.atan((double)w2 / alto);
		int cy = (int)(d / Math.cos(a) + 0.5);
		
		Point[] v = new Point[3];
		Point[] m = new Point[3];
		Point c;
		
		v[0] = new Point(w2, 0);
		v[1] = new Point(ancho - 1, alto - 1);
		v[2] = new Point(0, alto - 1);
		
		m[0] = new Point(ancho - w4, h2);
		m[1] = new Point(w2, alto - 1); 
		m[2] = new Point(w4, h2); 
		
		c = new Point(w2, cy);
		centroBase = c;
		for(int i = 0; i < 3; i++) {
			figuraBase.addPoint(v[i].x, v[i].y);
			int j = (i+2) % 3;
			int[] x = new int[] { v[i].x, m[i].x, c.x, m[j].x };
			int[] y = new int[] { v[i].y, m[i].y, c.y, m[j].y };
			sectorBase[i] = new Polygon(x, y, 4);
		}

		v[0] = new Point(0, 0);
		v[1] = new Point(ancho - 1, 0);
		v[2] = new Point(w2, alto - 1);
		
		m[0] = new Point(w2, 0);
		m[1] = new Point(ancho - w4, h2); 
		m[2] = new Point(w4, h2); 
		
		c = new Point(w2, alto - cy);
		centroInv=c;
		
		for(int i = 0; i < 3; i++) {
			figuraInv.addPoint(v[i].x, v[i].y);
			int j = (i+2) % 3;
			int[] x = new int[] { v[i].x, m[i].x, c.x, m[j].x };
			int[] y = new int[] { v[i].y, m[i].y, c.y, m[j].y };
			sectorInv[i] = new Polygon(x, y, 4);
		}
	}
	
	public Figura3(boolean base) {
		setTam(ancho, alto);
		setBase(base);
	}
	
	@Override
	public Orientable getOrientacion() {
		Orientable orientable;
		
		if(brav == null) {
			orientable = (base ? Orientacion3.NORTE : Orientacion3.NORESTE);
		} else {
			orientable = brav.getOrientacion();
		}
		
		return orientable;
	}
	
	@Override
	public void girar() {
		brav.girar(giroLibre? Giro3.GIRO_DER: Giro3.GIRO_INV);
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
