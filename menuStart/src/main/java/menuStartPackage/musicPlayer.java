package menuStartPackage;

import javafx.event.Event;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URISyntaxException;
public class musicPlayer extends Thread{
    MediaPlayer mediaPlayer;
    Media media;
    public String songName = "";
    public boolean menu = true;
    public boolean exit = false;
    boolean killthread = false;
    public String[] songs = {"bacchanale.mp3", "EgyptianMarch.mp3", "TurtleBeach.mp3"};
    public double volume = 0.2;



    @Override
    public void run() {
        while (true) {
            if(menu){
                songName="lacrimosa.mp3";
            }else if(exit){
                songName="diesIrae.mp3";
                exit=false;
                menu=true;
            }else{
                songName=songs[(int)(3*Math.random())];
            }
            try {
                media = new Media(getClass().getResource(songName).toURI().toString());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
            try {
                Thread.sleep(500);   //this is very important, without it thread has troubles calculating in time media duration, idk why
            } catch (InterruptedException e) {}

            try {
                Thread.sleep((long) media.getDuration().toMillis());
            } catch (InterruptedException e) {}

            if(killthread){
                return;
            }

        }
    }
    public void setVolume(double value){
        volume+=value;
        if(volume>1)volume=1;
        if(volume<0)volume=-0.001;


        mediaPlayer.setVolume(volume);
    }


    public void stopMusic(){
        if (mediaPlayer!=null) {
            mediaPlayer.stop();
            this.interrupt();
        }else{
            System.out.print("mediaplayer doesnt exist:");
        }
    }
}
