package application.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Day {
    private final StringProperty name;
    private final ListProperty<PlannedEvent> events = new SimpleListProperty<>();

    public Day(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public Day(String name, ArrayList<PlannedEvent> events) {
        this(name);
        this.events.addAll(events);
    }

    public ObservableList<PlannedEvent> getEvents() {
        return events.get();
    }

    public ListProperty<PlannedEvent> eventsProperty() {
        return events;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }
}
