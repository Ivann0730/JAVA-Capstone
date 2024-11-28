package DannyGermanSimulator;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundUrl[] = new URL[10];
    FloatControl fc;
    int volumeScale = 3;
    float volume;

    public Sound(){
        soundUrl[0] = getClass().getResource("/sound/coin.wav");
        soundUrl[1] = getClass().getResource("/sound/cursor.wav");
        soundUrl[2] = getClass().getResource("/sound/burning.wav");
        soundUrl[3] = getClass().getResource("/sound/LTTheme.wav");
        soundUrl[4] = getClass().getResource("/sound/levelup.wav");
        soundUrl[5] = getClass().getResource("/sound/hitmonster.wav");
        soundUrl[6] = getClass().getResource("/sound/receivedamage.wav");
        soundUrl[7] = getClass().getResource("/sound/swingSword.wav");
        soundUrl[8] = getClass().getResource("/sound/cuttree.wav");

    }
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch(Exception _){
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
    public void checkVolume(){
        switch (volumeScale){
            case 0: volume = -80f; break;
            case 1: volume = -20f; break;
            case 2: volume = -12f; break;
            case 3: volume = -5f; break;
            case 4: volume = 1f; break;
            case 5: volume = 6f; break;
        }
        fc.setValue(volume);
    }
}
