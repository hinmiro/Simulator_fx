package simu.model;

import simu.framework.*;
import java.util.LinkedList;
import eduni.distributions.ContinuousGenerator;


/**
 * The Palvelupiste class represents a service point in the simulation model, managing customer queues (regular and reserved),
 * calculating service times using a continuous distribution generator, and scheduling new service events. It handles adding
 * and removing customers from queues and tracks service metrics.
 */
public class Palvelupiste {
	private final LinkedList<Asiakas> jono = new LinkedList<>(); // Tietorakennetoteutus
	private final LinkedList<Asiakas> varattuJono = new LinkedList<>();
	private final ContinuousGenerator generator;
	private final Tapahtumalista tapahtumalista;
	private final TapahtumanTyyppi skeduloitavanTapahtumanTyyppi;

	//JonoStrategia strategia; //optio: asiakkaiden järjestys

	private boolean varattu = false;
	private double palvelunKesto;
	private int palveluAsiakkaat = 0;
	private String nimi;


	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi, String nimi) {
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		this.nimi = nimi;
	}

	/**
	 * Adds a customer to the queue.
	 * @param a The customer to be added.
	 */
	public void lisaaJonoon(Asiakas a) {   // Jonon 1. asiakas aina palvelussa
		jono.add(a);
	}

	/**
	 * Removes and returns the customer currently being served.
	 * @return The customer that was being served.
	 */
	public Asiakas otaJonosta() {  // Poistetaan palvelussa ollut
		varattu = false;
		Asiakas.setServiceDone(Asiakas.getServiceDone() + 1);
		return jono.poll();
	}

	/**
	 * Adds a reserved customer to the priority queue.
	 * @param a The reserved customer to be added.
	 */
	public void lisaaVarattuJonoon(Asiakas a) {
		varattuJono.add(a);
	}

	/**
	 * Adds a customer next in line in the queue.
	 * @param a The customer to be added next in line.
	 */
	public void lisaaJononSeuraavaksi(Asiakas a) {
		jono.add(1, a);
	}

	/**
	 * Removes and returns the reserved customer currently being served.
	 * @return The reserved customer that was being served.
	 */
	public Asiakas otaVarattuJonosta() {
		varattu = false;
		Asiakas.setServiceDone(Asiakas.getServiceDone() + 1);
		return varattuJono.poll();
	}

	/**
	 * Gets the length of the queue.
	 * @return The number of customers in the queue.
	 */
	public int getJononPituus() {
		return jono.size();
	}

	/**
	 * Gets the length of the reserved queue.
	 * @return The number of reserved customers in the queue.
	 */
	public int getVaratunJononPituus() {
		return varattuJono.size();
	}

	/**
	 * Initiates service for the next customer in line.
	 * @param varattuAsiakas Indicates if the service is for a reserved customer.
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
	 * Checks if the service point is currently occupied.
	 * @return True if the service point is occupied, otherwise false.
	 */
	public boolean onVarattu() {
		return varattu;
	}

	/**
	 * Checks if there are any customers in the queue.
	 * @return True if there are customers in the queue, otherwise false.
	 */
	public boolean onJonossa() {
		return !jono.isEmpty();
	}

	/**
	 * Checks if there are any reserved customers in the queue.
	 * @return True if there are reserved customers in the queue, otherwise false.
	 */
	public boolean onVarattuJonossa() {
		return !varattuJono.isEmpty();
	}

	/**
	 * Checks if a specific customer is in the queue.
	 * @param asiakas The customer to check for in the queue.
	 * @return True if the customer is in the queue, otherwise false.
	 */
	public boolean checkForCustomerJono(Asiakas asiakas) {
		return jono.contains(asiakas);
	}

	/**
	 * Checks if a specific reserved customer is in the priority queue.
	 * @param asiakas The reserved customer to check for in the queue.
	 * @return True if the reserved customer is in the queue, otherwise false.
	 */
	public boolean checkForCustomerVarattuJono(Asiakas asiakas) {
		return varattuJono.contains(asiakas);
	}

	/**
	 * Gets the number of customers served.
	 * @return The number of customers served.
	 */
	public int getPalveluAsiakkaat() {
		return palveluAsiakkaat;
	}

	/**
	 * Gets the average service time.
	 * @return The average time taken to service a customer.
	 */
	public double getPalvelunkesto() {
		return palvelunKesto/palveluAsiakkaat;
	}

	/**
	 * Gets the name of the service point.
	 * @return The name of the service point.
	 */
	public String getNimi() {
		return this.nimi;
	}

	/**
	 * Provides a string representation of the service point.
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

