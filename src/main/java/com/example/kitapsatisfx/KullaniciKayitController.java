package com.example.kitapsatisfx;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class KullaniciKayitController implements Initializable {
    @FXML
    private TextField usersEmailTxt;

    @FXML
    private TextField usersLastNameTxt;

    @FXML
    private TextField usersNameTxt;

    @FXML
    private PasswordField usersPassTxt;

    @FXML
    private Button usersSignUpBtn;
    @FXML
    private ComboBox<String> usersBirthDay;

    @FXML
    private ComboBox<String> usersBirthMonth;

    @FXML
    private ComboBox<Integer> usersBirthYear;
    @FXML
    private RadioButton radioBtnFemale;

    @FXML
    private RadioButton radioBtnMale;
    @FXML
    private ComboBox<String> usersCity;

    @FXML
    private ComboBox<String> usersDistrict;
    @FXML
    private TextField usersZipCode;
    @FXML
    private TextField usersCVV;
    @FXML
    private TextField usersCreditCardNo;
    @FXML
    private ComboBox<String> finalMonth;
    @FXML
    private ComboBox<String> finalYear;
    @FXML
    private Button goBackToMainBtn;
    @FXML
    private TextField usersPhoneNumber;

    @FXML
    private TextField usersAddress;
    private ToggleGroup group;
    @FXML
    void userSignUpBtnClicked(ActionEvent event) throws SQLException {

        SQLConnection conn = new SQLConnection();
        PreparedStatement psCheckUserExists;
        PreparedStatement psInsert;
        ResultSet rs;

        psCheckUserExists = conn.connection.prepareStatement("SELECT * FROM Musteriler WHERE musteriEmail = ?");
        psCheckUserExists.setString(1, usersEmailTxt.getText());
        rs = psCheckUserExists.executeQuery();

        if(rs.isBeforeFirst()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Böyle bir kullanıcı zaten var.");
            alert.show();
        }
        else {
            try {
                psInsert = conn.connection.prepareStatement("INSERT INTO Musteriler (musteriAdi, " +
                        "musteriSoyadi, musteriDogumTarihi, musteriCinsiyeti, musteriSehir, " +
                        "musteriIlce, " +
                        "musteriAdres, " +
                        "musteriPostaKodu, musteriEmail, " +
                        "musteriSifre, " +
                        "musteriTelefonNo ,musteriKrediKartiNo, " +
                        "musteriKrediKartiCVV2, " +
                        "musteriKrediKartiSKTAy, " +
                        "musteriKrediKartiSKTYil) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                psInsert.setString(1, usersNameTxt.getText());
                psInsert.setString(2, usersLastNameTxt.getText());
                psInsert.setInt(3, usersBirthYear.getValue());
                psInsert.setString(4, radioBtnMale.isSelected() ? radioBtnMale.getText() : radioBtnFemale.getText());
                psInsert.setString(5, usersCity.getValue());
                psInsert.setString(6, usersDistrict.getValue());
                psInsert.setString(7, usersAddress.getText());
                psInsert.setString(8, usersZipCode.getText());
                psInsert.setString(9, usersEmailTxt.getText());
                psInsert.setString(10, usersPassTxt.getText());
                psInsert.setString(11, usersPhoneNumber.getText());
                psInsert.setString(12, usersCreditCardNo.getText());
                psInsert.setString(13, usersCVV.getText());
                psInsert.setString(14, finalMonth.getValue());
                psInsert.setString(15, finalYear.getValue());
                psInsert.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Kayıt başarılı.");
                alert.show();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("* ile belirtilen alanlar doldurulmalıdır.");
                alert.show();
                e.printStackTrace();
            }
        }
    }

    @FXML
    void goBackToMainBtnClicked(ActionEvent event) throws IOException {
        ChangeScene changeScene = new ChangeScene();
        changeScene.changeScene(goBackToMainBtn, "giris.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usersBirthDay.setItems(FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"));
        usersBirthMonth.setItems(FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"));
        ArrayList<Integer> years = new ArrayList<>();
        for (int i = 1950; i <= 2020; i++) {
            years.add(i);
        }
        usersBirthYear.setItems(FXCollections.observableArrayList(years));
        group = new ToggleGroup();
        radioBtnFemale.setToggleGroup(group);
        radioBtnMale.setToggleGroup(group);
        ArrayList<String> creditYears = new ArrayList<>();
        for (int i = 2022; i < 2042; i++) {
            creditYears.add(Integer.toString(i));
        }
        finalYear.setItems(FXCollections.observableArrayList(creditYears));
        finalMonth.setItems(FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"));

        try {
            SQLConnection conn = new SQLConnection();
            ResultSet rs = conn.getStatement().executeQuery("SELECT * FROM iller");
            ArrayList<String> cities = new ArrayList<>();
            while (rs.next()) {
                cities.add(rs.getString("isim"));
            }
            usersCity.setItems(FXCollections.observableArrayList(cities));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        usersCity.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            try {
                SQLConnection conn = new SQLConnection();
                PreparedStatement ps = conn.connection.prepareStatement("SELECT ilceler.isim FROM ilceler INNER JOIN iller ON ilceler.il_no = iller.il_no AND iller.isim = ?");
                ps.setString(1, usersCity.getValue());
                ResultSet rs = ps.executeQuery();
                ArrayList<String> districts = new ArrayList<>();
                while (rs.next()) {
                    districts.add(rs.getString("isim"));
                }
                usersDistrict.setItems(FXCollections.observableArrayList(districts));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        usersCreditCardNo.textProperty().addListener((options, oldValue, newValue) -> {
            int size = usersCreditCardNo.getText().length();
            if (size == 4 || size == 9 || size == 14) {
                usersCreditCardNo.setText(usersCreditCardNo.getText() + " ");
            }
        });


    }


}
