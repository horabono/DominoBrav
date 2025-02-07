package brav;

import combinatoria.Particion;
import combinatoria.Partidor;
import combinatoria.Variador;

public class Paleta7 extends Paleta {

	public Paleta7() {
		super();
	}

	@Override
	protected void armarPaleta() {
		for(Particion p : new Partidor(7).particiones) {
			if(p.partes.size() <= 4) {
				for(int[] colores : Variador.variar(p.toArray(4), 4)) {
					lst.add(new Brav7(Generador.generar(colores)));
				}
			}
		}
	}

}
