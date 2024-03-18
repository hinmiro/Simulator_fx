package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.HashMap;

public class VisualisointiNaytto2 implements IVisualisointi{
    /**
     * The GraphicsContext object that provides the methods for drawing onto the Canvas.
     */
    private GraphicsContext gc;

    /**
     * The Canvas object that represents the area onto which the GraphicsContext can draw.
     */
    private Canvas canvas;

    /**
     * The constant representing the width of the drawing area. Its value is determined during the initialization of the class.
     */
    private final double WIDTH;

    /**
     * The constant representing the height of the drawing area. Its value is determined during the initialization of the class.
     */
    private final double HEIGHT;

    /**
     * The constant representing the width of a column in the grid. It is used for positioning elements in the grid.
     */
    private final int COLUMN = 50;

    /**
     * The constant representing the height of a row in the grid. It is used for positioning elements in the grid.
     */
    private final double ROW = 80;

    /**
     * Class constructor.
     * Initializes the canvas and graphics context, sets the initial color to black, and clears the screen.
     *
     * @param canvas The canvas to be used for drawing.
     */
    public VisualisointiNaytto2(Canvas canvas) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        WIDTH = canvas.getWidth() / 12;
        HEIGHT = canvas.getHeight() / 10;
        tyhjennaNaytto();
    }

    /**
     * Clears the screen by filling it with black color.
     */
    public void tyhjennaNaytto() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
     //   virheet.clear();
    }

    /**
     * Displays the service points on the screen.
     * Each service point is represented by a text and a colored rectangle.
     */
    @Override
    public void showPalvelupisteet() {

        gc.setFont(new Font(15));
        gc.setFill(Color.GREEN);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText("iNFO Tiski:        ", WIDTH, HEIGHT);
        gc.fillText("Uuden Tilin avaus: ", WIDTH, HEIGHT + ROW);
        gc.fillText("Talletus Palvelu:  ", WIDTH, HEIGHT + ROW * 2);
        gc.fillText("Sijoitus tiski:    ", WIDTH, HEIGHT + ROW * 3);


        gc.setFill(Color.ROSYBROWN);
        gc.fillRect(WIDTH + 170, HEIGHT - 12, 15, 15);
        gc.setFill(Color.BLUE);
        gc.fillRect(WIDTH + 170, HEIGHT - 12 + ROW, 15, 15);
        gc.setFill(Color.RED);
        gc.fillRect(WIDTH + 170, HEIGHT - 12 + ROW * 2, 15, 15);
        gc.setFill(Color.GREEN);
        gc.fillRect(WIDTH + 170, HEIGHT - 12 + ROW * 3, 15, 15);

    }

    /**
     * Adds a new service point to the screen.
     * The type of the service point determines the color of the rectangle representing it.
     *
     * @param palveluTyyppi The type of the service point.
     * @param size The number of service points of the same type.
     */
    @Override
    public void uusiPalveluPiste(String palveluTyyppi, int size) {
        size--;
        switch (palveluTyyppi) {
            case "Infopiste":
                gc.setFill(Color.ROSYBROWN);
                gc.fillRect(WIDTH + 170 + COLUMN * size, HEIGHT - 12, 15, 15);
                break;
            case "Uusi tili":
                gc.setFill(Color.BLUE);
                gc.fillRect(WIDTH + 170 + COLUMN * size, HEIGHT - 12 + ROW, 15, 15);
                break;
            case "Talletuspiste":
                gc.setFill(Color.RED);
                gc.fillRect(WIDTH + 170 + COLUMN * size, HEIGHT - 12 + ROW * 2, 15, 15);
                break;
            case "Sijoitusneuvonta":
                gc.setFill(Color.GREEN);
                gc.fillRect(WIDTH + 170 + COLUMN * size, HEIGHT - 12 + ROW * 3, 15, 15);
                break;
        }
    }

    /**
     * Removes a service point from the screen.
     * The type of the service point determines which rectangle to remove.
     *
     * @param palveluTyyppi The type of the service point.
     * @param size The number of service points of the same type.
     */
    @Override
    public void deletePalveluPiste(String palveluTyyppi, int size) {
        switch (palveluTyyppi) {
            case "Infopiste":
                gc.setFill(Color.BLACK);
                gc.fillRect(WIDTH + 170 + COLUMN * size, HEIGHT - 12, 15, 15);
                break;
            case "Uusi tili":
                gc.setFill(Color.BLACK);
                gc.fillRect(WIDTH + 170 + COLUMN * size, HEIGHT - 12 + ROW, 15, 15);
                break;
            case "Talletuspiste":
                gc.setFill(Color.BLACK);
                gc.fillRect(WIDTH + 170 + COLUMN * size, HEIGHT - 12 + ROW * 2, 15, 15);
                break;
            case "Sijoitusneuvonta":
                gc.setFill(Color.BLACK);
                gc.fillRect(WIDTH + 170 + COLUMN * size, HEIGHT - 12 + ROW * 3, 15, 15);
                break;
        }
    }

    /**
     * This method is not implemented.
     */
    @Override
    public void uusiAsiakas() {
        //Doesnt implement
    }

    /**
     * This method is not implemented.
     */
    @Override
    public void naytaSimulointiAika() {
        // Doesnt implement
    }

    /**
     * This method is not implemented.
     */
    @Override
    public void naytaVirheIlmoitus(String s) {
        //Doesnt implement
    }

    /**
     * Returns the GraphicsContext associated with this object.
     * This method is not implemented and always returns null.
     *
     * @return null
     */
    @Override
    public GraphicsContext getGraphicsContext2D() {
        return null;
    }

    /**
     * Returns the width of the canvas.
     * This method is not implemented and always returns 0.
     *
     * @return 0
     */
    @Override
    public double getWidth() {
        return 0;
    }

    /**
     * Returns the height of the canvas.
     * This method is not implemented and always returns 0.
     *
     * @return 0
     */
    @Override
    public double getHeight() {
        return 0;
    }

    /**
     * This method is not implemented.
     */
    @Override
    public void setLoppuaika(double d, double dd, int i, HashMap hashMap) {
        //Doesnt implement
    }
}
