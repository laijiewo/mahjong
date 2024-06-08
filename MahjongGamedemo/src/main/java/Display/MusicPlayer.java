package Display;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicPlayer implements Runnable {
    private String musicFile;

    public MusicPlayer(String musicFile) {
        this.musicFile = musicFile;
    }

    @Override
    public void run() {
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // 设置循环播放
        mediaPlayer.play();
    }
}
