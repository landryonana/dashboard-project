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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import retailsfinal1.models.DeclarationInteruption;
import retailsfinal1.models.SqlConnection;

/**
 * FXML Controller class
 *
 * @author JESUS-CHRIST
 */
public class RetablirConnexionController implements Initializable {

    private FileInputStream fis = null;
    private File file;

    @FXML
    private DatePicker inputDateRetablirConnexion;

    @FXML
    private TextField inputHeureRetablirConnexion;

    @FXML
    private TextField inputBandePassanteRetablirConnexion;

    @FXML
    private Button btnFileRetablirConnexion;

    @FXML
    private JFXButton btnEnregistrerRetablirConnexion;

    @FXML
    private TextArea PathFileRetablirConnexion;

    @FXML
    private AnchorPane anchorPaneRetablirConnexion;

    private Connection con;
    private ResultSet result = null;
    private PreparedStatement pst;
    private DeclarationInteruption decla = new DeclarationInteruption();

    @FXML
    private Label labelErrorRetablirInteruption;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            CheckConnection();
        } catch (SQLException ex) {
            System.err.println("Error retablir Interupton " + ex.getMessage());
        }
    }

    @FXML
    private void choseFileHandler(ActionEvent event) {

        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Choisir Votre fichier");

        Stage stage = (Stage) anchorPaneRetablirConnexion.getScene().getWindow();
        file = filechooser.showOpenDialog(stage);

        if (file != null) {
            PathFileRetablirConnexion.setText(file.getAbsolutePath());
        }

    }

    private void CheckConnection() throws SQLException {
        con = SqlConnection.DbConnector();
        if (con == null) {
            System.out.println("Connection failed retablir Interupton");
        } else {
            System.out.println("Connection successful retablir Interupton");
        }
    }

    @FXML
    private void AjouterRetablirInteruption(ActionEvent event) throws FileNotFoundException, SQLException {

        addRetablir();

    }

    private void addRetablir() throws FileNotFoundException, SQLException {

        fis = new FileInputStream(file);
        int declarationInteruption_id = this.getIdDeclarationInteruption();
        System.out.println("declarationInteruption_id " + declarationInteruption_id);
        if (!verificationInput()) {

            labelErrorRetablirInteruption.setText("certains champs sont vides ou invalides");

        } else {

            try {

                String query = "INSERT INTO retablirconnexions ("
                        + "bande_passante_retablir,"
                        + "date_retablir_connexion,"
                        + "heure_retablir_connexion,"
                        + "fichier_preuve_retablir_connexion,"
                        + "id_declaration_interuption"
                        + ")"
                        + " VALUES (?,?,?,?,?)";

                con = SqlConnection.DbConnector();
                pst = con.prepareStatement(query);

                System.out.println("declarationInteruption_id pst " + pst);
                //pst.setString(1, site.getNomSite());
                pst.setString(1, inputBandePassanteRetablirConnexion.getText());
                pst.setString(2, ((TextField) inputDateRetablirConnexion.getEditor()).getText());
                pst.setString(3, inputHeureRetablirConnexion.getText());
                pst.setBinaryStream(4, (InputStream) fis, (int) file.length());
                pst.setInt(5, declarationInteruption_id);
                System.out.println("declaration Interuption id execute " + declarationInteruption_id);
                //pst.execute();
                pst.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("information dialogue");
                alert.setHeaderText(null);
                alert.setContentText("Retablir connexion ajouter avec success!!!!");
                alert.showAndWait();

                labelErrorRetablirInteruption.setText("");
                clearInput();
                pst.close();
                con.close();

                System.out.println("connexion fermer avec succès");
                System.out.println("Retablir connexion ajouter avec succès");
                System.out.println("site " + this.decla.getSite());
                //System.out.println("ID psr " + this.site.getNomSite());
            } catch (SQLException ex) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("information dialogue");
                alert.setHeaderText(null);
                alert.setContentText("error dans la base de données : " + ex.getMessage());
                alert.showAndWait();

            }

        }

    }

    private int getIdDeclarationInteruption() {
        int rst = 0;
        System.out.println("nom site declaon " + this.decla.getSite());
        System.out.println("heure site decla " + this.decla.getHeure_interuption());
        try {

            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String current = format.format(date);

            String query = "SELECT id_declaration FROM declarationinteruptions "
                    + "WHERE heure_declaration_interuption=? ";
            con = SqlConnection.DbConnector();
            pst = con.prepareStatement(query);

            System.out.println("nom site retablir interuption " + this.decla.getSite());
            pst.setString(1, this.decla.getHeure_interuption());
            result = pst.executeQuery();

            System.out.println("resultSet " + result);
            System.out.println("date " + this.decla.getHeure_interuption());

            while (result.next()) {

                rst = result.getInt("id_declaration");
                System.out.println("rst " + rst);
            }

            pst.close();
            con.close();

        } catch (SQLException ex) {
            System.err.println("Error Déclaration Interuption : " + ex.getMessage());
        }

        System.out.println("id_declaration_interuption " + rst);

        return rst;

    }

    public Boolean verificationInput() {
        return !(((TextField) inputDateRetablirConnexion.getEditor()).getText().isEmpty() || inputBandePassanteRetablirConnexion.getText().isEmpty()
                || inputHeureRetablirConnexion.getText().isEmpty() || PathFileRetablirConnexion.getText().isEmpty());
    }

    public void clearInput() {
        //((TextField) dateInputDeclarationInteruption.getEditor()).setText("");
        inputBandePassanteRetablirConnexion.setText("");
        inputHeureRetablirConnexion.setText("");
        PathFileRetablirConnexion.setText("");
    }

    public void getObjet(DeclarationInteruption objet) {

        this.decla.setBande_passante_interuption(objet.getBande_passante_interuption());
        this.decla.setDate_interuption(objet.getDate_interuption());
        this.decla.setHeure_interuption(objet.getHeure_interuption());
        this.decla.setSite(objet.getSite());

    }

}
