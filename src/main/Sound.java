package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    // lembrar sempre que para usar essa biblioteca o som precisa ser no formado WAV e com 16 bits
    Clip clip; // usado para importar audio files
    URL soundURL[] = new URL[30]; //usado para armazenar as filePaths dos arquivos de som

    public Sound() {
        soundURL[0] = getClass().getResource("/res/sound/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource("/res/sound/coin.wav");
        soundURL[2] = getClass().getResource("/res/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/res/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/res/sound/fanfare.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            // essa é uma forma de ter audio em java
        }catch (Exception e) {
        }
    }
    public void play() {
        // sempre que quisermos tocar um audio de alguma musica usamos esse método play
        clip.start();

    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        //quando quisermos parar o som usamos esse metodo
        clip.stop();
    }
}

