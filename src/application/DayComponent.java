package application;

import application.model.Day;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DayComponent extends VBox {

    private final ObjectProperty<Day> day = new SimpleObjectProperty<>();

    public DayComponent() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("day_component.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
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
