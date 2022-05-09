package ObjetosJuego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import estados.EstadoJuego;
import estados.Tamanios;
import main.Window;
import matematicas.Vector2D;

public class Meteoro extends ObjetoMovible{
	
	private Tamanios tamanios;

	public Meteoro(Vector2D posicion, Vector2D velocidad, double velMax, BufferedImage textura,
			EstadoJuego estadoJuego, Tamanios tamanios) {
		super(posicion, velocidad, velMax, textura, estadoJuego);
		this.tamanios = tamanios;
		this.velocidad = velocidad.escalar(velMax);
	}

	@Override
	public void actualizar() {
		posicion = posicion.add(velocidad);
		
		if(posicion.getX() > Window.WIDTH)
			posicion.setX(-ancho);
		if(posicion.getY() > Window.HEIGHT)
			posicion.setY(-altura);
		
		if(posicion.getX() < -ancho)
			posicion.setX(Window.WIDTH);
		if(posicion.getY() < -altura)
			posicion.setY(Window.HEIGHT);
		
		angulo += 0.03;
		
	}
	
	@Override
	public void Destruir() {
		estadoJuego.dividirMeteoro(this);
		estadoJuego.sumarPuntaje(15); 	//Puntaje del meteoro
		super.Destruir();
		
	}

	@Override
	public void dibujar(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		a = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
		a.rotate(angulo, ancho-60, altura-15);
		
		g2d.drawImage(textura, a, null);
		
	}
	
	public Tamanios getTamanio() {
		return tamanios;
	}

}
