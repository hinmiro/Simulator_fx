package view;

import controller.IKontrolleriForM;
import controller.Kontrolleri;
import dao.SimuDao;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import controller.IKontrolleriForV;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import simu.framework.IMoottori;
import simu.framework.Kello;
import simu.framework.Trace;
import simu.model.OmaMoottori;
import javafx.scene.image.Image;

/**
 * This class is responsible for controlling the GUI of the application.
 * It interacts with the Kontrolleri, SimuDao, and Visualisointi classes to control and display data.
 */
public class UusiGuiKontolleri {
    /**
     * The SimuDao object used for database operations.
     */
    private SimuDao simuDao;

    /**
     * The IVisualisointi object used for visualizing the simulation data.
     */
    private IVisualisointi visualisointi = null;

    /**
     * The IVisualisointi object used for visualizing the simulation data in a different style.
     */
    private IVisualisointi visualisointi2 = null;

    /**
     * The RotateTransition object used for rotating the dollar image.
     */
    private RotateTransition rotate;

    @FXML
    /**
     * The Canvas object used for the first visualization.
     */
    private Canvas visu1;

    @FXML
    /**
     * The Canvas object used for the second visualization.
     */
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
    private KontrolleriData kontrolleriData;
    private UusiGui gui;
    @FXML
    private TextField kuinkaMontaField;
    @FXML
    private Button infoButton;
    @FXML
    private ImageView dollarImage;

    /**
     * Constructor for the UusiGuiKontolleri class.
     * Initializes the Kontrolleri and SimuDao objects, and sets up a RotateTransition.
     */
    public UusiGuiKontolleri() {
        kontrolleri = new Kontrolleri(this);
        simuDao = new SimuDao();
        rotate = new RotateTransition();
    }

    /**
     * Initializes the GUI controller. This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
        gui = new UusiGui();
        poistaCombo.getItems().addAll("Infopiste", "Uusi tili", "Talletuspiste", "Sijoitusneuvonta");
        lisaaCombo.getItems().addAll("Infopiste", "Uusi tili", "Talletuspiste", "Sijoitusneuvonta");
        nopeus.setDisable(true);
        lisaaButton.setDisable(true);
        poistaButton.setDisable(true);
        Trace.setTraceLevel(Trace.Level.INFO);
        visualisointi = new VisualisointiNaytto1(visu1);
        visualisointi2 =  new VisualisointiNaytto2(visu2);
        kontrolleriData = new KontrolleriData();
    }


    /**
     * Starts the simulation. Enables the speed slider and the add and remove service buttons. Disables the simulate button.
     * Displays the service points in the second visualization and starts the dollar image rotation.
     */
    public void kaynnistaSimulointi() {
        kontrolleri.kaynnistaSimulointi();
        nopeus.setDisable(false);
        lisaaButton.setDisable(false);
        poistaButton.setDisable(false);
        simuloiButton.setDisable(true);
        visualisointi2.showPalvelupisteet();
        rotate.setNode(dollarImage);
        rotate.setDuration(Duration.millis(2000));
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.setByAngle(360);
        rotate.setCycleCount(RotateTransition.INDEFINITE);
        rotate.setAutoReverse(true);
        rotate.play();
    }


    /**
     * Clears the visualizations and the input fields. Resets the speed slider and the clock time. Disables the speed slider and the add and remove service buttons. Enables the simulate button.
     * Initializes the controller data.
     */
    public void handleTyhjenna() {
        visualisointi.tyhjennaNaytto();
        visualisointi2.tyhjennaNaytto();
        aikaField.clear();
        viiveField.clear();
        varatutField.clear();
        nopeus.setValue(545);
        nopeus.setDisable(true);
        lisaaButton.setDisable(true);
        poistaButton.setDisable(true);
        simuloiButton.setDisable(false);
        kuinkaMontaField.clear();
        Kello.getInstance().setAika(0);
        kontrolleri.initializeData();
    }


