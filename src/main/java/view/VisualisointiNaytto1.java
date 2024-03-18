package view;
/**
 * The VisualisointiNaytto1 class implements the IVisualisointi interface, handling the graphical visualization of simulation data on a canvas. It updates the display to show new customers, error messages, and final statistics, including overall time, customer satisfaction, and service point performances.
 */

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

    /**
     * Constructor for VisualisointiNaytto1 that initializes the canvas and graphical context.
     * The canvas is filled with black color as the default background.
     * @param canvas The canvas on which the graphical visualization will be drawn.
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
     * Clears the display area by filling it with black color and clearing any error messages.
     * This method resets the visualization area before new elements are drawn.
     */
    @Override
    public void tyhjennaNaytto() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        virheet.clear();
    }

    /**
     * Updates the visualization to represent a new customer.
     * The number of customers represented is incremented and displayed at the center of the canvas.
     */
    @Override
    public void uusiAsiakas() {
        asiakasLkm++;

        double x = canvas.getWidth() / 2;
        double y = canvas.getHeight() / 2;
        tyhjennaNaytto();

        gc.setFill(Color.GREEN);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(new Font(20));
        gc.fillText("Asiakas " + asiakasLkm, x, y);

    }

    /**
     * Displays error messages on the canvas.
     * Each new message is added to a list and displayed in the center of the canvas.
     * @param virhe The error message to display.
     */
    @Override
    public void naytaVirheIlmoitus(String virhe) {
        System.out.println(virhe);
        double y = canvas.getHeight() / 2;
        virheet.add(virhe);
        gc.setFill(Color.ORANGERED);
        gc.setFont(new Font(15));
        gc.setTextAlign(TextAlignment.CENTER);
        for (String s : virheet) {
            gc.fillText(s, canvas.getWidth() / 2, y);
            y += 20;
        }
    }

    /**
     * Returns the GraphicsContext associated with this canvas.
     * This method is intended to provide low-level access to drawing operations.
     * @return The GraphicsContext for the canvas.
     */
    @Override
    public GraphicsContext getGraphicsContext2D() {
        return null;
    }

    /**
     * Returns the width of the canvas.
     * @return The width of the canvas.
     */
    @Override
    public double getWidth() {
        return 0;
    }

    /**
     * Returns the height of the canvas.
     * @return The height of the canvas.
     */
    @Override
    public double getHeight() {
        return 0;
    }


    /**
     * Updates the canvas to display final statistics at the end of the simulation.
     * Displays overall simulation time, customer satisfaction, and statistics for each service point.
     * @param aika Total time of the simulation.
     * @param hcr Overall customer satisfaction rating.
     * @param asiakkaat Total number of customers served.
     * @param palvelut Map containing statistics for each service point.
     */
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
                increment += 2;
            }
        }
        asiakasLkm = 0;
    }
}
