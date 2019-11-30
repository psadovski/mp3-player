package pl.player.mp3player.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.player.mp3player.mp3.Mp3Song;

import java.io.File;
import java.io.IOException;

@Getter
public class ContentPaneController {
    private static final String TITLE_COLUMN = "Tytuł" ;
    public static final String AUTHOR_COLUMN = "Autor";
    public static final String ALBUM_COLUMN = "Album";
    private static Logger logger = LoggerFactory.getLogger(ContentPaneController.class);

    @FXML
    private TableView<Mp3Song> contentTable;

    public void initialize() {
        logger.info("Content controller created");
        configureTableColumns();
        createTestData();
    }

    private void configureTableColumns() {
        TableColumn<Mp3Song, String> titleColumn = new TableColumn<>(TITLE_COLUMN);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Mp3Song, String> authorColumn = new TableColumn<>(AUTHOR_COLUMN);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Mp3Song, String> albumColumn = new TableColumn<>(ALBUM_COLUMN);
        albumColumn.setCellValueFactory(new PropertyValueFactory<>("album"));

        contentTable.getColumns().add(titleColumn);
        contentTable.getColumns().add(authorColumn);
        contentTable.getColumns().add(albumColumn);
    }

    private void createTestData() {
        ObservableList<Mp3Song> items = contentTable.getItems();
        Mp3Song mp3Song = createMp3SongFromPath("SampleAudio_0.4mb.mp3");
        items.add(mp3Song);
    }

    private Mp3Song createMp3SongFromPath(String filePath) {
        File sourceFile = new File(filePath);
        try {
            MP3File mp3File = new MP3File(sourceFile);
            String title = mp3File.getID3v2Tag().getSongTitle();
            String author = mp3File.getID3v2Tag().getLeadArtist();
            String album = mp3File.getID3v2Tag().getDuplicateFrameId();
            String absolutePath = sourceFile.getAbsolutePath();
            return new Mp3Song(title,author,album,absolutePath);
        } catch (IOException | TagException e) {
            e.printStackTrace();
            return null;
        }
    }
}
