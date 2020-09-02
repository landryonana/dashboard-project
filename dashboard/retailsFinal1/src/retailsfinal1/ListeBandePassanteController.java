/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retailsfinal1;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import retailsfinal1.models.SiteConcerne;
import retailsfinal1.models.SqlConnection;

/**
 * FXML Controller class
 *
 * @author JESUS-CHRIST
 */
public class ListeBandePassanteController implements Initializable {

    @FXML
    private TableColumn<SiteConcerne, String> coloneDateBandePassante;
    @FXML
    private TableColumn<SiteConcerne, String> coloneHeureBandePassante;
    @FXML
    private TableColumn<SiteConcerne, String> coloneValeurBandePassante;
    @FXML
    private TableColumn<SiteConcerne, String> coloneSiteBandePassante;
    @FXML
    private TableView<SiteConcerne> tableBandePassanteSite;

    private Connection con;
    private ObservableList<SiteConcerne> list;

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
            System.err.println("ERROR : " + ex.getMessage());
        }

        afficheBandePassante();
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
            list = FXCollections.observableArrayList();
            String query = "SELECT * FROM site";
            con = SqlConnection.DbConnector();
            ResultSet set = con.createStatement().executeQuery(query);
            while (set.next()) {

                SiteConcerne site = new SiteConcerne();
                site.setDateSite(set.getString("date_site"));
                site.setHeureSite(set.getString("heure_site"));
                site.setBandePassanteSite(set.getString("bande_passante_site").concat(" Megas"));
                site.setNomSite(set.getString("nom_site"));

                list.add(site);
            }

            coloneDateBandePassante.setCellValueFactory(new PropertyValueFactory<>("dateSite"));
            coloneHeureBandePassante.setCellValueFactory(new PropertyValueFactory<>("heureSite"));
            coloneValeurBandePassante.setCellValueFactory(new PropertyValueFactory<>("bandePassanteSite"));
            coloneSiteBandePassante.setCellValueFactory(new PropertyValueFactory<>("nomSite"));

            tableBandePassanteSite.setItems(list);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close(); // <-- This is important
                } catch (SQLException e) {
                    System.err.println("Error ListeBandePassanteController finally " + e.getMessage());
                }
            }

        }

    }
}
