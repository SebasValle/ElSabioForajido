package ObjetosJuego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import estados.EstadoJuego;
import graficos.Assets;
import matematicas.Vector2D;

public class BalaEnemigo extends ObjetoMovible{

	public BalaEnemigo(Vector2D posicion, Vector2D velocidad, double velMax, double angulo, BufferedImage textura, EstadoJuego estadoJuego) {
		super(posicion, velocidad, velMax, textura, estadoJuego);
		this.angulo = angulo;
		this.velocidad = velocidad.escalar(velMax);
	}

	@Override
	public void actualizar() {		//Borrando las balas
		posicion = posicion.add(velocidad);
		if(posicion.getX() < 0 || posicion.getX() > 1000 || 	//ancho de la ventana
				posicion.getY() < 0 || posicion.getY() > 600){	//altura de la ventana
			Destruir();
				}
		colisionanding();
	}

	@Override
	public void dibujar(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		a = AffineTransform.getTranslateInstance(posicion.getX()+29, posicion.getY()+32);
		a.rotate(angulo, -35, 0);
		
		g2d.drawImage(Assets.moco, a, null);
	}
	
	@Override
	public Vector2D getCenter() {
		return new Vector2D(posicion.getX()+ancho/2, posicion.getY()+ancho/2);
	}

}
