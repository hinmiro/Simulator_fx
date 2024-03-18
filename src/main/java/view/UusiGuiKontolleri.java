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
    private SimuDao simuDao;
    private IVisualisointi visualisointi = null;
    private IVisualisointi visualisointi2 = null;
    private RotateTransition rotate;

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

    public void showInfo(){
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Info");
        infoAlert.setHeaderText(null);
        infoAlert.setContentText("Valitse kuinka monen simulaation tiedot haluat.");
        Stage stage = (Stage) infoAlert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("dollar.png"));
        infoAlert.showAndWait();
    }

    public void showAlert(int maxId){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Virheellinen syöte");
        alert.setHeaderText(null);
        alert.setContentText("Simulaatioita ei ole näin paljon... Voit nähdä " + maxId + " simulaation tulokset.");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("dollar.png"));
        alert.showAndWait();
    }

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

    public String getAika() {
            return aikaField.getText();
    }

    public String getViive() {
        return viiveField.getText();
    }

    public String getVaratutAsiakkaat() {
            return varatutField.getText();
    }

    public IVisualisointi getVisualisointi() {
        return visualisointi;
    }
    public IVisualisointi getVisualisointi2() {
        return visualisointi2;
    }

    public void setUusiGui(UusiGui uusiGui) {
    }

    public void nopeusLiuku() {
        kontrolleri.nopeutaHidasta(nopeus.getValue());
    }

    public void lisaaPalvelu() {
        kontrolleri.lisaaPalvelu(lisaaCombo.getValue());
    }

    public void poistaPalvelu() {
        kontrolleri.poistaPalvelu(poistaCombo.getValue());
    }

    public UusiGuiKontolleri getKontrolleri() {
        return this;
    }

    public void stopRotate(){
        rotate.stop();
        dollarImage.setRotate(0);
    }

}
