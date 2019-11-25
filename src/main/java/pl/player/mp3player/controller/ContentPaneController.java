package pl.player.mp3player.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class ContentPaneController {
    private static Logger logger = LoggerFactory.getLogger(ContentPaneController.class);

    @FXML
    private TableView<?> contentTable;

    public void initialize() {
        logger.info("Content controller created");
    }
}
