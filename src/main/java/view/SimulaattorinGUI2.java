package view;

/*
 *
 The SimulaattorinGUI2 class extends Application and implements the ISimulaattorinUI interface to provide a graphical user interface for the simulation. It contains UI elements for inputting simulation parameters and controls for starting, pausing, and adjusting the simulation's speed.
 */

import java.text.DecimalFormat;

import controller.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import simu.framework.Trace;
import simu.framework.Trace.Level;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;


public class SimulaattorinGUI2 extends Application implements ISimulaattorinUI {

    //Kontrollerin esittely (tarvitaan käyttöliittymässä)
    private IKontrolleriForV kontrolleri;

    // Käyttöliittymäkomponentit:
    private TextField aika;
    private TextField viive;
    private TextField varatut;
    private Label tulos;
    private Label aikaLabel;
    private Label viiveLabel;
    private Label varatutLabel;
    private Label tulosLabel;
    private Label happyCustomerLabel;
    private Label happyCustomer;


    private Button kaynnistaButton;
    private Button hidastaButton;
    private Button nopeutaButton;
    private Button clearButton;
    private Button lisaaPalvelupiste;
    private Button poistaPalvelupiste;

    private ComboBox<String> comboLisaa;
    private ComboBox<String> comboPoista;

    private IVisualisointi naytto;
    private IVisualisointi naytto2;
    private IVisualisointi naytto3;
    private IVisualisointi naytto4;

    /**
     * Initializes any necessary elements or data structures for the UI.
     * Typically used to set initial values or configurations for the UI elements.
     */
    @Override
    public void init() {

        Trace.setTraceLevel(Level.INFO);

       // kontrolleri = new Kontrolleri(this);
    }

    /**
     * Starts and displays the application's main stage.
     * @param primaryStage The primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage primaryStage) {
        // Käyttöliittymän rakentaminen
        try {

            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    Platform.exit();
                    System.exit(0);
                }
            });


            primaryStage.getIcons().add(new Image("dollar.png"));
            primaryStage.setTitle("Pankkipalvelut Simulaattori");

            kaynnistaButton = new Button();
            kaynnistaButton.setText("Käynnistä simulointi");
            kaynnistaButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    nopeutaButton.setDisable(false);
                    hidastaButton.setDisable(false);
                    kontrolleri.kaynnistaSimulointi();
                    kaynnistaButton.setDisable(true);
                }
            });

            clearButton = new Button();
            clearButton.setText("Tyhjennä");
            clearButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    aika.clear();
                    viive.clear();
                    varatut.clear();
                    tulos.setText("");
                    happyCustomer.setText("");
                    kaynnistaButton.setDisable(false);
                    naytto.tyhjennaNaytto();
                    naytto2.tyhjennaNaytto();
                    naytto3.tyhjennaNaytto();
                    naytto4.tyhjennaNaytto();
                    kontrolleri.nollaaKello();
                    for (int i = 0; i < 100; i++) {
                        System.out.println();
                    }
                }
            });


            poistaPalvelupiste = new Button();
            poistaPalvelupiste.setText("Poista Palvelupiste");
            poistaPalvelupiste.setDisable(false);
            comboPoista = new ComboBox<>();
            comboPoista.getItems().addAll("Infopiste","Uusi tili", "Talletuspiste", "Sijoitusneuvonta");
            poistaPalvelupiste.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String poistettavaPalvelu = comboPoista.getValue();
                    poistaPalvelupiste(poistettavaPalvelu);
                }
            });


            lisaaPalvelupiste = new Button();
            lisaaPalvelupiste.setText("Lisää Palvelupiste");
            lisaaPalvelupiste.setDisable(false);
            comboLisaa = new ComboBox<>();
            comboLisaa.getItems().addAll("Infopiste","Uusi tili", "Talletuspiste", "Sijoitusneuvonta");
            lisaaPalvelupiste.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String lisattavaPalvelu = comboLisaa.getValue();
                    lisaaUusiPalvelupiste(lisattavaPalvelu);
                }
            });

            hidastaButton = new Button();
            hidastaButton.setText("Hidasta");
            hidastaButton.setOnAction(e -> kontrolleri.hidasta());
            hidastaButton.setDisable(true);

            nopeutaButton = new Button();
            nopeutaButton.setText("Nopeuta");
            nopeutaButton.setOnAction(e -> kontrolleri.nopeuta());
            nopeutaButton.setDisable(true);

            aikaLabel = new Label("Simulointiaika:");
            aikaLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            aika = new TextField();
            aika.setPromptText("Syötä aika");
            aika.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
            aika.setPrefWidth(150);

            viiveLabel = new Label("Viive:");
            viiveLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            viive = new TextField();
            viive.setPromptText("Syötä viive");
            viive.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
            viive.setPrefWidth(150);

            varatutLabel = new Label("Varatut asiakkaat:");
            varatutLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            varatut = new TextField();
            varatut.setPromptText("Syötä prosentti");
            varatut.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
            varatut.setPrefWidth(150);


            tulosLabel = new Label("Kokonaisaika:");
            tulosLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            tulos = new Label();
            tulos.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            tulos.setPrefWidth(150);

            happyCustomerLabel = new Label("Asiakastyytyväisyys:");
            happyCustomerLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            happyCustomer = new Label();
            happyCustomer.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            happyCustomer.setPrefWidth(150);




            HBox hBox = new HBox();
            hBox.setPadding(new Insets(15, 12, 15, 12)); // marginaalit ylÃ¤, oikea, ala, vasen
            hBox.setSpacing(10);   // noodien välimatka 10 pikseliä

            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setVgap(10);
            grid.setHgap(5);

            grid.add(aikaLabel, 0, 0);   // sarake, rivi
            grid.add(aika, 1, 0);          // sarake, rivi
            grid.add(viiveLabel, 0, 1);      // sarake, rivi
            grid.add(viive, 1, 1);           // sarake, rivi
            grid.add(varatutLabel, 0,2);
            grid.add(varatut, 1, 2);
            grid.add(tulosLabel, 0, 3);      // sarake, rivi
            grid.add(tulos, 1, 3);           // sarake, rivi
            grid.add(happyCustomerLabel, 0, 4);
            grid.add(happyCustomer, 1, 4);
            grid.add(kaynnistaButton, 0, 5);  // sarake, rivi
            grid.add(clearButton, 0, 6);
            grid.add(nopeutaButton, 1, 5);   // sarake, rivi
            grid.add(hidastaButton, 1, 6);   // sarake, rivi
            grid.add(lisaaPalvelupiste, 0, 8);
            grid.add(comboLisaa, 1, 8);
            grid.add(poistaPalvelupiste, 0, 9);
            grid.add(comboPoista, 1, 9);

            GridPane nahtava = new GridPane();
            naytto = new Visualisointi2(500, 300);
            naytto2 = new Visualisointi(500, 300);
            naytto3 = new Visualisointi2(500, 300);
            naytto4 = new Visualisointi2(500, 300);
            nahtava.setHgap(10);
            nahtava.setVgap(10);
            nahtava.add((Canvas) naytto, 0, 0);
            nahtava.add((Canvas) naytto2, 1, 0);
            nahtava.add((Canvas) naytto3, 0, 1);
            nahtava.add((Canvas) naytto4, 1, 1);

            // TÃ¤ytetÃ¤Ã¤n boxi:
            hBox.getChildren().addAll(grid, nahtava);




            Scene scene = new Scene(hBox);
            scene.getStylesheets().add("style.css");
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Retrieves the percentage of reserved customers specified by the user in the UI.
     * @return The percentage of reserved customers input by the user.
     */
    @Override
    public int getVaratutAsiakkaat() { return Integer.parseInt(varatut.getText());}

