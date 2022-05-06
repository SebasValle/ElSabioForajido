package estados;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ObjetosJuego.*;
import UI.Accion;
import graficos.Assets;
import graficos.Sonidos;
import matematicas.Vector2D;

public class EstadoJuego extends Estado {

	public static final Vector2D PLAYER_START_POSITION = new Vector2D(1600 / 2 - Assets.player.getWidth() / 2,
			600 / 2 - Assets.player.getHeight() / 2);

	private Jugador jugador;
	private ArrayList<ObjetoMovible> objetoMovible = new ArrayList<ObjetoMovible>();
	private ArrayList<Message> messages = new ArrayList<Message>();

	private int puntaje = 0;
	private Cronometro GOtimer;
	private int meteoros;
	private int vidas = 3;

	private Sonidos BGmusic;
	private static int BackGroundMusicOn = 1;
	
	public EstadoJuego() {
		jugador = new Jugador(new Vector2D(100, 400), new Vector2D(), 5, Assets.player, this);
		objetoMovible.add(jugador);
		GOtimer = new Cronometro();
		
		meteoros = 1;
		
		iniciarRonda();
		if(BackGroundMusicOn == 1) {
			BGmusic = new Sonidos();
			BGmusic.ReproducirSonidoLoop("C:/Users/che_v/OneDrive/Documentos/git/ElSabioForajido/recursos/sonidos/BG.wav", 1);
		}
		}
	
	public void sumarPuntaje(int value) {
		puntaje += value;
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
					2.0*Math.random() +1,	//2.0 es la velocidad del meteoro 
					textura,
					this,
					Tamanios.GRANDE));		
		}
		meteoros++;
		aparecerOvni();
	}
	
	private void aparecerOvni() {
		int rand = (int) (Math.random()*2);
		
		double x = rand == 800 ? (Math.random()*1000): 800;
		double y = rand == 100 ? 100: (Math.random()*600);
		
		ArrayList<Vector2D> camino = new ArrayList<>();
		
		double posX, posY;
		//Sector superior izquierdo
		posX = Math.random()*1000/2;
		posY = Math.random()*600/2;
		camino.add(new Vector2D(posX, posY));
		//Sector superior derecho
		posX = Math.random()*(1000/2) + (1000/2);
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
		
		dibujarScore(gp);
		dibujarVidas(gp);
	}

	private void dibujarScore(Graphics g) {
		Vector2D pos = new Vector2D(850, 25);
		
		String puntajeToString = Integer.toString(puntaje);
		
		for(int i=0 ; i < puntajeToString.length() ; i++) {
			g.drawImage(Assets.nums[Integer.parseInt(puntajeToString.substring(i, i+1))],
					(int)pos.getX(), (int)pos.getY(), null);
			pos.setX(pos.getX() + 20);
		}
	}
	
	private void dibujarVidas(Graphics g) {
		if(vidas< 1) {
			BackGroundMusicOn = 0;
			Estado.cambiarEstado(new Menu());
		}

		Vector2D posicionVida = new Vector2D(25,25);
		g.drawImage(Assets.vida, (int)posicionVida.getX(), (int)posicionVida.getY(), null);
		g.drawImage(Assets.nums[10], (int)posicionVida.getX()+40, (int)posicionVida.getY()+5, null);
		
		String vidasToString = Integer.toString(vidas);
		
		Vector2D pos = new Vector2D(posicionVida.getX(), posicionVida.getY());
		
		for(int i=0 ; i<vidasToString.length(); i++) {
			int numero = Integer.parseInt(vidasToString.substring(i, i+1));
			
			if(numero <= 0)
				break;
			g.drawImage(Assets.nums[numero], (int)pos.getX() + 60,
					(int)pos.getY() + 5, null);
			pos.setX(pos.getX()+20);
		}
	}

	public void GAMEOVER(){
		Message mensajeGO = new Message(PLAYER_START_POSITION,true,"GAME OVER", Color.WHITE,this);
		this.messages.add(mensajeGO);

	}
	
	public ArrayList<ObjetoMovible> getObjetoMovible() {
		return objetoMovible;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}
	
	public Jugador getJugador() {
		return jugador;
	}
		
	public void restandoVidas() {
		vidas--;
	}
	
}
