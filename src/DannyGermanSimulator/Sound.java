package DannyGermanSimulator;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundUrl[] = new URL[30];
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
        soundUrl[9] = getClass().getResource("/sound/gameover.wav");
        soundUrl[10] = getClass().getResource("/sound/stairs.wav");
        soundUrl[11] = getClass().getResource("/sound/track/TitleTheme .wav");
        soundUrl[12] = getClass().getResource("/sound/expansion.wav");
        soundUrl[13] = getClass().getResource("/sound/sleep.wav");
        soundUrl[14] = getClass().getResource("/sound/blocked.wav");
        soundUrl[15] = getClass().getResource("/sound/parry.wav");
        soundUrl[16] = getClass().getResource("/sound/track/journey.wav");
        soundUrl[17] = getClass().getResource("/sound/speak.wav");
        soundUrl[18] = getClass().getResource("/sound/track/Dungeon.wav");
        soundUrl[19] = getClass().getResource("/sound/roarOgre.wav");
        soundUrl[20] = getClass().getResource("/sound/chipwall.wav");
        soundUrl[21] = getClass().getResource("/sound/doorOpen.wav");
        soundUrl[22] = getClass().getResource("/sound/unlock.wav");
        soundUrl[23] = getClass().getResource("/sound/track/Decisive Battle(Loop).wav");
        soundUrl[24] = getClass().getResource("/sound/fanfare.wav");
        soundUrl[25] = getClass().getResource("/sound/track/Final.wav");
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
