package application;

import application.model.store.FileStore;
import application.model.store.Store;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.lang.reflect.Constructor;

public class FxCalendar extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setMaxWidth(1500);
        primaryStage.setMaxHeight(600);

        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(200);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxcalendar.fxml"));
        Store store = new FileStore();

        loader.setControllerFactory((Class<?> type) -> {
            try {
                // look for constructor taking MyService as a parameter
                for (Constructor<?> c : type.getConstructors()) {
                    if (c.getParameterCount() == 1) {
                        if (c.getParameterTypes()[0] == Store.class) {
                            return c.newInstance(store);
                        }
                    }
                }
                // didn't find appropriate constructor, just use default constructor:
                return type.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Parent root = loader.load();

        Scene scene = new Scene(root, 512, 256);
        scene.getStylesheets().add(getClass()
                .getResource("style.css").toExternalForm());

        primaryStage.setTitle("ERES3");
        primaryStage.setScene(scene);
        primaryStage.show();

        ReadOnlyDoubleProperty width = scene.widthProperty();
        root.styleProperty().bind(Bindings.createStringBinding(() -> String.format("-fx-font-size: %dpx", (int) width.get() / 80), width));

    }

}
