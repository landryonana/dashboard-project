package retailsfinal1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author JESUS-CHRIST
 */
public class RetailsFinal1 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("vue/retails.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(true);
        stage.setTitle("RETAILS");
        //stage.getIcons().add(new Image("/com/project/images/logo.png"));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
