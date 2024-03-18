package simu.framework;

import java.util.PriorityQueue;

/**
 * The `Tapahtumalista` class manages a priority queue of `Tapahtuma` objects, facilitating the addition, removal, and time retrieval of scheduled simulation events.
 */
public class Tapahtumalista {
	private PriorityQueue<Tapahtuma> lista = new PriorityQueue<Tapahtuma>();

	/**
	 * Constructs an empty Tapahtumalista (Event List) to hold simulation events.
	 */
	public Tapahtumalista(){
	 
	}
	/**
	 * Removes and returns the next event in the list based on priority (scheduled time).
	 * @return The next Tapahtuma (event) to occur, or null if the list is empty.
	 */
	public Tapahtuma poista(){
		return lista.remove();
	}

	/**
	 * Adds a new event to the priority queue.
	 * @param t The Tapahtuma (event) to be added to the list.
	 */
	public void lisaa(Tapahtuma t){
		lista.add(t);
	}

	/**
	 * Retrieves the scheduled time of the next event without removing it from the queue.
	 * @return The time of the next event, or Double.MAX_VALUE if the list is empty.
	 */
	public double getSeuraavanAika(){
		return lista.peek().getAika();
	}
	
	
}
