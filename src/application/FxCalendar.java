package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxCalendar extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxcalendar.fxml"));

        Scene scene = new Scene(root, 512, 256);
        scene.getStylesheets().add(getClass()
                .getResource("style.css").toExternalForm());

        primaryStage.setTitle("ERES3");
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
