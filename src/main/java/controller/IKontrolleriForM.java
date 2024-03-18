package controller;

import simu.model.Palvelupiste;

import java.util.ArrayList;
import java.util.HashMap;

public interface IKontrolleriForM {

    /**
     * Displays the final simulation time, customer satisfaction rating, total number of customers,
     * and detailed statistics for each service point.
     * @param aika The final time of the simulation.
     * @param happyRating The overall customer satisfaction rating.
     * @param asiakkaat The total number of customers served.
     * @param palvelupisteet A map containing lists of service points, associated with their respective statistics.
     */
    public void naytaLoppuaika(double aika, double happyRating, int asiakkaat, HashMap<String, ArrayList<Palvelupiste>> palvelupisteet);


    /**
     * Visualizes a new customer in the simulation. This method should be implemented to update the simulation UI
     * to reflect the arrival of a new customer.
     */
    public void visualisoiAsiakas();

}
