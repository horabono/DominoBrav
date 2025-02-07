package brav;

import combinatoria.Particion;
import combinatoria.Partidor;
import combinatoria.Variador;

public class Paleta6 extends Paleta {

	public Paleta6() {
		super();
	}

	@Override
	protected void armarPaleta() {
		for(Particion p : new Partidor(6).particiones) {
			if(p.partes.size() <= 4) {
				for(int[] colores : Variador.variar(p.toArray(4), 4)) {
					lst.add(new Brav6(Generador.generar(colores)));
				}
			}
		}
	}

}
