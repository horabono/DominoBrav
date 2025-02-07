package figura;

import java.awt.Point;
import java.awt.Polygon;

import brav.Orientable;
import brav.Brav4.Giro4;
import brav.Brav4.Orientacion4;


public class Figura4 extends Figura {
	private static final long serialVersionUID = 1L;

	private static Polygon figuraBase;
	private static Polygon[] sectorBase;
	private static Point centroBase;
	
	public static int ancho, alto;
    private static boolean giroLibre;
    
    static {
    	sectorBase = new Polygon[4];
//    	set();
    }
    
	// 1. Rect�ngulo libre
    public static void set(int _ancho, int _alto) {
		ancho = impar(_ancho);
		alto = impar(_alto);
		giroLibre = ancho == alto;
		armar();
    }
    
	// 2. Cuadrado en funci�n del ancho. LLama a 1.
    public static void set(int _ancho) {
    	set(_ancho, _ancho);
    }
    
    // 3.Cuadrado por defecto. LLama a 2.
    public static void set() {
    	set(ANCHO);
    }
    
    private static void armar() {
    	figuraBase = new Polygon();

    	int w2 = ancho / 2;
		int h2 = alto / 2;
		
		Point[] v = new Point[4];
		Point[] m = new Point[4];

		v[0] = new Point(0, 0);
		v[1] = new Point(ancho - 1, 0);
		v[2] = new Point(ancho - 1, alto - 1);
		v[3] = new Point(0, alto - 1);

		for(int i = 0; i < 4; i++) {
			m[i] = mitad(v[i], v[(i+1) % 4]);
		}
		
		Point c = new Point(w2, h2);
		centroBase = c;
		
		for(int i = 0; i < 4; i++) {
			figuraBase.addPoint(v[i].x, v[i].y);
			int j = (i+3) % 4;
			int[] x = new int[] { v[i].x, m[i].x, c.x, m[j].x };
			int[] y = new int[] { v[i].y, m[i].y, c.y, m[j].y };
			sectorBase[i] = new Polygon(x, y, 4);
		}
    	
    }
	
	public Figura4() {
		poligono = figuraBase;
		sectores = sectorBase;
		centro = centroBase;
		
		setTam(ancho, alto);
	}
	
	@Override
	public Orientable getOrientacion() {
		Orientable orientable;
		
		if(brav == null) {
			orientable = Orientacion4.NORTE;
		} else {
			orientable = brav.getOrientacion();
		}
		
		return orientable;
	}
	
	public void girar() {
		brav.girar(giroLibre? Giro4.GIRO_DER: Giro4.GIRO_INV);
        repaint();
	}

}
