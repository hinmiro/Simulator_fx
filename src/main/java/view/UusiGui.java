package view;
/**
 *
 The UusiGui class extends Application to create a JavaFX GUI for the simulator, setting up the primary stage and initializing the layout from an FXML file. It manages the main application window and connects the FXML controller with the GUI.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;

public class UusiGui extends Application {
    private Stage primaryStage;

    /**
     * Starts the JavaFX application, setting up the primary stage.
     * This method is called by the JavaFX runtime when the application is launched.
     * @param primaryStage The primary stage for this application, onto which the application scene can be set.
     * @throws Exception if there is a problem starting the application.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Simulaattori");

        initRootLayout();



    }

    /**
     * Initializes the root layout by loading the FXML file, setting the scene, and displaying the stage.
     * This method sets up the main layout for the user interface.
     */
    public void initRootLayout() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/simuGui.fxml"));
            GridPane pane = (GridPane) fxmlLoader.load();

            Scene scene = new Scene(pane);


            primaryStage.setScene(scene);
            primaryStage.show();
            UusiGuiKontolleri controller = fxmlLoader.getController();
            controller.setUusiGui(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Returns the primary stage of the application.
     * This allows other classes access to the primary stage to set different scenes.
     * @return The primary stage of this JavaFX application.
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned, and after the system is ready for the application to begin running.
     * @param args Command line arguments passed to the application.
     * Not used in this application.
     */
    public static void main(String[] args) {
        launch(args);
    }

}
