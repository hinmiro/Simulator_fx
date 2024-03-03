package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

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
        asiakasLkm++;

        double x = canvas.getWidth()/2;
        double y = canvas.getHeight()/2;
        tyhjennaNaytto();

        gc.setFill(Color.DARKORANGE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(new Font(20));
        gc.fillText("Asiakas " + asiakasLkm, x, y);

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
