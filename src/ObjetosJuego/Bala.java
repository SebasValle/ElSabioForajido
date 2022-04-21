package ObjetosJuego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import graficos.Assets;
import matematicas.Vector2D;

public class Bala extends ObjetoMovible{

	public Bala(Vector2D posicion, Vector2D velocidad, double velMax, double angulo, BufferedImage textura) {
		super(posicion, velocidad, velMax, textura);
		this.angulo = angulo;
		this.velocidad = velocidad.escalar(velMax);
	}

	@Override
	public void actualizar() {
		posicion = posicion.add(velocidad);
	}

	@Override
	public void dibujar(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		a = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
		a.rotate(angulo, ancho-60, altura-15);
		
		g2d.drawImage(Assets.bala1, a, null);
	}

}
