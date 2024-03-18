package controller;

/**
 * The IKontrolleriForV interface defines the methods that a controller for the simulator should implement for the view.
 * It provides methods for starting the simulation, speeding up and slowing down the simulation, resetting the clock,
 * adding and removing services, adjusting the speed, and initializing data.
 */
public interface IKontrolleriForV {

    /**
     * Starts the simulation.
     */
    public void kaynnistaSimulointi();

    /**
     * Speeds up the simulation.
     */
    public void nopeuta();

    /**
     * Slows down the simulation.
     */
    public void hidasta();

    /**
     * Resets the clock.
     */
    public void nollaaKello();

    /**
     * Adds a new service.
     * @param lisattavaPiste the service to be added
     */
    public void lisaaPalvelu(String lisattavaPiste);

    /**
     * Removes a service.
     * @param poistettavaPiste the service to be removed
     */
    public void poistaPalvelu(String poistettavaPiste);

    /**
     * Adjusts the speed of the simulation.
     * @param value the value to adjust the speed by
     */
    public void nopeutaHidasta(double value);

    /**
     * Initializes the data for the simulation.
     */
    public void initializeData();
}
