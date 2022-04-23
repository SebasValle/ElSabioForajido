package ObjetosJuego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import estados.EstadoJuego;
import graficos.Assets;
import matematicas.Vector2D;

public class Bala extends ObjetoMovible{

	public Bala(Vector2D posicion, Vector2D velocidad, double velMax, double angulo, BufferedImage textura, EstadoJuego estadoJuego) {
		super(posicion, velocidad, velMax, textura,estadoJuego);
		this.angulo = angulo;
		this.velocidad = velocidad.escalar(velMax);
	}

	@Override
	public void actualizar() {
		posicion = posicion.add(velocidad);
		//son si sale del ancho de la ventana para borrar las balas
		if(posicion.getX() < 0 || posicion.getX()  > 1000 || posicion.getY() < 0 || posicion.getY() > 600){
			estadoJuego.getObjetoMovible().remove(this);
		}
	}

	@Override
	public void dibujar(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		a = AffineTransform.getTranslateInstance(posicion.getX()+29, posicion.getY()+32);
		a.rotate(angulo, -35, 0);
		
		g2d.drawImage(Assets.bala1, a, null);
	}

}