    /**
     * Displays the data of a specified number of simulations. The number is taken from the kuinkaMontaField text field.
     * If the number is greater than the maximum ID in the database, an alert is shown.
     */
    public void naytaData() {
        try {
            int number = Integer.parseInt(kuinkaMontaField.getText());
            int maxId = simuDao.getMaxIdFromDatabase();

            if (number > maxId) {
                showAlert(maxId);
            } else {
                gui.dataWindow(number);
            }
        } catch (NumberFormatException e) {
            inputError();
        }
    }

    /**
     * Shows an information alert with instructions on how to use the simulation data display feature.
     */
    public void showInfo(){
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Info");
        infoAlert.setHeaderText(null);
        infoAlert.setContentText("Valitse kuinka monen simulaation tiedot haluat.");
        Stage stage = (Stage) infoAlert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("dollar.png"));
        infoAlert.showAndWait();
    }

    /**
     * Shows an error alert when the user tries to display more simulation data than is available in the database.
     * @param maxId The maximum number of simulations that can be displayed.
     */
    public void showAlert(int maxId){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Virheellinen syöte");
        alert.setHeaderText(null);
        alert.setContentText("Simulaatioita ei ole näin paljon... Voit nähdä " + maxId + " simulaation tulokset.");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("dollar.png"));
        alert.showAndWait();
    }

    /**
     * Shows an error alert when the user enters an invalid input in the kuinkaMontaField text field.
     */
    public void inputError(){
        Alert inputAlert = new Alert(Alert.AlertType.ERROR);
        inputAlert.setTitle("Virheellinen syöte");
        inputAlert.setHeaderText(null);
        inputAlert.setContentText("Syötteesi oli virheellinen. Syötä numero.");
        Stage stage = (Stage) inputAlert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("dollar.png"));
        inputAlert.showAndWait();
        kuinkaMontaField.setText("");
    }

    /**
     * Returns the text in the aikaField text field.
     * @return The text in the aikaField text field.
     */
    public String getAika() {
            return aikaField.getText();
    }


    /**
     * Returns the text in the viiveField text field.
     * @return The text in the viiveField text field.
     */
    public String getViive() {
        return viiveField.getText();
    }


    /**
     * Returns the text in the varatutField text field.
     * @return The text in the varatutField text field.
     */
    public String getVaratutAsiakkaat() {
            return varatutField.getText();
    }


    /**
     * Returns the first visualization object.
     * @return The first visualization object.
     */
    public IVisualisointi getVisualisointi() {
        return visualisointi;
    }

    /**
     * Returns the second visualization object.
     * @return The second visualization object.
     */
    public IVisualisointi getVisualisointi2() {
        return visualisointi2;
    }


    /**
     * Sets the UusiGui object. This method is currently empty and does not perform any actions.
     * @param uusiGui The UusiGui object to set.
     */
    public void setUusiGui(UusiGui uusiGui) {
    }

    /**
     * Adjusts the simulation speed according to the value of the speed slider.
     */
    public void nopeusLiuku() {
        kontrolleri.nopeutaHidasta(nopeus.getValue());
    }

    /**
     * Adds a service according to the value selected in the add service combo box.
     */
    public void lisaaPalvelu() {
        kontrolleri.lisaaPalvelu(lisaaCombo.getValue());
    }

    /**
     * Removes a service according to the value selected in the remove service combo box.
     */
    public void poistaPalvelu() {
        kontrolleri.poistaPalvelu(poistaCombo.getValue());
    }

    /**
     * Returns this GUI controller.
     * @return This GUI controller.
     */
    public UusiGuiKontolleri getKontrolleri() {
        return this;
    }

    /**
     * Stops the rotation of the dollar image and resets its rotation angle to 0.
     */
    public void stopRotate(){
        rotate.stop();
        dollarImage.setRotate(0);
    }

}
