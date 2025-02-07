package brav;

public class Brav5 extends Brav {

	public enum Orientacion5 implements Orientable {
		NORTE(0, 0),
		NORNORESTE(1, 4),
		ESTENORESTE(2, 4),
		ESTESUDESTE(3, 3),
		SUDSUDESTE(4, 3),
		SUR(5, 2),
		SUDSUDOESTE(6, 2),
		OESTESUDOESTE(7, 1),
		OESTENOROESTE(8, 1),
		NORNOROESTE(9, 0);
		
		public final int valor;
		public final int inicio;

		Orientacion5(int valor, int inicio) {
			this.valor = valor;
			this.inicio = inicio;
		}
		
		public static Orientacion5 get(int valor) {
			return values()[valor % 10];
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
			return girar(Giro5.GIRO_INV);
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
	
	public enum Giro5 implements Girable {
		GIRO_NUL(0),
		GIRO_DER(1),
		GIRO_INV(5),
		GIRO_IZQ(9);
		
		public final int valor;
	
		Giro5(int valor) {
			this.valor = valor;
		}
		
		public static Giro5 get(int valor) {
			switch(valor % 10) {
			case 0:
				return GIRO_NUL;
			case 1:
				return GIRO_DER;
			case 5:
				return GIRO_INV;
			case 9:
				return GIRO_IZQ;
			}
			return null;
		}
		
		@Override
		public int getValor() {
			return valor;
		}
	}

	public Brav5(int[] s) {
		super(new ColorBrav[] { 
			ColorBrav.get(s[0]), 
			ColorBrav.get(s[1]), 
			ColorBrav.get(s[2]), 
			ColorBrav.get(s[3]), 
			ColorBrav.get(s[4]) 
		});
		
		orientacion = Orientacion5.NORTE;
	}

}
