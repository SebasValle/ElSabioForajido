package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loader {
	//Forma en que java guarda imagenes en memoria
	public static BufferedImage ImageLoader(String path) {	//ruta de la imagen
		try {
			return ImageIO.read(Loader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;	//En caso de que no se carque la imagen
	}

}
