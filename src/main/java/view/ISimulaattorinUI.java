package view;

public interface ISimulaattorinUI {
	
	// Kontrolleri tarvitsee syötteitä, jotka se välittää Moottorille
	public double getAika();
	public long getViive();
	public int getVaratutAsiakkaat();
	public void naytaVirheIlmoitus(String s);
	public void lisaaUusiPalvelupiste(String lisattavaPiste);
	public void poistaPalvelupiste(String poistettavaPiste);


	//Kontrolleri antaa käyttöliittymälle tuloksia, joita Moottori tuottaa 
	public void setLoppuaika(double aika, double happyCustomer);
	
	// Kontrolleri tarvitsee  
	public IVisualisointi getVisualisointi();

}
