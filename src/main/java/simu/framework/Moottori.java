package simu.framework;


import controller.IKontrolleriForM; // UUSI

/**
 * The `Moottori` class is an abstract simulation engine in the framework, managing time and event flow while interfacing with a controller. It controls simulation dynamics, such as timing and customer allocations, and requires derived classes to implement specific simulation functionalities.
 */
public abstract class Moottori extends Thread implements IMoottori{  // UUDET MÄÄRITYKSET
	
	private double simulointiaika = 0;
	private long viive = 0;
	
	private Kello kello;
	private int varatutProsentti;
	
	protected Tapahtumalista tapahtumalista;

	protected IKontrolleriForM kontrolleri; // UUSI


	/**
	 * Constructs a Moottori object and initializes the simulation clock and event list.
	 * @param kontrolleri The controller interface used for communication between the engine and the UI.
	 */
	public Moottori(IKontrolleriForM kontrolleri){  // UUSITTU
		
		this.kontrolleri = kontrolleri;  //UUSI

		kello = Kello.getInstance(); // Otetaan kello muuttujaan yksinkertaistamaan koodia
		
		tapahtumalista = new Tapahtumalista();
		
		// Palvelupisteet luodaan simu.model-pakkauksessa Moottorin aliluokassa
	}

	/**
	 * Sets the percentage of reserved customers in the simulation.
	 * @param varatutProsentti The percentage of customers that are considered reserved.
	 */
	@Override
	public void setVaratutAsiakkaat(int varatutProsentti) {
		this.varatutProsentti = varatutProsentti;
	}

	/**
	 * Sets the total simulation time.
	 * @param aika The duration for the simulation.
	 */
	@Override
	public void setSimulointiaika(double aika) {
		simulointiaika = aika;
	}

	/**
	 * Sets the delay between each step in the simulation.
	 * @param viive The time to delay in milliseconds.
	 */
	@Override // UUSI
	public void setViive(long viive) {
		this.viive = viive;
	}

	/**
	 * Gets the current delay between simulation steps.
	 * @return The delay time in milliseconds.
	 */
	@Override // UUSI 
	public long getViive() {
		return viive;
	}

	/**
	 * Gets the percentage of reserved customers.
	 * @return The percentage of reserved customers.
	 */
	public int getVaratutProsentti(){ return  varatutProsentti; }

	/**
	 * The main simulation loop that manages time and event processing.
	 */
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
		
	}


	/**
	 * Processes all B-phase (business logic) events occurring at the current simulation time.
	 */
	private void suoritaBTapahtumat(){
		while (tapahtumalista.getSeuraavanAika() == kello.getAika()){
			suoritaTapahtuma(tapahtumalista.poista());
		}
	}

	/**
	 * Attempts to process C-phase (conclusion or continuation) events based on current conditions.
	 * This method should be implemented in subclasses.
	 */
	protected abstract void yritaCTapahtumat();


	/**
	 * Retrieves the current simulation time from the event list.
	 * @return The current simulation time.
	 */
	private double nykyaika(){
		return tapahtumalista.getSeuraavanAika();
	}


	/**
	 * Determines whether the simulation should continue running.
	 * @return True if the current time is less than the total simulation time; false otherwise.
	 */
	private boolean simuloidaan(){
		Trace.out(Trace.Level.INFO, "Kello on: " + kello.getAika());
		return kello.getAika() < simulointiaika;
	}

	/**
	 * Implements a delay between each simulation step.
	 */
	private void viive() { // UUSI
		Trace.out(Trace.Level.INFO, "Viive " + viive);
		try {
			sleep(viive);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prepares the simulation environment. This method should be implemented in subclasses.
	 */
	protected abstract void alustukset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa

	/**
	 * Processes a single simulation event. This method should be implemented in subclasses.
	 * @param t The event to be processed.
	 */
	protected abstract void suoritaTapahtuma(Tapahtuma t);  // Määritellään simu.model-pakkauksessa Moottorin aliluokassa


	/**
	 * Finalizes the simulation and processes results. This method should be implemented in subclasses.
	 */
	protected abstract void tulokset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
}