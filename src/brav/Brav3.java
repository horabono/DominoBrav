package brav;

public class Brav3 extends Brav {

	public enum Orientacion3 implements Orientable {
		NORTE(0, 0),
		NORESTE(1, 2),
		SUDESTE(2, 2),
		SUR(3, 1),
		SUDOESTE(4, 1),
		NOROESTE(5, 0);
		
		public final int valor;
		public final int inicio;

		Orientacion3(int valor, int inicio) {
			this.valor = valor;
			this.inicio = inicio;
		}
		
		public static Orientacion3 get(int valor) {
			return values()[valor % 6];
		}
		
		@Override
		public int getValor() {
			return valor;
		}

		@Override
		public int getInicio() {
			return inicio;
		}
		
		@Override
		public Orientable girar(Girable giro) {
			return get(valor + giro.getValor());
		}

		@Override
		public Orientable simetrico(Girable giro) {
			return girar(giro).girar(giro);
		}
		
		@Override
		public Orientable opuesto() {
			return girar(Giro3.GIRO_INV);
		}
		
		@Override
		public boolean isBase() {
			return (valor & 1) == 0;
		}
	}
	
	public enum Giro3 implements Girable {
		GIRO_NUL(0),
		GIRO_DER(1),
		GIRO_INV(3),
		GIRO_IZQ(5);
		
		public final int valor;
	
		Giro3(int valor) {
			this.valor = valor;
		}
		
		public static Giro3 get(int valor) {
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
	
	public Brav3(int[] s) {
		super(new ColorBrav[] { 
			ColorBrav.get(s[0]), 
			ColorBrav.get(s[1]), 
			ColorBrav.get(s[2]) 
		});
		
		orientacion = Orientacion3.NORTE;
	}

}
