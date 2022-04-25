package ObjetosJuego;

import estados.EstadoJuego;
import graficos.Assets;
import main.Window;
import matematicas.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Meteoro extends ObjetoMovible{

    private Tamanios tamanios;

    public Meteoro(Vector2D posicion, Vector2D velocidad, double velMax, BufferedImage textura, EstadoJuego estadoJuego,Tamanios tamanios) {
        super(posicion, velocidad, velMax, textura, estadoJuego);
        this.tamanios = tamanios;
    }

    @Override
    public void actualizar() {
        posicion = posicion.add(velocidad);

        if(posicion.getX() > main.Window.WIDTH)
            posicion.setX(0);
        if(posicion.getY() > main.Window.HEIGHT)
            posicion.setY(0);

        if(posicion.getX() < 0)
            posicion.setX(main.Window.WIDTH);
        if(posicion.getY() < 0)
            posicion.setY(Window.HEIGHT);

        angulo += 0.1;
    }

    @Override
    public void dibujar(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        a = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
        a.rotate(angulo, ancho-60, altura-15);

        g2d.drawImage(textura, a, null);
    }

    public Tamanios getTamanios(){
        return tamanios;
    }
}