    /**
     * Retrieves the simulation time specified by the user in the UI.
     * @return The simulation time input by the user.
     */
    @Override
    public double getAika() {
        return Double.parseDouble(aika.getText());
    }

    /**
     * Retrieves the simulation delay (viive) specified by the user in the UI.
     * @return The simulation delay time input by the user.
     */
    @Override
    public long getViive() {
        return Long.parseLong(viive.getText());
    }

    /**
     * Updates the UI with the final simulation time and happiness rating upon simulation completion.
     * @param aika The final simulation time.
     * @param happyCustomer The final average happiness rating of all customers.
     */
    @Override
    public void setLoppuaika(double aika, double happyCustomer) {
        DecimalFormat formatter = new DecimalFormat("#0.00");
        this.tulos.setText(formatter.format(aika));
        this.happyCustomer.setText(formatter.format(happyCustomer));
        hidastaButton.setDisable(true);
        nopeutaButton.setDisable(true);
        kaynnistaButton.setDisable(false);
    }

    /**
     * Displays an error message on the UI.
     * @param s The error message to be displayed.
     */
    @Override
    public void naytaVirheIlmoitus(String virhe) {
        naytto.naytaVirheIlmoitus(virhe);
    }

    /**
     * Adds a new service point (Palvelupiste) based on user selection.
     * @param lisattavaPiste The type of service point to be added.
     */
    @Override
    public void lisaaUusiPalvelupiste(String lisattavaPiste) {
           kontrolleri.lisaaPalvelu(lisattavaPiste);
    }

    /**
     * Removes an existing service point (Palvelupiste) based on user selection.
     * @param poistettavaPiste The type of service point to be removed.
     */
    @Override
    public void poistaPalvelupiste(String poistettavaPiste) {
          kontrolleri.poistaPalvelu(poistettavaPiste);
    }

    /**
     * Provides access to the visualization component (IVisualisointi) used in the simulation UI.
     * @return The visualization component used for displaying simulation data.
     */
    @Override
    public IVisualisointi getVisualisointi() {
        return naytto;
    }
}