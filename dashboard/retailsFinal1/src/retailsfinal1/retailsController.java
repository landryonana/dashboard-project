/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retailsfinal1;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import retailsfinal1.models.SqlConnection;

/**
 *
 * @author JESUS-CHRIST
 */
public class retailsController implements Initializable {

    @FXML
    private AnchorPane anchorPaneHeader;
    @FXML
    private MenuItem btnFr;
    @FXML
    private MenuItem btnProfile;
    @FXML
    private MenuItem btnCreerUser;
    @FXML
    private Button deconnecter;
    @FXML
    private JFXButton btnBar2;
    @FXML
    private JFXButton btnBar1;
    @FXML
    private JFXButton btn_admin;
    @FXML
    private JFXButton btn_yd;
    @FXML
    private JFXButton btnBonaberie;
    @FXML
    private JFXButton btnLimbe;
    @FXML
    private JFXButton btnBamenda;
    @FXML
    private MenuButton btnSiege;
    @FXML
    private MenuItem btnRouteur;
    @FXML
    private MenuItem btnOrange;
    @FXML
    private AnchorPane paneSide;
    @FXML
    private Pane paneTabBboard;
    @FXML
    private JFXButton btnRechercheTab;
    @FXML
    private AnchorPane anchorVariation;
    @FXML
    private BarChart<?, ?> chartIndisponi;
    @FXML
    private BarChart<?, ?> chartBandePassante;
    @FXML
    private BarChart<?, ?> chartPenalite;
    @FXML
    private Pane paneYaounde;
    @FXML
    private BarChart<?, ?> chartYaoundeInterup;
    @FXML
    private BarChart<?, ?> chartYaoundeIndispo;
    @FXML
    private BarChart<?, ?> chartYaoundePenaliteSite;
    @FXML
    private BarChart<?, ?> chartYaoundeBandePassante;
    @FXML
    private JFXButton btnAjouterBandePassanteYaounde;
    @FXML
    private JFXButton btnDeclarationInteruptionYaounde;
    @FXML
    private Pane paneBamenda;
    @FXML
    private BarChart<?, ?> chartBamendaInterup;
    @FXML
    private BarChart<?, ?> chartBamendaIndispo;
    @FXML
    private BarChart<?, ?> chartBamendaPenalite;
    @FXML
    private BarChart<?, ?> chartBamendaBandePassante;
    @FXML
    private JFXButton btnAjouterBandePassanteBamenda;
    @FXML
    private JFXButton btnDeclarationInteruptionBamenda;
    @FXML
    private Pane paneBonaberie;
    @FXML
    private BarChart<?, ?> chartBonaberieInterup;
    @FXML
    private BarChart<?, ?> chartBonaberieIndispo;
    @FXML
    private BarChart<?, ?> chartBonaberiePenalite;
    @FXML
    private BarChart<?, ?> chartBonaberieBandePassante;
    @FXML
    private JFXButton btnAjouterBandePassanteBonaberie;
    @FXML
    private JFXButton btnDeclarationInteruptionBonaberie;
    @FXML
    private Pane paneLimbe;
    @FXML
    private BarChart<?, ?> chartLimbeInterup;
    @FXML
    private BarChart<?, ?> chartLimbeIndispo;
    @FXML
    private BarChart<?, ?> chartLimbePenalite;
    @FXML
    private BarChart<?, ?> chartLimbeBandePassante;
    @FXML
    private JFXButton btnAjouterBandePassanteLimbe;
    @FXML
    private JFXButton btnDeclarationInteruptionLimbe;

