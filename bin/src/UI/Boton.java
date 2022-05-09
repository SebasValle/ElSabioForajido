package UI;

import entrada.Mouse;
import entrada.Teclado;
import matematicas.Vector2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import graficos.Assets;
import graficos.Texto;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Boton {

    private BufferedImage mouseFuera;
    private BufferedImage mouseEncima;
    private boolean mouseDentro;

    private Rectangle Cajaclick;
    private String texto;
    private Accion accion;

    public Boton(BufferedImage mouseFuera,BufferedImage mouseEncima,int x, int y , String texto,Accion accion) {

        this.mouseEncima = mouseEncima;
        this.mouseFuera = mouseFuera;
        this.texto = texto;
        this.accion = accion;
        Cajaclick = new Rectangle(x,y,mouseEncima.getWidth(),mouseEncima.getHeight());

    }
    public void actualizar(){
        if (Cajaclick.contains(Mouse.x,Mouse.y)){
            mouseDentro = true;
        }else{
            mouseDentro = false;
        }

        if(mouseDentro && Mouse.BotonIzquiero){

            accion.haceraccion();

        }

    }

    public void dibujar(Graphics g){

        if(mouseDentro){
            g.drawImage(mouseEncima,Cajaclick.x,Cajaclick.y,null);

        }else {
            g.drawImage(mouseFuera,Cajaclick.x,Cajaclick.y,null);
        }

        Texto.drawText(g,
        		texto,
        		new Vector2D(Cajaclick.getX()+Cajaclick.getWidth()/2,Cajaclick.getY()+Cajaclick.getHeight()),
        		true,
        		Color.BLACK,
        		Assets.fontMed);



    }
}
