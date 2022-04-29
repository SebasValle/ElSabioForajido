package estados;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ObjetosJuego.Jugador;
import ObjetosJuego.Meteoro;
import ObjetosJuego.ObjetoMovible;
import ObjetosJuego.Ovni;
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
	
	public void dividirMeteoro(Meteoro meteoro){

		   Tamanios tamanios = meteoro.getTamanio();
		   BufferedImage[] texturas = tamanios.texturas;
		   Tamanios newTamanios = null;
		   switch (tamanios) {
		      case GRANDE:
		         newTamanios = Tamanios.MED;
		         break;
		      case MED:
		         newTamanios = Tamanios.PEQUEÑO;
		         break;
		      case PEQUEÑO:
		         newTamanios = Tamanios.PEQUEÑITO;
		         break;
		      default:
		         return;
		   }
		         for(int i = 0; i < tamanios.cantidad; i++){
		            objetoMovible.add(new Meteoro(
		                  meteoro.getPosicion(),
		                  new Vector2D(0, 1).setDireccion(Math.random()*Math.PI*2),
		                  3.0*Math.random() +1,  //2.0 es la velocidad del meteoro
		                  texturas[(int)(Math.random()*texturas.length)],
		                  this,
		                  newTamanios));
		         }

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
					5.0*Math.random() +1,	//2.0 es la velocidad del meteoro
					textura,
					this,
					Tamanios.GRANDE));		
		}
		meteoros++;
		aparecerOvni();
	}
	
	private void aparecerOvni() {
		int rand = (int) (Math.random()*2);
		
		double x = rand == 0 ? (Math.random()*1000): 0;
		double y = rand == 0 ? 0: (Math.random()*600);
		
		ArrayList<Vector2D> camino = new ArrayList<>();
		
		double posX, posY;
		//Sector superior izquierdo
		posX = Math.random()*1000/2;
		posY = Math.random()*600/2;
		camino.add(new Vector2D(posX, posY));
		//Sector superior derecho
		posX = Math.random()*1000/2 + 1000/2;
		posY = Math.random()*600/2;
		camino.add(new Vector2D(posX, posY));
		//Sector inferior izquierdo
		posX = Math.random()*1000/2;
		posY = Math.random()*600/2 + 600/2;
		camino.add(new Vector2D(posX, posY));
		//Sector inferior derecho
		posX = Math.random()*1000/2 + 1000/2;
		posY = Math.random()*600/2 + 600/2;
		camino.add(new Vector2D(posX, posY));
		
		objetoMovible.add(new Ovni(
				new Vector2D(x, y),
				new Vector2D(),
				3,	//Velocidad maxima
				Assets.UFO,
				camino,
				this));
		
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
	
	public Jugador getJugador() {
		return jugador;
	}
		
		
	
}
