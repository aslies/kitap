module com.example.kitapsatisfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    opens com.example.kitapsatisfx to javafx.fxml;
    exports com.example.kitapsatisfx;
}