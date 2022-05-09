package estados;

import UI.Accion;
import UI.Boton;
import graficos.Assets;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.Action;

public class Menu extends Estado{
    private ArrayList<Boton> botones;

    public Menu(){
        botones = new ArrayList<Boton>();
        
        botones.add(new Boton(
        		Assets.greyBtn,
        		Assets.blueBtn,
        		1000/2 - Assets.greyBtn.getWidth()/2,
        		600/2 - Assets.greyBtn.getHeight()-30,
        		"PLAY",
        		new Accion() {
        			@Override
        			public void haceraccion() {
        				Estado.cambiarEstado(new EstadoJuego());
        			}
        		}
        		));

        botones.add(new Boton(
        		Assets.greyBtn,
        		Assets.blueBtn,
        		1000/2 - Assets.greyBtn.getWidth()/2,
        		600/2 - Assets.greyBtn.getHeight()/2,
        		"EXIT",
        		new Accion() {
        			@Override
        			public void haceraccion() {
        				System.exit(0);
        			}
        		}
        		));

    }

    @Override
    public void actualizar() {

    	for(Boton b: botones)
    		b.actualizar();
    }

    @Override
    public void dibujar(Graphics g) {
    	for(Boton b: botones)
    		b.dibujar(g);
    	
    }
}