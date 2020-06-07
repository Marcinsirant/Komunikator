package sample;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class SoundManager {

    static String bip = "src/resource/sounds/swiftly.mp3";  //sound file path
    static AudioClip audioClip;

    //Media pick = new Media(new File(bip).toURI().toString());
    public static void soundReceived(){
        audioClip = new AudioClip(new File(bip).toURI().toString());
        audioClip.play();
    }


}
