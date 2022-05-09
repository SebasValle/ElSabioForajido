package entrada;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener{
	
	private boolean[] teclas = new boolean[256];	//Comprobar el estado de las teclas.
	public static boolean ARRIBA, IZQUIERDA, DERECHA, DISPARO;
	
	public Teclado() {
		ARRIBA = false;
		IZQUIERDA = false;
		DERECHA = false;
	}
	
	public void actualizar() {
		ARRIBA = teclas[KeyEvent.VK_UP];
		IZQUIERDA = teclas[KeyEvent.VK_LEFT];
		DERECHA = teclas[KeyEvent.VK_RIGHT];
		DISPARO = teclas[KeyEvent.VK_SPACE];
	}

	@Override//Cuando se oprime una tecla se guarda en e
	public void keyPressed(KeyEvent e) {
		teclas[e.getKeyCode()] = true;
		
	}

	@Override	//Al soltar la tecla:
	public void keyReleased(KeyEvent e) {
		teclas[e.getKeyCode()] = false;
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}