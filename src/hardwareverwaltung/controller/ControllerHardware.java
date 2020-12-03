package hardwareverwaltung.controller;

import hardwareverwaltung.logic.DataManager;
import hardwareverwaltung.logic.ViewManager;
import hardwareverwaltung.model.Drucker;
import hardwareverwaltung.model.Hardware;
import hardwareverwaltung.model.Raum;
import hardwareverwaltung.util.AlertWindow;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class ControllerHardware implements Initializable {
    @FXML
    protected TextField textfieldId;
    @FXML
    protected TextField textfieldSeriennummer;
    @FXML
    protected TextField textfieldModell;
    @FXML
    protected TextField textfieldHersteller;
    @FXML
    protected ChoiceBox<String> choiceboxStatus;
    @FXML
    protected TextField textfieldHerstellergarantie;
    @FXML
    protected DatePicker datepickerLieferdatum;
    @FXML
    protected ChoiceBox<Raum> choiceboxRaum;
    @FXML
    protected ListView<Hardware> listview;

    /**
     * Generiert das Hardware Objekt und sichert es im DataManager
     * @param e das Button event
     */
    @FXML
    private void handleButtonSichern(ActionEvent e) {
        //Check for existing Hardware
        Hardware hw = DataManager.getInstance().findHardwareByID(textfieldId.getText());
        if(hw == null) {
            // Create new Hardware
            try {
                DataManager.getInstance().add(this.generateHardware());
                clear();
            } catch (IllegalArgumentException err) {
                AlertWindow.createErrorWindow(err.getMessage());
            }
        } else {
            // Update existing Hardware
            update(hw);
            clear();
            listview.refresh();
        }
        e.consume();
    }

    protected Hardware update(Hardware hw) {
        //Update the room
        if(!hw.getRaum().getId().equals(choiceboxRaum.getValue().getId())) {
            //Remove existing hardware from room
            DataManager.getInstance().findRaumByID(hw.getRaum().getId()).getHardwareList().remove(hw);
            //Add changed hardware to new room
            DataManager.getInstance().findRaumByID(choiceboxRaum.getValue().getId()).getHardwareList().add(hw);
        }

        hw.setSeriennummer(textfieldSeriennummer.getText());
        hw.setModell(textfieldModell.getText());
        hw.setHersteller(textfieldHersteller.getText());
        hw.setStatus(choiceboxStatus.getValue());
        hw.setHerstellergarantie(Integer.parseInt(textfieldHerstellergarantie.getText()));
        hw.setLieferdatum(datepickerLieferdatum.getValue());

        return updateAdditional(hw);
    }

    /**
     * TODO doc
     * @param hw
     * @return
     */
    protected abstract Hardware updateAdditional(Hardware hw);

    /**
     * Leert alle Felder.
     * @param e das Button event
     */
    @FXML
    private void handleButtonAbbrechen(ActionEvent e) {
        clear();
        e.consume();
    }

    /**
     * Leert alle Felder und wechselt die View zum Dashboard.
     * @param e das Button event
     */
    @FXML
    private void handleButtonDashboard(ActionEvent e) {
        clear();
        ViewManager.getInstance().activateScene(ViewManager.getInstance().getSceneDashboard());
        e.consume();
    }

    /**
     * TODO Doc
     * @param e
     */
    @FXML
    private void handleMouseSelection(MouseEvent e) {
        Hardware hw = listview.getSelectionModel().getSelectedItem();
        if(hw != null) {
            clear();
            textfieldId.setText(String.valueOf(hw.getId()));
            textfieldSeriennummer.setText(hw.getSeriennummer());
            textfieldModell.setText(hw.getModell());
            textfieldHersteller.setText(hw.getHersteller());
            choiceboxStatus.getSelectionModel().select(hw.getStatus());
            textfieldHerstellergarantie.setText("" + hw.getHerstellergarantie());
            datepickerLieferdatum.setValue(hw.getLieferdatum());
            choiceboxRaum.setValue(hw.getRaum());
            loadHardware(hw);
        }
        e.consume();
    }


    /**
     * Leert alle Felder
     */
    private void clear() {
        textfieldSeriennummer.clear();
        textfieldModell.clear();
        textfieldHersteller.clear();
        choiceboxStatus.setValue(null);
        textfieldHerstellergarantie.clear();
        datepickerLieferdatum.setValue(null);
        clearFields();

        updateId();
    }

    /**
     * Aktualisiert das Textfeld ID mit der neuen zu vergebenden ID.
     */
    public void updateId() {
        textfieldId.setText(String.valueOf(Drucker.getAnzahl() + 1));
    }

    /**
     * ügt vordefinierte Objekte vom Typ Hardware dem Drucker hinzu und bereitet den Controller vor
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPredefinedHardware();
        setup();
        updateId();
        choiceboxStatus.setItems(FXCollections.observableArrayList(
                "ok",
                "in Reperatur",
                "defekt"));
    }

    /**
     * Leert alle weiteren Felder
     */
    protected abstract void clearFields();

    /**
     * Generiert ein Objekt vom Typ Hardware
     * @return
     */
    protected abstract Hardware generateHardware();

    /**
     * Bereitet den Controller vor
     */
    protected abstract void setup();

    /**
     * Fügt vordefinierte Objekte vom Typ Hardware dem Drucker hinzu
     */
    protected abstract void loadPredefinedHardware();

    /**
     * Lädt die Hardware in die Konfigurationsmaske
     * @param hw Die zu ladene Hardware
     */
    protected abstract void loadHardware(Hardware hw);
}
