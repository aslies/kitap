package com.example.kitapsatisfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class GirisController implements Initializable {
    @FXML
    private Button guestBtn;

    @FXML
    private ImageView imageView;
    @FXML
    private Button loginCustomerBtn;

    @FXML
    private Button loginEmployeeBtn;

    @FXML
    void guestBtnClicked(ActionEvent event) {

    }

    @FXML
    void loginCustomerBtnClicked(ActionEvent event) throws IOException {
        ChangeScene newScene = new ChangeScene();
        newScene.changeScene(loginCustomerBtn, "kullaniciGirisi.fxml");

    }

    @FXML
    void loginEmployeeBtnClicked(ActionEvent event) throws IOException {
        ChangeScene newScene = new ChangeScene();
        newScene.changeScene(loginEmployeeBtn, "CalisanGirisi.fxml");

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
