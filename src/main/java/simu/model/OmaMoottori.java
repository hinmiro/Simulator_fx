package simu.model;

import simu.framework.*;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import controller.IKontrolleriForM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 * The `OmaMoottori` class extends `Moottori` to implement a specific simulation engine, managing customer arrivals and service processes across different service points. It initializes service processes and customer flow based on defined distributions and handles the dynamic addition and removal of service points. The class also oversees the simulation's progression, customer queue management, and service completion, culminating in the reporting of simulation results such as average processing time and customer satisfaction.
 */

public class OmaMoottori extends Moottori {

    private Saapumisprosessi saapumisprosessi;

    private HashMap<String, ArrayList<Palvelupiste>> palvelupisteet = new HashMap<>();
    private int prosentti;

    /**
     * Initializes a new instance of the simulation engine with a specific controller.
     * @param kontrolleri The controller interface implementation to be used by this engine.
     */
    public OmaMoottori(IKontrolleriForM kontrolleri) {

        super(kontrolleri);
        initializeData();
    }
    /**
     * Initializes or resets the simulation data, setting up service processes and customer flows.
     */
    public void initializeData(){
        palvelupisteet.clear();
        Asiakas.reset();
        saapumisprosessi = new Saapumisprosessi(new Negexp(15, 5), tapahtumalista, TapahtumanTyyppi.SAAPUMINEN);
        for (int i = 0; i < 4; i++) {
            palvelupisteet.put(Integer.toString(i), new ArrayList<>());
        }
        palvelupisteet.get("0").add(new Palvelupiste(new Normal(10, 6), tapahtumalista, TapahtumanTyyppi.INFOTISKI, "Info"));
        palvelupisteet.get("1").add(new Palvelupiste(new Normal(10, 10), tapahtumalista, TapahtumanTyyppi.UUDEN_TILIN_AVAUS, "Uudet tilit"));
        palvelupisteet.get("2").add(new Palvelupiste(new Normal(5, 3), tapahtumalista, TapahtumanTyyppi.TALLETUS, "Talletus"));
        palvelupisteet.get("3").add(new Palvelupiste(new Normal(6, 9), tapahtumalista, TapahtumanTyyppi.SIJOITUS_PALVELUT, "Sijoutuspalvelut"));
    }

    /**
     * Adds a new service point of the specified type to the simulation.
     * @param type The type of service point to add, defined by a string identifier.
     */
    public void addPalvelu(String type) {
        switch (type) {
            case "Infopiste":
                palvelupisteet.get("0").add(new Palvelupiste(new Normal(10, 6), tapahtumalista, TapahtumanTyyppi.INFOTISKI, "Info"));
                System.out.println(palvelupisteet.get("0").size() + " infopisteet");
                break;
            case "Uusi tili":
                palvelupisteet.get("1").add(new Palvelupiste(new Normal(10, 10), tapahtumalista, TapahtumanTyyppi.UUDEN_TILIN_AVAUS, "Uudet tilit"));
                System.out.println(palvelupisteet.get("1").size() + " uudet tilit");
                break;
            case "Talletuspiste":
                palvelupisteet.get("2").add(new Palvelupiste(new Normal(5, 3), tapahtumalista, TapahtumanTyyppi.TALLETUS, "Talletus"));
                System.out.println(palvelupisteet.get("2").size() + " talletuspisteet");
                break;
            case "Sijoitusneuvonta":
                palvelupisteet.get("3").add(new Palvelupiste(new Normal(6, 9), tapahtumalista, TapahtumanTyyppi.SIJOITUS_PALVELUT, "Sijoituspalvelut"));
                System.out.println(palvelupisteet.get("3").size() + " sijoitusneuvonta");
                break;
        }
    }

    /**
     * Removes the last service point of the specified type from the simulation.
     * @param type The type of service point to remove, defined by a string identifier.
     */
    public void deletePalvelu(String type) {
        switch (type) {
            case "Infopiste":
                if (palvelupisteet.get("0").size() > 1) // Ei voi poistaa viimeistä palvelupistettä (joka on aina olemassa
                    palvelupisteet.get("0").remove(palvelupisteet.get("0").size() - 1);
                System.out.println(palvelupisteet.get("0").size() + " infopisteet");
                break;
            case "Uusi tili":
                if (palvelupisteet.get("1").size() > 1) // Ei voi poistaa viimeistä palvelupistettä (joka on aina olemassa
                    palvelupisteet.get("1").remove(palvelupisteet.get("1").size() - 1);
                System.out.println(palvelupisteet.get("1").size() + " uudet tilit");
                break;
            case "Talletuspiste":
                if (palvelupisteet.get("2").size() > 1) // Ei voi poistaa viimeistä palvelupistettä (joka on aina olemassa
                    palvelupisteet.get("2").remove(palvelupisteet.get("2").size() - 1);
                System.out.println(palvelupisteet.get("2").size() + " talletuspisteet");
                break;
            case "Sijoitusneuvonta":
                if (palvelupisteet.get("3").size() > 1) // Ei voi poistaa viimeistä palvelupistettä (joka on aina olemassa
                    palvelupisteet.get("3").remove(palvelupisteet.get("3").size() - 1);
                System.out.println(palvelupisteet.get("3").size() + " sijoitusneuvonta");
                break;
        }
    }

