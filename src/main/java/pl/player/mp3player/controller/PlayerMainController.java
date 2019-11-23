package pl.player.mp3player.controller;

import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerMainController {
    private static Logger logger = LoggerFactory.getLogger(PlayerMainController.class);

    @FXML
    private MenuPaneController menuPaneController;

    @FXML
    private ControlPaneController controlPaneController;

    @FXML
    private ContentPaneController contentPaneController;

    public void initialize() {
        logger.info("PlayerMainController created");
    }
}
