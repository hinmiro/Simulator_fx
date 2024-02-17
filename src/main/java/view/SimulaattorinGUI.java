package view;


import java.text.DecimalFormat;
import controller.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import simu.framework.Trace;
import simu.framework.Trace.Level;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

import static javafx.scene.paint.Color.LIGHTBLUE;
import static javafx.scene.paint.Color.LIGHTGREY;


public class SimulaattorinGUI extends Application implements ISimulaattorinUI {

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

    private IVisualisointi naytto;


    @Override
    public void init() {

        Trace.setTraceLevel(Level.INFO);

        kontrolleri = new Kontrolleri(this);
    }

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


            primaryStage.setTitle("Simulaattori");

            kaynnistaButton = new Button();
            kaynnistaButton.setText("Käynnistä simulointi");
            kaynnistaButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    nopeutaButton.setDisable(false);
                    hidastaButton.setDisable(false);
                    kontrolleri.kaynnistaSimulointi();
                    //kaynnistaButton.setDisable(true);
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
                    getVisualisointi().tyhjennaNaytto();
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

            naytto = new Visualisointi2(500, 300);


            // TÃ¤ytetÃ¤Ã¤n boxi:
            hBox.getChildren().addAll(grid, (Canvas) naytto);




            Scene scene = new Scene(hBox);
            scene.getStylesheets().add("style.css");
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Käyttöliittymän rajapintametodit (kutsutaan kontrollerista)

    @Override
    public int getVaratutAsiakkaat() { return Integer.parseInt(varatut.getText());}

    @Override
    public double getAika() {
        return Double.parseDouble(aika.getText());
    }

    @Override
    public long getViive() {
        return Long.parseLong(viive.getText());
    }

    @Override
    public void setLoppuaika(double aika, double happyCustomer) {
        DecimalFormat formatter = new DecimalFormat("#0.00");
        this.tulos.setText(formatter.format(aika));
        this.happyCustomer.setText(formatter.format(happyCustomer));
        hidastaButton.setDisable(true);
        nopeutaButton.setDisable(true);
    }
    @Override
    public void naytaVirheIlmoitus(String virhe) {
        naytto.naytaVirheIlmoitus(virhe);
    }

    @Override
    public IVisualisointi getVisualisointi() {
        return naytto;
    }
}




