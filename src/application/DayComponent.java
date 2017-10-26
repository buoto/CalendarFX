package application;

import application.model.Day;
import application.model.PlannedEvent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DayComponent extends VBox {

    private final ObjectProperty<Day> day = new SimpleObjectProperty<>();

    public DayComponent() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("day_component.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        day.addListener((observable, oldDay, newDay) -> {
            newDay.eventsProperty().addListener((observable1, oldEvents, newEvents) -> setEventLabels(newEvents));
            setEventLabels(newDay.getEvents());
        });
    }

    private void setEventLabels(ObservableList<PlannedEvent> newEvents) {
        List<EventLabel> eventLabels = newEvents.stream()
                .map(EventLabel::new)
                .collect(Collectors.toList());

        VBox eventsList = ((VBox) this.lookup(".day__events"));
        eventsList.getChildren().setAll(eventLabels);
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
