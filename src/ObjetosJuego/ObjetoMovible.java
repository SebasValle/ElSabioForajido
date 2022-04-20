package ObjetosJuego;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import matematicas.Vector2D;

public abstract class ObjetoMovible extends ObjetoJuego{
	
	protected Vector2D velocidad;
	protected AffineTransform a;
	protected double angulo;
<<<<<<< HEAD
	protected double velMax;

	public ObjetoMovible(Vector2D posicion, Vector2D velocidad, BufferedImage textura, double velMax) {
		super(posicion, textura);
		this.velocidad = velocidad;
		this.velMax = velMax;
=======

	public ObjetoMovible(Vector2D posicion, Vector2D velocidad, BufferedImage textura) {
		super(posicion, textura);
		this.velocidad = velocidad;
>>>>>>> 97b96cf7dc59aa182e1e9180214360972e2ec6db
		angulo = 0;

	}

}
