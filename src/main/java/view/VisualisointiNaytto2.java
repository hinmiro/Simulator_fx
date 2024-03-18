package view;
/**
 * The `VisualisointiNaytto2` class implements the `IVisualisointi` interface, primarily focusing on maintaining a black background for the canvas it controls. This class serves as a template for visualizations that do not currently implement methods for displaying new customers, error messages, or final statistics.
 */

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class VisualisointiNaytto2 implements IVisualisointi{
    private GraphicsContext gc;
    private Canvas canvas;

    /**
     * Constructs a VisualisointiNaytto2 object. This initializes the canvas and sets its background to black.
     * @param canvas The Canvas object to be used for drawing.
     */
    public VisualisointiNaytto2(Canvas canvas) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        tyhjennaNaytto();
    }

    /**
     * Clears the display area by filling it with black color. This method resets the visualization area.
     */
    public void tyhjennaNaytto() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
     //   virheet.clear();
    }

    /**
     * Method stub for adding a new customer visualization. Currently, this method does not implement any functionality.
     */
    @Override
    public void uusiAsiakas() {
        //Doesnt implement
    }

    /**
     * Method stub for displaying error messages. Currently, this method does not implement any functionality.
     * @param s The error message to display.
     */
    @Override
    public void naytaVirheIlmoitus(String s) {
        //Doesnt implement
    }

    /**
     * Returns the GraphicsContext associated with this canvas. Intended for low-level drawing operations.
     * Currently, this method does not return any object.
     * @return null, as the method does not implement any functionality.
     */
    @Override
    public GraphicsContext getGraphicsContext2D() {
        return null;
    }

    /**
     * Returns the width of the canvas. Currently, this method does not return any value.
     * @return 0, as the method does not implement any functionality.
     */
    @Override
    public double getWidth() {
        return 0;
    }

    /**
     * Returns the height of the canvas. Currently, this method does not return any value.
     * @return 0, as the method does not implement any functionality.
     */
    @Override
    public double getHeight() {
        return 0;
    }


    /**
     * Method stub for setting the final simulation time and statistics. Currently, this method does not implement any functionality.
     * @param d Final simulation time.
     * @param dd Overall customer satisfaction.
     * @param i Total number of customers served.
     * @param hashMap Statistics for each service point.
     */
    @Override
    public void setLoppuaika(double d, double dd, int i, HashMap hashMap) {
        //Doesnt implement
    }
}
