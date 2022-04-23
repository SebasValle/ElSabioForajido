package ObjetosJuego;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import estados.EstadoJuego;
import matematicas.Vector2D;

public abstract class ObjetoMovible extends ObjetoJuego{
	
	protected Vector2D velocidad;
	protected AffineTransform a;
	protected double angulo;
	protected double velMax;
	protected int ancho;
	protected int altura;
	protected EstadoJuego estadoJuego;

	public ObjetoMovible(Vector2D posicion, Vector2D velocidad, double velMax, BufferedImage textura,EstadoJuego estadoJuego) {
		super(posicion, textura);
		this.velocidad = velocidad;
		this.velMax = velMax;
		this.estadoJuego = estadoJuego;
		ancho = textura.getWidth();
		altura = textura.getHeight()
;		angulo = 0;

	}

}
