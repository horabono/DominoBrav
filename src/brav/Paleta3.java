package brav;

public class Paleta3 extends Paleta {

	public Paleta3() {
		super();
	}
	
	@Override
	protected void armarPaleta() {
		int[] colores = new int[3];
		int s, c, i;
		
//	    ' 1+1+1
		for(s = 0; s < 4; s++) {
			i = 0;
			for(c = 0; c < 4; c++) {
				if(c != s) {
					colores[i++] = c;
				}
			}
			lst.add(new Brav3(colores.clone()));
		}
		
//	    ' 1+2
		for(s = 0; s < 4; s++) {
			for(c = 0; c < 4; c++) {
				if(c != s) {
					colores[0] = s;
					colores[1] = c;
					colores[2] = c;
					lst.add(new Brav3(colores.clone()));
				}
			}
		}
	
//    ' 3
		for(s = 0; s < 4; s++) {
			for(i = 0; i < 3; i++) {
				colores[i] = s;
			}
			lst.add(new Brav3(colores.clone()));
		}
	}
}
