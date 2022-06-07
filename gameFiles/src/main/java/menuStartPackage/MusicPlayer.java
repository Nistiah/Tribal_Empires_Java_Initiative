package menuStartPackage;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer{
    public static MediaPlayer mediaPlayer;
    private String             songName   = "";
    public boolean            menu       = true;
    public boolean            exit       = false;
    private String[]           songs      = { "bacchanale.mp3", "EgyptianMarch.mp3", "TurtleBeach.mp3" };
    public double             volume     = 0.5;
    private Media             media;
    private File              file;


    public void startUpmusic() {
            if (menu) {
            songName = "lacrimosa.mp3";
            } else if (exit) {
            songName = "diesIrae.mp3";
            exit     = false;
            menu     = true;
            } else {
            songName = songs[(int) (3 * Math.random())];
            }

            file = new File(songName);
            media = new Media(file.toURI().toString());
            mediaPlayer = null;
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();

            System.out.println(mediaPlayer.getStatus());
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
    }
}
