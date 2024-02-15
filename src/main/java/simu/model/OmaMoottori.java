package simu.model;

import simu.framework.*;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import controller.IKontrolleriForM;

import java.util.Random;

public class OmaMoottori extends Moottori {

	private Saapumisprosessi saapumisprosessi;

	private Palvelupiste[] palvelupisteet;

	public OmaMoottori(IKontrolleriForM kontrolleri){

		super(kontrolleri);

		palvelupisteet = new Palvelupiste[4];

		palvelupisteet[0] = new Palvelupiste(new Normal(10, 6), tapahtumalista, TapahtumanTyyppi.INFOTISKI);
		palvelupisteet[1] = new Palvelupiste(new Normal(10, 10), tapahtumalista, TapahtumanTyyppi.UUDEN_TILIN_AVAUS);
		palvelupisteet[2] = new Palvelupiste(new Normal(5, 3), tapahtumalista, TapahtumanTyyppi.TALLETUS);
		palvelupisteet[3] = new Palvelupiste(new Normal(6, 9), tapahtumalista, TapahtumanTyyppi.SIJOITUS_PALVELUT);
		saapumisprosessi = new Saapumisprosessi(new Negexp(15, 5), tapahtumalista, TapahtumanTyyppi.SAAPUMINEN);

	}

	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}

	@Override
	protected void suoritaTapahtuma(Tapahtuma t) {  // B-vaiheen tapahtumat

		Asiakas a;
		switch ((TapahtumanTyyppi) t.getTyyppi()) {

			case SAAPUMINEN:
				Asiakas as = new Asiakas(generateTrueFalse());
				if (as.isOnVarattu())
					palvelupisteet[1].lisaaVarattuJonoon(as);
				else
					palvelupisteet[0].lisaaJonoon(as);
				kontrolleri.visualisoiAsiakas();
				saapumisprosessi.generoiSeuraava();
				break;
			case INFOTISKI: // 0
				a = (Asiakas) palvelupisteet[0].otaJonosta();
				palvelupisteet[1].lisaaJonoon(a);
				break;
			case UUDEN_TILIN_AVAUS: // 1
				if (palvelupisteet[1].onVarattuJonossa()) {
					a = (Asiakas) palvelupisteet[1].otaVarattuJonosta();
					if (!palvelupisteet[1].onVarattu()) {
						palvelupisteet[2].lisaaJonoon(a);
					} else {
						if (palvelupisteet[2].onJonossa()) {
							palvelupisteet[2].lisaaJononSeuraavaksi(a);
						} else {
							palvelupisteet[2].lisaaJonoon(a);
						}
					}
				} else {
					a = (Asiakas) palvelupisteet[1].otaJonosta();
					palvelupisteet[2].lisaaJonoon(a);
				}
				break;
			case TALLETUS:  // 2
				if (palvelupisteet[2].onVarattuJonossa()) {
					a = (Asiakas) palvelupisteet[2].otaVarattuJonosta();
					if (!palvelupisteet[2].onVarattu()) {
						palvelupisteet[3].lisaaJonoon(a);
					} else {
						if (palvelupisteet[3].onJonossa()) {
							palvelupisteet[3].lisaaJononSeuraavaksi(a);
						} else {
							palvelupisteet[3].lisaaJonoon(a);
						}
					}
				} else {
					a = (Asiakas) palvelupisteet[2].otaJonosta();
					palvelupisteet[3].lisaaJonoon(a);

				}
				break;
			case SIJOITUS_PALVELUT: // 3
				if (palvelupisteet[3].onVarattuJonossa()) {
					a = (Asiakas) palvelupisteet[3].otaVarattuJonosta();
					a.setPoistumisaika(Kello.getInstance().getAika());
					a.raportti();

				} else {
					a = (Asiakas) palvelupisteet[3].otaJonosta();
					a.setPoistumisaika(Kello.getInstance().getAika());
					a.raportti();
				}
				break;
		}
	}


	@Override
	protected void yritaCTapahtumat() {
		for (Palvelupiste p : palvelupisteet) {
			if (!p.onVarattu() && p.onJonossa()) {
				p.aloitaPalvelu();
			}
		}
	}

	@Override
	protected void tulokset() {
		System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
		System.out.println("---------------------------------------------------------");
		System.out.println("Keskimääräinen läpikulku aika on:  " + Asiakas.getAverageTimeSpent());
		System.out.println("Asiakkaita palveltu: " + Asiakas.getTotalCustomers());
		System.out.println("Keskimääräinen asiakastyytyväisyys: " + Asiakas.getHappyRating());


		// UUTTA graafista
		kontrolleri.naytaLoppuaika(Kello.getInstance().getAika());
	}

	protected boolean generateTrueFalse() {
		Random random = new Random();
		double normalNum = new Normal(5, random.nextInt(10) + 1).sample();
		return normalNum <= 2 || normalNum >= 8;
	}
}
