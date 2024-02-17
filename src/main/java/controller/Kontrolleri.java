package controller;

import javafx.application.Platform;
import simu.framework.IMoottori;
import simu.framework.Moottori;
import simu.model.OmaMoottori;
import view.ISimulaattorinUI;

public class Kontrolleri implements IKontrolleriForM, IKontrolleriForV{   // UUSI
	
	private IMoottori moottori; 
	private ISimulaattorinUI ui;
	
	public Kontrolleri(ISimulaattorinUI ui) {
		this.ui = ui;
		
	}

	
	// Moottorin ohjausta:
		
	@Override
	public void kaynnistaSimulointi() {
		boolean noErrors = true;
		moottori = new OmaMoottori(this); // luodaan uusi moottorisäie jokaista simulointia varten
		try {
			moottori.setSimulointiaika(ui.getAika());
		}catch (NumberFormatException e) {
			e.printStackTrace();
			naytaVirheIlmoitus("Virheellinen aika");
			noErrors = false;
		}
		try {
			moottori.setViive(ui.getViive());
		}catch (NumberFormatException e) {
			e.printStackTrace();
			naytaVirheIlmoitus("Virheellinen viive");
			noErrors = false;
		}
		try {
			moottori.setVaratutAsiakkaat(ui.getVaratutAsiakkaat());
		}catch (NumberFormatException e) {
			e.printStackTrace();
			naytaVirheIlmoitus("Virheellinen varattujen aikojen prosentti (Vain 0-100%)");
			noErrors = false;
		}
		if (noErrors) {
			ui.getVisualisointi().tyhjennaNaytto();
			((Thread) moottori).start();
		}
	}
	
	@Override
	public void hidasta() { // hidastetaan moottorisäiettä
		moottori.setViive((long)(moottori.getViive()*1.10));
	}

	@Override
	public void nopeuta() { // nopeutetaan moottorisäiettä
		moottori.setViive((long)(moottori.getViive()*0.9));
	}


	// Simulointitulosten välittämistä käyttöliittymään.
	// Koska FX-ui:n päivitykset tulevat moottorisäikeestä, ne pitää ohjata JavaFX-säikeeseen:
		
	@Override
	public void naytaLoppuaika(double aika, double happyCustomer) {
		Platform.runLater(()->ui.setLoppuaika(aika, happyCustomer));
	}
	private void naytaVirheIlmoitus(String virhe) {
		Platform.runLater(() -> ui.naytaVirheIlmoitus(virhe));
	}

	
	@Override
	public void visualisoiAsiakas() {
		Platform.runLater(new Runnable(){
			public void run(){
				ui.getVisualisointi().uusiAsiakas();
			}
		});
	}



}
