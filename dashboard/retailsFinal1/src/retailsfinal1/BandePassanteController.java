/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retailsfinal1;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import retailsfinal1.models.SiteConcerne;
import retailsfinal1.models.SqlConnection;

/**
 * FXML Controller class
 *
 * @author JESUS-CHRIST
 */
public class BandePassanteController implements Initializable {

    @FXML
    private DatePicker textDateBandePassane;
    @FXML
    private TextField textValueBandePassane;
    @FXML
    private TextField textHeureBandePassane;
    @FXML
    private Button btnFileBandePassante;
    @FXML
    private JFXButton btnEnregistrerBandePassanet;
    @FXML
    private AnchorPane anchorBandePassante;

    private SiteConcerne site = new SiteConcerne();
    private File file;
    private FileInputStream fis;

    private Connection con;
    private PreparedStatement pst;
    private ResultSet result;

    @FXML
    private TextArea pathFileBandePassante;

    @FXML
    private Label labelError;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            CheckConnection();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @FXML
    private void choseFileHandler(ActionEvent event) {

        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Choisir Votre fichier");

        Stage stage = (Stage) anchorBandePassante.getScene().getWindow();
        file = filechooser.showOpenDialog(stage);

        if (file != null) {
            pathFileBandePassante.setText(file.getAbsolutePath());
        }

    }

    private void CheckConnection() throws SQLException {
        con = SqlConnection.DbConnector();
        if (con == null) {
            System.out.println("Connection failed");
        } else {
            System.out.println("Connection successful");
        }

    }

    //ajoue d'une bande passante par site
    @FXML
    public void AjouterBandePassante() throws FileNotFoundException {
        if (!verificationInput()) {
            labelError.setText("certaines champs sont vide ou invalide");
        } else {

            try {

                String query = "INSERT INTO site(nom_site,bande_passante_site,date_site,heure_site,fichier_preuve) VALUES (?,?,?,?,?)";
                con = SqlConnection.DbConnector();
                pst = con.prepareStatement(query);
                String sit=this.site.getNomSite();
                pst.setString(1,sit );
                //pst.setString(1,labelSiteBandePassante.getText());
                pst.setString(2, textValueBandePassane.getText());
                pst.setString(3, ((TextField) textDateBandePassane.getEditor()).getText());
                pst.setString(4, textHeureBandePassane.getText());

                fis = new FileInputStream(file);
                pst.setBinaryStream(5, (InputStream) fis, (int) file.length());
                pst.execute();

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("information dialogue");
                alert.setHeaderText(null);
                alert.setContentText("bante passante ajouter avec success!!!!");
                alert.showAndWait();
                labelError.setText("");
                clearInput();
                pst.close();
                con.close();
                System.out.println("connexio fermer avec succès");
                System.out.println("Bande passante ajouter avec succès");

            } catch (SQLException ex) {

                System.err.println("error : " + ex.getMessage());

            }

        }

    }

    private void HandlerAjouterBandePassante(ActionEvent event) {

        try {
            AjouterBandePassante();
        } catch (FileNotFoundException ex) {
            System.err.println("error la fonction ne fonctionne pas bien");
        }

    }

    private String sitenom = null;

    public void siteName(String site) {
        //this.labelSiteBandePassante.setText(site);
        this.site.setNomSite(site);
        //sitenom = site;
    }

    public Boolean verificationInput() {
        if (((TextField) textDateBandePassane.getEditor()).getText().isEmpty() || textValueBandePassane.getText().isEmpty()
                || textHeureBandePassane.getText().isEmpty() || pathFileBandePassante.getText().isEmpty()) {

            return false;

        }

        return true;
    }
    
    public void clearInput(){
        //((TextField) textDateBandePassane.getEditor()).setText("");
        textValueBandePassane.clear();
        textHeureBandePassane.clear();
        pathFileBandePassante.clear();
    }
    

}