    /**
     * Sets the percentage of reserved customers in the simulation.
     * @param uusiProsentti The new percentage of reserved customers.
     */
    public void setProsentti(int uusiProsentti) {
        prosentti = uusiProsentti;
    }

    /**
     * Prepares initial settings and generates the first arrival event in the simulation.
     */
    @Override
    protected void alustukset() {
        saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
    }

    /**
     * Processes a single simulation event, determining actions based on the event's type.
     * @param t The simulation event to be processed.
     */
    @Override
    protected void suoritaTapahtuma(Tapahtuma t) {  // B-vaiheen tapahtumat

        ArrayList<Asiakas> a = new ArrayList<>();
        switch ((TapahtumanTyyppi) t.getTyyppi()) {

            case SAAPUMINEN:
                Asiakas as = new Asiakas(generateTrueFalse()); // uusi Asiakas saapui
                System.out.println(as.isOnVarattu()); // debug
                if (as.isOnVarattu())
                    lisaaJonoon(Byte.toString(as.getTavoite()), "lisaaVarattuJonoon", as);
                else
                    lisaaJonoon("0", "lisaaJonoon", as);
                System.out.println("Infotiski jonossa:" + palvelupisteet.get("0").toString());
                System.out.println("Uuden tilin avaajat jonossa:" + palvelupisteet.get("1").toString());
                System.out.println("Talletuspiste jonossa:" + palvelupisteet.get("2").toString());
                System.out.println("Sijoitusneuvonta jonossa:" + palvelupisteet.get("3").toString());
                kontrolleri.visualisoiAsiakas();
                saapumisprosessi.generoiSeuraava();
                break;
            case INFOTISKI: // 0
                // System.out.println("Infotiski jonossa:" + palvelupisteet.get("0"););
                a = otaJonosta("0", "otaJonosta");
                System.out.println("Infotiski palvelee asiakasta");
                Iterator<Asiakas> iterator = a.iterator();
                while (iterator.hasNext()) {
                    Asiakas asiakas = iterator.next();
                    if (asiakas != null && asiakas.getTavoite() == 0) {
                        System.out.println(asiakas.getTavoite());
                        asiakas.setPoistumisaika(Kello.getInstance().getAika());
                        asiakas.raportti();
                        iterator.remove();
                    }
                }
                if (!a.isEmpty()){
                    iterator = a.iterator();
                    while (iterator.hasNext()) {
                        Asiakas asiakas = iterator.next();
                        if (asiakas != null) {
                            System.out.println("asiakas " + asiakas.getId() + " siirtyy jonoon" + asiakas.getTavoite());
                            lisaaJonoon(Byte.toString(asiakas.getTavoite()), "lisaaJonoon", asiakas);
                            iterator.remove();
                        }
                    }
                }
                break;
            case UUDEN_TILIN_AVAUS: // 1
                palvelee(a, "1", "2");
                break;
            case TALLETUS:  // 2
                palvelee(a, "2", "3");
                break;
            case SIJOITUS_PALVELUT: // 3
                palvelee(a, "3", "4");
                break;
        }
    }


    // C-vaiheen tapahtumat

    // Tässä vaiheessa palvelupisteet käyvät läpi jononsa ja aloittavat palvelun
    // jos asiakas on jonossa ja palvelupiste on vapaa
    // tai jos asiakas on varattu jonossa ja palvelupiste on vapaa
    // Tämä metodi kutsutaan aina kun kello etenee
    /**
     * Attempts to start service for waiting customers at all service points based on current conditions.
     */
    @Override
    protected void yritaCTapahtumat() {
        palvelupisteet.forEach((k, v) -> {
            for (Palvelupiste p : v) {
                if (p.onJonossa() && !p.onVarattu()) {
                    p.aloitaPalvelu(false);
                } else if (p.onVarattuJonossa() && !p.onVarattu()) {
                    p.aloitaPalvelu(true);
                }
            }
        });
    }

