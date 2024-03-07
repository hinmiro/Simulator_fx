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

    public Simu(double simulaatioAika, int asiakkaat, int palvelupisteet, double asiakastyytyvaisyys, int varatutAsiakkaat, double infoKeskiaika, double uusitiliKeskiaika, double talletusKeskiaika ,double sijoitusKeskiaika) {
        this.simulaatioAika = simulaatioAika;
        this.asiakkaat = asiakkaat;
        this.palvelupisteet = palvelupisteet;
        this.asiakastyytyvaisyys = asiakastyytyvaisyys;
        this.varatutAsiakkaat = varatutAsiakkaat;
        this.infoKeskiaika = infoKeskiaika;
        this.uusitiliKeskiaika = uusitiliKeskiaika;
        this.talletusKeskiaika = talletusKeskiaika;
        this.sijoitusKeskiaika = sijoitusKeskiaika;
    }

    public Simu() {
        
    }

}
