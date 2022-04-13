package ObjetosJuego;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import matematicas.Vector2D;

public abstract class ObjetoMovible extends ObjetoJuego{
	
	protected Vector2D velocidad;
	protected AffineTransform a;
	protected double angulo;

	public ObjetoMovible(Vector2D posicion, Vector2D velocidad, BufferedImage textura) {
		super(posicion, textura);
		this.velocidad = velocidad;
		angulo = 0;

	}

}
