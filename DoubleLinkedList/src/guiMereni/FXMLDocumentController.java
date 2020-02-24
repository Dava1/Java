package guiMereni;

import doubleList.KolekceException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import mereni.AbstrMereni;
import mereni.EnumPozice;
import mereni.Generator;
import mereni.Mereni;
import mereni.SpravceMereni;
import mereni.TypSenzoru;
import senzory.Senzor;

/**
 *
 * @author st55440
 */
public class FXMLDocumentController implements Initializable {

    private SpravceMereni<Mereni> merens;

    @FXML
    private DatePicker doData;
    @FXML
    private DatePicker odData;
    @FXML
    private AnchorPane paneMereni;
    @FXML
    private ListView<Mereni> mereniList;
    @FXML
    private ChoiceBox<EnumPozice> zpristupni;
    @FXML
    private ChoiceBox<TypSenzoru> typSenzoru;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        zpristupni.getItems().addAll(EnumPozice.values());
        zpristupni.setValue(EnumPozice.PRVNI);
        typSenzoru.getItems().addAll(TypSenzoru.values());
        typSenzoru.setValue(TypSenzoru.SENZOR_NA_ELEKTRIKA);
        merens = new AbstrMereni();
//        obsSenzor = FXCollections.observableArrayList("s","sd","sdq");
//        obsMereni = FXCollections.observableArrayList(merens);
    }

    @FXML
    private void zrus(ActionEvent event) {
        merens.zrus();
        mereniList.getItems().clear();
    }

    @FXML
    private void generuj(ActionEvent event) throws KolekceException {

    }

    @FXML
    private void save(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Zachovani");
        alert.setHeaderText("Chcete ulozit soubor");
        Optional<ButtonType> optional = alert.showAndWait();
        if (optional.isPresent()) {
            try {
                FileOutputStream fos = new FileOutputStream("Mereni.bin");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                Iterator<Mereni> iterator = merens.iterator();
                while (iterator.hasNext()) {
                    oos.writeObject(iterator.next());
                }
                oos.close();
                fos.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void maxSpotreba(ActionEvent event) {
        merens.MaxSpotreba(LocalDateTime.MIN, LocalDateTime.MIN);
    }

    @FXML
    private void vloz(ActionEvent event) throws NullPointerException, KolekceException {
        Senzor temp = Generator.generatorSenzor(typSenzoru.getValue());
        merens.vlozMereni(temp.getMereni(), zpristupni.getValue());
        mereniList.getItems().add(temp.getMereni());
    }

    @FXML
    private void start(ActionEvent event) {
    }

    @FXML
    private void stop(ActionEvent event) {
    }

    @FXML
    private void nacti(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Nacteni");
        alert.setHeaderText("Nacist soubor");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            mereniList.getItems().clear();
            merens.zrus();
            try {
                FileInputStream fis = new FileInputStream("Mereni.bin");
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (true) {
                    Object object = ois.readObject();
                    if (object == null) {
                        break;
                    } else {
                        Mereni mereni = (Mereni) object;
                        merens.vlozMereni(mereni, EnumPozice.PRVNI);
                        mereniList.getItems().add(mereni);
                    }
                }
            } catch (Exception e) {
                Logger.getLogger(FXMLDocumentController.class.getName()).
                        log(Level.SEVERE, null, e);
            }
        }
    }
}
