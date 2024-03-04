package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import simu.model.Palvelupiste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VisualisointiNaytto1 implements IVisualisointi {

    private GraphicsContext gc;
    private Canvas canvas;
    private ArrayList<String> virheet;
    private HashMap<String, ArrayList<Palvelupiste>> palvelupisteet;


    int asiakasLkm = 0;

    public VisualisointiNaytto1(Canvas canvas) {
        virheet = new ArrayList<>();
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        tyhjennaNaytto();

    }

    @Override
    public void tyhjennaNaytto() {
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        virheet.clear();
    }

    @Override
    public void uusiAsiakas() {
        asiakasLkm++;

        double x = canvas.getWidth() / 2;
        double y = canvas.getHeight() / 2;
        tyhjennaNaytto();

        gc.setFill(Color.DARKORANGE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(new Font(20));
        gc.fillText("Asiakas " + asiakasLkm, x, y);

    }

    @Override
    public void naytaVirheIlmoitus(String virhe) {
        System.out.println(virhe);
        double y = canvas.getHeight() / 2;
        virheet.add(virhe);
        gc.setFill(Color.DARKORANGE);
        gc.setFont(new Font(15));
        gc.setTextAlign(TextAlignment.CENTER);
        for (String s : virheet) {
            gc.fillText(s, canvas.getWidth() / 2, y);
            y += 20;
        }
    }

    @Override
    public GraphicsContext getGraphicsContext2D() {
        return null;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public void setLoppuaika(double aika, double hcr, int asiakkaat, HashMap palvelut) {
        double width = canvas.getWidth() / 10;
        double height = canvas.getHeight() / 10;
        double row = 20;
        int increment = 4;
        tyhjennaNaytto();
        palvelupisteet = palvelut;
        gc.setFont(new Font(15));
        gc.setFill(Color.DARKORANGE);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText("Kokonaisaika: " + String.format("%.2f", aika), width, height);
        gc.fillText("Asiakastyytyväisyys: " + String.format("%.2f", hcr), width, height + row);
        gc.fillText("Asiakasmäärä: " + asiakkaat, width, height + row * 2);
     //   gc.fillText("************", width, height + row * 4);
        for (Map.Entry<String, ArrayList<Palvelupiste>> entry : palvelupisteet.entrySet()) {
            String key = entry.getKey();
            ArrayList<Palvelupiste> value = entry.getValue();
            for (Palvelupiste pp : value) {
                gc.fillText("Keskimääräinen palvelun kesto " + pp.getNimi() + ": " + String.format("%.2f", pp.getPalvelunkesto()), width, height + row * increment);
                increment++;
                gc.fillText("Palvelussa " + pp.getNimi() + " käyneet asiakkaat: " + pp.getPalveluAsiakkaat(), width, height + row * increment);
                increment += 2;
            }
        }
    }
}
