package entrada;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {

    public static int x,y;
    public static boolean BotonIzquiero;



    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            BotonIzquiero = true;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            BotonIzquiero = false;
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x=e.getX();
        y = e.getY();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x=e.getX();
        y = e.getY();

    }
}
