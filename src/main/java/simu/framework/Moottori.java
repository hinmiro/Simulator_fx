package simu.framework;


import controller.IKontrolleriForM; // UUSI
import view.UusiGuiKontolleri;

import java.sql.SQLOutput;

/**
 * The `Moottori` class is an abstract simulation engine in the framework, managing time and event flow while interfacing with a controller. It controls simulation dynamics, such as timing and customer allocations, and requires derived classes to implement specific simulation functionalities.
 */
public abstract class Moottori extends Thread implements IMoottori{  // UUDET MÄÄRITYKSET
	private UusiGuiKontolleri uusiGuiKontolleri;
	
	private double simulointiaika = 0;
	private long viive = 0;
	
	private Kello kello;
	private int varatutProsentti;
	
	protected Tapahtumalista tapahtumalista;

	protected IKontrolleriForM kontrolleri; // UUSI

	

	public Moottori(IKontrolleriForM kontrolleri, UusiGuiKontolleri uusiGuiKontolleri){  // UUSITTU
		
		this.kontrolleri = kontrolleri;  //UUSI
		this.uusiGuiKontolleri = uusiGuiKontolleri;

		kello = Kello.getInstance(); // Otetaan kello muuttujaan yksinkertaistamaan koodia
		
		tapahtumalista = new Tapahtumalista();
		
		// Palvelupisteet luodaan simu.model-pakkauksessa Moottorin aliluokassa 
		
		
	}

	@Override
	public void setVaratutAsiakkaat(int varatutProsentti) {
		this.varatutProsentti = varatutProsentti;
	}

	@Override
	public void setSimulointiaika(double aika) {
		simulointiaika = aika;
	}
	
	@Override // UUSI
	public void setViive(long viive) {
		this.viive = viive;
	}
	
	@Override // UUSI 
	public long getViive() {
		return viive;
	}

	public int getVaratutProsentti(){ return  varatutProsentti; }
	@Override
	public void run(){ // Entinen aja()
		alustukset(); // luodaan mm. ensimmäinen tapahtuma
		while (simuloidaan()){
			viive(); // UUSI
			kello.setAika(nykyaika());
			suoritaBTapahtumat();
			yritaCTapahtumat();
		}
		tulokset();
		uusiGuiKontolleri.stopRotate(); // Pysäyttää dollarin merkin pyörimisen
		
	}
	
	private void suoritaBTapahtumat(){
		while (tapahtumalista.getSeuraavanAika() == kello.getAika()){
			suoritaTapahtuma(tapahtumalista.poista());
		}
	}

	protected abstract void yritaCTapahtumat();

	
	private double nykyaika(){
		return tapahtumalista.getSeuraavanAika();
	}
	
	private boolean simuloidaan(){
		Trace.out(Trace.Level.INFO, "Kello on: " + kello.getAika());
		return kello.getAika() < simulointiaika;
	}
	
			
	private void viive() { // UUSI
		Trace.out(Trace.Level.INFO, "Viive " + viive);
		try {
			sleep(viive);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected abstract void alustukset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
	protected abstract void suoritaTapahtuma(Tapahtuma t);  // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
	protected abstract void tulokset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
}