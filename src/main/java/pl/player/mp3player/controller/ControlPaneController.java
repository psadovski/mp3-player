package pl.player.mp3player.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
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

    public void initialize() {
        logger.info("ControlPaneController created");
        configureButtons();
        configureVolumes();
    }

    private void configureSliders() {
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) ->
                logger.info(String.format("Zminana glośności%s", newValue.doubleValue())));
        progressSlider.valueProperty().addListener((observable, oldValue, newValue) ->
                logger.info("PRzesunięcie piosenki"));
    }

    private void configureVolumes() {
        volumeSlider.addEventFilter(MouseEvent.MOUSE_PRESSED, event ->
                logger.info("Wciśnięto przycisk na suwaku głośności"));
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
