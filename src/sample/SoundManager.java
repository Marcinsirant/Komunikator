package sample;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class SoundManager {

    static String bip = "out/production/Komunikator/resource/sounds/swiftly.mp3";  //sound file path
    static AudioClip audioClip;

    //Media pick = new Media(new File(bip).toURI().toString());
    public static void soundReceived(){
        audioClip = new AudioClip(String.valueOf(SoundManager.class.getResource("../resource/sounds/swiftly.mp3")));
        audioClip.play();
    }


}
