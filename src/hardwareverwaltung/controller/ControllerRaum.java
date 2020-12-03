package hardwareverwaltung.controller;

import hardwareverwaltung.logic.DataManager;
import hardwareverwaltung.logic.ViewManager;
import hardwareverwaltung.model.Hardware;
import hardwareverwaltung.model.Raum;
import hardwareverwaltung.model.RaumBuilder;
import hardwareverwaltung.util.AlertWindow;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRaum implements Initializable {
    @FXML
    private TextField textfieldId;
    @FXML
    private ChoiceBox<String> choiceboxTyp;
    @FXML
    private TextField textfieldGroesse;

    @FXML
    public Button buttonHardware;
    @FXML
    public Button buttonSichern;
    @FXML
    public Button buttonAbbrechen;
    @FXML
    public Button buttonDashboard;

    @FXML
    private ListView<Raum> listview;

    /**
     * Leert alle Felder und wechselt die View zum Dashboard.
     * @param e das Button event
     */
    @FXML
    protected void handleButtonDashboard(ActionEvent e) {
        clear();
        ViewManager.getInstance().activateScene(ViewManager.getInstance().getSceneDashboard());
        e.consume();
    }

    private void clear() {
        textfieldId.clear();
        choiceboxTyp.setValue(null);
        textfieldGroesse.clear();
    }

    @FXML
    private void handleMouseSelection(MouseEvent e) {
        //TODO laden
        e.consume();
    }

    @FXML
    private void handleButtonHardware(ActionEvent e) {
        Raum r = listview.getSelectionModel().getSelectedItem();

        if(r != null) {
            Stage window = new Stage();
            window.setTitle("Hardware in Raum: " + r.getId());

            VBox layout = new VBox();
            layout.setAlignment(Pos.CENTER);

            Button buttonOK = new Button("OK");
            buttonOK.setOnAction(event -> {
                window.close();
            });

            ListView<Hardware> itemView = new ListView<>();
            itemView.setItems(FXCollections.observableList(r.getHardwareList()));

            layout.getChildren().addAll(itemView, buttonOK);
            window.setScene(new Scene(layout, 800, 300));
            window.showAndWait();
        }
    }

    @FXML
    private void handleButtonSichern(ActionEvent e) {
        try {
            DataManager.getInstance().add(
                    new RaumBuilder()
                            .setId(textfieldId.getText())
                            .setTyp(choiceboxTyp.getValue())
                            .setGroesse(textfieldGroesse.getText())
                            .createRaum()
            );
        } catch(IllegalArgumentException err) {
            AlertWindow.createErrorWindow(err.getMessage());
        }
        e.consume();
    }

    @FXML
    private void handleButtonAbbrechen(ActionEvent e) {
        clear();
        e.consume();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listview.setItems(DataManager.getInstance().getRaumList());

        DataManager.getInstance().add(
                new RaumBuilder()
                        .setId("A123")
                        .setTyp("IT Fachraum")
                        .setGroesse(12.3)
                        .createRaum()
        );
        choiceboxTyp.setItems(FXCollections.observableArrayList(
                "Klassenraum",
                "IT Fachraum",
                "ET Fachraum",
                "CH Labor",
                "Serviceraum",
                "Lehrervorbereitungsraum",
                "Büro",
                "Sonstiges"
        ));
    }
}
