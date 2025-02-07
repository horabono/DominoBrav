package brav;

public class Brav4 extends Brav {

	public enum Orientacion4 implements Orientable {
		NORTE(0),
		ESTE(1),
		SUR(2),
		OESTE(3);
		
		public final int valor;

		Orientacion4(int valor) {
			this.valor = valor;
		}
		
		public static Orientacion4 get(int valor) {
			return values()[valor % 4];
		}
		
		@Override
		public int getValor() {
			return valor;
		}
		
		@Override
		public Orientable girar(Girable giro) {
			return get(valor + giro.getValor());
		}

		@Override
		public Orientable simetrico(Girable giro) {
			return girar(giro);
		}
		
		@Override
		public Orientable opuesto() {
			return girar(Giro4.GIRO_INV);
		}

		@Override
		public int getInicio() {
			return valor;
		}

		@Override
		public boolean isBase() {
			return true;
		}
	}
	
	public enum Giro4 implements Girable {
		GIRO_NUL(0),
		GIRO_IZQ(1),
		GIRO_INV(2),
		GIRO_DER(3);
		
		public final int valor;
	
		Giro4(int valor) {
			this.valor = valor;
		}
		
		public static Giro4 get(int valor) {
			return values()[valor % 4];
		}
		
		@Override
		public int getValor() {
			return valor;
		}
	}
	
	public Brav4(int[] s) {
		super(new ColorBrav[] { 
			ColorBrav.get(s[0]), 
			ColorBrav.get(s[1]), 
			ColorBrav.get(s[2]), 
			ColorBrav.get(s[3]) 
		});
		
		orientacion = Orientacion4.NORTE;
	}
	
}
