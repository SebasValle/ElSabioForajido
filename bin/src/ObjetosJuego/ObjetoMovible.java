package ObjetosJuego;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import estados.EstadoJuego;
import graficos.Sonidos;
import matematicas.Vector2D;
import ObjetosJuego.Meteoro;

public abstract class ObjetoMovible extends ObjetoJuego{
	
	protected Vector2D velocidad;
	protected AffineTransform a;
	protected double angulo;
	protected double velMax;
	protected int ancho;
	protected int altura;
	protected EstadoJuego estadoJuego;

	private Sonidos explosion;

	public ObjetoMovible(Vector2D posicion, Vector2D velocidad, double velMax, BufferedImage textura, EstadoJuego estadoJuego) {
		super(posicion, textura);
		this.velocidad = velocidad;
		this.velMax = velMax;
		this.estadoJuego = estadoJuego;
		ancho = textura.getWidth();
		altura = textura.getHeight();
		angulo = 0;
		explosion = new Sonidos();

	}
	
	protected void colisionanding() {
		ArrayList<ObjetoMovible> objetoMovible = estadoJuego.getObjetoMovible();
		
		for(int i=0 ; i < objetoMovible.size() ; i++) {
			ObjetoMovible m = objetoMovible.get(i);
			
			if(m.equals(this))
				continue;
			
			double distancia = m.getCenter().restar(getCenter()).getMagnitud();
			if(distancia < m.ancho/2 + ancho/2 && objetoMovible.contains(this)) {
				colisionObjeto(m, this);
			}
		}
	}
	
	private void colisionObjeto(ObjetoMovible a, ObjetoMovible b) {
		
		if(a instanceof Jugador && ((Jugador)a).estaApareciendo())
			return;
		if(b instanceof Jugador && ((Jugador)b).estaApareciendo())
			return;
		
		if(!(a instanceof Meteoro && b instanceof Meteoro) &&
				  !(a instanceof Bala && b instanceof Jugador) && !(a instanceof Jugador && b instanceof Bala)&&
				  !(a instanceof Ovni && b instanceof Meteoro) && !(a instanceof Meteoro && b instanceof Ovni)&&
				  !(a instanceof BalaEnemigo && b instanceof Meteoro) && !(a instanceof Meteoro && b instanceof BalaEnemigo)) {
			a.Destruir();
			b.Destruir();
		}
	}
	
	protected void Destruir() {
		estadoJuego.getObjetoMovible().remove(this);
		if(!(this instanceof  Bala)) {
			explosion.ReproducirSonido("C:\\Users\\cpere\\Documents\\Ingenieria en Sistemas Computacionales\\CUARTOSEMESTRE\\Programacion orientada a objetos\\sonidos\\explosion.wav");
		}
	}
	
	public Vector2D getCenter() {
		return new Vector2D(posicion.getX()+ancho/2, posicion.getY()+altura/2);
	}

}
