package estados;

import java.awt.image.BufferedImage;

import graficos.Assets;

public enum Tamanios {
	GRANDE(2, Assets.medianos), MED(2, Assets.pequeños), PEQUEÑO(2, Assets.pequeñitos), PEQUEÑITO(0, null);
	
	public int cantidad;
	
	public BufferedImage[] texturas;
	
	private Tamanios(int cantidad, BufferedImage[] texturas) {
		this.cantidad = cantidad;
		this.texturas = texturas;
	}
}
