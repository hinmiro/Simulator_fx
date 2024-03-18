package view;

import dao.SimuDao;
import entity.Simu;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

import java.text.DecimalFormat;
import java.util.List;

/**
 * This class is responsible for controlling the data in the simulation.
 * It interacts with the GUI and the SimuDao to fetch and display data.
 */
public class KontrolleriData {
    @FXML
    private LineChart<String, Number> simuData;
    private UusiGui gui;
    private UusiGuiKontolleri kontrolleri;
    private SimuDao dao;
    private List<Simu> simut;
    private int simuMaara;

    /**
     * Sets the controller for this class.
     * @param kontrolleri the controller to be set
     */
    public void setController(UusiGuiKontolleri kontrolleri) {
        this.kontrolleri = kontrolleri;
    }

    /**
     * Initializes the class with a specified amount of data.
     * @param maara the amount of data to be fetched
     */
    public void initialize(int maara) {
        dao = new SimuDao();
        simut = dao.findAmount(maara);
        loadDataToChart();
    }

    /**
     * Sets the GUI for this class.
     * @param gui the GUI to be set
     */
    public void setGui(UusiGui gui) {
        this.gui = gui;
    }

    /**
     * Loads data to the chart.
     */
    private void loadDataToChart() {

        for (Simu s : simut) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Simu " + s.getId());
            series.getData().add(new XYChart.Data<>("Palvelupisteet yhteensä", s.getPalvelupisteet()));
            series.getData().add(new XYChart.Data<>("Asiakkaat", s.getAsiakkaat()));
            series.getData().add(new XYChart.Data<>("Varatut ajat %", s.getVaratutAsiakkaat(), s.getVaratutAsiakkaat()));
            series.getData().add(new XYChart.Data<>("Asiakastyytyväisyys", s.getAsiakastyytyvaisyys()));
            series.getData().add(new XYChart.Data<>("Info keskiaika", s.getInfoKeskiaika()));
            series.getData().add(new XYChart.Data<>("Info käyttöaste", s.getInfoKayttoaste()));
            series.getData().add(new XYChart.Data<>("Uudet tilit keskiaika", s.getUusitiliKeskiaika()));
            series.getData().add(new XYChart.Data<>("Uudet tilit käyttöaste", s.getUusitiliKayttoaste()));
            series.getData().add(new XYChart.Data<>("Kassa palvelu", s.getTalletusKeskiaika()));
            series.getData().add(new XYChart.Data<>("Kassa käyttöaste", s.getTalletusKayttoaste()));
            series.getData().add(new XYChart.Data<>("Sijoitusneuvonta", s.getSijoitusKeskiaika()));
            series.getData().add(new XYChart.Data<>("Sijoutusneuvonta käyttöaste", s.getSijoitusKayttoaste()));
        //    series.getData().add(new XYChart.Data<>("Simulaation kokonaisaika", s.getSimulaatioAika()));

            simuData.setStyle("-fx-font-size: 14px;");


            simuData.getData().add(series);

            simuData.setCreateSymbols(true);

            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            for (XYChart.Data<String, Number> data : series.getData()) {
                Node node = data.getNode();
                Tooltip tooltip = new Tooltip();
                tooltip.install(node, tooltip);

                node.setOnMouseEntered(event -> {
                    double value = data.getYValue().doubleValue();
                    String formattedValue = decimalFormat.format(value);
                    tooltip.setText(formattedValue);
                    tooltip.show(node, event.getScreenX()+10, event.getScreenY()+10);
                });
                node.setOnMouseExited(event -> tooltip.hide());
            }
        }
    }
}