    Connection con;
    @FXML
    private Pane paneRouteur;
    private JFXComboBox<String> critereRechercheRouteur;
    @FXML
    private BarChart<?, ?> chartRouteurInterup;
    @FXML
    private BarChart<?, ?> chartRouteurIndispo;
    @FXML
    private BarChart<?, ?> chartRouteurPenaliteSite;
    @FXML
    private JFXButton btnAjouterBandePassanteRouteur;
    @FXML
    private JFXButton btnDeclarationInteruptionRouteur;
    @FXML
    private Pane paneOrange;
    @FXML
    private BarChart<?, ?> chartOrangeInterup;
    @FXML
    private BarChart<?, ?> chartOrangeIndispo;
    @FXML
    private BarChart<?, ?> chartOrangePenalite;
    @FXML
    private BarChart<?, ?> chartOrangeBandePassante;
    @FXML
    private JFXButton btnAjouterBandePassanteOrange;
    @FXML
    private JFXButton btnDeclarationInteruptionOrange;
    @FXML
    private BarChart<?, ?> chartRouteurBandePassante;
    @FXML
    private JFXComboBox<String> ComboxBonaberie;
    @FXML
    private JFXButton btnRechercheBonaberie;
    @FXML
    private JFXComboBox<String> ComboxYaounde;
    @FXML
    private JFXButton btnRechercheYaounde;
    @FXML
    private JFXComboBox<String> ComboxOrange;
    @FXML
    private JFXButton btnRechercheOrange;
    @FXML
    private JFXComboBox<String> ComboxRouteur;
    @FXML
    private JFXButton btnRechercheRouteur;
    @FXML
    private JFXComboBox<String> ComboxLimbe;
    @FXML
    private JFXButton btnRechercheLimbe;
    @FXML
    private JFXComboBox<String> ComboxBamenda;

    @FXML
    private JFXButton btnRechercheBamenda;

    @FXML
    private JFXComboBox<String> ComboxTabBord;

    @FXML
    private MenuButton btnMenuUser;

    private PieChart pieChartFrequence;

    @FXML
    private Pane paneView;
    @FXML
    private Label nomSiteYaounde;
    @FXML
    private Label nomRouteur;
    @FXML
    private Label nomSiteBonaberie;
    @FXML
    private Label nomOrange;
    @FXML
    private Label nomSiteBamenda;
    @FXML
    private Label nomSiteLimbe;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //loadData();
        try {
            CheckConnection();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        //passage des valeurs à la combo box de critère :yaoundé,tab de bord, bonaberie,limbe,bamenda
        ComboxTabBord.getItems().addAll("Bande Passante", "Taux de Disponibilite", "penalite", "declaration", "retour connexion");
        ComboxBamenda.getItems().addAll("Bande Passante", "Taux de Disponibilite", "penalite", "declaration", "retour connexion");
        ComboxBonaberie.getItems().addAll("Bande Passante", "Taux de Disponibilite", "penalite", "declaration", "retour connexion");
        ComboxYaounde.getItems().addAll("Bande Passante", "Taux de Disponibilite", "penalite", "declaration", "retour connexion");
        ComboxLimbe.getItems().addAll("Bande Passante", "Taux de Disponibilite", "penalite", "declaration", "retour connexion");
        ComboxOrange.getItems().addAll("Bande Passante", "Taux de Disponibilite", "penalite", "declaration", "retour connexion");
        ComboxRouteur.getItems().addAll("Bande Passante", "Taux de Disponibilite", "penalite", "declaration", "retour connexion");

        paneSide.setTranslateX(-213);
        btnBar1.setVisible(true);
        btnBar2.setVisible(false);

        //load chart tab de bord
        loadChart(chartIndisponi);
        loadChart(chartPenalite);
        loadChart(chartBandePassante);

        //@@@@@@@@@@@@@ Site de yaoundé @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        //chartYaoundeInterup, chartYaoundeIndispo, chartYaoundePenaliteSite,chartYaoundeBandePassante;
        //Diagramme de frequence d'interuption yaounde 
        loadChart(chartYaoundeInterup);

        //Diagramme de variation de taux d'indisponibilite yaounde
        loadChart(chartYaoundeIndispo);

        //Diagramme de variation bande passante yaounde
        loadChart(chartYaoundeBandePassante);

        //Diagramme de variation du taux de pénalité par site yaounde
        loadChart(chartYaoundePenaliteSite);

//@@@@@@@@@@@@ Site de Bamenda @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        //chartBamendaInterup, chartBamendaIndispo,chartBamendaPenalite,chartBamendaBandePassante;
        //Diagramme de frequence d'interuption bamenda 
        loadChart(chartBamendaInterup);

        //Diagramme de variation de taux d'indisponibilite bamenda
        loadChart(chartBamendaIndispo);

        //Diagramme de variation du taux de pénalité par site bamenda
        loadChart(chartBamendaPenalite);

        //Diagramme de variation bande passante bamenda
        loadChart(chartBamendaBandePassante);

//@@@@@@@@@@@@ Site de Limbe @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        //chartLimbeInterup,chartLimbeIndispo,chartLimbePenalite, chartLimbeBandePassante;
        //Diagramme de frequence d'interuption Limbe 
        loadChart(chartLimbeInterup);

        //Diagramme de variation de taux d'indisponibilite Limbe
        loadChart(chartLimbeIndispo);

        //Diagramme de variation du taux de pénalité par site Limbe
        loadChart(chartLimbePenalite);

        //Diagramme de variation bande passante Limbe
        loadChart(chartLimbeBandePassante);

//@@@@@@@@@@@@ Site de Bonaberie @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@     
        //chartBonaberieInterup,chartBonaberieIndispo,chartBonaberiePenalite,chartBonaberieBandePassante;
        //Diagramme de frequence d'interuption Bonaberie 
        loadChart(chartBonaberieInterup);

        //Diagramme de variation de taux d'indisponibilite Bonaberie
        loadChart(chartBonaberieIndispo);

        //Diagramme de variation du taux de pénalité par site Bonaberie
        loadChart(chartBonaberiePenalite);

        //Diagramme de variation bande passante Bonaberie
        loadChart(chartBonaberieBandePassante);

//@@@@@@@@@@@@ de Routeur @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@     
        //chartBonaberieInterup,chartBonaberieIndispo,chartBonaberiePenalite,chartBonaberieBandePassante;
        //Diagramme de frequence d'interuption Bonaberie 
        loadChart(chartRouteurInterup);

        //Diagramme de variation de taux d'indisponibilite Bonaberie
        loadChart(chartRouteurIndispo);

        //Diagramme de variation du taux de pénalité par site Bonaberie
        loadChart(chartRouteurPenaliteSite);

        //Diagramme de variation bande passante Bonaberie
        loadChart(chartRouteurBandePassante);

//@@@@@@@@@@@@ de Orange @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@     
        //chartBonaberieInterup,chartBonaberieIndispo,chartBonaberiePenalite,chartBonaberieBandePassante;
        //Diagramme de frequence d'interuption Bonaberie 
        loadChart(chartOrangeInterup);

        //Diagramme de variation de taux d'indisponibilite Bonaberie
        loadChart(chartOrangeIndispo);

        //Diagramme de variation du taux de pénalité par site Bonaberie
        loadChart(chartOrangePenalite);

        //Diagramme de variation bande passante Bonaberie
        loadChart(chartOrangeBandePassante);

    }

