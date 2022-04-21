package ObjetosJuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import matematicas.Vector2D;

public abstract class ObjetoJuego {
	protected BufferedImage textura;
	protected Vector2D posicion;
	
	public ObjetoJuego(Vector2D posicion, BufferedImage textura) {
		this.posicion = posicion;
		this.textura = textura;
	}
	
	public abstract void actualizar();
	public abstract void dibujar(Graphics g);

	public Vector2D getPosicion() {
		return posicion;
	}

	public void setPosicion(Vector2D posicion) {
		this.posicion = posicion;
	}
	
	
}
