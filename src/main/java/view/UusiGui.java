package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class UusiGui extends Application {
    private Stage primaryStage;
    private Stage secondaryStage;
    private UusiGuiKontolleri controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Simulaattori");

        initRootLayout();



    }

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

    public void dataWindow() {
        secondaryStage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dataIkkuna.fxml"));
            BorderPane pane = (BorderPane) fxmlLoader.load();

            Scene scene = new Scene(pane);

            KontrolleriData kontrolleriData = fxmlLoader.getController();
            kontrolleriData.setGui(this);
            kontrolleriData.setController(controller);

            secondaryStage.setScene(scene);
            secondaryStage.initModality(Modality.APPLICATION_MODAL);

            secondaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
