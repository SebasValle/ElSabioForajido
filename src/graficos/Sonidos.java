package graficos;

import javax.sound.sampled.Clip;

public class Sonidos {
    private Clip clip;

    public Sonidos(Clip clip){
        this.clip = clip;
    }

    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }

    public void stop(){
        clip.stop();
    }
}
