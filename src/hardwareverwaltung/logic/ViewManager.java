package hardwareverwaltung.logic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewManager {
    private static ViewManager viewManager;

    private Scene sceneDrucker;
    private Scene sceneRechner;
    private Scene sceneDashboard;
    private Scene sceneRaum;

    private Stage primaryStage;

    private ViewManager() {
        loadFXMLFiles();
    }

    /**
     * Liefert die aktuelle Version des ViewManagers
     * @return aktuelle Version des ViewManagers
     */
    public static ViewManager getInstance() {
        if(ViewManager.viewManager == null) {
            ViewManager.viewManager = new ViewManager();
        }
        return ViewManager.viewManager;
    }

    /**
     * Lädt alle notwendigen fxml Dateien und erzeugt aus diesen Scenes.
     */
    private void loadFXMLFiles() {
        try {
            sceneRaum = new Scene(FXMLLoader.load(getClass().getResource("/ViewRaum.fxml")), 800, 800);
            sceneDrucker = new Scene(FXMLLoader.load(getClass().getResource("/ViewDrucker.fxml")), 800, 800);
            sceneRechner = new Scene(FXMLLoader.load(getClass().getResource("/ViewRechner.fxml")), 800, 800);
            sceneDashboard = new Scene(FXMLLoader.load(getClass().getResource("/ViewDashboard.fxml")), 600, 200);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public Scene getSceneDrucker() {
        return sceneDrucker;
    }

    public Scene getSceneRechner() {
        return sceneRechner;
    }

    public Scene getSceneDashboard() {
        return sceneDashboard;
    }

    public Scene getSceneRaum() {
        return sceneRaum;
    }

    /**
     * Setup setzt beim erstmaligen Ausführen alle wichtigen Einstellungen der zu übergebenden Stage
     * und speichert diese.
     * @param primaryStage die zu übergebende Stage
     */
    public void setup(Stage primaryStage) {
        if(this.primaryStage == null) {
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("Userstory 04");
            activateScene(getSceneDashboard());
            this.primaryStage.show();
        }
    }

    /**
     * Ändert die aktuell angezeigte Scene zu der übergebenden
     * @param scene Scene zu welcher gewechselt werden soll.
     */
    public void activateScene(Scene scene) {
        primaryStage.setScene(scene);
    }
}
