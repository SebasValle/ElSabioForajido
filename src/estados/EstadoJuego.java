package estados;

import java.awt.*;
import java.util.ArrayList;

import ObjetosJuego.Jugador;
import ObjetosJuego.ObjetoMovible;
import graficos.Assets;
import matematicas.Vector2D;

public class EstadoJuego {
	
	private Jugador jugador;
	private ArrayList<ObjetoMovible> objetoMovible = new ArrayList<ObjetoMovible>();
	
	public EstadoJuego() {
		jugador = new Jugador(new Vector2D(100, 400), new Vector2D(), 5, Assets.player, this);
		objetoMovible.add(jugador);
	}
	
	public void actualizar() {
		for(int i = 0 ; i<objetoMovible.size(); i++)
			objetoMovible.get(i).actualizar();
	}
	public void dibujar(Graphics gp) {
		Graphics2D g2d = (Graphics2D)gp;
		//POR EL ANTIALIASING, RENDERIZAR AL JUGADOR EN LAS VUELTAS
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		for(int i = 0 ; i<objetoMovible.size(); i++)
			objetoMovible.get(i).dibujar(gp);
	}

	public ArrayList<ObjetoMovible> getObjetoMovible() {
		return objetoMovible;
	}
	
	
		
		
	
}
