                         //Importamos las librerIas necesarias
package main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import entrada.Mouse;
import entrada.Teclado;
import estados.Estado;
import estados.EstadoJuego;
import estados.Menu;
import graficos.Assets;

public class Window extends JFrame implements Runnable{
	//Tamanio de la ventana
	public static final int WIDTH = 1000, HEIGHT = 600;
	//Objetos creados de las librerIas importadas
	private Canvas canvas;
	private Thread hilo;
	//Iniciador de nuestro juego
	private boolean corriendo = false;
	
	//Objetos para dibujo
	private BufferStrategy bufs;
	private Graphics gf;
	
	private final int FPS = 60;
	private double TARGET_TIME = 1000000000/FPS;
	private double delta = 0;	//Almacena el tiempo que va pasando, temporalmente
	private int AVERAGE_FPS = FPS;
	
	//private EstadoJuego estadoJuego;
	private Teclado teclado;
	private Mouse mouse;

	
	//Constructor de nuestra ventana
	public Window() {
		setTitle("El Sabio Forajido");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		canvas = new Canvas();
		teclado = new Teclado();
		mouse = new Mouse();
		
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setFocusable(true);
		
		add(canvas);
		canvas.addKeyListener(teclado);
		canvas.addMouseListener(mouse);
		canvas.addMouseMotionListener(mouse);
		setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		new Window().start();
		
	}
	
	private void actualizar(){
		teclado.actualizar();
		Estado.getEstadoACtual().actualizar();
	}
	
	private void dibujar(){
		bufs  = canvas.getBufferStrategy();
		
		if(bufs == null) {
			canvas.createBufferStrategy(3);	//Numero de buffers adecuado
			return;
		}
		//Preparamos el lienso
		gf = bufs.getDrawGraphics();
		//Dibujo inicio
		gf.setColor(Color.BLACK);
		
		gf.fillRect(0, 0, WIDTH, HEIGHT);

		Estado.getEstadoACtual().dibujar(gf);

		gf.drawString(""+AVERAGE_FPS, 10, 20);
		//Dibujo final
		
		gf.dispose();
		bufs.show();
	}
	
	private void init() {
		Assets.init();
		Estado.cambiarEstado(new Menu());
	}

	//Se inicializa el juego
	@Override
	public void run() {
		
		long ahora = 0;
		long lastTime = System.nanoTime();
		int frames = 0;
		long time = 0;
		
		init();
		
		while(corriendo)
		{
			ahora = System.nanoTime();
			//CUANDO SEA 1, PODREMOS ACTUALIZAR LOS FOTOGRAMAS.
			delta += (ahora - lastTime)/TARGET_TIME;
			time +=(ahora - lastTime);
			lastTime = ahora;
			
			if(delta >= 1)
			{
				actualizar();
				dibujar();
				delta --;
				frames++;
			}
			if(time >= 1000000000)	//Ya pasO un segundo.
			{
				AVERAGE_FPS = frames;
				frames = 0; //Para empezar a contar de nuevo.
				time = 0;
			}
		}
		
		stop();
	}
	//Da luz verde al inicio del juego
	private void start() {
		//Utilizamos un hilo por temas de renderizaciOn de la imagen.
		hilo = new Thread(this);
		hilo.start();
		corriendo = true;
	}
	//Termina el juego
	private void stop() {
		try {
			hilo.join();
			corriendo = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
