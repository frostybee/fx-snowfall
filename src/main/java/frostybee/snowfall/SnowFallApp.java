package frostybee.snowfall;

import frostybee.snowfall.controllers.FXMLSnowFallController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 *
 * @author frostybee.
 */
public class SnowFallApp extends Application {

    private FXMLSnowFallController fxmlController;
    @Override
    public void start(Stage primaryStage) {
        try {
            //-- 1) Load the scene graph from the specified FXML file and 
            // associate it with its FXML controller.
            fxmlController = new FXMLSnowFallController();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SnowFallingApp_layout.fxml"));
            loader.setController(fxmlController);
            Pane root = loader.load();
            //-- 2) Create and set the scene to the stage.
            Scene scene = new Scene(root, 1000, 834);
            primaryStage.setTitle("FX Snowfall Animation");
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();
            // We just need to bring the main window to front.
            primaryStage.setAlwaysOnTop(true);            
            primaryStage.show();
            primaryStage.setMaximized(true);
            primaryStage.setAlwaysOnTop(false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        fxmlController.stopAnimation();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
