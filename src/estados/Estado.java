package estados;

import java.awt.*;

public abstract class Estado {

    private static Estado estadoActual = null;
    
    public static Estado getEstadoACtual()  {return estadoActual;}

    public static void cambiarEstado (Estado nuevoestado){
        estadoActual = nuevoestado;
    }
    public abstract void actualizar();
    public  abstract  void dibujar(Graphics g );
}
