package com.example.kitapsatisfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;



import java.io.IOException;

public class KullaniciGiris extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(KullaniciGiris.class.getResource("kullaniciGirisi.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("Giriş");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)  {
        launch();
    }
}