package retailsfinal1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import retailsfinal1.models.DeclarationInteruption;
import retailsfinal1.models.SqlConnection;

/**
 * FXML Controller class
 *
 * @author JESUS-CHRIST
 */
public class ListeDeclarationInterruptionController implements Initializable {

    private Connection con;
    private ObservableList<DeclarationInteruption> list;

    @FXML
    private TableColumn<DeclarationInteruption, String> coloneDateInteruption;
    @FXML
    private TableColumn<DeclarationInteruption, String> coloneHeureInteruption;
    @FXML
    private TableColumn<DeclarationInteruption, String> coloneBandePassanteInteruption;
    @FXML
    private TableColumn<DeclarationInteruption, String> coloneSiteInteruption;
    @FXML
    private TableColumn<DeclarationInteruption, String> coloneEtatInteruption;
    @FXML
    private TableColumn<DeclarationInteruption, String> coloneDisponibiliteInteruption;
    @FXML
    private TableColumn<DeclarationInteruption, String> colonePenaliteInteruption;
    @FXML
    private TableColumn<DeclarationInteruption, String> coloneActionInteruption;
    @FXML
    private TableView<DeclarationInteruption> tabViewInteruption;

    private AnchorPane anchorPaneCell;

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
            System.err.println("Error connexion " + ex.getMessage());
        }

        this.afficheBandePassante();

    }

    private void CheckConnection() throws SQLException {
        con = SqlConnection.DbConnector();
        if (con == null) {
            System.out.println("Connection failed");
        } else {
            System.out.println("Connection successful");
        }
    }

    private void afficheBandePassante() {

        try {

            //recupération du nom du site où l'interruption est produit
            String siteNom = "SELECT nom_site FROM site AS s,declarationinteruptions AS d WHERE s.site_id=d.site_id";;
            System.out.println("siteNom " + siteNom);
            list = FXCollections.observableArrayList();
            String query = "SELECT * FROM declarationinteruptions";
            con = SqlConnection.DbConnector();
            ResultSet set = con.createStatement().executeQuery(query);
            ResultSet set1 = con.createStatement().executeQuery(siteNom);
            while (set.next() && set1.next()) {

                DeclarationInteruption interup = new DeclarationInteruption();

                interup.setDate_interuption(set.getString("date_declaration_interuption"));
                interup.setHeure_interuption(set.getString("heure_declaration_interuption"));
                interup.setBande_passante_interuption(set.getString("bande_passante_interuption").concat(" Megas"));
                interup.setSite(set1.getString("nom_site"));

                interup.setEtat_interuption(set.getString("etat_declaration_interuption"));
                interup.setDisponibilite_interuption(set.getString("disponibilite_declaration_interuption"));
                interup.setPenalite_interuption(set.getString("penalite_declaration_interuption"));

                list.add(interup);
            }

            coloneDateInteruption.setCellValueFactory(new PropertyValueFactory<>("date_interuption"));
            coloneHeureInteruption.setCellValueFactory(new PropertyValueFactory<>("heure_interuption"));
            coloneBandePassanteInteruption.setCellValueFactory(new PropertyValueFactory<>("bande_passante_interuption"));
            coloneSiteInteruption.setCellValueFactory(new PropertyValueFactory<>("site"));
            System.out.println("siteNom " + new PropertyValueFactory<>("site"));
            coloneEtatInteruption.setCellValueFactory(new PropertyValueFactory<>("etat_interuption"));
            coloneDisponibiliteInteruption.setCellValueFactory(new PropertyValueFactory<>("disponibilite_interuption"));
            colonePenaliteInteruption.setCellValueFactory(new PropertyValueFactory<>("penalite_interuption"));

            //creation une cellule dans notre tableau afin d'insérer les Button détails et retailbir connexion
            Callback<TableColumn<DeclarationInteruption, String>, TableCell<DeclarationInteruption, String>> cellFactory = (params) -> {

                final TableCell<DeclarationInteruption, String> cell;
                cell = new TableCell<DeclarationInteruption, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {

                        super.updateItem(item, empty);

                        if (empty) {

                            setGraphic(null);
                            setText(null);

                        } else {
                            anchorPaneCell = new AnchorPane();
                            final Button retablir = new Button("Retablir");
                            retablir.setLayoutX(77);
                            retablir.setLayoutY(0);

                            final Button detailButton = new Button("Detail");
                            detailButton.setLayoutX(0);
                            detailButton.setLayoutY(0);

                            final Label slash = new Label("/");
                            slash.setLayoutX(64);
                            slash.setLayoutY(0);
                            //AnchorPane.setRightAnchor(slash,20.0);

                            detailButton.setOnAction(event -> {
                                DeclarationInteruption d = getTableView().getItems().get(getIndex());
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setContentText("Informtion detail \n" + " "
                                        + "Date Interuption : " + d.getDate_interuption() + "\n"
                                        + "Heure Interuption : " + d.getHeure_interuption() + "\n"
                                        + "Bande Passante Interuption : " + d.getBande_passante_interuption() + "\n"
                                        + "Site Interuption : " + d.getSite() + "\n"
                                        + "Etat Interuption : " + d.getEtat_interuption() + "\n"
                                        + "Disponibilité Interuption : " + d.getDisponibilite_interuption() + "\n"
                                        + "Pénalité Interuption : " + d.getPenalite_interuption()
                                );

                                alert.show();

                            });

                            retablir.setOnAction(event -> {

                                try {

                                    DeclarationInteruption d = getTableView().getItems().get(getIndex());
                                    Stage stage = new Stage();
                                    /*Scene scene = new Scene(FXMLLoader.load(getClass().getResource("vue/bandePassante.fxml")));
                                        stage.setScene(scene);
                                        stage.initStyle(StageStyle.DECORATED);
                                        stage.setResizable(false);
                                        stage.setTitle("ENTRER BANDE PASSANTE");*/

                                    FXMLLoader loader = new FXMLLoader();
                                    loader.setLocation(getClass().getResource("vue/retablirConnexion.fxml"));
                                    loader.load();

                                    RetablirConnexionController objet = loader.getController();
                                    System.out.println("//// fonction vue retablir interuption");
                                    objet.getObjet(d);

                                    Parent p = loader.getRoot();
                                    stage.setScene(new Scene(p));
                                    stage.initStyle(StageStyle.DECORATED);
                                    stage.setResizable(false);
                                    stage.setTitle("RETABLIR INTERUPTION");
                                    stage.show();

                                } catch (IOException ex) {
                                    System.err.println("error : " + ex.getMessage());
                                }

                            });

                            anchorPaneCell.getChildren().addAll(detailButton, slash, retablir);
                            setGraphic(anchorPaneCell);
                            setText(null);

                        }

                    }

                };

                return cell;

            };

            coloneActionInteruption.setCellFactory(cellFactory);

            tabViewInteruption.setItems(list);

        } catch (SQLException ex) {

            System.err.println(ex.getMessage());

        }finally {
            if (con != null) {
                try {
                    con.close(); // <-- This is important
                } catch (SQLException e) {
                    System.err.println("Error getNameSiteInteruption finally "+e.getMessage());
                }
            }

        }
        
        
    }

    //recupération du nom du site ki a pour id dans declarationinteruptions
    private String getNameSiteInteruption() {
        String rst = "";
        try {
            //requete de recupération
            String query = "SELECT nom_site FROM site AS s,declarationinteruptions AS d WHERE s.site_id=d.site_id";

            //recupération de l'objet connection
            con = SqlConnection.DbConnector();
            //creation d'un requete en base de données et recupération du resultat dans ResultSet( qui est tableau de données)
            ResultSet result = con.createStatement().executeQuery(query);
            //parcour du tableau et recupération des données(ici on aura une seul données nom du site dans la colonne nom_site)
            while (result.next()) {

                rst = result.getString("nom_site");
                System.out.println("nom_site interuption " + rst);
            }
            System.out.println("nom_site interuption close " + rst);

        } catch (SQLException ex) {
            System.err.println("Error getNameSiteInteruption "+ex.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close(); // <-- This is important
                } catch (SQLException e) {
                    System.err.println("Error getNameSiteInteruption finally "+e.getMessage());
                }
            }

        }
        return rst;
    }
}
