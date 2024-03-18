package view;
/**
 *
 The Visualisointi class extends Canvas and implements the IVisualisointi interface, providing visualization functionalities such as clearing the display and representing new customers. It handles graphical drawing on the canvas within the JavaFX framework.
 */

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class Visualisointi extends Canvas implements IVisualisointi{

	private final GraphicsContext gc;
	
	double i = 0;
	double j = 10;

	/**
	 * Constructor creating a new canvas for visualization.
	 * Initializes the canvas with specified width and height, and prepares the drawing context.
	 * @param w The width of the canvas.
	 * @param h The height of the canvas.
	 */
	public Visualisointi(int w, int h) {
		super(w, h);
		gc = this.getGraphicsContext2D();
		tyhjennaNaytto();
	}


	/**
	 * Clears the display area by filling it with a gray color.
	 * This method is intended to reset the visualization before drawing new elements.
	 */
	public void tyhjennaNaytto() {
		gc.setFill(Color.GRAY);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	/**
	 * Visualizes a new customer on the canvas.
	 * Draws a red oval representing a customer and places it in a new position each time this method is called.
	 */
	public void uusiAsiakas() {
		gc.setFill(Color.RED);
		gc.fillOval(i,j,10,10);
		
		i = (i + 10) % this.getWidth();
		//j = (j + 12) % this.getHeight();
		if (i==0) j+=10;			
	}

	/**
	 * Displays an error message on the canvas.
	 * This method is a placeholder in this context and does not have an implementation.
	 * @param s The error message to be displayed. (Currently not implemented)
	 */
	@Override
	public void naytaVirheIlmoitus(String s) {}

	/**
	 * Sets the final time and updates the visualization accordingly.
	 * This method is a placeholder in this context and does not have an implementation.
	 * @param aika The final time of the simulation.
	 * @param hcr The final happiness/customer satisfaction rating.
	 * @param i The final number of processed customers.
	 * @param palvelupisteet A map of service points for additional details. (Currently not implemented)
	 */
	@Override
	public void setLoppuaika(double aika, double hcr, int i, HashMap palvelupisteet) {}

}
