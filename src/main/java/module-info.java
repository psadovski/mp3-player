module mp3player {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires slf4j.api;
    requires lombok;
    requires jid3lib;

    exports pl.player.mp3player.main to javafx.graphics;
    opens pl.player.mp3player.controller to javafx.fxml, lombok, javafx.controls;
    opens pl.player.mp3player.mp3 to javafx.base;
}