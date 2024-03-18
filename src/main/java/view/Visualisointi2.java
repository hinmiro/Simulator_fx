package view;
/**
 * The `Visualisointi2` class extends `Canvas` and implements the `IVisualisointi` interface, providing visualization functionalities within a JavaFX application. It manages graphical drawing on the canvas, including clearing the display, updating customer count, and showing error messages. The visualization updates to reflect new customers and display errors centrally on the screen.
 */

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Visualisointi2 extends Canvas implements IVisualisointi {

    private GraphicsContext gc;
    ArrayList<String> virheet = new ArrayList<>();

    int asiakasLkm = 0;

    /**
     * Constructor creating a new canvas for visualization.
     * Initializes the canvas with the specified width and height, and prepares the drawing context.
     * @param w The width of the canvas.
     * @param h The height of the canvas.
     */
    public Visualisointi2(int w, int h) {
        super(w, h);
        gc = this.getGraphicsContext2D();
        tyhjennaNaytto();
    }


    /**
     * Clears the display area by setting it to a default color.
     * This method is intended to reset the visualization before drawing new elements or updating the display.
     */
    public void tyhjennaNaytto() {
        gc.clearRect(0, 0, this.getWidth(), this.getHeight());
        gc.setFill(Color.GREY);
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    /**
     * Visualizes an increment in the new customer count on the canvas.
     * Updates the count and redraws the count display centered on the canvas each time a new customer is added.
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

    /**
     * Displays error messages centrally on the canvas.
     * Collects and displays all accumulated error messages in the center of the canvas, then clears the list of messages after displaying.
     * @param virhe The error message to be displayed.
     */
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

    /**
     * Updates the canvas with final simulation results such as the total time, happiness/customer satisfaction rating, and other details.
     * This method is intended for overriding in future implementations. Currently, it does not perform any operations.
     * @param aika The final simulation time.
     * @param hcr The final happiness/customer satisfaction rating.
     * @param i The total number of processed customers.
     * @param palvelupisteet A map representing additional details about service points. (Currently not implemented)
     */
    @Override
    public void setLoppuaika(double aika, double hcr, int i, HashMap palvelupisteet) {
    }
}
