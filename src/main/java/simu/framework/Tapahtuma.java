package simu.framework;

import simu.model.TapahtumanTyyppi;

/**
 * The `Tapahtuma` class represents an event in the simulation framework, characterized by its type and scheduled time. It implements the `Comparable` interface to enable sorting based on event time.
 */

public class Tapahtuma implements Comparable<Tapahtuma> {
	
		
	private TapahtumanTyyppi tyyppi;
	private double aika;

	/**
	 * Constructs a new Tapahtuma (event) with a specified type and scheduled time.
	 * @param tyyppi The type of the event.
	 * @param aika The scheduled time for the event.
	 */
	public Tapahtuma(TapahtumanTyyppi tyyppi, double aika){
		this.tyyppi = tyyppi;
		this.aika = aika;
	}

	/**
	 * Sets the type of this event.
	 * @param tyyppi The new type for this event.
	 */
	public void setTyyppi(TapahtumanTyyppi tyyppi) {
		this.tyyppi = tyyppi;
	}
	/**
	 * Retrieves the type of this event.
	 * @return The type of this event.
	 */
	public TapahtumanTyyppi getTyyppi() {
		return tyyppi;
	}
	/**
	 * Sets the scheduled time for this event.
	 * @param aika The new scheduled time for this event.
	 */
	public void setAika(double aika) {
		this.aika = aika;
	}
	/**
	 * Retrieves the scheduled time for this event.
	 * @return The scheduled time for this event.
	 */
	public double getAika() {
		return aika;
	}

	/**
	 * Compares this event with another event for order based on the scheduled time.
	 * @param arg The event to be compared.
	 * @return A negative integer, zero, or a positive integer as this event is less than, equal to, or greater than the specified event.
	 */
	@Override
	public int compareTo(Tapahtuma arg) {
		if (this.aika < arg.aika) return -1;
		else if (this.aika > arg.aika) return 1;
		return 0;
	}
	
	
	

}
