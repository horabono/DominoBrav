package brav;

import combinatoria.Particion;
import combinatoria.Partidor;
import combinatoria.Variador;

public class Paleta5 extends Paleta {

	public Paleta5() {
		super();
	}

	@Override
	protected void armarPaleta() {
		for(Particion p : new Partidor(5).particiones) {
			if(p.partes.size() <= 4) {
				for(int[] colores : Variador.variar(p.toArray(4), 4)) {
					lst.add(new Brav5(Generador.generar(colores)));
				}
			}
		}
	}
}
