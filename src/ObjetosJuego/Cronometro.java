package ObjetosJuego;

public class Cronometro {
    private long delta,ultimotiempo;
    private long tiempo ;
    private boolean corriendo;

    public Cronometro(){
        delta = 0;
        ultimotiempo = 0;
        corriendo = false;

    }

    public void correr(long tiempo){
        corriendo = true;
        this.tiempo = tiempo;
    }

    public void actualizar(){
        if(corriendo)
            delta+= System.currentTimeMillis() - ultimotiempo;
        if(delta>= tiempo){
            corriendo = false;
            delta = 0;
        }

        ultimotiempo = System.currentTimeMillis();

    }

    public boolean isCorriendo(){
        return corriendo;
    }
}
