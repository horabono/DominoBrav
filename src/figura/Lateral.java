package figura;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import figura.Figura.Estado;
import layoutkit.Layout;

public abstract class Lateral extends JPanel implements FiguraListener, NodoListener { 
	private static final long serialVersionUID = 1L;
	
	public enum Lado {
		IZQUIERDO,
		DERECHO
	}
	
	protected Figura[] figuras;
	protected int indiceActivo;
	protected JLabel lblPuntos;
	protected int puntos;
	private boolean activo;
	
	final int mov;
	public static Figura figuraActiva;
	
	protected Lateral(int tam, Lado lado, String nombre) {
		String title = nombre == null || nombre.length() == 0 ? lado.toString(): nombre.toUpperCase();
		setBorder(new TitledBorder(null, title, 
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
        setOpaque(true);
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
		mov = lado == Lado.IZQUIERDO ? 1 : -1;
		figuraActiva = null;
		indiceActivo = -1;
		activo = false;
		puntos = 0;
		
        Figura.addFiguraListener(this);
        Nodo.addNodoListener(this);
		
		if(tam > 0) {
	        figuras = new Figura[tam];
	        
	        lblPuntos = new JLabel();
	        lblPuntos.setBounds(Layout.SINGLE_MARGIN, Layout.DOUBLE_MARGIN, Figura.ANCHO + Layout.DOUBLE_MARGIN, Layout.DOUBLE_MARGIN);
	        lblPuntos.setOpaque(true);
	        lblPuntos.setBackground(Color.WHITE);
	        lblPuntos.setFont(new Font("Lucida Console", Font.ITALIC | Font.BOLD, 14));
	        lblPuntos.setHorizontalAlignment(JLabel.CENTER);
	        lblPuntos.setVerticalAlignment(JLabel.CENTER);
	        
			informarPuntos();
	        add(lblPuntos);
	        
			iniciar();
		} else {
	        figuras = null;
		}
	}
	
	public void alternar() {
        activo = !activo;
        setBackground(activo ? Color.ORANGE : Color.LIGHT_GRAY);
	}
	
	protected void iniciar() {
		int y = Layout.bottomOf(lblPuntos) + Layout.SINGLE_MARGIN;

		for(int i = 0; i < figuras.length; i++) {
			Figura figura = nuevaFigura(y);
			figuras[i] = figura;
			add(figura);
			y = Layout.bottomOf(figura) + Layout.SINGLE_MARGIN;
		}

		setSize(Layout.rightOf(figuras[0]) + Layout.DOUBLE_MARGIN, y + Layout.SINGLE_MARGIN);
	}

	protected abstract Figura nuevaFigura(int y);
	
	protected void activar(int i) {
		figuraActiva = figuras[i];
		int y = figuraActiva.getY();
		int x = figuraActiva.getX() + Layout.SINGLE_MARGIN * mov;
		figuraActiva.setLocation(x, y);
		figuraActiva.setEstado(Estado.ACTIVO);
		indiceActivo = i;
	}
	
	// Contexto: indiceActivo >= 0
	protected void inactivar() {
		Figura figura = figuras[indiceActivo];
		int y = figura.getY();
		int x = figura.getX() - Layout.SINGLE_MARGIN * mov;
		figura.setLocation(x, y);
		figura.setEstado(Estado.INACTIVO);
		figuraActiva = null;
		indiceActivo = -1;
	}
	
	private int buscar(Figura figura) {
		int i;
		for(i = figuras.length-1; i >= 0 && !figura.equals(figuras[i]); i--);
		return i;
	}

	@Override
	public boolean clic(Figura figura) {
		if(activo) {
			int i = buscar(figura);
			if(i >= 0) {
				if(figura.getEstado() == Estado.INACTIVO) {
					if(indiceActivo >= 0) {
						inactivar();
					}
					activar(i);
				} else if(figura.getEstado() == Estado.ACTIVO) {
					inactivar();
				}
			}
		}
		return false;
	}
	
	@Override
	public void jugada(int puntos) {
		if(indiceActivo >= 0) {
			int y = figuraActiva.getY(); 
			remove(figuraActiva);
			figuraActiva = null;
			Figura nueva = nuevaFigura(y);
			figuras[indiceActivo] = nueva;
			indiceActivo = -1;
			
			this.puntos += puntos;
			informarPuntos();
		}
	}
	
	private void informarPuntos() {
        lblPuntos.setText(String.format("Puntos:\n%2d", puntos));
	}

}
