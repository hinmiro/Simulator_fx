package controller;

public interface IKontrolleriForV {

    // Rajapinta, joka tarjotaan  käyttöliittymälle:

    public void kaynnistaSimulointi();
    public void nopeuta();
    public void hidasta();
    public void nollaaKello();

    public void lisaaPalvelu(String lisattavaPiste);
    public void poistaPalvelu(String poistettavaPiste);
    public void nopeutaHidasta(double value);

    public void initializeData();
}
