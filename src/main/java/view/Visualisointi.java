package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashMap;

/**
 * This class is responsible for visualizing the simulation data.
 * It extends the Canvas class and implements the IVisualisointi interface.
 */
public class Visualisointi extends Canvas implements IVisualisointi{

	/**
	 * The GraphicsContext object used for drawing on the canvas.
	 */
	private final GraphicsContext gc;

	/**
	 * The x-coordinate for drawing the next customer.
	 */
	double i = 0;

	/**
	 * The y-coordinate for drawing the next customer.
	 */
	double j = 10;


	/**
	 * Constructor for the Visualisointi class.
	 * Initializes the GraphicsContext and clears the display.
	 * @param w the width of the canvas
	 * @param h the height of the canvas
	 */
	public Visualisointi(int w, int h) {
		super(w, h);
		gc = this.getGraphicsContext2D();
		tyhjennaNaytto();
	}

	/**
	 * Clears the display by filling it with a gray color.
	 */
	public void tyhjennaNaytto() {
		gc.setFill(Color.GRAY);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	/**
	 * Visualizes a new customer by drawing a red circle.
	 */
	public void uusiAsiakas() {
		gc.setFill(Color.RED);
		gc.fillOval(i,j,10,10);
		
		i = (i + 10) % this.getWidth();
		//j = (j + 12) % this.getHeight();
		if (i==0) j+=10;			
	}

	@Override
	public void naytaSimulointiAika() {
		// Doesnt implement
	}

	@Override
	public void naytaVirheIlmoitus(String s) {}

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
	public void setLoppuaika(double aika, double hcr, int i, HashMap palvelupisteet) {}

}
