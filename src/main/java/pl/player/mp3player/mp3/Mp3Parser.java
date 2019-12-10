package pl.player.mp3player.mp3;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mp3Parser {
    public static Mp3Song createMp3Song(File file) throws IOException, TagException {
        MP3File mp3File = new MP3File(file);
        String absolutePath = file.getAbsolutePath();
        String title = mp3File.getID3v2Tag().getSongTitle();
        String author = mp3File.getID3v2Tag().getLeadArtist();
        String album = mp3File.getID3v2Tag().getAlbumTitle();

        return new Mp3Song(title, author, album, absolutePath);
    }

    public static List<Mp3Song> createMp3List(File directory) throws IOException, TagException {
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Not a directory");
        }
        List<Mp3Song> songs = new ArrayList<>();
        for (File file: directory.listFiles()) {
            String fileExtension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            if(fileExtension.equals("mp3"))
                songs.add(createMp3Song(file));
        }
        return songs;
    }
}
