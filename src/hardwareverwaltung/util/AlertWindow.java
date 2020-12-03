package hardwareverwaltung.util;

import javafx.scene.control.ButtonType;

import java.util.Optional;


public class AlertWindow {
    /**
     * Erzeugt ein Error Alert mit dem Nachrichten Text vom übergebenden String
     * @param s die Nachricht die angezeigt werden soll
     */
    public static void createErrorWindow(String s) {
        new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR, s).show();
    }

    /**
     * Erzeugt ein Information Alert mit dem Nachrichten Text vom übergebenden String
     * @param s die Nachricht die angezeigt werden soll
     */
    public static void createInformationWindow(String s) {
        new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION, s).show();
    }

    /**
     * Erzeugt ein Confirmation Alert mit dem Nachrichten Text vom übergebenden String
     * @param s die Nachricht die angezeigt werden soll
     * @return true wenn der nutzer mit "OK" bestätigt hat
     */
    public static boolean createConfirmationWindow(String s) {
        javafx.scene.control.Alert a = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = a.showAndWait();
        if(result.get().equals(ButtonType.OK)) {
            return true;
        }
        return false;
    }
}
