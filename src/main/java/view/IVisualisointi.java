package view;
/**
 * IVisualisointi interface outlines methods for visual representation in the simulation's user interface, including screen clearing, new customer visualization, error message display, and final time setting with additional details. It provides access to graphics context and dimensions for drawing purposes.
 */

import javafx.scene.canvas.GraphicsContext;

import java.util.HashMap;

public interface IVisualisointi {
	/**
	 * Clears the display area in the user interface, preparing it for new content.
	 */
	public void tyhjennaNaytto();

	/**
	 * Visualizes the arrival of a new customer in the simulation.
	 */
	public void uusiAsiakas();

	/**
	 * Displays an error message within the visualization area.
	 * @param s The error message to be displayed.
	 */
	public void naytaVirheIlmoitus(String s);

	/**
	 * Retrieves the GraphicsContext associated with the visualization, allowing for custom drawing.
	 * @return The GraphicsContext of the canvas used for drawing.
	 */
	GraphicsContext getGraphicsContext2D();

	/**
	 * Returns the width of the visualization area.
	 * @return The width of the visualization component.
	 */
	double getWidth();

	/**
	 * Returns the height of the visualization area.
	 * @return The height of the visualization component.
	 */
	double getHeight();

	/**
	 * Sets the final time and other simulation results to be displayed at the end of the simulation.
	 * @param d The final simulation time.
	 * @param dd The average happiness rating of customers.
	 * @param i The total number of customers served.
	 * @param hashMap Additional details or results from the simulation to be visualized.
	 */
	public void setLoppuaika(double d, double dd, int i, HashMap hashMap);

}

