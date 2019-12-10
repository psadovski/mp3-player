package pl.player.mp3player.player;

import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import pl.player.mp3player.mp3.Mp3Song;

import java.io.File;

public class Mp3Player {
    private ObservableList<Mp3Song> songs;

    private Media media;
    private MediaPlayer mediaPlayer;

    public Mp3Player(ObservableList<Mp3Song> songs) {
        this.songs = songs;
    }

    public MediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }

    public void play() {
        if (mediaPlayer != null
                && (getMediaPlayer().getStatus() == MediaPlayer.Status.READY
                || getMediaPlayer().getStatus() == MediaPlayer.Status.PAUSED)) {
            mediaPlayer.play();
        }
    }

    public void stop() {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.stop();
        }
    }

    public double getLoadedSongLength() {
        return mediaPlayer != null ? media.getDuration().toSeconds() : 0;
    }

    public void setVolume(double volume) {
        if (mediaPlayer != null) {
            getMediaPlayer().setVolume(volume);
        }
    }

    public void loadSong(int index) {
        if (mediaPlayer != null && MediaPlayer.Status.PLAYING == mediaPlayer.getStatus()) {
            mediaPlayer.stop();
        }
        Mp3Song mp3Song = songs.get(index);
        media = new Media(new File(mp3Song.getFilePath()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.statusProperty().addListener((observable, oldValue, newStatus) -> {
            if (newStatus == MediaPlayer.Status.READY) {
                mediaPlayer.setAutoPlay(true);
            }
        });
    }
}
