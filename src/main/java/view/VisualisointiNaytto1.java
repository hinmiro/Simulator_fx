package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class VisualisointiNaytto1 implements IVisualisointi{

    private GraphicsContext gc;
    private Canvas canvas;

    int asiakasLkm = 0;

    public VisualisointiNaytto1(Canvas canvas) {
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
    }

    @Override
    public void uusiAsiakas() {

    }

    @Override
    public void naytaVirheIlmoitus(String s) {

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
}
