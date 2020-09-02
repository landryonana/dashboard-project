package retailsfinal1;

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
import java.util.Arrays;
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
import retailsfinal1.models.SiteConcerne;
import retailsfinal1.models.SqlConnection;

/**
 * FXML Controller class
 *
 * @author JESUS-CHRIST
 */
public class DeclarationInterruptionController implements Initializable {

    @FXML
    private Button btnFileDeclarationInteruption;

    @FXML
    private AnchorPane anchorDeclarationInteruption;

    private File file;

    @FXML
    private TextArea pathFileDeclrationInteruption;

    private Connection con;
    private PreparedStatement pst;

    @FXML
    private DatePicker dateInputDeclarationInteruption;
    @FXML
    private TextField heureInputDeclarationInteruption;
    @FXML
    private TextField bandePassanteInputDeclarationInteruption;
    @FXML
    private Label labelErrorDeclaration;

    private FileInputStream fis = null;
    private final SiteConcerne site = new SiteConcerne();
    private final DeclarationInteruption decl=new DeclarationInteruption();
    private final SiteConcerne siteid = new SiteConcerne();
    private ResultSet result = null;

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
            //getIdSite();
        } catch (SQLException ex) {

            System.err.println("Erro declaration interuption : " + ex.getMessage());

        }

    }

    @FXML
    private void choseFileHandler(ActionEvent event) {

        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Choisir Votre fichier");

        Stage stage = (Stage) anchorDeclarationInteruption.getScene().getWindow();
        file = filechooser.showOpenDialog(stage);

        if (file != null) {
            pathFileDeclrationInteruption.setText(file.getAbsolutePath());
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

    //Ajoue de la declaration d'interuption par site
    @FXML
    private void AjouterDeclarationInteruption(ActionEvent event) throws FileNotFoundException {
        try {
            addDeclaration();
        } catch (SQLException ex) {
            System.out.println("Error addDeclaration " + ex.getMessage());
        }
    }

    public Boolean verificationInput() {
        return !(((TextField) dateInputDeclarationInteruption.getEditor()).getText().isEmpty() || bandePassanteInputDeclarationInteruption.getText().isEmpty()
                || heureInputDeclarationInteruption.getText().isEmpty() || pathFileDeclrationInteruption.getText().isEmpty());
    }

    public void clearInput() {
        //((TextField) dateInputDeclarationInteruption.getEditor()).setText("");
        bandePassanteInputDeclarationInteruption.setText("");
        heureInputDeclarationInteruption.setText("");
        pathFileDeclrationInteruption.setText("");
    }

    private final String sitenom="";
    
    public void siteName(String site) {
        //this.labelSiteBandePassante.setText(site);
        this.site.setNomSite(site);
        //sitenom = site;
    }

    private int getIdAndHeureSite() {
        int rst = 0;

        try {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String current = format.format(date);

            String query = "SELECT site_id,heure_site FROM site WHERE nom_site=? AND date_site=? ";
            con = SqlConnection.DbConnector();
            pst = con.prepareStatement(query);

            System.out.println("nom site " + site.getNomSite());

            pst.setString(1, this.site.getNomSite());
            pst.setString(2, current);
            result = pst.executeQuery();

            System.out.println("resultSet " + result);
            System.out.println("date " + current);

            while (result.next()) {

                rst = result.getInt("site_id");
                decl.setSite(result.getString("heure_site"));
                System.out.println("ID " + rst);
            }
            pst.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error Déclaration Interuption : " + ex.getMessage());
        }
        System.out.println("ID " + rst);
        return rst;
    }

    private void addDeclaration() throws FileNotFoundException, SQLException {

        fis = new FileInputStream(file);
        int site_id = this.getIdAndHeureSite();
        String disponibilite = this.calculeDisponibilite().toString();
        String penalite = calculePenalite(this.calculeDisponibilite());
        System.out.println("penalite " + penalite);
        String etat = "NON RETABLIE";

        if (!verificationInput()) {

            labelErrorDeclaration.setText("certaines champs sont vide ou invalide");

        } else {

            try {

                String query = "INSERT INTO declarationinteruptions("
                        + "bande_passante_interuption,"
                        + "date_declaration_interuption,"
                        + "heure_declaration_interuption,"
                        + "fichier_preuve_declaration_interuption,"
                        + "disponibilite_declaration_interuption,"
                        + "penalite_declaration_interuption,"
                        + "etat_declaration_interuption,"
                        + "site_id"
                        + ")"
                        + " VALUES (?,?,?,?,?,?,?,?)";

                con = SqlConnection.DbConnector();
                pst = con.prepareStatement(query);
                //pst.setString(1, site.getNomSite());
                pst.setString(1, bandePassanteInputDeclarationInteruption.getText());
                pst.setString(2, ((TextField) dateInputDeclarationInteruption.getEditor()).getText());
                pst.setString(3, heureInputDeclarationInteruption.getText());
                pst.setBinaryStream(4, (InputStream) fis, (int) file.length());
                pst.setString(5, disponibilite.concat("%"));
                System.out.println("disponibilite " + disponibilite.concat("%"));
                if(penalite.isEmpty()){
                    penalite="la fonction à mal fonctionné";
                    System.out.println("penalite " + penalite);
                }else{
                    pst.setString(6, penalite);
                    System.out.println("penalite " + penalite);
                }
                pst.setString(7, etat);
                pst.setInt(8, site_id);
                pst.execute();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("information dialogue");
                alert.setHeaderText(null);
                alert.setContentText("Déclaration bande passante ajouter avec success!!!!");
                alert.showAndWait();
                labelErrorDeclaration.setText("");
                clearInput();
                pst.close();
                con.close();

                System.out.println("connexion fermer avec succès");
                System.out.println("Déclaration bande passante ajouter avec succès");

                System.out.println("ID psr " + this.getIdAndHeureSite());
                System.out.println("ID psr " + this.site.getNomSite());

            } catch (SQLException ex) {

                System.err.println("error : " + ex.getMessage());

            }

        }

    }

    //convertir l'heure en minute
    private int converteHeureToMinute(String heureSite, String heureDeclarationInteruption) {
        String[] tab1;
        String[] tab2;

        tab1 = heureSite.split("h");
        System.out.println("tab1[0] "+tab1[0]);
        int h1 = Integer.parseInt(tab1[0]);
        int m1 = Integer.parseInt(tab1[1]);
        
        System.out.println("h1 "+h1);
        System.out.println("m1 "+m1);
        System.out.println("tab1 "+Arrays.toString(tab1));

        tab2 = heureDeclarationInteruption.split("h");
        int h2 = Integer.parseInt(tab2[0]);
        int m2 = Integer.parseInt(tab2[1]);
        System.out.println("tab2 "+h2);
        System.out.println("tab2 "+m2);
        System.out.println("tab2 "+Arrays.toString(tab2));
        
        int minute1 = h1 * 60 + m1;
        int minute2 = h2 * 60 + m2;
        if (minute1 > minute2) {
            return minute1 - minute2;
        } else {
            return minute2 - minute1;
        }

    }

    //calcule disponibilite
    private Float calculeDisponibilite() throws SQLException {
        String heureSite = decl.getSite();
        int diff = this.converteHeureToMinute(heureSite, heureInputDeclarationInteruption.getText());
        System.out.println("CALC heureInputDeclarationInteruption "+heureInputDeclarationInteruption.getText());
        System.out.println("CALC heureSite "+heureSite);
        Float disponibilite;
        disponibilite = (float) ((diff * 100) / 560);

        return disponibilite;
    }

    //calcule de la pénalité
    private String calculePenalite(Float dispo) throws SQLException {
        dispo = this.calculeDisponibilite();
        System.out.println("pena fonction test "+dispo);
        String penalite = "";
        if (dispo >= 99.0) {
            penalite = "0.0%";
        } else if ((97.0 < dispo) && (dispo <= 99.0)) {
            penalite = "5%";
        } else if ((96.0 < dispo) && (dispo <= 97.0)) {
            penalite = "7%";
        } else if ((90.0 < dispo) && (dispo <= 96.0)) {
            penalite = "10%";
        } else if ((80.0 < dispo) && (dispo <= 90.0)) {
            penalite = "20%";
        }else if (dispo<80.0) {
            penalite = "pas de facturation";
        }
        return penalite;
    }
}
