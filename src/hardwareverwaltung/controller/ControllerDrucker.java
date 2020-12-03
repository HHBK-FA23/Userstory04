package hardwareverwaltung.controller;

import hardwareverwaltung.logic.DataManager;
import hardwareverwaltung.model.Drucker;
import hardwareverwaltung.model.DruckerBuilder;
import hardwareverwaltung.model.Hardware;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

import java.time.LocalDate;

public class ControllerDrucker extends ControllerHardware {
    @FXML
    private CheckBox checkboxFarbdruckfunktion;
    @FXML
    private ChoiceBox<String> choiceboxTechnologie;
    @FXML
    private ChoiceBox<String> choiceboxMaxPapierformat;

    @Override
    protected void setup() {
        listview.setItems(DataManager.getInstance().getDruckerList());
        choiceboxRaum.setItems(DataManager.getInstance().getRaumList());

        choiceboxTechnologie.setItems(FXCollections.observableArrayList(
                "Tintenstrahldrucker",
                "Farbtintenstrahldrucker",
                "Farblaserdrucker",
                "Laserdrucker"));

        choiceboxMaxPapierformat.setItems(FXCollections.observableArrayList(
                "A3",
                "A4"));
    }

    @Override
    protected void loadPredefinedHardware() {
        DataManager.getInstance().addAll(
                new DruckerBuilder()
                        .setSeriennummer("S001")
                        .setModell("HP DeskJet 2630")
                        .setHersteller("HP")
                        .setStatus("ok")
                        .setHerstellergarantie(12)
                        .setLieferdatum(LocalDate.of(2019, 9, 3))
                        .setTechnologie("Tintenstrahldrucker")
                        .setFarbdruckfunktion(true)
                        .setPaperformatmax("A4")
                        .setRaum(DataManager.getInstance().findRaumByID("A123"))
                        .createDrucker(),
                new DruckerBuilder()
                        .setSeriennummer("S002")
                        .setModell("Samsung XPRESS C480FW")
                        .setHersteller("Samsung")
                        .setStatus("ok")
                        .setHerstellergarantie(24)
                        .setLieferdatum(LocalDate.of(2019, 9, 3))
                        .setTechnologie("Farblaserdrucker")
                        .setFarbdruckfunktion(true)
                        .setPaperformatmax("A4")
                        .setRaum(DataManager.getInstance().findRaumByID("A123"))
                        .createDrucker(),
                new DruckerBuilder()
                        .setSeriennummer("S003")
                        .setModell("Brother MFC-J6930DW")
                        .setHersteller("Brother")
                        .setStatus("ok")
                        .setHerstellergarantie(36)
                        .setLieferdatum(LocalDate.of(2019, 9, 3))
                        .setTechnologie("Farbtintenstrahldrucker")
                        .setFarbdruckfunktion(true)
                        .setPaperformatmax("A3")
                        .setRaum(DataManager.getInstance().findRaumByID("A123"))
                        .createDrucker()
        );
    }

    @Override
    protected void loadHardware(Hardware hw) {
        if(hw instanceof Drucker) {
            Drucker d = (Drucker)hw;
            checkboxFarbdruckfunktion.setSelected(d.isFarbdruckfunktion());
            choiceboxTechnologie.getSelectionModel().select(d.getTechnologie());
            choiceboxMaxPapierformat.getSelectionModel().select(d.getPaperformatmax());
        } else throw new IllegalArgumentException("hw is not a Drucker!");
    }

    @Override
    protected Hardware generateHardware() throws IllegalArgumentException {
        return new DruckerBuilder()
                .setSeriennummer(textfieldSeriennummer.getText())
                .setModell(textfieldModell.getText())
                .setHersteller(textfieldHersteller.getText())
                .setStatus(choiceboxStatus.getValue())
                .setHerstellergarantie(textfieldHerstellergarantie.getText())
                .setLieferdatum(datepickerLieferdatum.getValue())
                .setRaum(choiceboxRaum.getValue())
                .setTechnologie(choiceboxTechnologie.getValue())
                .setFarbdruckfunktion(checkboxFarbdruckfunktion.isSelected())
                .setPaperformatmax(choiceboxMaxPapierformat.getValue())
                .createDrucker();
    }

    @Override
    protected Hardware updateAdditional(Hardware hw) {
        if(hw instanceof Drucker) {
            return hw;
        } else throw new IllegalArgumentException("hw is not of type Drucker");
    }

    @Override
    protected void clearFields() {
        checkboxFarbdruckfunktion.setSelected(false);
        choiceboxStatus.setValue(null);
        choiceboxTechnologie.setValue(null);
        choiceboxMaxPapierformat.setValue(null);
    }
}
