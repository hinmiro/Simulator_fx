package simu.model;

import dao.SimuDao;
import entity.Simu;
import simu.framework.*;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import controller.IKontrolleriForM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class OmaMoottori extends Moottori {

    /**
     * Saapumisprosessi instance that handles the arrival process of the simulation.
     */
    private Saapumisprosessi saapumisprosessi;

    /**
     * Constant that defines the maximum capacity for a service point.
     */
    private final int MAX_CAP = 4;

    /**
     * A HashMap that stores the service points. The key is a String that represents the service point type,
     * and the value is an ArrayList of Palvelupiste objects, which represent the service points of that type.
     */
    private HashMap<String, ArrayList<Palvelupiste>> palvelupisteet = new HashMap<>();

    /**
     * An integer that represents the percentage of customers who have a reservation.
     */
    private int prosentti;

    /**
     * SimuDao instance that handles the data access operations for the simulation.
     */
    private SimuDao dao;

    /**
     * Constructor for the OmaMoottori class. It takes an IKontrolleriForM object as a parameter,
     * calls the superclass constructor with this object, and then calls the initializeData method to initialize the simulation data.
     *
     * @param kontrolleri An instance of a class that implements the IKontrolleriForM interface.
     */
    public OmaMoottori(IKontrolleriForM kontrolleri) {
        super(kontrolleri);
        initializeData();
    }

    /**
     * This method initializes the data for the simulation.
     */
    public void initializeData(){
        dao = new SimuDao();
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
     * This method adds a new service point of the given type.
     * @param type The type of the service point to be added.
     */
    public void addPalvelu(String type) {
        switch (type) {
            case "Infopiste":
                if (palvelupisteet.get("0").size() <= MAX_CAP) {
                    palvelupisteet.get("0").add(new Palvelupiste(new Normal(10, 6), tapahtumalista, TapahtumanTyyppi.INFOTISKI, "Info"));
                    System.out.println(palvelupisteet.get("0").size() + " infopisteet");
                    kontrolleri.visualisoiPalvelupiste(type, palvelupisteet.get("0").size());
                }
                break;
            case "Uusi tili":
                if (palvelupisteet.get("1").size() <= MAX_CAP) {
                    palvelupisteet.get("1").add(new Palvelupiste(new Normal(10, 10), tapahtumalista, TapahtumanTyyppi.UUDEN_TILIN_AVAUS, "Uudet tilit"));
                    System.out.println(palvelupisteet.get("1").size() + " uudet tilit");
                    kontrolleri.visualisoiPalvelupiste(type, palvelupisteet.get("1").size());
                }
                break;
            case "Talletuspiste":
                if(palvelupisteet.get("2").size() <= MAX_CAP) {
                    palvelupisteet.get("2").add(new Palvelupiste(new Normal(5, 3), tapahtumalista, TapahtumanTyyppi.TALLETUS, "Talletus"));
                    System.out.println(palvelupisteet.get("2").size() + " talletuspisteet");
                    kontrolleri.visualisoiPalvelupiste(type, palvelupisteet.get("2").size());
                }
                break;
            case "Sijoitusneuvonta":
                if (palvelupisteet.get("3").size() <= MAX_CAP) {
                    palvelupisteet.get("3").add(new Palvelupiste(new Normal(6, 9), tapahtumalista, TapahtumanTyyppi.SIJOITUS_PALVELUT, "Sijoituspalvelut"));
                    System.out.println(palvelupisteet.get("3").size() + " sijoitusneuvonta");
                    kontrolleri.visualisoiPalvelupiste(type, palvelupisteet.get("3").size());
                }
                break;
        }
    }

    /**
     * This method removes a service point of the given type.
     * @param type The type of the service point to be removed.
     */
    public void deletePalvelu(String type) {
        switch (type) {
            case "Infopiste":
                if (palvelupisteet.get("0").size() > 1) {// Ei voi poistaa viimeistä palvelupistettä (joka on aina olemassa
                    palvelupisteet.get("0").remove(palvelupisteet.get("0").size() - 1);
                    kontrolleri.unvisualisoiPalvelupiste(type, palvelupisteet.get("0").size());
                }
                System.out.println(palvelupisteet.get("0").size() + " infopisteet");
                break;
            case "Uusi tili":
                if (palvelupisteet.get("1").size() > 1) {// Ei voi poistaa viimeistä palvelupistettä (joka on aina olemassa
                    palvelupisteet.get("1").remove(palvelupisteet.get("1").size() - 1);
                    kontrolleri.unvisualisoiPalvelupiste(type, palvelupisteet.get("1").size());
                }
                System.out.println(palvelupisteet.get("1").size() + " uudet tilit");
                break;
            case "Talletuspiste":
                if (palvelupisteet.get("2").size() > 1){ // Ei voi poistaa viimeistä palvelupistettä (joka on aina olemassa
                    palvelupisteet.get("2").remove(palvelupisteet.get("2").size() - 1);
                    kontrolleri.unvisualisoiPalvelupiste(type, palvelupisteet.get("2").size());
                }
                System.out.println(palvelupisteet.get("2").size() + " talletuspisteet");
                break;
            case "Sijoitusneuvonta":
                if (palvelupisteet.get("3").size() > 1) {// Ei voi poistaa viimeistä palvelupistettä (joka on aina olemassa
                    palvelupisteet.get("3").remove(palvelupisteet.get("3").size() - 1);
                    kontrolleri.unvisualisoiPalvelupiste(type, palvelupisteet.get("3").size());
                }
                System.out.println(palvelupisteet.get("3").size() + " sijoitusneuvonta");
                break;
        }
    }

    /**
     * This method sets the percentage of customers who have a reservation.
     * @param uusiProsentti The new percentage of customers who have a reservation.
     */
    public void setProsentti(int uusiProsentti) {
        prosentti = uusiProsentti;
    }

    /**
     * This method initializes the arrival process for the simulation.
     */
    @Override
    protected void alustukset() {
        saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
    }

    /**
     * This method executes the event of the given type.
     * @param t The event to be executed.
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
                kontrolleri.naytaSimulointiAika();
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
                if (!a.isEmpty()) {
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
     * This method attempts to execute the C-phase events for the simulation.
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
     * This method handles the customers at the service points.
     * @param a The list of customers.
     * @param palvelupisteNmrAlus The starting service point number.
     * @param palvelupisteNmrLoppu The ending service point number.
     */
    public void palvelee(ArrayList<Asiakas> a, String palvelupisteNmrAlus, String palvelupisteNmrLoppu) {
        a = otaJonosta(palvelupisteNmrAlus, "otaVarattuJonosta");
        handleCustomers(palvelupisteNmrLoppu, a, true);
        a = otaJonosta(palvelupisteNmrAlus, "otaJonosta");
        handleCustomers(palvelupisteNmrLoppu, a, false);
    }

    /**
     * This method generates the simulation results.
     */
    @Override
    protected void tulokset() {
        for (ArrayList<Palvelupiste> pList : palvelupisteet.values()) {
            for (Palvelupiste p : pList) {
                p.setKayttoaste();
            }
        }
        System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
        System.out.println("---------------------------------------------------------");
        System.out.println("Keskimääräinen läpikulku aika on:  " + Asiakas.getAverageTimeSpent());
        System.out.println("Asiakkaita palveltu: " + Asiakas.getTotalCustomers());
        System.out.println("Keskimääräinen asiakastyytyväisyys: " + Asiakas.getHappyRating());


        dao.persist(new Simu(Kello.getInstance().getAika(), Asiakas.getTotalCustomers(), palvelupisteet.size(),
                Asiakas.getHappyRating(), getVaratutProsentti(), palvelupisteet.get("0").get(0).getPalvelunkesto(), palvelupisteet.get("0").get(0).getKayttoAste(),
                palvelupisteet.get("1").get(0).getPalvelunkesto(), palvelupisteet.get("1").get(0).getKayttoAste(), palvelupisteet.get("2").get(0).getPalvelunkesto(), palvelupisteet.get("2").get(0).getKayttoAste(),
                palvelupisteet.get("3").get(0).getPalvelunkesto(), palvelupisteet.get("3").get(0).getKayttoAste()));


        // UUTTA graafista
        kontrolleri.naytaLoppuaika(Kello.getInstance().getAika(), Asiakas.getHappyRating(), Asiakas.getTotalCustomers(), palvelupisteet);

    }

    /**
     * This method generates a boolean value based on the percentage of customers who have a reservation.
     * @return True if the generated random number is less than or equal to the percentage of customers who have a reservation, false otherwise.
     */
    protected boolean generateTrueFalse() {
        Random random = new Random();
        double rn = random.nextDouble() * 100;
        return rn <= getVaratutProsentti() || getVaratutProsentti() == 100;
    }

    /**
     * This method adds a customer to the queue of the given service point.
     * @param palvelupisteNmr The number of the service point.
     * @param cmd The command to be executed.
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
     * This method removes a customer from the queue of the given service point.
     * @param palvelupisteNmr The number of the service point.
     * @param cmd The command to be executed.
     * @return The list of customers removed from the queue.
     */
    protected ArrayList<Asiakas> otaJonosta(String palvelupisteNmr, String cmd) {
        ArrayList<Asiakas> asiakkaat = new ArrayList<>();
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
     * This method handles the customers at the service points.
     * @param palvelupisteNmr The number of the service point.
     * @param a The list of customers.
     * @param varattu The reservation status of the customers.
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
