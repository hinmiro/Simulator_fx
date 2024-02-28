package simu.model;
import simu.framework.Kello;
import simu.model.Asiakas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AsiakasTest {

    Asiakas asiakas;

    @BeforeEach
    void setUp() {
        Asiakas.resetStatics();
        asiakas = new Asiakas(false);
    }

    @Test
    void testSetSaapumisAika() {
        System.out.println("setSaapumisAika");
        asiakas.setSaapumisaika(5);
        assertEquals(5, asiakas.getSaapumisaika());
    }
    @Test
    void testGetTotalCustomers() {
        System.out.println("getTotalCustomers");
        asiakas.setPoistumisaika(10);
        asiakas.setSaapumisaika(5);
        assertEquals(1, asiakas.getTotalCustomers());
    }
}
