package brav;

public class Generador {
	
	public static int[] generar(int[] vec) {
		int tam = 0;
		for(int n : vec) {
			tam += n;
		}
		int[] colores = new int[tam];
		int i, j;
		
		for(j = 0; j < tam; j++) {
			colores[j] = -1;
		}
		
		for(i = 0; i < vec.length; i++) {
			if(vec[i] > 0) {
				colores[i] = i;
				vec[i]--;
				tam--;
			}
		}
		
		j = 0;
		while(tam > 0) {
			for(i = 0; i < vec.length; i++) {
				if(vec[i] > 0) {
					while(colores[j] >= 0) {
						j++;
					}
					colores[j] = i;
					vec[i]--;
					tam--;
				}
			}
		}
		
		return colores;
	}
}
