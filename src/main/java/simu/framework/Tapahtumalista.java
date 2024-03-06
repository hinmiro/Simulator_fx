package simu.framework;

import java.util.PriorityQueue;

/**
 * The `Tapahtumalista` class manages a priority queue of `Tapahtuma` objects, facilitating the addition, removal, and time retrieval of scheduled simulation events.
 */
public class Tapahtumalista {
	private PriorityQueue<Tapahtuma> lista = new PriorityQueue<Tapahtuma>();
	
	public Tapahtumalista(){
	 
	}
	
	public Tapahtuma poista(){
		return lista.remove();
	}
	
	public void lisaa(Tapahtuma t){
		lista.add(t);
	}
	
	public double getSeuraavanAika(){
		return lista.peek().getAika();
	}
	
	
}
