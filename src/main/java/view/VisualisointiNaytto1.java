package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import simu.framework.Kello;
import simu.model.Palvelupiste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for visualizing the simulation data in a specific style.
 * It implements the IVisualisointi interface.
 */
public class VisualisointiNaytto1 implements IVisualisointi {

    /**
     * The GraphicsContext object used for drawing on the canvas.
     */
    private GraphicsContext gc;

    /**
     * The Canvas object on which the visualization is drawn.
     */
    private Canvas canvas;

    /**
     * A list of error messages to be displayed.
     */
    private ArrayList<String> virheet;

    /**
     * A map of service points, each associated with a list of customers.
     */
    private HashMap<String, ArrayList<Palvelupiste>> palvelupisteet;

    /**
     * The number of customers that have been visualized.
     */
    int asiakasLkm = 0;

    /**
     * Constructor for the VisualisointiNaytto1 class.
     * Initializes the GraphicsContext and clears the display.
     * @param canvas the canvas on which the visualization is drawn
     */
    public VisualisointiNaytto1(Canvas canvas) {
        virheet = new ArrayList<>();
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        tyhjennaNaytto();

    }

    /**
     * Clears the display by filling it with a black color.
     */
    @Override
    public void tyhjennaNaytto() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        virheet.clear();
    }

    /**
     * Visualizes a new customer by updating a counter and displaying it at the top of the canvas.
     */
    @Override
    public void uusiAsiakas() {
        asiakasLkm++;

        double x = canvas.getWidth() / 2;
        double y = canvas.getHeight() / 10;
        tyhjennaNaytto();

        gc.setFill(Color.GREEN);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(new Font(20));
        gc.fillText("Asiakas " + asiakasLkm, x, y);

    }

    /**
     * Displays the current simulation time.
     */
    @Override
    public void naytaSimulointiAika() {
        double x = canvas.getWidth() / 2;
        double y = canvas.getHeight() / 10 + 30;

        gc.setFill(Color.GREEN);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(new Font(20));
        gc.fillText(String.format("Simulointiaika:  %.3f", Kello.getInstance().getAika()), x, y);
    }

    /**
     * Displays an error message.
     * @param virhe the error message to be displayed
     */
    @Override
    public void naytaVirheIlmoitus(String virhe) {
        System.out.println(virhe);
        double y = canvas.getHeight() / 5;
        virheet.add(virhe);
        gc.setFill(Color.ORANGERED);
        gc.setFont(new Font(15));
        gc.setTextAlign(TextAlignment.CENTER);
        for (String s : virheet) {
            gc.fillText(s, canvas.getWidth() / 2, y);
            y += 20;
        }
    }

    @Override
    public void uusiPalveluPiste(String palveluTyyppi, int size) {

    }

    @Override
    public void deletePalveluPiste(String palveluTyyppi, int size) {

    }

    @Override
    public void showPalvelupisteet() {

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
        gc.setFill(Color.GREEN);
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
                increment++;
                gc.fillText("Palvelun " + pp.getNimi() + " käyttöaste: " + String.format("%.2f", pp.getKayttoAste()), width, height + row * increment);
                increment += 2;
            }
        }
        asiakasLkm = 0;
    }
}
