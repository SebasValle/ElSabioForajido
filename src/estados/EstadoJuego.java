package estados;

import java.awt.Graphics;

import ObjetosJuego.Jugador;
import graficos.Assets;
import matematicas.Vector2D;

public class EstadoJuego {
	
	private Jugador jugador;
	
	public EstadoJuego() {
<<<<<<< HEAD
		jugador = new Jugador(new Vector2D(100, 400), new Vector2D(),5, Assets.player);
=======
		jugador = new Jugador(new Vector2D(100, 400), new Vector2D(), Assets.player);
>>>>>>> 97b96cf7dc59aa182e1e9180214360972e2ec6db
	}
	
	public void actualizar() {
		jugador.actualizar();
	}
	public void dibujar(Graphics gp) {
		jugador.dibujar(gp);
	}
		
		
	
}
