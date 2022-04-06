//LIBRERIAS NECESARIAS

package main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;



public class Window extends JFrame implements Runnable{
	
	//TAMAÑO DE LA VENTANA DEL JUEGO
	public static final int WIDTH = 800, HEIGHT = 600;
	
	//OBJETOS CREADOS DE LAS LIBRERÍAS IMPORTADAS
	private Canvas canvas;
	private Thread hilo;
	
	//INICIADOR DE NUESTRO JUEGO
	private boolean corriendo = false;
	
	//OBJETOS PARA DIBUJO
	private BufferStrategy bufs;
	private Graphics gf;
	
	
	private final int FPS = 60;
	private double TIEMPO_X_FPS= 1000000000/FPS;//target
	private double Tiempo_transcurrido = 0;//delta ALMACENA EL TIEMPO QEU VA PASANDO
	private int FPS_PROMEDIO = FPS;//average
	
	
	//CONSTRUCTOR DE NUESTRA VENTANA 
	public Window() {
		setTitle("El Sabio Forajido");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		canvas.setMaximumSize(new Dimension(WIDTH,HEIGHT));
		canvas.setMinimumSize(new Dimension(WIDTH,HEIGHT));
		canvas.setFocusable(true);
		
		add(canvas);
	}
	
	public static void main(String[] args) {
		
		new Window().start();
	}
	
	private int x =0;
	
	private void actualizar() {
		x++;
	}
	
	
	private void dibujar() {
		
		bufs = canvas.getBufferStrategy();
		
		if (bufs == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		
		gf=bufs.getDrawGraphics();
		
		//DIBUJO INICIO
		gf.clearRect(0, 0, WIDTH, HEIGHT);
		gf.setColor(Color.BLACK);
		gf.drawString("FPS "+FPS_PROMEDIO , 10,10);
	
		//DIBUJO FINAL
		
		gf.dispose();
		bufs.show();
	}
	
	//SE INICIZALIZA EL JUEGO
	@Override
	public void run() {
		
		long actual = 0;
		long ultimosegundo = System.nanoTime();
		int frames = 0;
		long time = 0;
		
		
		while (corriendo) {
			actual = System.nanoTime();
			//Cuando Tiempo_transcurrido SEA UNO PODREMOS ACTAULIZAR LOS FOTOGRAMAS
			Tiempo_transcurrido += ( actual - ultimosegundo )/TIEMPO_X_FPS;
			time += ( actual - ultimosegundo );
			ultimosegundo = actual;
			
			if(Tiempo_transcurrido >= 1)
			{
				actualizar();
				dibujar();
				Tiempo_transcurrido--;
				frames++;
				
			}
			if(time>= 1000000000)//YA PASO UN SEGUNDO
			{
				TIEMPO_X_FPS = frames;
				frames = 0;//SE REINICIA LA CUENTA DE FRMAES
				time = 0;
			}
		}  
		
	}
	
	//DA LUZ VERDE A INICIO DE JUEGO
	private void start() {
		//UTILIZAMOS UN HILO POR TEMAS DE RENDERIZACIÓN DE LA IMAGEN
		hilo = new Thread(this);
		hilo.start();
		corriendo = true;
	}
	
	//TERMINA EL JUEGO
	private void stop() {
		try {
			hilo.join();
			corriendo = false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}