package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class is responsible for visualizing the simulation data in a different style.
 * It extends the Canvas class and implements the IVisualisointi interface.
 */
public class Visualisointi2 extends Canvas implements IVisualisointi {

    /**
     * The GraphicsContext object used for drawing on the canvas.
     */
    private GraphicsContext gc;

    /**
     * A list of error messages to be displayed.
     */
    ArrayList<String> virheet = new ArrayList<>();

    /**
     * The number of customers that have been visualized.
     */
    int asiakasLkm = 0;

    /**
     * Constructor for the Visualisointi2 class.
     * Initializes the GraphicsContext and clears the display.
     * @param w the width of the canvas
     * @param h the height of the canvas
     */
    public Visualisointi2(int w, int h) {
        super(w, h);
        gc = this.getGraphicsContext2D();
        tyhjennaNaytto();
    }

    /**
     * Clears the display by filling it with a gray color.
     */
    public void tyhjennaNaytto() {
        gc.clearRect(0, 0, this.getWidth(), this.getHeight());
        gc.setFill(Color.GREY);
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    /**
     * Visualizes a new customer by updating a counter and displaying it in the center of the canvas.
     */
    public void uusiAsiakas() {

        asiakasLkm++;

        double x = this.getWidth() / 2;
        double y = this.getHeight() / 2;

        gc.clearRect(x - 50, y - 10, 100, 20);

        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
        gc.setFill(Color.DARKORANGE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(new Font(20));
        gc.fillText("Asiakas " + asiakasLkm, x, y);

    }

    @Override
    public void naytaSimulointiAika() {
        // Doesnt implement
    }


    @Override
    public void naytaVirheIlmoitus(String virhe) {
        double y = this.getHeight() / 2;
        virheet.add(virhe);
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
        gc.setFill(Color.DARKORANGE);
        gc.setFont(new Font(15));
        gc.setTextAlign(TextAlignment.CENTER);
        for (String s : virheet) {
            gc.fillText(s, this.getWidth() / 2, y += 20);
        }
        virheet.clear();
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
    public void setLoppuaika(double aika, double hcr, int i, HashMap palvelupisteet) {
    }
}
