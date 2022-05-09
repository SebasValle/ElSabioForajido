package graficos;
import javax.sound.sampled.Clip;

import java.awt.Font;
import java.awt.image.BufferedImage;


public class Assets {
	
	public static BufferedImage player;
	
	//Efectos
	public static BufferedImage turbo;
	//Balas
	public static BufferedImage bala1;
	public static BufferedImage moco;
	//Meteoros
	
	public static BufferedImage[] grandes = new BufferedImage[4];
	public static BufferedImage[] medianos = new BufferedImage[2];
	public static BufferedImage[] pequeños = new BufferedImage[2];
	public static BufferedImage[] pequeñitos = new BufferedImage[2];
	
	//Numeros
	public static BufferedImage[] nums = new BufferedImage[11];
	
	//Ovni
	public static BufferedImage UFO;
	
	//Vida
	public static BufferedImage vida;

	//Sunds
	public static Clip musicaFondo, explosion,muerte,disparo,OvniDisparo;
	
	// fonts
	
	public static Font fontBig;
	public static Font fontMed;

	//Botones
	
	public static BufferedImage greyBtn;
	public static BufferedImage blueBtn;

	
	public static void init() {

		player = Loader.ImageLoader("/ships/p1.png");
		turbo = Loader.ImageLoader("/Efectos/turbo.png");
	    bala1 = Loader.ImageLoader("/lasers/BALA1.png");
	    moco = Loader.ImageLoader("/lasers/mocoUFO.png");
	    
	    for(int i = 0 ; i<grandes.length ; i++)
	    	grandes[i] = Loader.ImageLoader("/meteoros/G"+(i+1)+".png");
	    
	    for(int i = 0 ; i<medianos.length ; i++)
	    	medianos[i] = Loader.ImageLoader("/meteoros/M"+(i+1)+".png");
	    
	    for(int i = 0 ; i<pequeños.length ; i++)
	    	pequeños[i] = Loader.ImageLoader("/meteoros/C"+(i+1)+".png");
	    
	    for(int i = 0 ; i<pequeñitos.length ; i++)
	    	pequeñitos[i] = Loader.ImageLoader("/meteoros/D"+(i+1)+".png");
	    
	    UFO = Loader.ImageLoader("/ships/UFO.png");
	    
	    for(int i = 0 ; i<nums.length ; i++)
	    	nums[i] = Loader.ImageLoader("/HUD/"+(i)+".png");
	    
	    vida = Loader.ImageLoader("/HUD/vida.png");
	    
	    fontBig = Loader.loadFont("/fonts/futureFont.ttf", 42);
	    fontMed = Loader.loadFont("/fonts/futureFont.ttf", 20);
	    
	    greyBtn = Loader.ImageLoader("/ui/boton_azul.png");
	    blueBtn = Loader.ImageLoader("/ui/boton_gris.png");
	}
}