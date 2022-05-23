package menuStartPackage;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URISyntaxException;

public class MusicPlayer extends Thread {
    public static MediaPlayer mediaPlayer;
    private Media media;
    public String songName = "";
    public boolean menu = true;
    public boolean exit = false;
    public boolean killthread = false;
    public String[] songs = {"bacchanale.mp3", "EgyptianMarch.mp3", "TurtleBeach.mp3"};
    public double volume = 0.2;



    @Override
    public void run() {
        while (true) {
            if (menu) {
                songName = "lacrimosa.mp3";
            }else if (exit) {
                songName = "diesIrae.mp3";
                exit = false;
                menu = true;
            } else {
                songName = songs[(int) (3 * Math.random())];
            }
            try {
                media = new Media(getClass().getResource(songName).toURI().toString());
            } catch (URISyntaxException e) {
                System.out.println("brak sciezki dzwiekowej");
            }
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(volume);
            startUpmusic();

            //mediaPlayer.setOnEndOfMedia();   --do ogarniecia czy wgl watek trzeba

            while(mediaPlayer.getStatus()== MediaPlayer.Status.PLAYING){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    System.out.println("interr 2celowy");
                }
//            i++;
//            System.out.println(i);
            }


//            try {
//                Thread.sleep((long) media.getDuration().toMillis());
//            } catch (InterruptedException e) {
//            }

            if (killthread) {
                return;
            }
        }
    }

    public void startUpmusic(){
        mediaPlayer.play();
//        int i = 0;
        while(mediaPlayer.getStatus()!= MediaPlayer.Status.PLAYING){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("interr celowy");
            }
//            i++;
//            System.out.println(i);
        }

    }


    public void setVolume(double value) {
        volume += value;
        if (volume > 1) volume = 1;
        if (volume < 0) volume = -0.001;


        mediaPlayer.setVolume(volume);
    }

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.interrupt();
        } else {
            System.out.print("mediaplayer doesnt exist:");
        }
    }
}