    @FXML
    private void runBar2(ActionEvent event) {

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(paneSide);

        slide.setToX(-213);
        slide.play();

        paneSide.setTranslateX(0);

        slide.setOnFinished((ActionEvent e) -> {
            btnBar1.setVisible(true);
            btnBar2.setVisible(false);
        });

    }

    @FXML
    private void runBar1(ActionEvent event) {

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(paneSide);

        slide.setToX(0);
        slide.play();

        paneSide.setTranslateX(-213);

        slide.setOnFinished((ActionEvent e) -> {
            btnBar1.setVisible(false);
            btnBar2.setVisible(true);
        });

    }

    private void loadChart(BarChart<?, ?> chart) {
        XYChart.Series serie = new XYChart.Series<>();
        serie.getData().add(new XYChart.Data<>("janvier", 5));
        serie.getData().add(new XYChart.Data<>("février", 8));
        serie.getData().add(new XYChart.Data<>("mars", 12));
        serie.getData().add(new XYChart.Data<>("avril", 15));
        serie.getData().add(new XYChart.Data<>("mai", 18));
        serie.getData().add(new XYChart.Data<>("juin", 21));
        serie.getData().add(new XYChart.Data<>("juillet", 25));
        serie.getData().add(new XYChart.Data<>("aout", 28));
        serie.getData().add(new XYChart.Data<>("septembre", 32));
        serie.getData().add(new XYChart.Data<>("octobre", 36));
        serie.getData().add(new XYChart.Data<>("novembre", 39));
        serie.getData().add(new XYChart.Data<>("décembre", 45));
        chart.setLegendVisible(false);
        chart.getData().setAll(serie);
    }

