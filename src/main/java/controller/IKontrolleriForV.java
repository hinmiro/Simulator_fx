package controller;

public interface IKontrolleriForV {

    // Rajapinta, joka tarjotaan  käyttöliittymälle:

    /**
     * Starts the simulation process. This method should initialize and begin the simulation.
     */

    public void kaynnistaSimulointi();

    /**
     * Increases the speed of the simulation. This method is intended to reduce the time between events.
     */
    public void nopeuta();

    /**
     * Decreases the speed of the simulation. This method is intended to increase the time between events.
     */
    public void hidasta();

    /**
     * Resets the simulation clock. This method sets the simulation time back to zero.
     */
    public void nollaaKello();

    /**
     * Adds a new service point to the simulation.
     * @param lisattavaPiste The identifier of the service point to be added.
     */
    public void lisaaPalvelu(String lisattavaPiste);

    /**
     * Removes an existing service point from the simulation.
     * @param poistettavaPiste The identifier of the service point to be removed.
     */
    public void poistaPalvelu(String poistettavaPiste);

    /**
     * Adjusts the speed of the simulation dynamically based on a given value.
     * @param value The new speed value for the simulation; this could influence the delay between events.
     */
    public void nopeutaHidasta(double value);

    /**
     * Initializes the data required for the simulation. This method sets up initial conditions or resets them.
     */
    public void initializeData();
}
