package simu.model;

import simu.framework.Kello;
import simu.framework.Trace;

import java.util.Random;


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
	private static double happyRating = 0;
	private boolean onVarattu;
	private byte tavoite;


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

	public double getPoistumisaika() {
		return poistumisaika;
	}

	public void setPoistumisaika(double poistumisaika) {
		this.poistumisaika = poistumisaika;
		totalTime += (poistumisaika - saapumisaika);
		totalCustomers++;
	}

	public double getSaapumisaika() {
		return saapumisaika;
	}

	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
	}

	public byte getTavoite() {
		return tavoite;
	}

	public void setTavoite(byte tavoite) {
		this.tavoite = tavoite;
	}

	public boolean isOnVarattu() {
		return onVarattu;
	}

	public int getId() {
		return id;
	}

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

	public static double getAverageTimeSpent(){
		if (totalCustomers == 0){
			return 0;
		}else {
			return totalTime / totalCustomers;
		}
	}
	public static int getTotalCustomers(){
		return totalCustomers;
	}

	public static double getHappyRating(){
		return happyRating / totalCustomers;
	}
	public static void resetStatics() {
		totalTime = 0;
		totalCustomers = 0;
	}
}

