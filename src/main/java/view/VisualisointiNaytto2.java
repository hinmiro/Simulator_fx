package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.HashMap;

public class VisualisointiNaytto2 implements IVisualisointi{
    private GraphicsContext gc;
    private Canvas canvas;
    private final double WIDTH;
    private final double HEIGHT;
    private final int COLUMN = 50;
    private final double ROW = 80;

    public VisualisointiNaytto2(Canvas canvas) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        WIDTH = canvas.getWidth() / 12;
        HEIGHT = canvas.getHeight() / 10;
        tyhjennaNaytto();
    }


    public void tyhjennaNaytto() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
     //   virheet.clear();
    }

    @Override
    public void showPalvelupisteet() {

        gc.setFont(new Font(15));
        gc.setFill(Color.GREEN);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText("Infopiste:        ", WIDTH, HEIGHT);
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

    @Override
    public void uusiAsiakas() {
        //Doesnt implement
    }

    @Override
    public void naytaSimulointiAika() {
        // Doesnt implement
    }

    @Override
    public void naytaVirheIlmoitus(String s) {
        //Doesnt implement
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
    public void setLoppuaika(double d, double dd, int i, HashMap hashMap) {
        //Doesnt implement
    }
}
