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
	private double palvelunKesto;
	private int palveluAsiakkaat = 0;
	private String nimi;
	private double kayttoaste;
	private double aloitus;
	private double lopetus;



	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi, String nimi) {
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		this.nimi = nimi;
		aloitus = Kello.getInstance().getAika();
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

	public int getPalveluAsiakkaat() {
		return palveluAsiakkaat;
	}

	public double getPalvelunkesto() {
		return palvelunKesto/palveluAsiakkaat;
	}

	public String getNimi() {
		return this.nimi;
	}

	public void setKayttoaste() {
		lopetus = Kello.getInstance().getAika();
		kayttoaste = palvelunKesto / (lopetus - aloitus);
	}

	public double getKayttoAste() {
		return kayttoaste;
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

