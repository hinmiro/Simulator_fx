package entity;

import jakarta.persistence.*;

/**
 * The Simu class represents a simulation in the system.
 * It contains information about the simulation such as the time of the simulation,
 * the number of customers, the number of service points, customer satisfaction,
 * the number of reserved customers, and various average times and utilization rates
 * for different desks in the simulation.
 */
@Entity
@Table(name = "simu")
public class Simu {
    /**
     * The unique identifier of the simulation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The time of the simulation.
     */
    @Column(name = "simulaatio_aika")
    private double simulaatioAika;

    /**
     * The number of customers in the simulation.
     */
    private int asiakkaat;

    /**
     * The number of service points in the simulation.
     */
    private int palvelupisteet;

    /**
     * The customer satisfaction in the simulation.
     */
    private double asiakastyytyvaisyys;

    /**
     * The number of reserved customers in the simulation.
     */
    @Column(name="varatut_asiakkaat")
    private int varatutAsiakkaat;

    /**
     * The average time spent at the information desk in the simulation.
     */
    @Column(name="info_keskiaika")
    private double infoKeskiaika;

    /**
     * The average time spent at the new account desk in the simulation.
     */
    @Column(name="uusitili_keskiaika")
    private double uusitiliKeskiaika;

    /**
     * The average time spent at the deposit desk in the simulation.
     */
    @Column(name="talletus_keskiaika")
    private double talletusKeskiaika;

    /**
     * The average time spent at the investment desk in the simulation.
     */
    @Column(name="sijoitus_keskiaika")
    private double sijoitusKeskiaika;

    /**
     * The utilization rate of the information desk in the simulation.
     */
    @Column(name="info_käyttöaste")
    private double infoKayttoaste;

    /**
     * The utilization rate of the new account desk in the simulation.
     */
    @Column(name="uusitili_käyttöaste")
    private double uusitiliKayttoaste;

    /**
     * The utilization rate of the deposit desk in the simulation.
     */
    @Column(name="talletus_käyttöaste")
    private double talletusKayttoaste;

    /**
     * The utilization rate of the investment desk in the simulation.
     */
    @Column(name="sijoitus_käyttöaste")
    private double sijoitusKayttoaste;

    /**
     * Constructor for the Simu class with all parameters.
     *
     * @param simulaatioAika The time of the simulation.
     * @param asiakkaat The number of customers in the simulation.
     * @param palvelupisteet The number of service points in the simulation.
     * @param asiakastyytyvaisyys The customer satisfaction in the simulation.
     * @param varatutAsiakkaat The number of reserved customers in the simulation.
     * @param infoKeskiaika The average time spent at the information desk in the simulation.
     * @param infoKayttoaste The utilization rate of the information desk in the simulation.
     * @param uusitiliKeskiaika The average time spent at the new account desk in the simulation.
     * @param uusitiliKayttoaste The utilization rate of the new account desk in the simulation.
     * @param talletusKeskiaika The average time spent at the deposit desk in the simulation.
     * @param talletusKayttoaste The utilization rate of the deposit desk in the simulation.
     * @param sijoitusKeskiaika The average time spent at the investment desk in the simulation.
     * @param sijoitusKayttoaste The utilization rate of the investment desk in the simulation.
     */
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

    /**
     * Default constructor for the Simu class.
     */
    public Simu() {
        
    }

    /**
     * @return The number of customers in the simulation.
     */
    public int getAsiakkaat() {
        return asiakkaat;
    }

    /**
     * @return The customer satisfaction in the simulation.
     */
    public double getAsiakastyytyvaisyys() {
        return asiakastyytyvaisyys;
    }

    /**
     * @return The average time spent at the information desk in the simulation.
     */
    public double getInfoKeskiaika() {
        return infoKeskiaika;
    }

    /**
     * @return The unique identifier of the simulation.
     */
    public int getId() {
        return id;
    }

    /**
     * @return The number of reserved customers in the simulation.
     */
    public int getVaratutAsiakkaat() {
        return varatutAsiakkaat;
    }

    /**
     * @return The time of the simulation.
     */
    public double getSimulaatioAika() {
        return simulaatioAika;
    }

    /**
     * @return The average time spent at the new account desk in the simulation.
     */
    public double getUusitiliKeskiaika() {
        return uusitiliKeskiaika;
    }

    /**
     * @return The average time spent at the investment desk in the simulation.
     */
    public double getSijoitusKeskiaika() {
        return sijoitusKeskiaika;
    }

    /**
     * @return The average time spent at the deposit desk in the simulation.
     */
    public double getTalletusKeskiaika() {
        return talletusKeskiaika;
    }

    /**
     * @return The number of service points in the simulation.
     */
    public int getPalvelupisteet() {
        return palvelupisteet;
    }

    /**
     * @return The utilization rate of the information desk in the simulation.
     */
    public double getInfoKayttoaste() {
        return infoKayttoaste;
    }

    /**
     * @return The utilization rate of the new account desk in the simulation.
     */
    public double getUusitiliKayttoaste() {
        return uusitiliKayttoaste;
    }

    /**
     * @return The utilization rate of the deposit desk in the simulation.
     */
    public double getTalletusKayttoaste() {
        return talletusKayttoaste;
    }

    /**
     * @return The utilization rate of the investment desk in the simulation.
     */
    public double getSijoitusKayttoaste() {
        return sijoitusKayttoaste;
    }
}
