package view;


import javafx.scene.canvas.GraphicsContext;

import java.util.HashMap;

/**
 * The IVisualisointi interface defines the methods that a visualization for the simulator should implement.
 * It provides methods for clearing the display, managing service points, displaying simulation time and error messages,
 * and getting the GraphicsContext object.
 */
public interface IVisualisointi {

	/**
	 * Clears the display.
	 */
	public void tyhjennaNaytto();

	/**
	 * Adds a new service point.
	 * @param palveluTyyppi the type of the service point
	 * @param size the size of the service point
	 */
	void uusiPalveluPiste(String palveluTyyppi, int size);

	/**
	 * Removes a service point.
	 * @param palveluTyyppi the type of the service point
	 * @param size the size of the service point
	 */
	void deletePalveluPiste(String palveluTyyppi, int size);

	/**
	 * Displays the service points.
	 */
	void showPalvelupisteet();

	/**
	 * Visualizes a new customer.
	 */
	public void uusiAsiakas();

	/**
	 * Displays the simulation time.
	 */
	public void naytaSimulointiAika();

	/**
	 * Displays an error message.
	 * @param s the error message to be displayed
	 */
	public void naytaVirheIlmoitus(String s);

	/**
	 * Gets the GraphicsContext object.
	 * @return the GraphicsContext object
	 */
	GraphicsContext getGraphicsContext2D();

	/**
	 * Gets the width of the display.
	 * @return the width of the display
	 */
	double getWidth();

	/**
	 * Gets the height of the display.
	 * @return the height of the display
	 */
	double getHeight();

	/**
	 * Sets the end time, the number of happy customers, and the service point data.
	 * @param d the end time
	 * @param dd the number of happy customers
	 * @param i the number of service points
	 * @param hashMap the service point data
	 */
	public void setLoppuaika(double d, double dd, int i, HashMap hashMap);
}

