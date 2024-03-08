package view;

import controller.Kontrolleri;
import dao.SimuDao;
import entity.Simu;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.List;

public class KontrolleriData {
    @FXML
    private LineChart<String, Number> simuData;
    private UusiGui gui;
    private UusiGuiKontolleri kontrolleri;
    private SimuDao dao;
    private List<Simu> simut;

    public void initialize() {
        dao = new SimuDao();
        simut = dao.findAll();

        loadDataToChart();
    }

    public void setGui(UusiGui gui) {
        this.gui = gui;
    }

    public void setController(UusiGuiKontolleri kontrolleri) {
        this.kontrolleri = kontrolleri;
    }

    private void loadDataToChart() {

        for (Simu s : simut) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Simu " + s.getId());
            series.getData().add(new XYChart.Data<>("Palvelupisteet yhteensä", s.getPalvelupisteet()));
            series.getData().add(new XYChart.Data<>("Asiakkaat", s.getAsiakkaat()));
            series.getData().add(new XYChart.Data<>("Varatut ajat %", s.getVaratutAsiakkaat(), s.getVaratutAsiakkaat()));
            series.getData().add(new XYChart.Data<>("Asiakastyytyväisyys", s.getAsiakastyytyvaisyys()));
            series.getData().add(new XYChart.Data<>("Info keskiaika", s.getInfoKeskiaika()));
            series.getData().add(new XYChart.Data<>("Uudet tilit keskiaika", s.getUusitiliKeskiaika()));
            series.getData().add(new XYChart.Data<>("Kassa palvelu", s.getTalletusKeskiaika()));
            series.getData().add(new XYChart.Data<>("Sijoitusneuvonta", s.getSijoitusKeskiaika()));
            //    series.getData().add(new XYChart.Data<>("Simulaation kestoaika", s.getSimulaatioAika()));
            simuData.getData().add(series);


            //  simuData.getData().addAll(asiakkaat);
        }
    }
}
