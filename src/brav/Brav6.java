package brav;

public class Brav6 extends Brav {

	public enum Orientacion6 implements Orientable {
		NORTE(0),
		NORESTE(1),
		SUDESTE(2),
		SUR(3),
		SUDOESTE(4), 
		NOROESTE(5);
		
		public final int valor;

		Orientacion6(int valor) {
			this.valor = valor;
		}
		
		public static Orientacion6 get(int valor) {
			return values()[valor % 6];
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
			return girar(Giro6.GIRO_INV);
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
	
	public enum Giro6 implements Girable {
		GIRO_NUL(0),
		GIRO_IZQ(1),
		GIRO_INV(3),
		GIRO_DER(5);
		
		public final int valor;
	
		Giro6(int valor) {
			this.valor = valor;
		}
		
		public static Giro6 get(int valor) {
			switch(valor % 6) {
			case 0:
				return GIRO_NUL;
			case 1:
				return GIRO_DER;
			case 3:
				return GIRO_INV;
			case 5:
				return GIRO_IZQ;
			}
			return null;
		}

		@Override
		public int getValor() {
			return valor;
		}
	}

	public Brav6(int[] s) {
		super(new ColorBrav[] { 
			ColorBrav.get(s[0]), 
			ColorBrav.get(s[1]), 
			ColorBrav.get(s[2]), 
			ColorBrav.get(s[3]), 
			ColorBrav.get(s[4]), 
			ColorBrav.get(s[5]) 
		});
		
		orientacion = Orientacion6.NORTE;
	}

}
