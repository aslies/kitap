package com.example.kitapsatisfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;


public class KullaniciGirisiController {
    @FXML
    private TextField userEmailTxt;

    @FXML
    private Button userLoginBtn, goBackBtn;

    @FXML
    private Button userSignUpBtn;

    @FXML
    private PasswordField userPasTxt;
    private int id;
    @FXML
    void userLoginBtnClicked(ActionEvent event) throws SQLException, IOException {
        SQLConnection connection = new SQLConnection();
        ResultSet rs = connection.getStatement().executeQuery("SELECT * FROM Musteriler");
        boolean emailFlag = false;
        boolean passFlag = false;

        while(rs.next()) {
            if(rs.getString("musteriEmail").equals(userEmailTxt.getText())) {
                emailFlag = true;
                    if (rs.getString("musteriSifre").equals(userPasTxt.getText())) {
                        passFlag = true;
                        break;
                    }
            }
        }

        if (!emailFlag && !passFlag) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Sisteme kayıtlı kullanıcı bulunamadı.");
            alert.show();
        }

        else if (emailFlag && !passFlag) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Hatalı şifre");
            alert.show();
        }

        else {
            try
            {
                String email = userEmailTxt.getText();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
                Parent root = loader.load();
                MainPageController mainPageController = loader.getController();
                mainPageController.setInfo(email);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    @FXML
    void userSignUpBtnClicked(ActionEvent event) throws IOException {
        ChangeScene newScene = new ChangeScene();
        newScene.changeScene(userSignUpBtn, "kullaniciKayit.fxml");
    }

    @FXML
    void goBackBtnClicked(ActionEvent event) throws IOException {
        ChangeScene newScene = new ChangeScene();
        newScene.changeScene(goBackBtn, "Giris.fxml");
    }


}