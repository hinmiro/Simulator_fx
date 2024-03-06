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
	
	public Kontrolleri(UusiGuiKontolleri ui) {
		this.ui = ui;
		
	}

	
	// Moottorin ohjausta:
		
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
	@Override
	public void initializeData() {
		moottori.initializeData();
	}
	@Override
	public void hidasta() { // hidastetaan moottorisäiettä
		moottori.setViive((long)(moottori.getViive()*1.10));
	}

	@Override
	public void nopeuta() { // nopeutetaan moottorisäiettä
		moottori.setViive((long)(moottori.getViive()*0.9));
	}

	@Override
	public void nopeutaHidasta(double value) {
		moottori.setViive((long)Math.round(value));
	}

	@Override
	public void lisaaPalvelu(String lisattavaPiste) {
		((OmaMoottori) moottori).addPalvelu(lisattavaPiste);
	}
	@Override
	public void poistaPalvelu(String poistettavaPiste) {
		((OmaMoottori) moottori).deletePalvelu(poistettavaPiste);
	}

	@Override
	public void naytaLoppuaika(double aika, double happyCustomer, int asiakkaat, HashMap<String, ArrayList<Palvelupiste>> palvelupisteet) {
		Platform.runLater(()->ui.getVisualisointi().setLoppuaika(aika, happyCustomer, asiakkaat, palvelupisteet));
	}
	private void naytaVirheIlmoitus(String virhe) {
		Platform.runLater(() -> ui.getVisualisointi().naytaVirheIlmoitus(virhe));
	}

	
	@Override
	public void visualisoiAsiakas() {
		Platform.runLater(new Runnable(){
			public void run(){
				ui.getVisualisointi().uusiAsiakas();
			}
		});
	}

	@Override
	public void nollaaKello() {
		Kello.getInstance().setAika(0);
	}
}
