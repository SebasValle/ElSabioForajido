package graficos;
import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage player;
	
	//Efectos
	public static BufferedImage turbo;
	//Bala
	public static BufferedImage bala1;
	
	
	public static void init() {
		player = Loader.ImageLoader("/ships/p1.png");
		turbo = Loader.ImageLoader("/Efectos/turbo.png");
	    bala1 = Loader.ImageLoader("/lasers/BALA1.png");
	}
}