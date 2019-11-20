package pl.player.mp3player.controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuPaneController {
    private static Logger logger = LoggerFactory.getLogger(MenuPaneController.class);

    @FXML
    private MenuItem fileMenuItem;

    @FXML
    private MenuItem dirMenuItem;

    @FXML
    private MenuItem closeMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    public void initialize() {
        logger.info("Menu controller created");
    }
}
