package pl.player.mp3player.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControlPaneController {
    private static Logger logger = LoggerFactory.getLogger(ControlPaneController.class);

    @FXML
    private Button previousButton;

    @FXML
    private ToggleButton playButton;

    @FXML
    private Button nextButton;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Slider progressSlider;

    public Button getPreviousButton() {
        return this.previousButton;
    }

    public ToggleButton getPlayButton() {
        return this.playButton;
    }

    public Button getNextButton() {
        return this.nextButton;
    }

    public Slider getVolumeSlider() {
        return this.volumeSlider;
    }

    public Slider getProgressSlider() {
        return this.progressSlider;
    }

    public void initialize() {
        logger.info("ControlPaneController created");
        configureButtons();
        configureVolumes();
    }

    private void configureVolumes() {
        volumeSlider.addEventFilter(MouseEvent.MOUSE_PRESSED, event ->
                System.out.println("Wciśnięto przycisk na suwaku głośności"));
    }

    private void configureButtons() {
        previousButton.setOnAction(event -> System.out.println("Poprzednia piosenka"));
        nextButton.setOnAction(event -> System.out.println("Następna piosenka"));
        playButton.setOnAction(event -> {
            if (playButton.isSelected()) {
                System.out.println("Play");
            } else {
                System.out.println("Stop");
            }
        });

    }
}
