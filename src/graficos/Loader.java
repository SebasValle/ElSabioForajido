package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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

	public static Clip loadSound(String path) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Loader.class.getResource(path)));
			return clip;
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			System.out.println("Error al reproducir el sonido.");
		}
		return null;
	}

}
