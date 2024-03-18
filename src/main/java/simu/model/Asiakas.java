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
	/**
	 * A static Random object used for generating random values.
	 */
	private static Random random = new Random();

	/**
	 * The time the customer arrives.
	 */
	private double saapumisaika;

	/**
	 * The time the customer leaves.
	 */
	private double poistumisaika;

	/**
	 * The unique identifier of the customer.
	 */
	private int id;

	/**
	 * A static counter used for assigning unique identifiers to customers.
	 */
	private static int i = 1;

	/**
	 * A static variable used for calculating the average time spent by customers.
	 */
	private static long sum = 0;

	/**
	 * A static variable used for calculating the total time spent by all customers.
	 */
	private static double totalTime = 0;

	/**
	 * A static variable used for keeping track of the total number of customers.
	 */
	private static int totalCustomers = 0;

	/**
	 * A static variable used for keeping track of the number of services done.
	 */
	private static int serviceDone = 0;

	/**
	 * A static variable used for calculating the average happiness rating of customers.
	 */
	private static double happyRating = 0;

	/**
	 * A boolean indicating whether the customer has a reservation.
	 */
	private boolean onVarattu;

	/**
	 * The target of the customer.
	 */
	private byte tavoite;

	/**
	 * Constructor for the Asiakas class.
	 * @param onVarattu A boolean indicating whether the customer has a reservation.
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
	 * A static method for setting the number of services done.
	 * @param serviceDone The number of services done.
	 */
	public static void setServiceDone(int serviceDone) {
		Asiakas.serviceDone = serviceDone;
	}

	/**
	 * A static method for getting the number of services done.
	 * @return The number of services done.
	 */
	public static int getServiceDone() {
		return serviceDone;
	}

	/**
	 * A method for getting the time the customer leaves.
	 * @return The time the customer leaves.
	 */
	public double getPoistumisaika() {
		return poistumisaika;
	}

	/**
	 * A method for setting the time the customer leaves.
	 * @param poistumisaika The time the customer leaves.
	 */
	public void setPoistumisaika(double poistumisaika) {
		this.poistumisaika = poistumisaika;
		totalTime += (poistumisaika - saapumisaika);
		totalCustomers++;
		System.out.println(totalCustomers);
	}

	/**
	 * A method for getting the time the customer arrives.
	 * @return The time the customer arrives.
	 */
	public double getSaapumisaika() {
		return saapumisaika;
	}

	/**
	 * A method for setting the time the customer arrives.
	 * @param saapumisaika The time the customer arrives.
	 */
	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
	}

	/**
	 * A method for getting the target of the customer.
	 * @return The target of the customer.
	 */
	public byte getTavoite() {
		return tavoite;
	}

	/**
	 * A method for setting the target of the customer.
	 * @param tavoite The target of the customer.
	 */
	public void setTavoite(byte tavoite) {
		this.tavoite = tavoite;
	}

	/**
	 * A method for checking whether the customer has a reservation.
	 * @return A boolean indicating whether the customer has a reservation.
	 */
	public boolean isOnVarattu() {
		return onVarattu;
	}

	/**
	 * A method for getting the unique identifier of the customer.
	 * @return The unique identifier of the customer.
	 */
	public int getId() {
		return id;
	}

	/**
	 * A method for generating a report about the customer.
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
	 * A static method for calculating the happiness rating of a customer based on the time they spent.
	 * @param timeSpent The time the customer spent.
	 * @return The happiness rating of the customer.
	 */
	public static int calculateHappyRating(double timeSpent) {
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
	 * A static method for getting the average time spent by all customers.
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
	 * A static method for getting the total number of customers.
	 * @return The total number of customers.
	 */
	public static int getTotalCustomers(){
		return totalCustomers;
	}

	/**
	 * A static method for getting the average happiness rating of all customers.
	 * @return The average happiness rating of all customers.
	 */
	public static double getHappyRating(){
		return happyRating / totalCustomers;
	}

	/**
	 * A static method for resetting all static variables to their initial values.
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

