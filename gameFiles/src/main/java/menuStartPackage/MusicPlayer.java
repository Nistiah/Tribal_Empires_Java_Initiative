package menuStartPackage;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer{
    public static MediaPlayer mediaPlayer;
    private String            songName   = "";
    public boolean            menu       = true;
    public boolean            exit       = false;
    private String[]          songs      = { "Bacchanale.mp3", "EgyptianMarch.mp3", "Africa.mp3", "Aida.mp3", "PolovetsianDances.mp3", "Sardar.mp3"};
    public double             volume     = 0.5;
    private Media             media;
    private File              file;
    private int               lastChoice    = -1;
    private int               currentNumber = -1;

    public void startUpmusic() {
            if (menu) {
            songName = "lacrimosa.mp3";
            } else if (exit) {
            songName = "diesIrae.mp3";
            exit     = false;
            menu     = true;
            } else {
                while (currentNumber==lastChoice) {
                    currentNumber=(int) (songs.length * Math.random());
                }
                lastChoice=currentNumber;
                songName = songs[currentNumber];
            }

            file = new File(songName);
            media = new Media(file.toURI().toString());
            mediaPlayer = null;
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();

            mediaPlayer.setOnEndOfMedia(()->{
                startUpmusic();
            });

    }

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            startUpmusic();
        } else {
            System.out.print("mediaplayer doesnt exist:");
        }
    }

    public void setVolumeAbsolute(double value) {
        volume=value;
        mediaPlayer.setVolume(volume);
    }
}
