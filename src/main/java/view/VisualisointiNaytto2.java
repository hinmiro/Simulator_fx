package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class VisualisointiNaytto2 implements IVisualisointi{
    private GraphicsContext gc;
    private Canvas canvas;

    public VisualisointiNaytto2(Canvas canvas) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        tyhjennaNaytto();
    }


    public void tyhjennaNaytto() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
     //   virheet.clear();
    }

    @Override
    public void uusiAsiakas() {
        //Doesnt implement
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
