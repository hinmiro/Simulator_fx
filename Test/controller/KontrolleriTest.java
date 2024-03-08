package controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.UusiGuiKontolleri;

class KontrolleriTest {
    private Kontrolleri kontrolleri;
    private UusiGuiKontolleri ui;

    @BeforeEach
    void setUp() {
        ui = new UusiGuiKontolleri() {
            @Override
            public String getAika() {
                return "5000";
            }

            @Override
            public String getViive() {
                return "100";
            }

            @Override
            public String getVaratutAsiakkaat() {
                return "10";
            }
        };
        kontrolleri = new Kontrolleri(ui);
    }



    @Test
    void testKaynnistaSimulointi() {
        ui.getAika();
        ui.getViive();
        ui.getVaratutAsiakkaat();
        kontrolleri.kaynnistaSimulointi();
    }
}
