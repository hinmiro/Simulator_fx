package simu.framework;

/**
 * The `Kello` class is a singleton that represents a clock in the simulation framework. It tracks and manages simulation time.
 */
public class Kello {

	private double aika;
	private static Kello instanssi;

	/**
	 * Private constructor to prevent instantiation outside of the class.
	 * Initializes the simulation clock time to 0.
	 */
	private Kello(){
		aika = 0;
	}

	/**
	 * Returns the single instance of the Kello class, creating it if it doesn't already exist.
	 * @return The singleton instance of the Kello class.
	 */
	public static Kello getInstance(){
		if (instanssi == null){
			instanssi = new Kello();	
		}
		return instanssi;
	}

	/**
	 * Sets the current time of the simulation clock.
	 * @param aika The new time to set the clock to.
	 */
	public void setAika(double aika){
		this.aika = aika;
	}

	/**
	 * Retrieves the current time of the simulation clock.
	 * @return The current simulation time.
	 */
	public double getAika(){
		return aika;
	}
}
