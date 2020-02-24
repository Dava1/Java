package gui;

import casovani.Casovac;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kolekce.KolekceException;
import krizovatka.IKrizovatka;
import krizovatka.KrizovatkaSimulator;

public class GuiKrizivatko extends Application {

    private ListView<String> listView = new ListView<>();
    private IKrizovatka krizavatka;
    private long i = 0;
    private final int max = 18;

    private Button start;
    private Button stop;
    private Button conf;
    private Button zrus;
    private Button reset;
    private Button save;
    private TextField sever;
    private TextField jih;
    private TextField zapad;
    private TextField vychod;
    private TextField semaforTextField;

    @Override
    public void start(Stage primaryStage) throws Exception {
        krizavatka = new KrizovatkaSimulator();
        listView.getItems().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                sever.setText(Long.toString(
                        krizavatka.getPocetCekajicichZeSmeru(IKrizovatka.Smer.SEVER)));
                jih.setText(Long.toString(
                        krizavatka.getPocetCekajicichZeSmeru(IKrizovatka.Smer.JIH)));
                vychod.setText(Long.toString(
                        krizavatka.getPocetCekajicichZeSmeru(IKrizovatka.Smer.VYCHOD)));
                zapad.setText(Long.toString(
                        krizavatka.getPocetCekajicichZeSmeru(IKrizovatka.Smer.ZAPAD)));
                krizavatka.setHlaseniSemaforu(t -> {
                    semaforTextField.setText(t.toString());
                });
                if (listView.getItems().size() > max) {
                    listView.getItems().remove(0);
                }
            }
        });
        BorderPane root = new BorderPane();
        root.setMaxWidth(300);
        root.setRight(createFlowPane());
        root.setLeft(createListView());
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.setTitle("Krizivatko");
        primaryStage.show();
    }

    private Node createFlowPane() {
        FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(5, 10, 0, 10));
        flow.setVgap(10);
        flow.setHgap(15);
        flow.setPrefWrapLength(250);

        sever = new TextField(Long.toString(
                krizavatka.getPocetCekajicichZeSmeru(IKrizovatka.Smer.SEVER)));
        vychod = new TextField(Long.toString(
                krizavatka.getPocetCekajicichZeSmeru(IKrizovatka.Smer.VYCHOD)));
        jih = new TextField(Long.toString(
                krizavatka.getPocetCekajicichZeSmeru(IKrizovatka.Smer.JIH)));
        zapad = new TextField(Long.toString(
                krizavatka.getPocetCekajicichZeSmeru(IKrizovatka.Smer.ZAPAD)));
        semaforTextField = new TextField();

        Label labeSEV = new Label("Auta ze severu");
        labeSEV.setPadding(new Insets(0, 5, 0, 0));
        Label labeVYCH = new Label("Auta z vychodu");
        labeVYCH.setPadding(new Insets(0, 0, 0, 0));
        Label labeJIH = new Label("Auta z jihu");
        labeJIH.setPadding(new Insets(0, 25, 0, 0));
        Label labeZAPAD = new Label("Auta ze zapadu");
        labeZAPAD.setPadding(new Insets(0, 1, 0, 0));
        Label labeSEMAFOR = new Label("Semafor");
        labeSEMAFOR.setPadding(new Insets(0, 36, 0, 0));

        start = new Button("Start");
        start.setOnAction((e) -> {
            try {
                startuj();
            } catch (KolekceException ex) {
                Logger.getLogger(GuiKrizivatko.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(GuiKrizivatko.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        stop = new Button("Stop");
        stop.setOnAction((e) -> {
            zustav();
            try {
                ulozeniAObnoveni();
            } catch (KolekceException ex) {
                Logger.getLogger(GuiKrizivatko.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(GuiKrizivatko.class.getName()).log(Level.SEVERE, null, ex);
            }        });
        conf = new Button("Config");
        conf.setOnAction((e) -> nastavConfig());
        zrus = new Button("Zrus");
        zrus.setOnAction((e) -> zrus());
        reset = new Button("Reset");
        reset.setOnAction((e) -> {
            try {
                reset();
            } catch (KolekceException ex) {
                Logger.getLogger(GuiKrizivatko.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(GuiKrizivatko.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        flow.getChildren().addAll(
                labeSEV, sever,
                labeVYCH, vychod,
                labeJIH, jih,
                labeZAPAD, zapad,
                labeSEMAFOR, semaforTextField,
                start, stop, zrus, conf, reset);
        return flow;
    }

    private Node createListView() {
        listView.setLayoutX(10);
        listView.setLayoutY(10);
        listView.setPrefWidth(300);
        return listView;
    }

    public void startuj() throws KolekceException, InterruptedException {
        Casovac.instance().start();

        krizavatka.setHlaseniSemaforu(
                s -> listView.getItems().add(String.format(
                        "T= %04d: Zmena smeru na %s\n", i, s)));

        krizavatka.setHlaseniPrijezduZeSmeru(
                IKrizovatka.Smer.SEVER,
                s -> listView.getItems().add(String.format(
                        "T= %04d: Prijezd ze severu %s\n", i, s)));
        krizavatka.setHlaseniOdjezduZeSmeru(
                IKrizovatka.Smer.SEVER,
                s -> listView.getItems().add(String.format(
                        "T= %04d: Odjezd ze severu %s\n", i, s)));

        krizavatka.setHlaseniPrijezduZeSmeru(
                IKrizovatka.Smer.JIH,
                s -> listView.getItems().add(String.format(
                        "T= %04d: Prijezd z jihu %s\n", i, s)));
        krizavatka.setHlaseniOdjezduZeSmeru(
                IKrizovatka.Smer.JIH,
                s -> listView.getItems().add(String.format(
                        "T= %04d: Odjezd z jihu %s\n", i, s)));

        krizavatka.setHlaseniPrijezduZeSmeru(
                IKrizovatka.Smer.VYCHOD,
                s -> listView.getItems().add(String.format(
                        "T= %04d: Prijezd z vychodu %s\n", i, s)));
        krizavatka.setHlaseniOdjezduZeSmeru(
                IKrizovatka.Smer.VYCHOD,
                s -> listView.getItems().add(String.format(
                        "T= %04d: Odjezd z vychodu %s\n", i, s)));

        krizavatka.setHlaseniPrijezduZeSmeru(
                IKrizovatka.Smer.ZAPAD,
                s -> listView.getItems().add(String.format(
                        "T= %04d: Prijezd ze zapadu %s\n", i, s)));
        krizavatka.setHlaseniOdjezduZeSmeru(
                IKrizovatka.Smer.ZAPAD,
                s -> listView.getItems().add(String.format(
                        "T= %04d: Odjezd ze zapadu %s\n", i, s)));
        i++;
    }

    public void zustav() {
        Casovac.instance().stop();
    }
    private TextField tf1;
    private TextField tf2;
    private TextField tf3;
    private TextField tf4;
    private TextField tf5;
    private TextField tf6;
    private TextField tf7;

    public void nastavConfig() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Configurace");
        alert.setHeaderText("Zadani parametru simularoty krizovatky");

        BorderPane borderPane = new BorderPane();
        VBox vboxLeft = addVboxLeft();
        VBox vboxCenter = addVboxCenter();
        VBox vboxRight = addVboxRight();
        borderPane.setLeft(vboxLeft);
        borderPane.setCenter(vboxCenter);
        borderPane.setRight(vboxRight);
        borderPane.setPrefSize(500, 300);
        alert.getDialogPane().setContent(borderPane);

        Optional<ButtonType> result = alert.showAndWait();
        ButtonType buttton = result.orElse(ButtonType.CANCEL);

        if (buttton == ButtonType.OK) {
            Integer.decode(tf1.getText());
            Integer.decode(tf2.getText());
            Integer.decode(tf3.getText());
            Integer.decode(tf4.getText());
            Integer.decode(tf5.getText());
            Integer.decode(tf6.getText());
            Integer.decode(tf7.getText());

            krizavatka.setCetnostPrijezdu(IKrizovatka.Smer.SEVER, Long.parseLong(tf1.getText()));
            krizavatka.setCetnostPrijezdu(IKrizovatka.Smer.VYCHOD, Long.parseLong(tf2.getText()));
            krizavatka.setCetnostPrijezdu(IKrizovatka.Smer.JIH, Long.parseLong(tf3.getText()));
            krizavatka.setCetnostPrijezdu(IKrizovatka.Smer.ZAPAD, Long.parseLong(tf4.getText()));
            krizavatka.setSemaforDobaZelena(IKrizovatka.SmerPrujezdu.SEVER_JIH, Long.parseLong(tf5.getText()));
            krizavatka.setSemaforDobaZelena(IKrizovatka.SmerPrujezdu.VYCHOD_ZAPAD, Long.parseLong(tf6.getText()));
            krizavatka.setDobaPrujezdu(Long.parseLong(tf7.getText()));
        } else {
            System.out.println("Canceled");
        }
    }

 
    private VBox addVboxLeft() {
        Label l1 = new Label("Cetnost ze severu (A1)");
        Label l2 = new Label("Cetnost z vychodu (A2)");
        Label l3 = new Label("Cetnost z jihu (A3)");
        Label l4 = new Label("Cetnost ze zapadu (A4)");
        Label l5 = new Label("Doba prujezdu sever-jih (K1)");
        Label l6 = new Label("Doba prujezdu vychod-zapad (K2)");
        Label l7 = new Label("Doba odjezdu auta (S1)");
        VBox vboxLeft = new VBox(16);
        vboxLeft.getChildren().addAll(l1, l2, l3, l4, l5, l6, l7);
        vboxLeft.setPrefWidth(250);
        vboxLeft.setPadding(new Insets(0, 10, 0, 10));
        return vboxLeft;
    }

    private VBox addVboxCenter() {
        tf1 = new TextField(Long.toString(krizavatka.getCetnostPrijezdu(
                IKrizovatka.Smer.SEVER)));
        tf2 = new TextField(Long.toString(krizavatka.getCetnostPrijezdu(
                IKrizovatka.Smer.VYCHOD)));
        tf3 = new TextField(Long.toString(krizavatka.getCetnostPrijezdu(
                IKrizovatka.Smer.JIH)));
        tf4 = new TextField(Long.toString(krizavatka.getCetnostPrijezdu(
                IKrizovatka.Smer.ZAPAD)));
        tf5 = new TextField(Long.toString(krizavatka.getSemaforDobaZelena(
                IKrizovatka.SmerPrujezdu.SEVER_JIH)));
        tf6 = new TextField(Long.toString(krizavatka.getSemaforDobaZelena(
                IKrizovatka.SmerPrujezdu.VYCHOD_ZAPAD)));
        tf7 = new TextField(Long.toString(krizavatka.getDobaPrujezdu()));
        VBox vboxCenter = new VBox(5);
        vboxCenter.getChildren().addAll(tf1, tf2, tf3, tf4, tf5, tf6, tf7);
        vboxCenter.setPrefWidth(120);
        vboxCenter.setPadding(new Insets(0, 10, 0, 10));
        return vboxCenter;
    }

    private VBox addVboxRight() {
        Label lr1 = new Label("aut/hod");
        Label lr2 = new Label("aut/hod");
        Label lr3 = new Label("aut/hod");
        Label lr4 = new Label("aut/hod");
        Label lr5 = new Label("ms");
        Label lr6 = new Label("ms");
        Label lr7 = new Label("ms");
        VBox vboxRight = new VBox(16);
        vboxRight.getChildren().addAll(lr1, lr2, lr3, lr4, lr5, lr6, lr7);
        vboxRight.setPrefWidth(100);
        vboxRight.setPadding(new Insets(0, 10, 0, 10));
        return vboxRight;
    }

    public void zrus() {
        listView.getItems().clear();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void reset() throws KolekceException, InterruptedException {
        i = 0;
        krizavatka = new KrizovatkaSimulator();
        
        krizavatka.setHlaseniSemaforu(
                s -> listView.getItems().add(String.format(
                        "T= %04d: Zmena smeru na %s\n", i, s)));

        krizavatka.setHlaseniPrijezduZeSmeru(
                IKrizovatka.Smer.SEVER,
                s -> listView.getItems().add(String.format(
                        "T= %04d: Prijezd ze severu %s\n", i, s)));
        krizavatka.setHlaseniOdjezduZeSmeru(
                IKrizovatka.Smer.SEVER,
                s -> listView.getItems().add(String.format(
                        "T= %04d: Odjezd ze severu %s\n", i, s)));

        krizavatka.setHlaseniPrijezduZeSmeru(
                IKrizovatka.Smer.JIH,
                s -> listView.getItems().add(String.format(
                        "T= %04d: Prijezd z jihu %s\n", i, s)));
        krizavatka.setHlaseniOdjezduZeSmeru(
                IKrizovatka.Smer.JIH,
                s -> listView.getItems().add(String.format(
                        "T= %04d: Odjezd z jihu %s\n", i, s)));

        krizavatka.setHlaseniPrijezduZeSmeru(
                IKrizovatka.Smer.VYCHOD,
                s -> listView.getItems().add(String.format(
                        "T= %04d: Prijezd z vychodu %s\n", i, s)));
        krizavatka.setHlaseniOdjezduZeSmeru(
                IKrizovatka.Smer.VYCHOD,
                s -> listView.getItems().add(String.format(
                        "T= %04d: Odjezd z vychodu %s\n", i, s)));

        krizavatka.setHlaseniPrijezduZeSmeru(
                IKrizovatka.Smer.ZAPAD,
                s -> listView.getItems().add(String.format(
                        "T= %04d: Prijezd ze zapadu %s\n", i, s)));
        krizavatka.setHlaseniOdjezduZeSmeru(
                IKrizovatka.Smer.ZAPAD,
                s -> listView.getItems().add(String.format(
                        "T= %04d: Odjezd ze zapadu %s\n", i, s)));
        i++;
    }

    private void ulozeniAObnoveni() throws KolekceException, InterruptedException {
        try {
            String conSev = "Sever : " + sever.getText();
            String conJih = "Jih : " + jih.getText();
            String conZapad = "Zapad : " + zapad.getText();
            String conVychod = "Vychod : " + vychod.getText();
            List<String> stav = new ArrayList<>();
            stav.add(conSev);
            stav.add(conJih);
            stav.add(conZapad);
            stav.add(conVychod);
            File file = new File("stav.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(conSev +"\n");
            bw.write(conJih+"\n");
            bw.write(conVychod+"\n");
            bw.write(conZapad+"\n");
            bw.close();
            fw.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ulozeni");
            alert.setHeaderText("");
            alert.setContentText("Stav je ulozen");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
     reset();
    }
}
