package simu.framework;

/**
 * The `IMoottori` interface defines methods for configuring and initializing the simulation engine, including setting simulation time, delay intervals, the percentage of reserved customers, and initializing simulation data. This interface is utilized by the controller to manage simulation settings.
 */
public interface IMoottori { // UUSI
		

	/**
	 * Sets the total simulation time.
	 * @param aika The simulation time in units (usually seconds or minutes).
	 */
	public void setSimulointiaika(double aika);

	/**
	 * Sets the delay interval between simulation steps.
	 * @param aika The delay time in milliseconds.
	 */
	public void setViive(long aika);

	/**
	 * Retrieves the current delay interval between simulation steps.
	 * @return The current delay time in milliseconds.
	 */
	public long getViive();

	/**
	 * Sets the percentage of customers that are considered reserved.
	 * @param prosentti The percentage of reserved customers (0-100).
	 */
	public void setVaratutAsiakkaat(int prosentti);

	/**
	 * Initializes or resets the simulation data to prepare for a new simulation run.
	 */
	public void initializeData();
}
