package ObjetosJuego;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import graficos.Texto;
import matematicas.Vector2D;
import estados.EstadoJuego;

public class Message {
    private EstadoJuego estadoJuego;
    private float alpha;
    private String text;
    private Vector2D position;
    private Color color;
    private boolean center;
    private boolean fade;
    private Font font;
    private final float deltaAlpha = 0.01f;

    public Message(Vector2D position, boolean fade, String text, Color color,
                   EstadoJuego estadoJuego) {

        this.estadoJuego = estadoJuego;
        this.text = text;
        this.position = position;
        this.fade = fade;
        this.color = color;
        this.center = center;

        if(fade)
            alpha = 1;
        else
            alpha = 0;

    }

    public void draw(Graphics2D g2d) {

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        Texto.drawText(g2d, text, position, center, color, font);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

        position.setY(position.getY() - 1);

        if(fade)
            alpha -= deltaAlpha;
        else
            alpha += deltaAlpha;

        if(fade && alpha < 0) {
            estadoJuego.getMessages().remove(this);
        }

        if(!fade && alpha > 1) {
            fade = true;
            alpha = 1;
        }


    }


}