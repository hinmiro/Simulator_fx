package view;

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
    private KontrolleriData kontrolleriData;
    private UusiGui gui;
    @FXML
    private TextField kuinkaMontaField;

    public UusiGuiKontolleri() {
        kontrolleri = new Kontrolleri(this);
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
        Kello.getInstance().setAika(0);
        kontrolleri.initializeData();
    }

    public void naytaData() {
        try {
            int number = Integer.parseInt(kuinkaMontaField.getText());
            gui.dataWindow(number);
        } catch (NumberFormatException e) {
            kuinkaMontaField.setText("Enter a number...");
            System.out.println("Enter integer");
        }
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
}
