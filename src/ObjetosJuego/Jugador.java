package ObjetosJuego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import entrada.Teclado;
import matematicas.Vector2D;
import graficos.Assets;

public class Jugador extends ObjetoMovible{

		private Vector2D apuntador;

	public Jugador(Vector2D posicion, Vector2D velocidad, BufferedImage textura) {
		super(posicion, velocidad, textura);
		apuntador = new Vector2D(0, 1);
	}

	@Override
	public void actualizar() {
		if(Teclado.DERECHA)
			angulo += Math.PI/20;
		if(Teclado.IZQUIERDA)
			angulo -= Math.PI/20;
			
		apuntador = apuntador.setDireccion(angulo - Math.PI/2);
	}

	@Override
	public void dibujar(Graphics g) {
			Graphics2D g2d = (Graphics2D)g;
			
			a = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
			a.rotate(angulo, Assets.player.getWidth()/2, Assets.player.getHeight()/2);
			
			g2d.drawImage(Assets.player, a, null);
	}
	
}
