package simu.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AsiakasTest {

    private Asiakas asiakas;

    @BeforeEach
    void setUp() {
        asiakas = new Asiakas(false);
    }

    @Test
    void testAverageTimeSpent(){
        // Asiakkaita ei ole saapunut
        assertEquals(0, asiakas.getAverageTimeSpent());
        // Yksi asiakas kuluttanut 40 aika yksikköä.
        asiakas.setSaapumisaika(10);
        asiakas.setPoistumisaika(50);
        assertEquals(40, asiakas.getAverageTimeSpent());
    }

    @Test
    void testCalculateHappyRating(){
        assertEquals(5, Asiakas.calculateHappyRating(39));
        assertEquals(4, Asiakas.calculateHappyRating(42));
        assertEquals(3, Asiakas.calculateHappyRating(47));
        assertEquals(2, Asiakas.calculateHappyRating(52));
        assertEquals(1, Asiakas.calculateHappyRating(60));
    }
}
