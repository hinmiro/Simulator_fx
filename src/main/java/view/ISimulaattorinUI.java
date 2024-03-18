package view;

/**
 * The ISimulaattorinUI interface defines the methods that a user interface for the simulator should implement.
 * It provides methods for getting user inputs, displaying error messages, adding and removing service points,
 * and getting the visualization object.
 */
public interface ISimulaattorinUI {

	/**
	 * Gets the simulation time.
	 * @return the simulation time
	 */
	public double getAika();

	/**
	 * Gets the delay time.
	 * @return the delay time
	 */
	public long getViive();

	/**
	 * Gets the number of reserved customers.
	 * @return the number of reserved customers
	 */
	public int getVaratutAsiakkaat();

	/**
	 * Displays an error message.
	 * @param s the error message to be displayed
	 */
	public void naytaVirheIlmoitus(String s);

	/**
	 * Adds a new service point.
	 * @param lisattavaPiste the service point to be added
	 */
	public void lisaaUusiPalvelupiste(String lisattavaPiste);

	/**
	 * Removes a service point.
	 * @param poistettavaPiste the service point to be removed
	 */
	public void poistaPalvelupiste(String poistettavaPiste);

	/**
	 * Sets the end time and the number of happy customers.
	 * @param aika the end time
	 * @param happyCustomer the number of happy customers
	 */
	public void setLoppuaika(double aika, double happyCustomer);

	/**
	 * Gets the visualization object.
	 * @return the visualization object
	 */
	public IVisualisointi getVisualisointi();

}
