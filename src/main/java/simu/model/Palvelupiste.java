package simu.model;

import simu.framework.*;
import java.util.LinkedList;
import eduni.distributions.ContinuousGenerator;

public class Palvelupiste {
	/**
	 * A queue of customers (Asiakas) waiting for service.
	 */
	private final LinkedList<Asiakas> jono = new LinkedList<>();

	/**
	 * A queue of customers (Asiakas) who have a reservation.
	 */
	private final LinkedList<Asiakas> varattuJono = new LinkedList<>();

	/**
	 * A generator for continuous random variables, used for simulating various aspects of the service process.
	 */
	private final ContinuousGenerator generator;

	/**
	 * A list of events (Tapahtuma) that are scheduled to occur in the simulation.
	 */
	private final Tapahtumalista tapahtumalista;

	/**
	 * The type of event (TapahtumanTyyppi) that is scheduled to occur next.
	 */
	private final TapahtumanTyyppi skeduloitavanTapahtumanTyyppi;

	/**
	 * A flag indicating whether the service point is currently occupied by a customer.
	 */
	private boolean varattu = false;

	/**
	 * The duration of the current service.
	 */
	private double palvelunKesto;

	/**
	 * The number of customers that have been served.
	 */
	private int palveluAsiakkaat = 0;

	/**
	 * The name of the service point.
	 */
	private String nimi;

	/**
	 * The utilization rate of the service point.
	 */
	private double kayttoaste;

	/**
	 * The time at which the service point started operating.
	 */
	private double aloitus;

	/**
	 * The time at which the service point stopped operating.
	 */
	private double lopetus;

	/**
	 * Constructor for the Palvelupiste class.
	 * @param generator A generator for continuous random variables.
	 * @param tapahtumalista A list of events that are scheduled to occur in the simulation.
	 * @param tyyppi The type of event that is scheduled to occur next.
	 * @param nimi The name of the service point.
	 */
	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi, String nimi) {
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		this.nimi = nimi;
		aloitus = Kello.getInstance().getAika();
	}

	//Asiakas(boolean varattuAika)

	/**
	 * Adds a customer to the queue.
	 * @param a The customer to be added to the queue.
	 */
	public void lisaaJonoon(Asiakas a) {   // Jonon 1. asiakas aina palvelussa
		jono.add(a);
	}


	/**
	 * Removes a customer from the queue.
	 * @return The customer that was removed from the queue.
	 */
	public Asiakas otaJonosta() {  // Poistetaan palvelussa ollut
		varattu = false;
		Asiakas.setServiceDone(Asiakas.getServiceDone() + 1);
		return jono.poll();
	}


	/**
	 * Adds a customer to the reserved queue.
	 * @param a The customer to be added to the reserved queue.
	 */
	public void lisaaVarattuJonoon(Asiakas a) {
		varattuJono.add(a);
	}

	/**
	 * Adds a customer next in the queue.
	 * @param a The customer to be added next in the queue.
	 */
	public void lisaaJononSeuraavaksi(Asiakas a) {
		jono.add(1, a);
	}

	/**
	 * Removes a customer from the reserved queue.
	 * @return The customer that was removed from the reserved queue.
	 */
	public Asiakas otaVarattuJonosta() {
		varattu = false;
		Asiakas.setServiceDone(Asiakas.getServiceDone() + 1);
		return varattuJono.poll();
	}

	/**
	 * Gets the length of the queue.
	 * @return The length of the queue.
	 */
	public int getJononPituus() {
		return jono.size();
	}

	/**
	 * Gets the length of the reserved queue.
	 * @return The length of the reserved queue.
	 */
	public int getVaratunJononPituus() {
		return varattuJono.size();
	}


	/**
	 * Starts a new service for a customer.
	 * @param varattuAsiakas A boolean indicating whether the customer has a reservation.
	 */
	public void aloitaPalvelu(boolean varattuAsiakas) {  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
		palvelunKesto = Kello.getInstance().getAika();
		if (varattuAsiakas)
			Trace.out(Trace.Level.INFO, "Aloitetaan uusi "+ skeduloitavanTapahtumanTyyppi +" asiakkaalle " + varattuJono.peek().getId() + " reservation: true");
		else
			Trace.out(Trace.Level.INFO, "Aloitetaan uusi "+ skeduloitavanTapahtumanTyyppi +" asiakkaalle " + jono.peek().getId() + " reservation: false");


		varattu = true;
		palveluAsiakkaat++;
		double palveluaika = generator.sample();
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika));
		palvelunKesto += Kello.getInstance().getAika() + palveluaika - palvelunKesto;
	}


	/**
	 * Checks if the service point is reserved.
	 * @return A boolean indicating whether the service point is reserved.
	 */
	public boolean onVarattu() {
		return varattu;
	}


	/**
	 * Checks if there are customers in the queue.
	 * @return A boolean indicating whether there are customers in the queue.
	 */
	public boolean onJonossa() {
		return !jono.isEmpty();
	}

	/**
	 * Checks if there are customers in the reserved queue.
	 * @return A boolean indicating whether there are customers in the reserved queue.
	 */
	public boolean onVarattuJonossa() {
		return !varattuJono.isEmpty();
	}

	/**
	 * Checks if a specific customer is in the queue.
	 * @param asiakas The customer to check for in the queue.
	 * @return A boolean indicating whether the customer is in the queue.
	 */
	public boolean checkForCustomerJono(Asiakas asiakas) {
		return jono.contains(asiakas);
	}

	/**
	 * Checks if a specific customer is in the reserved queue.
	 * @param asiakas The customer to check for in the reserved queue.
	 * @return A boolean indicating whether the customer is in the reserved queue.
	 */
	public boolean checkForCustomerVarattuJono(Asiakas asiakas) {
		return varattuJono.contains(asiakas);
	}

	/**
	 * Gets the number of customers that have been served.
	 * @return The number of customers that have been served.
	 */
	public int getPalveluAsiakkaat() {
		return palveluAsiakkaat;
	}

	/**
	 * Gets the duration of the service.
	 * @return The duration of the service.
	 */
	public double getPalvelunkesto() {
		double keskiaika = palvelunKesto/palveluAsiakkaat;
		if (Double.isNaN(keskiaika))
			return 0;
		return keskiaika;
	}

	/**
	 * Gets the name of the service point.
	 * @return The name of the service point.
	 */
	public String getNimi() {
		return this.nimi;
	}

	/**
	 * Sets the utilization rate of the service point.
	 */
	public void setKayttoaste() {
		lopetus = Kello.getInstance().getAika();
		kayttoaste = palvelunKesto / (lopetus - aloitus);
	}

	/**
	 * Gets the utilization rate of the service point.
	 * @return The utilization rate of the service point.
	 */
	public double getKayttoAste() {
		return kayttoaste;
	}

	/**
	 * Returns a string representation of the service point.
	 * @return A string representation of the service point.
	 */
	@Override
	public String toString() {
		return "Palvelupiste{" +
				"jono size=" + jono.size() +
				", varattuJono size=" + varattuJono.size() +
				", varattu=" + varattu +
				'}';
	}
}

