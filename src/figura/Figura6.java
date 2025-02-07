package figura;

import java.awt.Point;
import java.awt.Polygon;

import brav.Orientable;
import brav.Brav6.Orientacion6;
import brav.Brav6.Giro6;

public class Figura6 extends Figura {
	private static final long serialVersionUID = 1L;

	private static Polygon figuraBase;
	private static Polygon[] sectorBase;
	private static Point centroBase;

	public static int ancho, lado, alto;
	public static int w2, b1;
	public static int h2;
    private static boolean giroLibre;
    
    static {
    	sectorBase = new Polygon[6];
    }
    
	// 1. Hex�gono libre en funci�n del ancho y del alto.
    public static void set(int _ancho, int _alto) {
		ancho = impar(_ancho);
		alto = impar(_alto);
		lado = impar((int)((Math.sqrt(16 * ancho * ancho + 12 * alto * alto) - 2 * ancho) / 6));
		w2 = ancho / 2;
		h2 = alto / 2;
		b1 = (ancho-lado) / 2;
		giroLibre = alto == impar((int)(ancho * COS30));
		
		armar();
    }
	
	// 2. Hex�gono regular en funci�n del ancho. Llama a 1.
    public static void set(int _ancho) {
		set(_ancho, (int)(_ancho * COS30));
	}
    
    // 3. Hex�gono regular por defecto. Llama a 2.
    public static void set() {
    	set(ANCHO);
    }
    
    private static void armar() {
    	figuraBase = new Polygon();
    	
		Point[] v = new Point[6];
		Point[] m = new Point[6];
		Point c;

		v[0] = new Point(b1, 0);
		v[1] = new Point(b1 + lado, 0);
		v[2] = new Point(ancho - 1, h2);
		v[3] = new Point(b1 + lado, alto - 1);
		v[4] = new Point(b1, alto - 1);
		v[5] = new Point(0, h2);

		for(int i = 0; i < 6; i++) {
			m[i] = mitad(v[i], v[(i+1) % 6]);
		}
		
		c = new Point(w2+1, h2);
		centroBase = c;
		
		for(int i = 0; i < 6; i++) {
			figuraBase.addPoint(v[i].x, v[i].y);
			int j = (i+5) % 6;
			int[] x = new int[] { v[i].x, m[i].x, c.x, m[j].x };
			int[] y = new int[] { v[i].y, m[i].y, c.y, m[j].y };
			sectorBase[i] = new Polygon(x, y, 4);
		}
	}
	
	public Figura6() {
		poligono = figuraBase;
		sectores = sectorBase;
		centro = centroBase;
		setTam(ancho, alto);
	}
	
	@Override
	public Orientable getOrientacion() {
		Orientable orientable;
		
		if(brav == null) {
			orientable = Orientacion6.NORTE;
		} else {
			orientable = brav.getOrientacion();
		}
		
		return orientable;
	}
	
	public void girar() {
		brav.girar(giroLibre? Giro6.GIRO_DER: Giro6.GIRO_INV);
        repaint();
	}

}
