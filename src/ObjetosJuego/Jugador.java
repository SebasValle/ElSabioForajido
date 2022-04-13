package ObjetosJuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entrada.Teclado;
import matematicas.Vector2D;

public class Jugador extends ObjetoJuego{

	public Jugador(Vector2D posicion, BufferedImage textura) {
		super(posicion, textura);
	}

	@Override
	public void actualizar() {
		if(Teclado.DERECHA)
			posicion.setX(posicion.getX()+1);
	}

	@Override
	public void dibujar(Graphics g) {
			g.drawImage(textura, (int)posicion.getX(), (int)posicion.getY(), null);
	}
	
}
