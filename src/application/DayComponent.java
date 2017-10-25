package application;

import application.model.Day;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DayComponent extends VBox {

    private final ObjectProperty<Day> day = new SimpleObjectProperty<>();
    @FXML
    private Label daynameLabel;

    public DayComponent() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("day_component.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Day getDay() {
        return day.get();
    }

    public void setDay(Day day) {
        this.day.set(day);
    }

    public ObjectProperty<Day> dayProperty() {
        return day;
    }
}
