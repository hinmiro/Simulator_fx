package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class UusiGui extends Application {
    private Stage primaryStage;
    private Stage secondaryStage;
    private UusiGuiKontolleri controller;

    /**
     * Starts the application and initializes the root layout.
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * @throws Exception if any error occurs during the initialization
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Simulaattori");
        primaryStage.getIcons().add(new Image("dollar.png"));

        initRootLayout();



    }

    /**
     * Initializes the root layout with the main scene.
     */
    public void initRootLayout() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/simuGui.fxml"));
            GridPane pane = (GridPane) fxmlLoader.load();

            Scene scene = new Scene(pane);


            primaryStage.setScene(scene);
            primaryStage.show();
            controller = fxmlLoader.getController();
            controller.setUusiGui(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a new window to display data.
     * @param monta the amount of data to be displayed
     */
    public void dataWindow(int monta) {
        secondaryStage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dataIkkuna.fxml"));
            BorderPane pane = (BorderPane) fxmlLoader.load();

            KontrolleriData kontrolleriData = fxmlLoader.getController();
            kontrolleriData.setGui(this);
            kontrolleriData.setController(controller);

            Scene scene = new Scene(pane);

            kontrolleriData.initialize(monta);



            secondaryStage.setScene(scene);
            secondaryStage.initModality(Modality.APPLICATION_MODAL);
            secondaryStage.getIcons().add(new Image("dollar.png"));

            secondaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Returns the primary stage of the application.
     * @return the primary stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    /**
     * The main entry point for all JavaFX applications.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
