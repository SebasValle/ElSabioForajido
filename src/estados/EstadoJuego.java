package estados;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ObjetosJuego.Jugador;
import ObjetosJuego.Meteoro;
import ObjetosJuego.ObjetoMovible;
import graficos.Assets;
import matematicas.Vector2D;

public class EstadoJuego {
	
	private Jugador jugador;
	private ArrayList<ObjetoMovible> objetoMovible = new ArrayList<ObjetoMovible>();
	
	private int meteoros;
	
	public EstadoJuego() {
		jugador = new Jugador(new Vector2D(100, 400), new Vector2D(), 5, Assets.player, this);
		objetoMovible.add(jugador);
		
		meteoros = 1;
		
		iniciarRonda();
	}
	
	//Iniciar meteoros
	private void iniciarRonda() {
		double x, y = 0;
		
		for(int i=0 ; i<meteoros ; i++) {
			x = i%2 == 0 ? Math.random()*1000 : 0;
			x = i%2 == 0 ? 0 : Math.random()*600;
			
			BufferedImage textura = Assets.grandes[(int)(Math.random()*Assets.grandes.length)];
			
			objetoMovible.add(new Meteoro(
					new Vector2D(x, y),
					new Vector2D(0, 1).setDireccion(Math.random()*Math.PI*2), 
					2.0*Math.random() +1,	//2.0 es la velocidad del meteoro
					textura,
					this,
					Tamanios.GRANDE));		
		}
		meteoros++;
	}
	
	public void actualizar() {
		for(int i = 0 ; i<objetoMovible.size(); i++)
			objetoMovible.get(i).actualizar();
		
		for(int i = 0 ; i<objetoMovible.size(); i++)
			if(objetoMovible.get(i) instanceof Meteoro)
				return;
		iniciarRonda();
	}
	public void dibujar(Graphics gp) { 		
		Graphics2D g2d = (Graphics2D) gp;	//Rendrizando al jugador al rotar.
		
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		for(int i = 0 ; i<objetoMovible.size(); i++)
			objetoMovible.get(i).dibujar(gp);
	}

	public ArrayList<ObjetoMovible> getObjetoMovible() {
		return objetoMovible;
	}
	
	
		
		
	
}
