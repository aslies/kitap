package com.example.kitapsatisfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeScene {

    public ChangeScene() {
    }

    public void changeScene(Button button_name, String file_name) throws IOException {
        button_name.getScene().getWindow().hide();
        Stage newStage = new Stage();
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource(file_name));
        Scene scene = new Scene(root);
        newStage.setResizable(false);
        newStage.setScene(scene);
        newStage.show();
    }


}
