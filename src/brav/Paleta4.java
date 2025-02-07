package brav;

import combinatoria.Particion;
import combinatoria.Partidor;
import combinatoria.Variador;

public class Paleta4 extends Paleta {
	
	public Paleta4() {
		super();
	}
	
	@Override
	protected void armarPaleta() {
		for(Particion p : new Partidor(4).particiones) {
			if(p.partes.size() <= 4) {
				for(int[] colores : Variador.variar(p.toArray(4), 4)) {
					lst.add(new Brav4(Generador.generar(colores)));
				}
			}
		}
	}
}