    @FXML
    private void actionButtonSidePane(ActionEvent event) throws IOException {

        if (event.getSource() == btn_admin) {
            paneTabBboard.toFront();
        } else if (event.getSource() == btn_yd) {
            paneYaounde.toFront();

        } else if (event.getSource() == btnLimbe) {
            paneLimbe.toFront();
        } else if (event.getSource() == btnBonaberie) {
            paneBonaberie.toFront();
        } else if (event.getSource() == btnBamenda) {
            paneBamenda.toFront();
        } else if (event.getSource() == btnOrange) {
            paneOrange.toFront();
        } else if (event.getSource() == btnRouteur) {
            paneRouteur.toFront();
        }

    }


    @FXML
    private void handlerDeconnection(ActionEvent event) {

        try {

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("vue/login.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.err.println("error : " + ex.getMessage());
        }

    }

    @FXML
    private void profilUser(ActionEvent event) {

        try {
            Stage stage = new Stage();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("vue/profileUser.fxml")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("error : " + ex.getMessage());
        }
    }

    @FXML
    private void creerUser(ActionEvent event) {

        try {
            Stage stage = new Stage();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("vue/creerUser.fxml")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("error : " + ex.getMessage());
        }

    }

    private void CheckConnection() throws SQLException {
        con = SqlConnection.DbConnector();
        if (con == null) {
            System.out.println("Connection failed retailsController");
        } else {
            System.out.println("Connection successful retailsController");
        }

    }

    @FXML
    private void handlerRechercheBamenda(ActionEvent event) {

        String elmt = ComboxBamenda.getSelectionModel().getSelectedItem();

        if (elmt.equals("Bande Passante")) {

            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("vue/listeBandePassante.fxml")));
                stage.setScene(scene);
                stage.initStyle(StageStyle.DECORATED);
                stage.setResizable(false);
                stage.setTitle("LISTE DES BANDES PASSANTES");
                stage.show();
            } catch (IOException ex) {
                System.err.println("error : " + ex.getMessage());
            }

        } else if (elmt.equals("declaration")) {

            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("vue/listeDeclarationInterruption.fxml")));
                stage.setScene(scene);
                stage.initStyle(StageStyle.DECORATED);
                stage.setResizable(false);
                stage.setTitle("LISTE DES DECLARATIONS D'INTERUPTION");
                stage.show();
            } catch (IOException ex) {
                System.err.println("error : " + ex.getMessage());
            }

        }

    }

    @FXML
    private void handlerRechercheLimbe(ActionEvent event) {

        String elmt = ComboxLimbe.getSelectionModel().getSelectedItem();

        if (elmt.equals("Bande Passante")) {

            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("vue/listeBandePassante.fxml")));
                stage.setScene(scene);
                stage.initStyle(StageStyle.DECORATED);
                stage.setResizable(false);
                stage.setTitle("LISTE DES BANDES PASSANTES");
                stage.show();
            } catch (IOException ex) {
                System.err.println("error : " + ex.getMessage());
            }

        } else if (elmt.equals("declaration")) {

            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("vue/listeDeclarationInterruption.fxml")));
                stage.setScene(scene);
                stage.initStyle(StageStyle.DECORATED);
                stage.setResizable(false);
                stage.setTitle("LISTE DES DECLARATIONS D'INTERUPTION");
                stage.show();
            } catch (IOException ex) {
                System.err.println("error : " + ex.getMessage());
            }

        }

    }

    @FXML
    private void handlerRechercheOrange(ActionEvent event) {

        String elmt = ComboxOrange.getSelectionModel().getSelectedItem();

        if (elmt.equals("Bande Passante")) {

            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("vue/listeBandePassante.fxml")));
                stage.setScene(scene);
                stage.initStyle(StageStyle.DECORATED);
                stage.setResizable(false);
                stage.setTitle("LISTE DES BANDES PASSANTES");
                stage.show();
            } catch (IOException ex) {
                System.err.println("error : " + ex.getMessage());
            }

        } else if (elmt.equals("declaration")) {

            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("vue/listeDeclarationInterruption.fxml")));
                stage.setScene(scene);
                stage.initStyle(StageStyle.DECORATED);
                stage.setResizable(false);
                stage.setTitle("LISTE DES DECLARATIONS D'INTERUPTION");
                stage.show();
            } catch (IOException ex) {
                System.err.println("error : " + ex.getMessage());
            }

        }

    }

    @FXML
    private void handleRechercheYaounde(ActionEvent event) {

        String elmt = ComboxYaounde.getSelectionModel().getSelectedItem();

        if (elmt.equals("Bande Passante")) {

            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("vue/listeBandePassante.fxml")));
                stage.setScene(scene);
                stage.initStyle(StageStyle.DECORATED);
                stage.setResizable(false);
                stage.setTitle("LISTE DES BANDES PASSANTES");
                stage.show();
            } catch (IOException ex) {
                System.err.println("error : " + ex.getMessage());
            }

        } else if (elmt.equals("declaration")) {

            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("vue/listeDeclarationInterruption.fxml")));
                stage.setScene(scene);
                stage.initStyle(StageStyle.DECORATED);
                stage.setResizable(false);
                stage.setTitle("LISTE DES DECLARATIONS D'INTERUPTION");
                stage.show();
            } catch (IOException ex) {
                System.err.println("error : " + ex.getMessage());
            }

        }

    }

    @FXML
    private void handlerRechercheBonaberie(ActionEvent event) {

        String elmt = ComboxBonaberie.getSelectionModel().getSelectedItem();

        if (elmt.equals("Bande Passante")) {

            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("vue/listeBandePassante.fxml")));
                stage.setScene(scene);
                stage.initStyle(StageStyle.DECORATED);
                stage.setResizable(false);
                stage.setTitle("LISTE DES BANDES PASSANTES");
                stage.show();
            } catch (IOException ex) {
                System.err.println("error : " + ex.getMessage());
            }

        } else if (elmt.equals("declaration")) {

            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("vue/listeDeclarationInterruption.fxml")));
                stage.setScene(scene);
                stage.initStyle(StageStyle.DECORATED);
                stage.setResizable(false);
                stage.setTitle("LISTE DES DECLARATIONS D'INTERUPTION");
                stage.show();
            } catch (IOException ex) {
                System.err.println("error : " + ex.getMessage());
            }

        }

    }

    @FXML
    private void handlerRechercherTab(ActionEvent event) {

        String elmt = ComboxTabBord.getSelectionModel().getSelectedItem();

        if (elmt.equals("Bande Passante")) {

            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("vue/listeBandePassante.fxml")));
                stage.setScene(scene);
                stage.initStyle(StageStyle.DECORATED);
                stage.setResizable(false);
                stage.setTitle("LISTE DES BANDES PASSANTES");
                stage.show();
            } catch (IOException ex) {
                System.err.println("error : " + ex.getMessage());
            }

        } else if (elmt.equals("declaration")) {

            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("vue/listeDeclarationInterruption.fxml")));
                stage.setScene(scene);
                stage.initStyle(StageStyle.DECORATED);
                stage.setResizable(false);
                stage.setTitle("LISTE DES DECLARATIONS D'INTERUPTION");
                stage.show();
            } catch (IOException ex) {
                System.err.println("error : " + ex.getMessage());
            }

        }

    }

    @FXML
    private void handlerRechercheRouteur(ActionEvent event) {

        String elmt = ComboxRouteur.getSelectionModel().getSelectedItem();

        if (elmt.equals("Bande Passante")) {

            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("vue/listeBandePassante.fxml")));
                stage.setScene(scene);
                stage.initStyle(StageStyle.DECORATED);
                stage.setResizable(false);
                stage.setTitle("LISTE DES BANDES PASSANTES");
                stage.show();
            } catch (IOException ex) {
                System.err.println("error : " + ex.getMessage());
            }

        } else if (elmt.equals("declaration")) {

            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("vue/listeDeclarationInterruption.fxml")));
                stage.setScene(scene);
                stage.initStyle(StageStyle.DECORATED);
                stage.setResizable(false);
                stage.setTitle("LISTE DES DECLARATIONS D'INTERUPTION");
                stage.show();
            } catch (IOException ex) {
                System.err.println("error : " + ex.getMessage());
            }

        }

    }

