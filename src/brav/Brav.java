package brav;

public abstract class Brav implements Comparable<Brav> {

	public final String id;
	protected final ColorBrav[] colores;
	protected Orientable orientacion;
	
	protected Brav(ColorBrav[] colores) {
		StringBuffer sb = new StringBuffer();
		
		for(ColorBrav c : colores) {
			sb.append(c.valor);
		}
		
		id = sb.toString();
		this.colores = colores;
	}
	
	public ColorBrav getColor(int i) {
		return colores[i];
	}
	
	public int ini() {
		return orientacion.getInicio();
	}
	
	public int sig(int i) {
		return (i+1) % colores.length;
	}
	
	public Orientable getOrientacion() {
		return orientacion;
	}
	
	public boolean isBase() {
		return orientacion.isBase();
	}

	public void girar(Girable giro) {
	    orientacion = orientacion.girar(giro);
	}
	
	@Override
	public boolean equals(Object otro) {
		return otro instanceof Brav && ((Brav)otro).id.equals(id);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[Id=" + id + "] " + colores[0].toString());
		
		for(int i = 1; i < colores.length; i++) {
			sb.append(", " + colores[i]);
		}
	
		return sb.toString();
	}
	
	@Override
	public int compareTo(Brav other) {
		return this.id.compareTo(other.id);
	}

}
