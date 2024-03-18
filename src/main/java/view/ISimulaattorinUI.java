package view;

/**
 * ISimulaattorinUI interface defines methods for interaction between the user interface and the simulation controller, including retrieving simulation parameters, displaying error messages, managing service points, and presenting final results and visualization components.
 */
public interface ISimulaattorinUI {
	
	// Kontrolleri tarvitsee syötteitä, jotka se välittää Moottorille
	/**
	 * Retrieves the simulation time set by the user.
	 * @return The simulation time.
	 */
	public double getAika();
	/**
	 * Retrieves the delay between simulation steps set by the user.
	 * @return The delay time in milliseconds.
	 */
	public long getViive();
	/**
	 * Retrieves the percentage of reserved customers set by the user.
	 * @return The percentage of reserved customers.
	 */
	public int getVaratutAsiakkaat();
	/**
	 * Displays an error message to the user.
	 * @param s The error message to be displayed.
	 */
	public void naytaVirheIlmoitus(String s);
	/**
	 * Adds a new service point to the simulation based on user input.
	 * @param lisattavaPiste The type of service point to add.
	 */
	public void lisaaUusiPalvelupiste(String lisattavaPiste);
	/**
	 * Removes a service point from the simulation based on user selection.
	 * @param poistettavaPiste The type of service point to remove.
	 */
	public void poistaPalvelupiste(String poistettavaPiste);


	/**
	 * Displays the final time, happiness rating, and other results at the end of the simulation.
	 * @param aika The final time of the simulation.
	 * @param happyCustomer The overall happiness rating of customers.
	 */
	public void setLoppuaika(double aika, double happyCustomer);

	/**
	 * Retrieves the visualization component of the simulation interface.
	 * @return The visualization component used for displaying simulation data.
	 */
	public IVisualisointi getVisualisointi();

}
