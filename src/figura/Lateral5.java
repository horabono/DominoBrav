package figura;

import brav.Brav;
import brav.Brav5;
import brav.Paleta;
import brav.Paleta5;
import figura.Figura.Estado;
import layoutkit.Layout;

public class Lateral5 extends Lateral {
	private static final long serialVersionUID = 1L;

	private static Paleta paleta;
	
	static {
		paleta = new Paleta5();
		paleta.mezclar();
	}
	
	public static Brav5 sacar() {
		return (Brav5) paleta.sacar();
	}
	
	public static Brav5 sacar(Brav5 brav) {
		paleta.sacar(brav);
		return brav;
	}

	public Lateral5(int tam, Lado lado, String nombre) {
		super(tam, lado, nombre);
	}

	@Override
	protected Figura nuevaFigura(int y) {
		Brav brav = paleta.sacar();
		Figura figura;
		
		if(brav == null) {
			figura = null;
		} else {
			figura = new Figura5(true);		// instancia la figura por defecto
			figura.setBrav(brav);
			figura.setLocation(Layout.DOUBLE_MARGIN, y);
			figura.setEstado(Estado.INACTIVO);
			add(figura);
		}
		
		return figura;
	}

}
