package view;


import javafx.scene.canvas.GraphicsContext;

public interface IVisualisointi {

	public void tyhjennaNaytto();
	
	public void uusiAsiakas();

	GraphicsContext getGraphicsContext2D();

	double getWidth();

	double getHeight();
}

