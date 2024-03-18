package controller;

import simu.model.Palvelupiste;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The IKontrolleriForM interface defines the methods that a controller for the simulator should implement.
 * It provides methods for displaying the end time, visualizing a customer, displaying the simulation time,
 * and visualizing and unvisualizing a service point.
 */
public interface IKontrolleriForM {

    /**
     * Displays the end time, the happiness rating, the number of customers, and the service points.
     * @param aika the end time
     * @param happyRating the happiness rating
     * @param asiakkaat the number of customers
     * @param palvelupisteet the service points
     */
    public void naytaLoppuaika(double aika, double happyRating, int asiakkaat, HashMap<String, ArrayList<Palvelupiste>> palvelupisteet);

    /**
     * Visualizes a new customer.
     */
    public void visualisoiAsiakas();

    /**
     * Displays the simulation time.
     */
    void naytaSimulointiAika();

    /**
     * Visualizes a new service point.
     * @param palveluTyyppi the type of the service point
     * @param size the size of the service point
     */
    public void visualisoiPalvelupiste(String palveluTyyppi, int size);

    /**
     * Unvisualizes a service point.
     * @param type the type of the service point
     * @param size the size of the service point
     */
    void unvisualisoiPalvelupiste(String type, int size);
}
