package simu.model;

import simu.framework.Kello;
import simu.framework.Trace;

import java.util.Random;

/**
 * The `Asiakas` class models a customer in the simulation, tracking their arrival and departure times, goals, and satisfaction. It generates statistical data such as average service time and overall happiness rating, supporting dynamic customer attributes and performance reporting.
 */


// TODO:
// Asiakas koodataan simulointimallin edellyttämällä tavalla (data!)
public class Asiakas{
	private static Random random = new Random();
	private double saapumisaika;
	private double poistumisaika;
	private int id;
	private static int i = 1;
	private static long sum = 0;
	private static double totalTime = 0;
	private static int totalCustomers = 0;
	private static int serviceDone = 0;
	private static double happyRating = 0;
	private boolean onVarattu;
	private byte tavoite;

	/**
	 * Constructor for creating a new customer instance.
	 * @param onVarattu Indicates whether the customer has a reservation.
	 */
	public Asiakas(boolean onVarattu) {
		id = i++;
		this.onVarattu = onVarattu;
		saapumisaika = Kello.getInstance().getAika();
		if (!onVarattu)
			tavoite = (byte) random.nextInt(3);
		else
			tavoite = (byte) (random.nextInt(2) + 1);
		Trace.out(Trace.Level.INFO, "Uusi asiakas nro " + id + " saapui klo "+saapumisaika);
	}

	/**
	 * Sets the total number of services completed.
	 * @param serviceDone The number of services that have been completed.
	 */
	public static void setServiceDone(int serviceDone) {
		Asiakas.serviceDone = serviceDone;
	}

	/**
	 * Gets the total number of services completed.
	 * @return The number of services completed.
	 */
	public static int getServiceDone() {
		return serviceDone;
	}


	/**
	 * Gets the departure time of the customer.
	 * @return The departure time.
	 */
	public double getPoistumisaika() {
		return poistumisaika;
	}

	/**
	 * Sets the departure time for the customer.
	 * @param poistumisaika The time when the customer leaves.
	 */
	public void setPoistumisaika(double poistumisaika) {
		this.poistumisaika = poistumisaika;
		totalTime += (poistumisaika - saapumisaika);
		totalCustomers++;
		System.out.println(totalCustomers);
	}

	/**
	 * Gets the arrival time of the customer.
	 * @return The arrival time.
	 */
	public double getSaapumisaika() {
		return saapumisaika;
	}

	/**
	 * Sets the arrival time for the customer.
	 * @param saapumisaika The time when the customer arrives.
	 */
	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
	}

	/**
	 * Gets the objective or target of the customer.
	 * @return The customer's target.
	 */
	public byte getTavoite() {
		return tavoite;
	}

	/**
	 * Sets the objective or target for the customer.
	 * @param tavoite The new target for the customer.
	 */
	public void setTavoite(byte tavoite) {
		this.tavoite = tavoite;
	}

	/**
	 * Checks if the customer has a reservation.
	 * @return True if the customer has a reservation, false otherwise.
	 */
	public boolean isOnVarattu() {
		return onVarattu;
	}

	/**
	 * Gets the ID of the customer.
	 * @return The customer's ID.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Produces a report on the customer's experience, including wait time and service quality.
	 */
	public void raportti() {
		Trace.out(Trace.Level.INFO, "\nAsiakas " + id + " valmis! ");
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " saapui: " + saapumisaika);
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " poistui: " + poistumisaika);
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " viipyi: " + (poistumisaika - saapumisaika));
		sum += (poistumisaika - saapumisaika);
		double keskiarvo = sum / id;
		int customerRating = calculateHappyRating(poistumisaika - saapumisaika);
		happyRating += customerRating;
		System.out.println("Asiakkaiden läpimenoaikojen keskiarvo tähän asti " + keskiarvo);
	}

	/**
	 * Calculates the happiness rating based on time spent.
	 * @param timeSpent The time the customer spent from arrival to departure.
	 * @return The happiness rating.
	 */
	private int calculateHappyRating(double timeSpent) {
		if (timeSpent < 40) {
			return 5;
		} else if (timeSpent < 45) {
			return 4;
		} else if (timeSpent < 50) {
			return 3;
		} else if (timeSpent < 55) {
			return 2;
		} else {
			return 1;
		}
	}

	/**
	 * Gets the average time spent by customers.
	 * @return The average time spent by all customers.
	 */
	public static double getAverageTimeSpent(){
		if (totalCustomers == 0){
			return 0;
		}else {
			return totalTime / totalCustomers;
		}
	}
	/**
	 * Gets the total number of customers.
	 * @return The total number of customers served.
	 */
	public static int getTotalCustomers(){
		return totalCustomers;
	}

	/**
	 * Gets the average happiness rating of all customers.
	 * @return The average happiness rating.
	 */
	public static double getHappyRating(){
		return happyRating / serviceDone;
	}

	/**
	 * Resets the customer statistics to initial values.
	 */
	public static void reset(){
		i = 1;
		sum = 0;
		totalTime = 0;
		totalCustomers = 0;
		serviceDone = 0;
		happyRating = 0;
	}
}

