package estados;

import UI.Boton;

import java.awt.*;
import java.util.ArrayList;

public class Menu extends Estado{
    private ArrayList<Boton> botones;

    public estadoMenu(){
        botones = new ArrayList<Boton>();


    }

    @Override
    public void actualizar() {

    }

    @Override
    public void dibujar(Graphics g) {

    }
}
