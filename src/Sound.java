import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    public static synchronized void play(final URL url) {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(url);
                clip.open(inputStream);
                clip.start();
            } catch (Exception e) {
                System.out.println("Play sound error: " + e.getMessage() + " for " + url);
            }
        }).start();
    }
}