package pl.player.mp3player.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.player.mp3player.mp3.Mp3Parser;
import pl.player.mp3player.mp3.Mp3Song;
import pl.player.mp3player.player.Mp3Player;

import java.io.File;

public class PlayerMainController {
    private static Logger logger = LoggerFactory.getLogger(PlayerMainController.class);

    @FXML
    private MenuPaneController menuPaneController;

    @FXML
    private ControlPaneController controlPaneController;

    @FXML
    private ContentPaneController contentPaneController;

    private Mp3Player mp3Player;

    public void initialize() {
        logger.info("PlayerMainController created");
        createPlayer();
        configureTableClick();
        configureButtons();
        configureMenu();
    }

    private void createPlayer() {
        ObservableList<Mp3Song> songs = contentPaneController.getContentTable().getItems();
        mp3Player = new Mp3Player(songs);
    }

    private void configureTableClick() {
        TableView<Mp3Song> contentTable = contentPaneController.getContentTable();
        contentTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() == 2) {
                int index = contentTable.getSelectionModel().getSelectedIndex();
                playSelectedSong(index);
            }
        });
    }

    private void playSelectedSong(int index) {
        mp3Player.loadSong(index);
        configureProgressBar();
        configureVolume();
        controlPaneController.getPlayButton().setSelected(true);
    }

    private void configureProgressBar() {
        Slider progressSlider = controlPaneController.getProgressSlider();
        mp3Player.getMediaPlayer().setOnReady(() -> progressSlider.setMax(mp3Player.getLoadedSongLength()));
        mp3Player.getMediaPlayer().currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            progressSlider.setValue(newValue.toSeconds());
        });
        progressSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (progressSlider.isValueChanging()) {
                mp3Player.getMediaPlayer().seek(Duration.seconds(newValue.doubleValue()));
            }
        });
    }

    private void configureVolume() {
        Slider volumeSlider = controlPaneController.getVolumeSlider();
        volumeSlider.valueProperty().unbind();
        volumeSlider.setMax(1.0);
        volumeSlider.valueProperty().bindBidirectional(mp3Player.getMediaPlayer().volumeProperty());
    }

    private void configureButtons() {
        TableView<Mp3Song> songs = contentPaneController.getContentTable();
        ToggleButton playButton = controlPaneController.getPlayButton();
        Button nextButton = controlPaneController.getNextButton();
        Button previousButton = controlPaneController.getPreviousButton();

        playButton.setOnAction(x -> {
            if (playButton.isSelected()) {
                mp3Player.play();
            } else {
                mp3Player.stop();
            }
        });

        nextButton.setOnAction(event -> {
            songs.getSelectionModel().select(songs.getSelectionModel().getSelectedIndex() + 1);
            playSelectedSong(songs.getSelectionModel().getSelectedIndex());
        });

        previousButton.setOnAction(event -> {
            songs.getSelectionModel().select(songs.getSelectionModel().getSelectedIndex() - 1);
            playSelectedSong(songs.getSelectionModel().getSelectedIndex());
        });
    }

    private void configureMenu() {
        MenuItem openFile = menuPaneController.getFileMenuItem();
        MenuItem openDir = menuPaneController.getDirMenuItem();

        openFile.setOnAction(event -> {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Mp3", "*.mp3"));
            File file = fc.showOpenDialog(new Stage());
            try {
                contentPaneController.getContentTable().getItems().add(Mp3Parser.createMp3Song(file));
                showMessage("Załadowano plik " + file.getName());
            } catch (Exception e) {
                showMessage("Wystąpił błąd podczas odczytu piosenki");
            }
        });

        openDir.setOnAction(event -> {
            DirectoryChooser dc = new DirectoryChooser();
            File dir = dc.showDialog(new Stage());
            try {
                contentPaneController.getContentTable().getItems().addAll(Mp3Parser.createMp3List(dir));
                showMessage("Wczytano plik z folderu " + dir.getName());
            } catch (Exception e) {
                showMessage("Wystąpił błąd podczas odczytu folderu");
            }
        });
    }

    private void showMessage(String message) {
        controlPaneController.getMessageTextField().setText(message);
    }
}
