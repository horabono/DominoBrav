package brav;

import java.awt.Color;

public enum ColorBrav {
	BLANCO(0, Color.WHITE),
	ROJO(1, Color.RED),
	VERDE(2, Color.GREEN),
	AZUL(3, Color.BLUE);

	public final int valor;
	public final Color color;

	private ColorBrav(int valor, Color color) {
		this.valor = valor;
		this.color = color;
	}

	public static ColorBrav get(int valor) {
		return values()[valor % 4];
	}
}
