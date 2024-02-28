package simu.model;

import simu.framework.*;
import java.util.LinkedList;
import eduni.distributions.ContinuousGenerator;

public class Palvelupiste {

	private final LinkedList<Asiakas> jono = new LinkedList<>(); // Tietorakennetoteutus
	private final LinkedList<Asiakas> varattuJono = new LinkedList<>();
	private final ContinuousGenerator generator;
	private final Tapahtumalista tapahtumalista;
	private final TapahtumanTyyppi skeduloitavanTapahtumanTyyppi;

	//JonoStrategia strategia; //optio: asiakkaiden järjestys

	private boolean varattu = false;


	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;

	}

	//Asiakas(boolean varattuAika)
	public void lisaaJonoon(Asiakas a) {   // Jonon 1. asiakas aina palvelussa
		jono.add(a);
	}

	public Asiakas otaJonosta() {  // Poistetaan palvelussa ollut
		varattu = false;
		Asiakas.setServiceDone(Asiakas.getServiceDone() + 1);
		return jono.poll();
	}

	public void lisaaVarattuJonoon(Asiakas a) {
		varattuJono.add(a);
	}

	public void lisaaJononSeuraavaksi(Asiakas a) {
		jono.add(1, a);
	}

	public Asiakas otaVarattuJonosta() {
		varattu = false;
		Asiakas.setServiceDone(Asiakas.getServiceDone() + 1);
		return varattuJono.poll();
	}

	public int getJononPituus() {
		return jono.size();
	}

	public int getVaratunJononPituus() {
		return varattuJono.size();
	}


	public void aloitaPalvelu(boolean varattuAsiakas) {  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana

		if (varattuAsiakas)
			Trace.out(Trace.Level.INFO, "Aloitetaan uusi "+ skeduloitavanTapahtumanTyyppi +" asiakkaalle " + varattuJono.peek().getId() + " reservation: true");
		else
			Trace.out(Trace.Level.INFO, "Aloitetaan uusi "+ skeduloitavanTapahtumanTyyppi +" asiakkaalle " + jono.peek().getId() + " reservation: false");


		varattu = true;
		double palveluaika = generator.sample();
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika));
	}


	public boolean onVarattu() {
		return varattu;
	}


	public boolean onJonossa() {
		return !jono.isEmpty();
	}

	public boolean onVarattuJonossa() {
		return !varattuJono.isEmpty();
	}

	public boolean checkForCustomerJono(Asiakas asiakas) {
		return jono.contains(asiakas);
	}
	public boolean checkForCustomerVarattuJono(Asiakas asiakas) {
		return varattuJono.contains(asiakas);
	}

	@Override
	public String toString() {
		return "Palvelupiste{" +
				"jono size=" + jono.size() +
				", varattuJono size=" + varattuJono.size() +
				", varattu=" + varattu +
				'}';
	}
}

