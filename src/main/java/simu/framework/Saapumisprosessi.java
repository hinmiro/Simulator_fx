package simu.framework;
import eduni.distributions.*;
import simu.model.TapahtumanTyyppi;

/**
 * The `Saapumisprosessi` class in the framework models the arrival process in a simulation, using a continuous distribution generator to schedule new events based on event type and timing, and adds them to the event list for processing.
 */
public class Saapumisprosessi {
	
	private ContinuousGenerator generaattori;
	private Tapahtumalista tapahtumalista;
	private TapahtumanTyyppi tyyppi;

	public Saapumisprosessi(ContinuousGenerator g, Tapahtumalista tl, TapahtumanTyyppi tyyppi){
		this.generaattori = g;
		this.tapahtumalista = tl;
		this.tyyppi = tyyppi;
	}

	public void generoiSeuraava(){
		Tapahtuma t = new Tapahtuma(tyyppi, Kello.getInstance().getAika()+generaattori.sample());
		tapahtumalista.lisaa(t);
	}

}
