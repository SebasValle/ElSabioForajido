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
		private Vector2D aceleracion;
		private final double acc = 0.08;	//Aceleracion constante.
		private final double ANGULITO = 0.1;

	public Jugador(Vector2D posicion, Vector2D velocidad, double velMax, BufferedImage textura) {
		super(posicion, velocidad, velMax, textura);
		apuntador = new Vector2D(0, 1);
		aceleracion = new Vector2D();
	}

	@Override
	public void actualizar() {
		if(Teclado.DERECHA)
			angulo += ANGULITO;
		if(Teclado.IZQUIERDA)
			angulo -= ANGULITO;
		
		if(Teclado.ARRIBA) {
			aceleracion = apuntador.escalar(acc);
		}
		else
		{
			if(velocidad.getMagnitud() != 0)
				aceleracion = (velocidad.escalar(-1).normalizacion().escalar(acc/2));
		}
		
		velocidad = velocidad.add(aceleracion);
		
		velocidad.limite(velMax);
			
		apuntador = apuntador.setDireccion(angulo - Math.PI/2);
		
		posicion = posicion.add(velocidad);
		
		
	}

	@Override
	public void dibujar(Graphics g) {
			Graphics2D g2d = (Graphics2D)g;
			
			a = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
			a.rotate(angulo, Assets.player.getWidth()/2, Assets.player.getHeight()/2);
			
			g2d.drawImage(Assets.player, a, null);
	}
	
}