//passage du texte dans btnMenuUser (donner prie login)
    public void afficheLogin(String text) {
        this.btnMenuUser.setText("Bienvenue " +text);
    }

    //<?import retailsfinal1.DoughnutChart?>
    //<DoughnutChart fx:id="pieChartFrequence" layoutX="-6.0" prefHeight="239.0" prefWidth="529.0" title="Diagramme de Fréquence d'intéruption par site" />
    private void loadData() {
        paneView.getChildren().clear();
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        list.add(new PieChart.Data("Yaounde", 20));
        list.add(new PieChart.Data("Limbe", 30));
        list.add(new PieChart.Data("Bonaberie", 50));
        list.add(new PieChart.Data("Bamenda", 10));
        pieChartFrequence.setData(list);
        pieChartFrequence.setLabelsVisible(false);
        pieChartFrequence.setLegendVisible(false);
        paneView.getChildren().add(pieChartFrequence);
    }

    @FXML
    private void ajouterBantePassanteYaounde(ActionEvent event) {
        AppelBandeSite(nomSiteYaounde.getText().toLowerCase());
    }

    @FXML
    private void ajouterBantePassanteRouteur(ActionEvent event) {
        AppelBandeSite(nomRouteur.getText().toLowerCase());
    }

    @FXML
    private void ajouterBantePassanteBonaberie(ActionEvent event) {
        AppelBandeSite(nomSiteBonaberie.getText().toLowerCase());
    }

    @FXML
    private void ajouterBantePassanteOrange(ActionEvent event) {
        AppelBandeSite(nomOrange.getText().toLowerCase());
    }

    @FXML
    private void ajouterBantePassanteBamenda(ActionEvent event) {
        AppelBandeSite(nomSiteBamenda.getText().toLowerCase());
    }

    @FXML
    private void ajouterBantePassanteLimbe(ActionEvent event) {
        //nomSiteLimbe.getText().toLowerCase();
        AppelBandeSite(nomSiteLimbe.getText().toLowerCase());

    }

    //appel du controller de Bande passante
    public void AppelBandeSite(String nomSite) {
        try {
            Stage stage = new Stage();
            /*Scene scene = new Scene(FXMLLoader.load(getClass().getResource("vue/bandePassante.fxml")));
            stage.setScene(scene);
            stage.initStyle(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.setTitle("ENTRER BANDE PASSANTE");*/

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("vue/bandePassante.fxml"));
            loader.load();

            BandePassanteController tab = loader.getController();
            System.out.println("//// fonction vue");
            tab.siteName(nomSite);
            System.out.println(nomSite);

            Parent p = loader.getRoot();
            stage.setScene(new Scene(p));
            stage.initStyle(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.setTitle("ENTRER BANDE PASSANTE");
            stage.show();

        } catch (IOException ex) {
            System.err.println("error : " + ex.getMessage());
        }
    }

    public void AppelDeclarationInteruptionSite(String nomSite) {
        try {
            Stage stage = new Stage();
            /*Scene scene = new Scene(FXMLLoader.load(getClass().getResource("vue/bandePassante.fxml")));
            stage.setScene(scene);
            stage.initStyle(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.setTitle("ENTRER BANDE PASSANTE");*/

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("vue/declarationInterruption.fxml"));
            loader.load();

            DeclarationInterruptionController tab = loader.getController();
            System.out.println("//// fonction vue declaration");
            tab.siteName(nomSite);
            System.out.println(nomSite);

            Parent p = loader.getRoot();
            stage.setScene(new Scene(p));
            stage.initStyle(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.setTitle("DECLARATION INTERUPTION");
            stage.show();

        } catch (IOException ex) {
            System.err.println("error declarationInterruption retailsController : " + ex.getMessage());
        }
    }

    @FXML
    private void declarationInteruptionYaounde(ActionEvent event) {
        //nomSiteYaounde.getText().toLowerCase()
        AppelDeclarationInteruptionSite(nomSiteYaounde.getText().toLowerCase());
    }

    @FXML
    private void declarationInteruptionLimbe(ActionEvent event) {
        AppelDeclarationInteruptionSite(nomSiteLimbe.getText().toLowerCase());
    }

    @FXML
    private void declarationInteruptionBamenda(ActionEvent event) {
        AppelDeclarationInteruptionSite(nomSiteBamenda.getText().toLowerCase());
    }

    @FXML
    private void declarationInteruptionOrange(ActionEvent event) {
        AppelDeclarationInteruptionSite(nomOrange.getText().toLowerCase());
    }

    @FXML
    private void declarationInteruptionBonaberie(ActionEvent event) {
        AppelDeclarationInteruptionSite(nomSiteBonaberie.getText().toLowerCase());
    }

    @FXML
    private void declarationInteruptionRouteur(ActionEvent event) {
        AppelDeclarationInteruptionSite(nomRouteur.getText().toLowerCase());
    }
    
    

}
