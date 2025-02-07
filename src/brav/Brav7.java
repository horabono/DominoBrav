package brav;

public class Brav7 extends Brav {

	public enum Orientacion7 implements Orientable {
		NORTE(0, 0),
		NORNORESTE(1, 6),
		NORESTE(2, 6),
		ESTENORESTE(3, 5),
		ESTESUDESTE(4, 5),
		SUDESTE(5, 4),
		SUDSUDESTE(6, 4),
		SUR(7, 3),
		SUDSUDOESTE(8, 3),
		SUDOESTE(9, 2),
		OESTESUDOESTE(10, 2),
		OESTENOROESTE(11, 1),
		NOROESTE(12,1),
		NORNOROESTE(13, 0);
		
		public final int valor;
		public final int inicio;

		Orientacion7(int valor, int inicio) {
			this.valor = valor;
			this.inicio = inicio;
		}
		
		public static Orientacion7 get(int valor) {
			return values()[valor % 14];
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
			return girar(giro).girar(giro);
		}
		
		@Override
		public Orientable opuesto() {
			return girar(Giro7.GIRO_INV);
		}
		
		@Override
		public int getInicio() {
			return inicio;
		}
		
		@Override
		public boolean isBase() {
			return (valor & 1) == 0;
		}
	}
	
	public enum Giro7 implements Girable {
		GIRO_NUL(0),
		GIRO_DER(1),
		GIRO_INV(7),
		GIRO_IZQ(13);
		
		public final int valor;
	
		Giro7(int valor) {
			this.valor = valor;
		}
		
		public static Giro7 get(int valor) {
			switch(valor % 14) {
			case 0:
				return GIRO_NUL;
			case 1:
				return GIRO_DER;
			case 7:
				return GIRO_INV;
			case 13:
				return GIRO_IZQ;
			}
			return null;
		}
		
		@Override
		public int getValor() {
			return valor;
		}
	}

	public Brav7(int[] s) {
		super(new ColorBrav[] { 
			ColorBrav.get(s[0]), 
			ColorBrav.get(s[1]), 
			ColorBrav.get(s[2]), 
			ColorBrav.get(s[3]), 
			ColorBrav.get(s[4]), 
			ColorBrav.get(s[5]), 
			ColorBrav.get(s[6]) 
		});
		
		orientacion = Orientacion7.NORTE;
	}

}
