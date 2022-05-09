package ObjetosJuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import estados.EstadoJuego;
import graficos.Assets;
import graficos.Sonidos;
import matematicas.Vector2D;

public class Ovni extends ObjetoMovible{
	
	private ArrayList<Vector2D> camino;
	
	private Vector2D nodoActual;	//Al que persigue el Ovni
	
	private int indice;	//Del arreglo de nodos
	
	private boolean siguiendo;	//Falso cuando se termina de recorrer el camino
	
	private Cronometro velDisparo;
	private Sonidos disparo, morir;

	public Ovni(Vector2D posicion, Vector2D velocidad, double velMax, BufferedImage textura, ArrayList<Vector2D> camino,
			EstadoJuego estadoJuego) {
		super(posicion, velocidad, velMax, textura, estadoJuego);
		this.camino = camino;
		indice = 0;
		siguiendo = true;
		velDisparo = new Cronometro();
		velDisparo.correr(1000);	//1 segundo
		disparo = new Sonidos();
	}
	
	private Vector2D caminoASeguir() {
		nodoActual = camino.get(indice);
		double distanciaANodo = nodoActual.restar(getCenter()).getMagnitud();
		
		if(distanciaANodo < 160) {	//El radio de cada nodo
			indice++;
			if(indice >= camino.size()) {	//Terminamos el camino
				siguiendo = false;
			}
		}
		
		return fuerzaBuscada(nodoActual);
	}
	
	private Vector2D fuerzaBuscada(Vector2D objetivo) {
		Vector2D velocidadDeseada = objetivo.restar(getCenter());
		velocidadDeseada = velocidadDeseada.normalizacion().escalar(velMax);
		return velocidadDeseada.restar(velocidad);
	}

	@Override
	public void actualizar() {
		Vector2D caminoASeguir;
		
		if(siguiendo)
			caminoASeguir = caminoASeguir();
		else
			caminoASeguir = new Vector2D();
		
		caminoASeguir = caminoASeguir.escalar(1/60.0);	//Segunda ley de Newton a = F/m
		
		velocidad = velocidad.add(caminoASeguir);
		
		velocidad = velocidad.limite(velMax);
		
		posicion = posicion.add(velocidad);
		
		//Disparo
		if(!velDisparo.isCorriendo()) {
			Vector2D aJugador = estadoJuego.getJugador().getCenter().restar(getCenter());
			
			aJugador = aJugador.normalizacion();
			
			double anguloActual = aJugador.getAngulo();
			
			anguloActual += Math.random()* (Math.PI/2 - Math.PI/2)/2;
			
			if(aJugador.getX() < 0)
				anguloActual = -anguloActual + Math.PI;
			
			aJugador = aJugador.setDireccion(anguloActual);
			
			BalaEnemigo bala = new BalaEnemigo(
					getCenter().add(aJugador.escalar(ancho)),
					aJugador,
					4.0,	//Velocidad del laser
					anguloActual + Math.PI/2,
					Assets.moco,
					estadoJuego);
			
			estadoJuego.getObjetoMovible().add(0, bala);
			
			velDisparo.correr(1000);

			disparo.ReproducirSonido("C:\\Users\\cpere\\Documents\\Ingenieria en Sistemas Computacionales\\CUARTOSEMESTRE\\Programacion orientada a objetos\\sonidos\\DISOVNI.wav");
		}
		
		angulo += 0.05;
		
		colisionanding();
		
		velDisparo.actualizar();
	}
	
	@Override
	public void Destruir() {
		estadoJuego.sumarPuntaje(40); //Score
		super.Destruir();
		//morir.ReproducirSonido("C:\\Users\\cpere\\Documents\\Ingenieria en Sistemas Computacionales\\CUARTOSEMESTRE\\Programacion orientada a objetos\\sonidos\\elvira.wav");
	}

	@Override
	public void dibujar(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		a = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
		//a.rotate(angulo, ancho-60, altura-15);
		a.rotate(angulo, ancho/2, altura/2);
		
		g2d.drawImage(textura, a, null);
		
		g.setColor(Color.RED);
		
	}

}
