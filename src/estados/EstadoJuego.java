package estados;

import java.awt.Graphics;

import ObjetosJuego.Jugador;
import graficos.Assets;
import matematicas.Vector2D;

public class EstadoJuego {
	
	private Jugador jugador;
	
	public EstadoJuego() {
		jugador = new Jugador(new Vector2D(100, 400), Assets.player);
	}
	
	public void actualizar() {
		jugador.actualizar();
	}
	public void dibujar(Graphics gp) {
		jugador.dibujar(gp);
	}
		
		
	
}
