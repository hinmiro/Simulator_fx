package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "simu")
public class Simu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "simulaatio_aika")
    private double simulaatioAika;
    private int asiakkaat;
    private int palvelupisteet;
    private double asiakastyytyvaisyys;
    @Column(name="varatut_asiakkaat")
    private int varatutAsiakkaat;
    @Column(name="info_keskiaika")
    private double infoKeskiaika;
    @Column(name="uusitili_keskiaika")
    private double uusitiliKeskiaika;
    @Column(name="talletus_keskiaika")
    private double talletusKeskiaika;
    @Column(name="sijoitus_keskiaika")
    private double sijoitusKeskiaika;
    @Column(name="info_käyttöaste")
    private double infoKayttoaste;
    @Column(name="uusitili_käyttöaste")
    private double uusitiliKayttoaste;
    @Column(name="talletus_käyttöaste")
    private double talletusKayttoaste;
    @Column(name="sijoitus_käyttöaste")
    private double sijoitusKayttoaste;

    public Simu(double simulaatioAika, int asiakkaat, int palvelupisteet, double asiakastyytyvaisyys, int varatutAsiakkaat, double infoKeskiaika, double infoKayttoaste, double uusitiliKeskiaika, double uusitiliKayttoaste, double talletusKeskiaika, double talletusKayttoaste, double sijoitusKeskiaika, double sijoitusKayttoaste) {
        this.simulaatioAika = simulaatioAika;
        this.asiakkaat = asiakkaat;
        this.palvelupisteet = palvelupisteet;
        this.asiakastyytyvaisyys = asiakastyytyvaisyys;
        this.varatutAsiakkaat = varatutAsiakkaat;
        this.infoKeskiaika = infoKeskiaika;
        this.infoKayttoaste = infoKayttoaste;
        this.uusitiliKeskiaika = uusitiliKeskiaika;
        this.uusitiliKayttoaste = uusitiliKayttoaste;
        this.talletusKeskiaika = talletusKeskiaika;
        this.talletusKayttoaste = talletusKayttoaste;
        this.sijoitusKeskiaika = sijoitusKeskiaika;
        this.sijoitusKayttoaste = sijoitusKayttoaste;
    }

    public Simu() {
        
    }

    public int getAsiakkaat() {
        return asiakkaat;
    }
    public double getAsiakastyytyvaisyys() {
        return asiakastyytyvaisyys;
    }

    public double getInfoKeskiaika() {
        return infoKeskiaika;
    }

    public int getId() {
        return id;
    }

    public int getVaratutAsiakkaat() {
        return varatutAsiakkaat;
    }

    public double getSimulaatioAika() {
        return simulaatioAika;
    }

    public double getUusitiliKeskiaika() {
        return uusitiliKeskiaika;
    }

    public double getSijoitusKeskiaika() {
        return sijoitusKeskiaika;
    }

    public double getTalletusKeskiaika() {
        return talletusKeskiaika;
    }

    public int getPalvelupisteet() {
        return palvelupisteet;
    }

    public double getInfoKayttoaste() {
        return infoKayttoaste;
    }

    public double getUusitiliKayttoaste() {
        return uusitiliKayttoaste;
    }

    public double getTalletusKayttoaste() {
        return talletusKayttoaste;
    }

    public double getSijoitusKayttoaste() {
        return sijoitusKayttoaste;
    }
}
