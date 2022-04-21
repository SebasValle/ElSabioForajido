package ObjetosJuego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import entrada.Teclado;
import main.Window;
import matematicas.Vector2D;
import graficos.Assets;

public class Jugador extends ObjetoMovible{

		private Vector2D apuntador;
		private Vector2D aceleracion;
		private final double acc = 0.08;	//Aceleracion constante.
		private final double ANGULITO = 0.1;

		private boolean acelerando = false;

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
			acelerando = true;
		}
		else
		{
			if(velocidad.getMagnitud() != 0)
				aceleracion = (velocidad.escalar(-1).normalizacion().escalar(acc/2));
			acelerando = false;
		}
		
		velocidad = velocidad.add(aceleracion);
		
		velocidad.limite(velMax);
			
		apuntador = apuntador.setDireccion(angulo - Math.PI/2);
		
		posicion = posicion.add(velocidad);

		if (posicion.getX() > Window.WIDTH)
			posicion.setX(0);
		if (posicion.getY() > Window.HEIGHT)
			posicion.setY(0);
		if (posicion.getY() < 0)
			posicion.setY(Window.HEIGHT);
		if (posicion.getX() < 0)
			posicion.setX(Window.WIDTH);
		
	}

	@Override
	public void dibujar(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform at1 = AffineTransform.getTranslateInstance(posicion.getX() + ancho - 60, posicion.getY() + altura - 15);
		AffineTransform at2 = AffineTransform.getTranslateInstance(posicion.getX() + ancho - 82, posicion.getY() + altura - 15);

		at1.rotate(angulo);
		at2.rotate(angulo, 22, 0);

		if (acelerando) {

				g2d.drawImage(Assets.turbo, at1, null);
				g2d.drawImage(Assets.turbo, at2, null);
			}

		a = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());

		a.rotate(angulo, ancho-60, altura-15);
			
		g2d.drawImage(Assets.player, a, null);
	}
	
}
