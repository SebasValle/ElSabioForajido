package graficos;
import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage player;
	
	//Efectos
	public static BufferedImage turbo;
	//Bala
	public static BufferedImage bala1;
	//Meteoros
	
	public static BufferedImage[] grandes = new BufferedImage[4];
	public static BufferedImage[] medianos = new BufferedImage[2];
	public static BufferedImage[] pequeños = new BufferedImage[2];
	public static BufferedImage[] pequeñitos = new BufferedImage[2];
	
	//Ovni
	public static BufferedImage UFO;
	
	
	public static void init() {
		player = Loader.ImageLoader("/ships/p1.png");
		turbo = Loader.ImageLoader("/Efectos/turbo.png");
	    bala1 = Loader.ImageLoader("/lasers/BALA1.png");
	    
	    for(int i = 0 ; i<grandes.length ; i++)
	    	grandes[i] = Loader.ImageLoader("/meteoros/G"+(i+1)+".png");
	    
	    for(int i = 0 ; i<medianos.length ; i++)
	    	medianos[i] = Loader.ImageLoader("/meteoros/M"+(i+1)+".png");
	    
	    for(int i = 0 ; i<pequeños.length ; i++)
	    	pequeños[i] = Loader.ImageLoader("/meteoros/C"+(i+1)+".png");
	    
	    for(int i = 0 ; i<pequeñitos.length ; i++)
	    	pequeñitos[i] = Loader.ImageLoader("/meteoros/D"+(i+1)+".png");
	    
	    UFO = Loader.ImageLoader("/ships/UFO.png");
	}
}