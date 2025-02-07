package figura;

import brav.Brav;
import brav.Brav6;
import brav.Paleta;
import brav.Paleta6;
import figura.Figura.Estado;
import layoutkit.Layout;

public class Lateral6 extends Lateral {
	private static final long serialVersionUID = 1L;

	private static Paleta paleta;
	
	static {
		paleta = new Paleta6();
		paleta.mezclar();
	}
	
	public static Brav6 sacar() {
		return (Brav6) paleta.sacar();
	}
	
	public static Brav6 sacar(Brav6 brav) {
		paleta.sacar(brav);
		return brav;
	}

	public Lateral6(int tam, Lado lado, String nombre) {
		super(tam, lado, nombre);
	}

	@Override
	protected Figura nuevaFigura(int y) {
		Brav brav = paleta.sacar();
		Figura figura;
		
		if(brav == null) {
			figura = null;
		} else {
			figura = new Figura6();
			figura.setBrav(brav);
			figura.setLocation(Layout.DOUBLE_MARGIN, y);
			figura.setEstado(Estado.INACTIVO);
			add(figura);
		}
		
		return figura;
	}

}
