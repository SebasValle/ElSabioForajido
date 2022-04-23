package ObjetosJuego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.EmptyStackException;

import entrada.Teclado;
import estados.EstadoJuego;
import matematicas.Vector2D;
import graficos.Assets;
import main.Window;

public class Jugador extends ObjetoMovible{

		private Vector2D apuntador;
		private Vector2D apuntador2;
		private Vector2D aceleracion;
		private final double acc = 0.08;	//Aceleracion constante.
		private final double ANGULITO = 0.1;
		private boolean acelerando = false;
		private Cronometro tiempodisparo;

	public Jugador(Vector2D posicion, Vector2D velocidad, double velMax, BufferedImage textura, EstadoJuego estadoJuego) {
		super(posicion, velocidad, velMax, textura,estadoJuego);
		apuntador = new Vector2D(0, 1);
		apuntador2 = new Vector2D(1, 0);
		aceleracion = new Vector2D();
		tiempodisparo = new Cronometro();
	}

	@Override
	public void actualizar() {

		if(Teclado.DISPARO && !tiempodisparo.isCorriendo() ) {
			estadoJuego.getObjetoMovible().add(new Bala(getCenter().add(apuntador.escalar(ancho/2)), apuntador2, 12,angulo ,Assets.player,estadoJuego));
			//200 es el tiempo de disparo
			tiempodisparo.correr(300);
		}
		
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
		apuntador2 = apuntador2.setDireccion(angulo - Math.PI*2);
		
		posicion = posicion.add(velocidad);
		
		if(posicion.getX() > Window.WIDTH)
			posicion.setX(0);
		if(posicion.getY() > Window.HEIGHT)
			posicion.setY(0);
		
		if(posicion.getX() < 0)
			posicion.setX(Window.WIDTH);
		if(posicion.getY() < 0)
			posicion.setY(Window.HEIGHT);

		tiempodisparo.actualizar();
		
	}

	@Override
	public void dibujar(Graphics g) {
			Graphics2D g2d = (Graphics2D)g;
			
			AffineTransform at1 = AffineTransform.getTranslateInstance(posicion.getX() + ancho-60, posicion.getY() + altura-15);
			AffineTransform at2 = AffineTransform.getTranslateInstance(posicion.getX() + ancho-82, posicion.getY() + altura-15);
			
			at1.rotate(angulo);
			at2.rotate(angulo, 22, 0);
			
			if(acelerando) {
				g2d.drawImage(Assets.turbo, at1, null);
				g2d.drawImage(Assets.turbo, at2, null);
			}
			
			a = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
			a.rotate(angulo, ancho-60, altura-15);
			
			g2d.drawImage(Assets.player, a, null);
	}
	
	public Vector2D getCenter() {
		return new Vector2D(posicion.getX()+ancho/2, posicion.getY()+altura/2);
	}
	
}
