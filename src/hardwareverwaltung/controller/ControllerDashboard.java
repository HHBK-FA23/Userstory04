package hardwareverwaltung.controller;


import hardwareverwaltung.logic.ViewManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ControllerDashboard {
    /**
     * Ruft die DruckerView auf
     * @param e das Button event
     */
    @FXML
    private void handleButtonDruckerverwaltung(ActionEvent e) {
        ViewManager.getInstance().activateScene(ViewManager.getInstance().getSceneDrucker());
        e.consume();
    }

    /**
     * Ruft die RechnerView auf
     * @param e das Button event
     */
    @FXML
    public void handleButtonRechnerverwaltung(ActionEvent e) {
        ViewManager.getInstance().activateScene(ViewManager.getInstance().getSceneRechner());
        e.consume();
    }

    /**
     * Ruft
     * @param e das Button event
     */
    @FXML
    public void handleButtonRaumverwaltung(ActionEvent e) {
        ViewManager.getInstance().activateScene(ViewManager.getInstance().getSceneRaum());
        e.consume();
    }
}
