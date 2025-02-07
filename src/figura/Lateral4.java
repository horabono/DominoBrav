package figura;

import brav.Brav;
import brav.Brav4;
import brav.Paleta;
import brav.Paleta4;
import figura.Figura.Estado;
import layoutkit.Layout;

public class Lateral4 extends Lateral {
	private static final long serialVersionUID = 1L;

	private static Paleta paleta;
	
	static {
		paleta = new Paleta4();
		paleta.mezclar();
	}
	
	public static Brav4 sacar() {
		return (Brav4) paleta.sacar();
	}
	
	public static Brav4 sacar(Brav4 brav) {
		paleta.sacar(brav);
		return brav;
	}

	public Lateral4(int tam, Lado lado, String nombre) {
		super(tam, lado, nombre);
	}

	@Override
	protected Figura nuevaFigura(int y) {
		Brav brav = paleta.sacar();
		Figura figura;
		
		if(brav == null) {
			figura = null;
		} else {
			figura = new Figura4();
			figura.setBrav(brav);
			figura.setLocation(Layout.DOUBLE_MARGIN, y);
			figura.setEstado(Estado.INACTIVO);
			add(figura);
		}
		
		return figura;
	}

}
