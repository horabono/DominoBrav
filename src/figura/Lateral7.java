package figura;

import brav.Brav;
import brav.Brav7;
import brav.Paleta;
import brav.Paleta7;
import figura.Figura.Estado;
import layoutkit.Layout;

public class Lateral7 extends Lateral {
	private static final long serialVersionUID = 1L;

	private static Paleta paleta;
	
	static {
		paleta = new Paleta7();
		paleta.mezclar();
	}
	
	public static Brav7 sacar() {
		return (Brav7) paleta.sacar();
	}
	
	public static Brav7 sacar(Brav7 brav) {
		paleta.sacar(brav);
		return brav;
	}

	public Lateral7(int tam, Lado lado, String nombre) {
		super(tam, lado, nombre);
	}

	@Override
	protected Figura nuevaFigura(int y) {
		Brav brav = paleta.sacar();
		Figura figura;
		
		if(brav == null) {
			figura = null;
		} else {
			figura = new Figura7(true);		// instancia la figura por defecto
			figura.setBrav(brav);
			figura.setLocation(Layout.DOUBLE_MARGIN, y);
			figura.setEstado(Estado.INACTIVO);
			add(figura);
		}
		
		return figura;
	}

}
