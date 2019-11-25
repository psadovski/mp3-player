module mp3player {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires slf4j.api;
    requires lombok;

    exports pl.player.mp3player.main to javafx.graphics;
    opens pl.player.mp3player.controller to javafx.fxml;
}