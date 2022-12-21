package com.example.kitapsatisfx;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;



public class MainPageController implements Initializable {
    @FXML
    private ComboBox<String> viewAllCombo;
    @FXML
    private ComboBox<String> viewNum1, viewNum2, viewNum3, viewNum4, viewNum5, viewNum6, viewNum7, viewNum8, viewNum9, viewNum10;
    @FXML
    private ImageView iw1, iw2, iw3, iw4, iw5, iw6, iw7, iw8, iw9, iw10;
    @FXML
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10,  orderBtn, logOutBtn,
    remove1, remove2, remove3, remove4, remove5, remove6, remove7, remove8, remove9, remove10;
    @FXML
    ScrollPane scrollPane;
    @FXML
    private VBox vb1, vb2, vb3, vb4, vb5, vb6, vb7, vb8, vb9, vb10, vbMain, basketVb, basketVb2;
    @FXML
    private Pane basketPane;
    @FXML
    private HBox hb;
    @FXML
    private Text totalPrice;
    public String email = "";
    static HashMap<String, Integer> map;
    ImageView[] iwList;
    Button[] nameList, removeList;
    VBox[] vbList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        map = new HashMap<>();
        scrollPane.setPannable(false);
        PreparedStatement ps;
        ResultSet rs;
        Image image;
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        ComboBox[] comboBoxes = {viewNum1, viewNum2, viewNum3, viewNum4, viewNum5, viewNum6, viewNum7, viewNum8, viewNum9, viewNum10};
        iwList = new ImageView[]{iw1, iw2, iw3, iw4, iw5, iw6, iw7, iw8, iw9, iw10};
        nameList = new Button[]{btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10};
        vbList = new VBox[]{vb1, vb2, vb3, vb4, vb5, vb6, vb7, vb8, vb9, vb10};
        removeList = new Button[] {remove1, remove2,  remove3, remove4, remove5, remove6, remove7, remove8, remove9, remove10};
        ArrayList<String> genres = new ArrayList<>();
        try {
            SQLConnection conn = new SQLConnection();
            rs = conn.getStatement().executeQuery("SELECT KategoriAdi FROM Kategoriler");
            while (rs.next()) {
                genres.add(rs.getString("KategoriAdi"));
            }

            viewAllCombo.setItems(FXCollections.observableArrayList(genres));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < comboBoxes.length; i++) {
            comboBoxes[i].setItems(FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10"));
            comboBoxes[i].setValue("1");
        }
        int i = 0;
        try {
            SQLConnection conn = new SQLConnection();
            ps = conn.connection.prepareStatement("SELECT * FROM Kitaplar");
            rs = ps.executeQuery();
            while (rs.next() && i < iwList.length) {
                InputStream is = rs.getBinaryStream("kitapGoruntu");
                OutputStream os = new FileOutputStream(new File("photo.jpg"));
                byte[] contents = new byte[1024];
                int size = 0;
                while ((size = is.read(contents)) != -1) {
                    os.write(contents, 0, size);
                }
                image = new Image("file:photo.jpg", iwList[i].getFitWidth(), iwList[i].getFitHeight(),true, true);
                iwList[i].setImage(image);
                vbList[i].setAlignment(Pos.CENTER);
                nameList[i].setText(rs.getString("kitapAdi"));
                i++;
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }


    public void setInfo(String email) {
        this.email = email;
    }

    @FXML
    void displayData(ActionEvent event) throws SQLException {
        SQLConnection conn = new SQLConnection();
        PreparedStatement ps = conn.connection.prepareStatement("SELECT * FROM Musteriler WHERE musteriEmail = ? ");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
           // System.out.println(rs.getString("musteriAdi") + " " + rs.getString("musteriSoyadi") + ", " + rs.getString("musteriEmail"));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Kullanıcı \n" + rs.getString("musteriAdi") + " " + rs.getString("musteriSoyadi") + ", "+ rs.getString("musteriEmail"));
            alert.show();
        }
    }

    @FXML
    void add1Clicked(ActionEvent event) {
        String str = btn1.getText();
        if (!str.isBlank()){
            Integer n = Integer.parseInt(viewNum1.getValue());
            map.put(str, n);
            remove1.setDisable(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(viewNum1.getValue() + " ürün sepete eklendi.");
            alert.show();
        }
    }

    @FXML
    void add2Clicked(ActionEvent event) {
        String str = btn2.getText();
        if (!str.isBlank()) {
            Integer n = Integer.parseInt(viewNum2.getValue());
            map.put(str, n);
            remove2.setDisable(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(viewNum2.getValue() + " ürün sepete eklendi.");
            alert.show();
        }
    }
    @FXML
    void add3Clicked(ActionEvent event) {
        String str = btn3.getText();
        if (!str.isBlank()) {
            Integer n = Integer.parseInt(viewNum3.getValue());
            map.put(str, n);
            remove3.setDisable(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(viewNum3.getValue() + " ürün sepete eklendi.");
            alert.show();
        }
    }
    @FXML
    void add4Clicked(ActionEvent event) {
        String str = btn4.getText();
        if (!str.isBlank()) {
            Integer n = Integer.parseInt(viewNum4.getValue());
            map.put(str, n);
            remove4.setDisable(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(viewNum4.getValue() + " ürün sepete eklendi.");
            alert.show();
        }
    }
    @FXML
    void add5Clicked(ActionEvent event) {
        String str = btn5.getText();
        if (!str.isBlank()) {
            Integer n = Integer.parseInt(viewNum5.getValue());
            map.put(str, n);
            remove5.setDisable(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(viewNum5.getValue() + " ürün sepete eklendi.");
            alert.show();
        }
    }
    @FXML
    void add6Clicked(ActionEvent event) {
        String str = btn6.getText();
        if (!str.isBlank()) {
            Integer n = Integer.parseInt(viewNum6.getValue());
            map.put(str, n);
            remove6.setDisable(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(viewNum6.getValue() + " ürün sepete eklendi.");
            alert.show();
        }
    }

    @FXML
    void add7Clicked(ActionEvent event) {
        String str = btn7.getText();
        if (!str.isBlank()) {
            Integer n = Integer.parseInt(viewNum7.getValue());
            map.put(str, n);
            remove7.setDisable(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(viewNum7.getValue() + " ürün sepete eklendi.");
            alert.show();
        }
    }
    @FXML
    void add8Clicked(ActionEvent event) {
        String str = btn8.getText();
        if (!str.isBlank()) {
            Integer n = Integer.parseInt(viewNum8.getValue());
            map.put(str, n);
            remove8.setDisable(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(viewNum8.getValue() + " ürün sepete eklendi.");
            alert.show();
        }
    }

    @FXML
    void add9Clicked(ActionEvent event) {
        String str = btn9.getText();
        if (!str.isBlank()) {
            Integer n = Integer.parseInt(viewNum9.getValue());
            map.put(str, n);
            remove9.setDisable(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(viewNum9.getValue() + " ürün sepete eklendi.");
            alert.show();
        }
    }

    @FXML
    void add10Clicked(ActionEvent event) {
        String str = btn10.getText();
        if (!str.isBlank()) {
            Integer n = Integer.parseInt(viewNum10.getValue());
            map.put(str, n);
            remove10.setDisable(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(viewNum10.getValue() + " ürün sepete eklendi.");
            alert.show();
        }
    }

    @FXML
    void basketBtnClicked(ActionEvent event) throws SQLException {
        int total = 0;
        ResultSet rs;

        if (basketPane.isVisible()) {
            basketPane.setVisible(false);
            orderBtn.setVisible(false);
        }
        else {
            basketPane.setVisible(true);
            orderBtn.setVisible(true);
        }

        basketVb.getChildren().clear();
        basketVb2.getChildren().clear();

        for (String s: map.keySet()) {
            Text txt = new Text(s);
            Text txt2 = new Text(map.get(s).toString());
            basketVb.getChildren().add(txt);
            basketVb2.getChildren().add(txt2);
        }

        SQLConnection conn = new SQLConnection();
        PreparedStatement ps = conn.connection.prepareStatement("SELECT kitapBirimFiyati FROM Kitaplar WHERE kitapAdi = ?");
        for (String s: map.keySet()) {
            ps.setString(1, s);
            rs = ps.executeQuery();
            if (rs.next()) {
                total += rs.getInt("kitapBirimFiyati") * map.get(s);
            }
        }

        String s = Integer.toString(total);
        totalPrice.setText(s);

    }
    @FXML
    void orderBtnClicked(ActionEvent event) throws SQLException {
        String orderStatus = "Ekleme";
        SQLConnection conn = new SQLConnection();
        PreparedStatement ps = conn.connection.prepareStatement("INSERT INTO Siparisler (musteriID, siparisTarihi, siparisDurumu) VALUES (?, CONVERT(datetime, ?, 111), ?)");
        PreparedStatement ps2 = conn.connection.prepareStatement("SELECT * FROM Musteriler WHERE musteriEmail = ? ");
        ps2.setString(1, email);
        ResultSet rs = ps2.executeQuery();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        if (rs.next()) {
            System.out.println(rs.getString(1));
            ps.setInt(1, rs.getInt(1));
            ps.setString(2, dtf.format(now).toString());
            ps.setString(3, orderStatus);
            ps.executeUpdate();
        }

        PreparedStatement psInsert = conn.connection.prepareStatement("INSERT INTO SiparisDetaylari (siparisID, kitapID, siparisAdet) VALUES (?, ?, ?)");
        PreparedStatement getSiparisID = conn.connection.prepareStatement("SELECT siparisID FROM Siparisler WHERE musteriID = ? AND siparisDurumu = ?");
        PreparedStatement psGetMusteriID = conn.connection.prepareStatement("SELECT * FROM Musteriler WHERE musteriEmail = ? ");
        psGetMusteriID.setString(1, email);
        ResultSet musteriID = psGetMusteriID.executeQuery();
        ResultSet siparisID = null;

        if (musteriID.next()) {
            getSiparisID.setString(2, orderStatus);
            getSiparisID.setInt(1, musteriID.getInt(1));
            siparisID = getSiparisID.executeQuery();
        }

        if (siparisID.next()) {
            for (String s: map.keySet()) {
                psInsert.setInt(1, siparisID.getInt(1));
                psInsert.setInt(2, getBookID(s));
                psInsert.setInt(3, map.get(s));
                psInsert.executeUpdate();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sipariş oluşturuldu.");
            alert.show();
            changeStatus(siparisID.getInt(1), "Bekleme");
        }
    }

    public static int getBookID(String bookName) throws SQLException {
        SQLConnection conn = new SQLConnection();
        PreparedStatement ps = conn.connection.prepareStatement("SELECT * FROM Kitaplar WHERE kitapAdi = ?");
        ps.setString(1, bookName);
        ResultSet rs = ps.executeQuery();

        if (rs.next())
            return rs.getInt(1);

        return 0;
    }

    public static void changeStatus(int orderID, String status) throws SQLException{
        SQLConnection conn = new SQLConnection();
        PreparedStatement ps = conn.connection.prepareStatement("UPDATE Siparisler SET siparisDurumu = ? WHERE siparisID = ?");
        ps.setInt(2, orderID);
        ps.setString(1, status);
        ps.executeUpdate();
    }

    @FXML
    void logOutBtnClicked(ActionEvent event) throws IOException {
        ChangeScene scene = new ChangeScene();
        scene.changeScene(logOutBtn, "kullaniciGirisi.fxml");
    }

    @FXML
    void remove1Clicked(ActionEvent event) {
        if (map.containsKey(btn1.getText())) {
            map.remove(btn1.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Ürün sepetten çıkarıldı.");
            alert.show();
            remove1.setDisable(true);
        }
    }
    @FXML
    void remove2Clicked(ActionEvent event) {
        if (map.containsKey(btn2.getText())) {
            map.remove(btn2.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Ürün sepetten çıkarıldı.");
            alert.show();
            remove2.setDisable(true);
        }
    }
    @FXML
    void remove3Clicked(ActionEvent event) {
        if (map.containsKey(btn3.getText())) {
            map.remove(btn3.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Ürün sepetten çıkarıldı.");
            alert.show();
            remove3.setDisable(true);
        }
    }
    @FXML
    void remove4Clicked(ActionEvent event) {
        if (map.containsKey(btn4.getText())) {
            map.remove(btn4.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Ürün sepetten çıkarıldı.");
            alert.show();
            remove4.setDisable(true);
        }
    }
    @FXML
    void remove5Clicked(ActionEvent event) {
        if (map.containsKey(btn5.getText())) {
            map.remove(btn5.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Ürün sepetten çıkarıldı.");
            alert.show();
            remove5.setDisable(true);
        }
    }
    @FXML
    void remove6Clicked(ActionEvent event) {
        if (map.containsKey(btn6.getText())) {
            map.remove(btn6.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Ürün sepetten çıkarıldı.");
            alert.show();
            remove6.setDisable(true);
        }
    }
    @FXML
    void remove7Clicked(ActionEvent event) {
        if (map.containsKey(btn7.getText())) {
            map.remove(btn7.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Ürün sepetten çıkarıldı.");
            alert.show();
            remove7.setDisable(true);
        }
    }
    @FXML
    void remove8Clicked(ActionEvent event) {
        if (map.containsKey(btn8.getText())) {
            map.remove(btn8.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Ürün sepetten çıkarıldı.");
            alert.show();
            remove8.setDisable(true);
        }
    }

    @FXML
    void remove9Clicked(ActionEvent event) {
        if (map.containsKey(btn9.getText())) {
            map.remove(btn9.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Ürün sepetten çıkarıldı.");
            alert.show();
            remove9.setDisable(true);
        }
    }

    @FXML
    void remove10Clicked(ActionEvent event) {
        if (map.containsKey(btn10.getText())) {
            map.remove(btn10.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Ürün sepetten çıkarıldı.");
            alert.show();
            remove10.setDisable(true);
        }
    }
}

