package hardwareverwaltung;

import hardwareverwaltung.logic.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Start extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewManager viewManager = ViewManager.getInstance();
        viewManager.setup(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
