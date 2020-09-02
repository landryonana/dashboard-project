/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retailsfinal1;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import retailsfinal1.models.SqlConnection;

/**
 * FXML Controller class
 *
 * @author JESUS-CHRIST
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField textLogin;
    @FXML
    private JFXPasswordField textPassword;
    @FXML
    private Label errorForm;
    @FXML
    private JFXButton btnLogin;

    private ResultSet result = null;
    private Connection con;
    private PreparedStatement pst;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            CheckConnection();

        } catch (SQLException ex) {

            System.err.println("Error Login : " + ex.getMessage());

        }

    }

    //initialisation de la connexion à la base de données
    private void CheckConnection() throws SQLException {
        con = SqlConnection.DbConnector();
        if (con == null) {
            System.out.println("Connection Login failed");
        } else {
            System.out.println("Connection Login successful ");
        }

    }

    @FXML
    private void validationForm(ActionEvent event) throws SQLException {

        String input_login = textLogin.getText();
        String pass = textPassword.getText();

        if (input_login.isEmpty() || pass.isEmpty()) {

            errorForm.setText("email or password invalid");

        } else if (this.UserExiste(input_login, pass)) {
            try {
                //appelle du controleur TabDidController et envoi des donnée vers tabGrid

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("vue/retails.fxml"));
                loader.load();

                Stage stage = new Stage();
                retailsController tab = loader.getController();
                tab.afficheLogin(this.UserName(input_login, pass));

                Node node = (Node) event.getSource();
                stage = (Stage) node.getScene().getWindow();
                stage.close();
                /*Scene scene = new Scene(FXMLLoader.load(getClass().getResource("vue/retails.fxml")));
                stage.setScene(scene);
                stage.show();*/

                Parent p = loader.getRoot();
                stage.setScene(new Scene(p));
                //stage.showAndWait();
                stage.show();
            } catch (IOException ex) {
                errorForm.setText("Erreur inattendu");
            }

        } else {
            errorForm.setText("Utilisateur inexistent");
        }

    }

    //fonction de verification de l'existence de l'utilisateur ki se connecte
    private boolean UserExiste(String input_login, String pass) {
        boolean rep = false;
        try {
            String query = "SELECT email,password FROM utilisateur WHERE email=? AND password=?";
            con = SqlConnection.DbConnector();
            pst = con.prepareStatement(query);

            pst.setString(1, input_login);
            pst.setString(2, pass);
            result = pst.executeQuery();

            while (result.next()) {
                rep = true;
            }
            pst.close();
            con.close();

        } catch (SQLException ex) {
            rep = false;
        }

        return rep;
    }

    //fonction de verification de l'existence de l'utilisateur ki se connecte
    private String UserName(String input_login, String pass) {
        String rep = "";
        try {
            String query = "SELECT nom FROM utilisateur WHERE email=? AND password=?";
            con = SqlConnection.DbConnector();
            pst = con.prepareStatement(query);

            pst.setString(1, input_login);
            pst.setString(2, pass);
            result = pst.executeQuery();

            while (result.next()) {
                rep = result.getString("nom");
            }
            pst.close();
            con.close();

        } catch (SQLException ex) {
            rep = "";
        }

        return rep;
    }

}
