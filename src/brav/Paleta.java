package brav;

import java.util.ArrayList;

public abstract class Paleta {
	protected ArrayList<Brav> lst;

	protected Paleta() {
		lst = new ArrayList<>();
		armarPaleta();
	}
	
	protected abstract void armarPaleta();
	
	public void mezclar() {
		int tam = lst.size();
		for(int i = 0; i < tam; i++) {
			int j = (int)(Math.random() * tam);
			cambiar(i, j);
		}
	}
	
	public void ordenar() {
		boolean cambio;
		int tam = lst.size();
		do {
			cambio = false;
			for(int i = 1; i < tam; i++) {
				if(lst.get(i).compareTo(lst.get(i-1)) < 0) {
					cambiar(i, i-1);
					cambio = true;
				}
//				if(lst.get(i).id.compareTo(lst.get(i-1).id) < 0) {
//					cambiar(i, i-1);
//					cambio = true;
//				}
			}
			tam--;
		} while(cambio);
	}
	
	public Brav brav(int i) {
		return lst.get(i);
	}
	
	public void mostrar() {
		int i = 0;
		for(Brav b : lst) {
			System.out.println("[" + ++i + "] " + b);
		}
	}
	
	private void cambiar(int i, int j) {
		Brav b = lst.get(i);
		lst.set(i, lst.get(j));
		lst.set(j, b);
	}
	
	public int tam() {
		return lst.size();
	}

	public void sacar(Brav brav) {
		if(lst.size() > 0) {
			lst.remove(brav);
		}
	}
	
	public Brav sacar() {
		return lst.size() > 0 ? lst.remove(0) : null;
	}
}
