package controller;

import javafx.application.Platform;
import simu.framework.IMoottori;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.model.OmaMoottori;
import simu.model.Palvelupiste;
import view.ISimulaattorinUI;
import view.UusiGui;
import view.UusiGuiKontolleri;

import java.util.ArrayList;
import java.util.HashMap;

/** The Kontrolleri class in JavaFX manages the simulation's operations and UI interactions, handles user inputs, adjusts simulation speed, and displays results and errors.
 * @author joku
 * @version 1234
 */
public class Kontrolleri implements IKontrolleriForM, IKontrolleriForV{   // UUSI
	
	private IMoottori moottori; 
	private UusiGuiKontolleri ui;

	/**
	 * Constructs a new controller instance.
	 * @param ui The UI controller to be used.
	 */
	public Kontrolleri(UusiGuiKontolleri ui) {
		this.ui = ui;
		
	}

	
	/**
	 * Starts the simulation based on user inputs from the UI.
	 */
	@Override
	public void kaynnistaSimulointi() {
		boolean noErrors = true;
		moottori = new OmaMoottori(this); // luodaan uusi moottorisäie jokaista simulointia varten
		try {
			moottori.setSimulointiaika(Double.parseDouble(ui.getAika()));
		}catch (NumberFormatException e) {
			e.printStackTrace();
			naytaVirheIlmoitus("Virheellinen aika");
			noErrors = false;
		}
		try {
			moottori.setViive(Long.parseLong(ui.getViive()));
		}catch (NumberFormatException e) {
			e.printStackTrace();
			naytaVirheIlmoitus("Virheellinen viive");
			noErrors = false;
		}
		try {
			moottori.setVaratutAsiakkaat(Integer.parseInt(ui.getVaratutAsiakkaat()));
		}catch (NumberFormatException e) {
			e.printStackTrace();
			naytaVirheIlmoitus("Virheellinen prosentti (Vain 0-100%)");
			noErrors = false;
		}
		if (noErrors) {
			ui.getVisualisointi().tyhjennaNaytto();
			((Thread) moottori).start();
		}
	}

	/**
	 * Initializes data for the simulation engine.
	 */
	@Override
	public void initializeData() {
		moottori.initializeData();
	}

	/**
	 * Slows down the simulation speed.
	 */
	@Override
	public void hidasta() { // hidastetaan moottorisäiettä
		moottori.setViive((long)(moottori.getViive()*1.10));
	}

	/**
	 * Speeds up the simulation.
	 */
	@Override
	public void nopeuta() { // nopeutetaan moottorisäiettä
		moottori.setViive((long)(moottori.getViive()*0.9));
	}

	/**
	 * Dynamically adjusts the simulation speed based on a value.
	 * @param value The new speed value.
	 */
	@Override
	public void nopeutaHidasta(double value) {
		moottori.setViive((long)Math.round(value));
	}

	/**
	 * Adds a service point to the simulation.
	 * @param lisattavaPiste The service point to add.
	 */
	@Override
	public void lisaaPalvelu(String lisattavaPiste) {
		((OmaMoottori) moottori).addPalvelu(lisattavaPiste);
	}

	/**
	 * Removes a service point from the simulation.
	 * @param poistettavaPiste The service point to remove.
	 */
	@Override
	public void poistaPalvelu(String poistettavaPiste) {
		((OmaMoottori) moottori).deletePalvelu(poistettavaPiste);
	}


	/**
	 * Displays the final simulation time, happiness score, and customer count.
	 * @param aika The final simulation time.
	 * @param happyCustomer The happiness score.
	 * @param asiakkaat The customer count.
	 * @param palvelupisteet The service points.
	 */
	@Override
	public void naytaLoppuaika(double aika, double happyCustomer, int asiakkaat, HashMap<String, ArrayList<Palvelupiste>> palvelupisteet) {
		Platform.runLater(()->ui.getVisualisointi().setLoppuaika(aika, happyCustomer, asiakkaat, palvelupisteet));
	}

	/**
	 * Displays a given error message on the UI.
	 * @param virhe The error message.
	 */
	private void naytaVirheIlmoitus(String virhe) {
		Platform.runLater(() -> ui.getVisualisointi().naytaVirheIlmoitus(virhe));
	}

	/**
	 * Visualizes a new customer in the UI.
	 */
	@Override
	public void visualisoiAsiakas() {
		Platform.runLater(new Runnable(){
			public void run(){
				ui.getVisualisointi().uusiAsiakas();
			}
		});
	}

	/**
	 * Resets the simulation clock.
	 */
	@Override
	public void nollaaKello() {
		Kello.getInstance().setAika(0);
	}
}
