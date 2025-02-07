package figura;

import brav.Brav;
import brav.Brav3;
import brav.Paleta;
import brav.Paleta3;
import figura.Figura.Estado;
import layoutkit.Layout;

public class Lateral3 extends Lateral {
	private static final long serialVersionUID = 1L;

	private static Paleta paleta;
	
	static {
		paleta = new Paleta3();
		paleta.mezclar();
	}
	
	public static Brav3 sacar() {
		return (Brav3) paleta.sacar();
	}
	
	public static Brav3 sacar(Brav3 brav) {
		paleta.sacar(brav);
		return brav;
	}

	public Lateral3(int tam, Lado lado, String nombre) {
		super(tam, lado, nombre);
	}

	@Override
	protected Figura nuevaFigura(int y) {
		Brav brav = paleta.sacar();
		Figura figura;
		
		if(brav == null) {
			figura = null;
		} else {
			figura = new Figura3(true);
			figura.setBrav(brav);
			figura.setLocation(Layout.DOUBLE_MARGIN, y);
			figura.setEstado(Estado.INACTIVO);
			add(figura);
		}
		
		return figura;
	}

}
