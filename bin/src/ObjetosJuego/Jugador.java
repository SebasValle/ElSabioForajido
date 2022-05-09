package ObjetosJuego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import entrada.Teclado;
import estados.EstadoJuego;
import graficos.Sonidos;
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
		private Cronometro tiempoDisparo;
		
		private boolean apareciendo, visible;
		
		private Cronometro tiempoAparicion, tiempoParpadeo;

		private Sonidos disparo,muerte;


	public Jugador(Vector2D posicion, Vector2D velocidad, double velMax, BufferedImage textura, EstadoJuego estadoJuego) {
		super(posicion, velocidad, velMax, textura, estadoJuego);
		apuntador = new Vector2D(0, 1);
		apuntador2 = new Vector2D(1, 0);
		aceleracion = new Vector2D();
		tiempoDisparo = new Cronometro();
		tiempoAparicion = new Cronometro();
		tiempoParpadeo = new Cronometro();
		disparo = new Sonidos();
		muerte = new Sonidos();
	}

	@Override
	public void actualizar() {
		
		if(!tiempoAparicion.isCorriendo())
		{
			apareciendo = false;
			visible = true;
		}
		
		if(apareciendo) {
			if(!tiempoParpadeo.isCorriendo())
			{
				tiempoParpadeo.correr(200);	//Parpadeamos por 200 milisegundos
				visible = !visible;
			}
		}
		
		if(Teclado.DISPARO && !tiempoDisparo.isCorriendo() && !apareciendo) {
			estadoJuego.getObjetoMovible().add(new Bala(getCenter().add(apuntador.escalar(ancho/2)), apuntador2, 12, angulo,Assets.player, estadoJuego));
			tiempoDisparo.correr(200);	//200 es el tiempo de disparo.
			disparo.ReproducirSonido("C:\\Users\\cpere\\Documents\\Ingenieria en Sistemas Computacionales\\CUARTOSEMESTRE\\Programacion orientada a objetos\\sonidos\\DISPARO.wav");
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
		
		tiempoDisparo.actualizar();		
		tiempoAparicion.actualizar();
		tiempoParpadeo.actualizar();
		colisionanding();
	}
	
	@Override
	public void Destruir() {
		apareciendo = true;
		tiempoAparicion.correr(3000);	//Tres segundo para aparecer.
		muerte.ReproducirSonido("C:\\Users\\cpere\\Documents\\Ingenieria en Sistemas Computacionales\\CUARTOSEMESTRE\\Programacion orientada a objetos\\sonidos\\muerte.wav");
		restaurarValores();
		estadoJuego.restandoVidas();
	}
	
	private void restaurarValores() {
		angulo = 0;
		velocidad = new Vector2D();
		posicion = new Vector2D(500 - Assets.player.getWidth()/2, 300 - Assets.player.getHeight()/2);
	}

	@Override
	public void dibujar(Graphics g) {
		
		if(!visible)
			return;
		
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
	
	public boolean estaApareciendo() {
		return apareciendo;
	}
	
}
