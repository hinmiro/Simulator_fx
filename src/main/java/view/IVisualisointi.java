package view;


import javafx.scene.canvas.GraphicsContext;

import java.util.HashMap;

public interface IVisualisointi {

	public void tyhjennaNaytto();
	
	public void uusiAsiakas();
	public void naytaVirheIlmoitus(String s);

	GraphicsContext getGraphicsContext2D();

	double getWidth();

	double getHeight();

	public void setLoppuaika(double d, double dd, int i, HashMap hashMap);

}

