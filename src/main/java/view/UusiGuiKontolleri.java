package view;
/**
 * The UusiGuiKontolleri class serves as the controller for the JavaFX GUI, handling user interactions such as starting the simulation, adjusting parameters, and visualizing results. It manages the interface elements and links actions like adding or removing service points to the simulation controller.
 */

import controller.IKontrolleriForM;
import controller.Kontrolleri;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import controller.IKontrolleriForV;
import javafx.stage.Stage;
import simu.framework.IMoottori;
import simu.framework.Kello;
import simu.framework.Trace;
import simu.model.OmaMoottori;


public class UusiGuiKontolleri {
    private IVisualisointi visualisointi = null;
    private IVisualisointi visualisointi2 = null;

    @FXML
    private Canvas visu1;
    @FXML
    private Canvas visu2;
    @FXML
    private Slider nopeus;
    @FXML
    private Button simuloiButton;
    @FXML
    private Button tyhjennaButton;
    @FXML
    private Button poistaButton;
    @FXML
    private Button lisaaButton;
    @FXML
    private ComboBox<String> lisaaCombo;
    @FXML
    private ComboBox<String> poistaCombo;
    @FXML
    private Label aikaLabel;
    @FXML
    private Label viiveLabel;
    @FXML
    private Label varatutLabel;
    @FXML
    private Label nopeusLabel;
    @FXML
    private TextField aikaField;
    @FXML
    private TextField varatutField;
    @FXML
    private TextField viiveField;
    private IKontrolleriForV kontrolleri;

    /**
     * Constructs a new UusiGuiKontrolleri instance. Initializes the controller used by this GUI.
     */
    public UusiGuiKontolleri() {
        kontrolleri = new Kontrolleri(this);
    }

    /**
     * Initializes the controller class. This method is automatically called after the FXML file has been loaded.
     */
    @FXML
    public void initialize() {
        poistaCombo.getItems().addAll("Infopiste", "Uusi tili", "Talletuspiste", "Sijoitusneuvonta");
        lisaaCombo.getItems().addAll("Infopiste", "Uusi tili", "Talletuspiste", "Sijoitusneuvonta");
        nopeus.setDisable(true);
        lisaaButton.setDisable(true);
        poistaButton.setDisable(true);
        Trace.setTraceLevel(Trace.Level.INFO);
        visualisointi = new VisualisointiNaytto1(visu1);
        visualisointi2 =  new VisualisointiNaytto2(visu2);
    }


    /**
     * Starts the simulation by calling the appropriate method in the controller and updates the UI accordingly.
     */
    public void kaynnistaSimulointi() {
        kontrolleri.kaynnistaSimulointi();
        nopeus.setDisable(false);
        lisaaButton.setDisable(false);
        poistaButton.setDisable(false);
        simuloiButton.setDisable(true);
    }

    /**
     * Clears the simulation visuals and resets all input fields and sliders to their default states.
     */
    public void handleTyhjenna() {
        visualisointi.tyhjennaNaytto();
        aikaField.clear();
        viiveField.clear();
        varatutField.clear();
        nopeus.setValue(545);
        nopeus.setDisable(true);
        lisaaButton.setDisable(true);
        poistaButton.setDisable(true);
        simuloiButton.setDisable(false);
        Kello.getInstance().setAika(0);
        kontrolleri.initializeData();
    }

    /**
     * Retrieves the simulation time specified by the user.
     * @return A string representing the simulation time.
     */
    public String getAika() {
            return aikaField.getText();
    }

    /**
     * Retrieves the simulation delay specified by the user.
     * @return A string representing the simulation delay.
     */
    public String getViive() {
        return viiveField.getText();
    }

    /**
     * Retrieves the percentage of reserved customers specified by the user.
     * @return A string representing the percentage of reserved customers.
     */
    public String getVaratutAsiakkaat() {
            return varatutField.getText();
    }

    /**
     * Returns the visual representation component of this controller.
     * @return An instance of IVisualisointi used for the simulation visualization.
     */
    public IVisualisointi getVisualisointi() {
        return visualisointi;
    }

    /**
     * Sets the reference to the main application class to this controller.
     * @param uusiGui The main application class instance.
     */
    public void setUusiGui(UusiGui uusiGui) {
    }


    /**
     * Adjusts the simulation speed based on the slider's current value.
     */
    public void nopeusLiuku() {
        kontrolleri.nopeutaHidasta(nopeus.getValue());
    }

    /**
     * Adds a new service point to the simulation based on the user's selection.
     */
    public void lisaaPalvelu() {
        kontrolleri.lisaaPalvelu(lisaaCombo.getValue());
    }

    /**
     * Removes a service point from the simulation based on the user's selection.
     */
    public void poistaPalvelu() {
        kontrolleri.poistaPalvelu(poistaCombo.getValue());
    }
}
