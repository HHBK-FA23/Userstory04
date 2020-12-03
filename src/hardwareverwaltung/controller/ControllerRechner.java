package hardwareverwaltung.controller;

import hardwareverwaltung.logic.DataManager;
import hardwareverwaltung.model.Drucker;
import hardwareverwaltung.model.Hardware;
import hardwareverwaltung.model.Rechner;
import hardwareverwaltung.model.RechnerBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ControllerRechner extends ControllerHardware{
    @FXML
    private TextField textfieldCPU;
    @FXML
    private TextField textfieldArbeitsspeicher;
    @FXML
    private TextField textfieldBetriebssystem;
    @FXML
    private ChoiceBox<String> choiceboxTyp;
    @FXML
    private TextField textfieldGrafikkarte;
    @FXML
    private TextField textfieldSSD;
    @FXML
    private TextField textfieldHDD;



    @Override
    protected Hardware generateHardware() {
        return new RechnerBuilder()
                .setSeriennummer(textfieldSeriennummer.getText())
                .setModell(textfieldModell.getText())
                .setHersteller(textfieldHersteller.getText())
                .setStatus(choiceboxStatus.getValue())
                .setHerstellergarantie(textfieldHerstellergarantie.getText())
                .setLieferdatum(datepickerLieferdatum.getValue())
                .setRaum(choiceboxRaum.getValue())
                .setCpu(textfieldCPU.getText())
                .setArbeitsspeicher(textfieldArbeitsspeicher.getText())
                .setBetriebssystem(textfieldBetriebssystem.getText())
                .setTyp(choiceboxTyp.getValue())
                .setGrafikkarte(textfieldGrafikkarte.getText())
                .setFestplatteSSD(textfieldSSD.getText())
                .setFestplatteHDD(textfieldHDD.getText())
                .createRechner();
    }

    @Override
    protected void clearFields() {
        textfieldCPU.clear();
        textfieldArbeitsspeicher.clear();
        textfieldBetriebssystem.clear();
        choiceboxTyp.setValue(null);
        textfieldGrafikkarte.clear();
        textfieldSSD.clear();
        textfieldHDD.clear();
    }

    @Override
    protected void loadPredefinedHardware() {

    }

    @Override
    protected void loadHardware(Hardware hw) {
        if(hw instanceof Rechner) {
            Rechner r = (Rechner)hw;
            textfieldCPU.setText(r.getCpu());
            textfieldArbeitsspeicher.setText("" + r.getArbeitsspeicher());
            textfieldBetriebssystem.setText(r.getBetriebssystem());
            choiceboxTyp.getSelectionModel().select(r.getTyp());
            textfieldGrafikkarte.setText(r.getGrafikkarte());
            textfieldSSD.setText("" + r.getFestplatteSSD());
            textfieldHDD.setText("" + r.getFestplatteHDD());

        } else throw new IllegalArgumentException("hw is not a Rechner!");
    }

    @Override
    protected Hardware updateAdditional(Hardware hw) {
        if(hw instanceof Rechner) {
            return hw;
        } else throw new IllegalArgumentException("hw is not of type Rechner");
    }

    @Override
    protected void setup() {
        listview.setItems(DataManager.getInstance().getRechnerList());
        choiceboxRaum.setItems(DataManager.getInstance().getRaumList());

        choiceboxTyp.setItems(FXCollections.observableArrayList(
                "Gamning-PC",
                "Multimedia-PC",
                "Office-PC"
        ));
    }
}
