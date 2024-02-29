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
    private IVisualisointi visualisointi = new Visualisointi2(400, 400);

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

    public UusiGuiKontolleri() {
        kontrolleri = new Kontrolleri(this);
    }


    public void kaynnistaSimulointi() {
        kontrolleri.kaynnistaSimulointi();
    }
    public void handleTyhjenna() {

    }

    public double getAika() {
        return Double.parseDouble(aikaField.getText());
    }

    public long getViive() {
        return Long.parseLong(viiveField.getText());
    }

    public int getVaratutAsiakkaat() {
        return Integer.parseInt(varatutField.getText());
    }

    public GraphicsContext getVisualisointi() {
        return visu1.getGraphicsContext2D();
    }

    public void setUusiGui(UusiGui uusiGui) {
    }
}
