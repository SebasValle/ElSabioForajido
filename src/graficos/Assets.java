package graficos;
import javax.sound.sampled.Clip;
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

	//SOunds
	public static Clip musicaFondo, explosion,muerte,disparo,OvniDisparo;
	
	
	public static void init() {
		//musicaFondo = Loader.loadSound("/ships/BG.wav");
		//explosion = Loader.loadSound("/ships/explosion.wav");
		//muerte = Loader.loadSound("/ships/muerte.wav");
		//disparo = Loader.loadSound("/sonidos/BG.wav");
		//OvniDisparo = Loader.loadSound("/ships/MatarAlien.wav");

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
	}
}