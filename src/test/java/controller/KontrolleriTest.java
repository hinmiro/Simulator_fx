package controller;


import javafx.scene.canvas.GraphicsContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import simu.framework.Kello;
import simu.model.OmaMoottori;
import view.*;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class KontrolleriTest {
    private static IVisualisointi view;
    private static Kontrolleri kontrolleri;
    private static UusiGuiKontolleri ui;
    private static OmaMoottori omaMoottori;

    @BeforeAll
    public static void setUp() {
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

            @Override
            public IVisualisointi getVisualisointi() {
                return new IVisualisointi() {
                    @Override
                    public void tyhjennaNaytto() {}

                    @Override
                    public void uusiPalveluPiste(String palveluTyyppi, int size) {}

                    @Override
                    public void deletePalveluPiste(String palveluTyyppi, int size) {}

                    @Override
                    public void showPalvelupisteet() {}

                    @Override
                    public void uusiAsiakas() {}

                    @Override
                    public void naytaSimulointiAika() {}

                    @Override
                    public void naytaVirheIlmoitus(String s) {}

                    @Override
                    public GraphicsContext getGraphicsContext2D() {
                        return null;
                    }

                    @Override
                    public double getWidth() {
                        return 0;
                    }

                    @Override
                    public double getHeight() {
                        return 0;
                    }

                    @Override
                    public void setLoppuaika(double d, double dd, int i, HashMap hashMap) {}
                };
            }

        };
        kontrolleri = new Kontrolleri(ui);
        omaMoottori = new OmaMoottori(kontrolleri);
    }



    @Test
    void testKaynnistaSimulointi() {
        assertDoesNotThrow(() -> kontrolleri.kaynnistaSimulointi());
    }

    @Test
    void testInitializeData() {
        assertDoesNotThrow(() -> kontrolleri.initializeData());
    }

    @Test
    void nollaaKelloTest() {
        Kello.getInstance().setAika(0);
        assertEquals(0, Kello.getInstance().getAika());
    }

    @Test
    void nopeutaTest() {
        assertEquals(0, omaMoottori.getViive());
        kontrolleri.nopeuta();
        assertEquals(omaMoottori.getViive()*0.9, omaMoottori.getViive());
    }

    @Test
    void hidasta() {
        assertEquals(0, omaMoottori.getViive());
        kontrolleri.hidasta();
        assertEquals(omaMoottori.getViive()*1.10, omaMoottori.getViive());
    }
}
