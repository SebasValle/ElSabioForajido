package graficos;
import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage player;

	//efectos

	public static BufferedImage turbo;
	
	public static void init() {

		player = Loader.ImageLoader("/ships/p1.png");
		turbo = Loader.ImageLoader("/efectos/turbo.png");

	}
}