    /**
     * Manages the service process for customers at a specified service point.
     * @param a The list of customers to be serviced.
     * @param palvelupisteNmrAlus The starting identifier for service points.
     * @param palvelupisteNmrLoppu The ending identifier for service points (for progression to the next service point).
     */
    public void palvelee(ArrayList<Asiakas> a, String palvelupisteNmrAlus, String palvelupisteNmrLoppu) {
        a = otaJonosta(palvelupisteNmrAlus, "otaVarattuJonosta");
        handleCustomers(palvelupisteNmrLoppu, a, true);
        a = otaJonosta(palvelupisteNmrAlus, "otaJonosta");
        handleCustomers(palvelupisteNmrLoppu, a, false);
    }
    /**
     * Compiles and outputs simulation results, including average processing times and customer satisfaction.
     */
    @Override
    protected void tulokset() {
        System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
        System.out.println("---------------------------------------------------------");
        System.out.println("Keskimääräinen läpikulku aika on:  " + Asiakas.getAverageTimeSpent());
        System.out.println("Asiakkaita palveltu: " + Asiakas.getTotalCustomers());
        System.out.println("Keskimääräinen asiakastyytyväisyys: " + Asiakas.getHappyRating());


        // UUTTA graafista
        kontrolleri.naytaLoppuaika(Kello.getInstance().getAika(), Asiakas.getHappyRating(), Asiakas.getTotalCustomers(), palvelupisteet);
    }

    /**
     * Generates a boolean value based on the set percentage of reserved customers.
     * @return True if the generated number is less than or equal to the percentage of reserved customers; otherwise, false.
     */
    protected boolean generateTrueFalse() {
        Random random = new Random();
        double rn = random.nextDouble() * 100;
//        System.out.println("Random: " + rn + " Prosentti: " + getVaratutProsentti());
        return rn <= getVaratutProsentti() || getVaratutProsentti() == 100;
    }

    /**
     * Adds a customer to the queue at a specified service point.
     * @param palvelupisteNmr The identifier for the service point.
     * @param cmd The command determining whether to add to a regular or reserved queue.
     * @param asiakas The customer to be added to the queue.
     */
    protected void lisaaJonoon(String palvelupisteNmr, String cmd, Asiakas asiakas) {
        switch (cmd) {
            case "lisaaJonoon":
                palvelupisteet.get(palvelupisteNmr).stream()
                        .reduce((a, b) -> a.getJononPituus() < b.getJononPituus() ? a : b)
                        .get().lisaaJonoon(asiakas);
                break;
            case "lisaaVarattuJonoon":
                palvelupisteet.get(palvelupisteNmr).stream()
                        .reduce((a, b) -> a.getVaratunJononPituus() < b.getVaratunJononPituus() ? a : b)
                        .get().lisaaVarattuJonoon(asiakas);
                break;
        }
    }

    /**
     * Removes and returns customers from the queue of a specified service point.
     * @param palvelupisteNmr The identifier for the service point.
     * @param cmd The command determining whether to remove from a regular or reserved queue.
     * @return A list of customers removed from the queue.
     */
    protected ArrayList<Asiakas> otaJonosta(String palvelupisteNmr, String cmd) {
        ArrayList <Asiakas> asiakkaat = new ArrayList<>();
        switch (cmd) {
            case "otaJonosta":
                for (Palvelupiste p : palvelupisteet.get(palvelupisteNmr)) {
                        asiakkaat.add(p.otaJonosta());
                }
                break;
            case "otaVarattuJonosta":
                for (Palvelupiste p : palvelupisteet.get(palvelupisteNmr)) {
                        asiakkaat.add(p.otaVarattuJonosta());
                }
                break;
        }
        return asiakkaat;
    }

    /**
     * Handles the processing of customers after service, determining if they should move to another service point or exit the simulation.
     * @param palvelupisteNmr The service point identifier for customer redirection.
     * @param a The list of customers to be processed.
     * @param varattu Indicates whether the service is for reserved customers.
     */
    protected void handleCustomers(String palvelupisteNmr, ArrayList<Asiakas> a, boolean varattu) {
        Iterator<Asiakas> iterator = a.iterator();
        while (iterator.hasNext()) {
            Asiakas asiakas = iterator.next();
            if (asiakas != null) {
                if (varattu) {
                    if (new Random().nextBoolean() && !palvelupisteNmr.equals("4")) {
                        lisaaJonoon(palvelupisteNmr, "lisaaVarattuJonoon", asiakas);
                    } else {
                        asiakas.setPoistumisaika(Kello.getInstance().getAika());
                        asiakas.raportti();
                    }
                } else {
                    if (new Random().nextBoolean() && !palvelupisteNmr.equals("4")) {
                        lisaaJonoon(palvelupisteNmr, "lisaaJonoon", asiakas);
                    } else {
                        asiakas.setPoistumisaika(Kello.getInstance().getAika());
                        asiakas.raportti();
                    }
                }
                iterator.remove();
            }
        }
    }

}
