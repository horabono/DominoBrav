package brav;

public interface Orientable extends Girable {
	Orientable girar(Girable giro);
	Orientable simetrico(Girable giro);
	Orientable opuesto();
	int getInicio(); 
	boolean isBase();
}
