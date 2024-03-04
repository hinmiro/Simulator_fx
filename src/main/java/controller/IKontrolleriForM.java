package controller;

import simu.model.Palvelupiste;

import java.util.ArrayList;
import java.util.HashMap;

public interface IKontrolleriForM {

    // Rajapinta, joka tarjotaan moottorille:

    public void naytaLoppuaika(double aika, double happyRating, int asiakkaat, HashMap<String, ArrayList<Palvelupiste>> palvelupisteet);
    public void visualisoiAsiakas();

}
