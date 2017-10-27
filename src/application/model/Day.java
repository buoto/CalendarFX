package application.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Day {
    private final StringProperty name;
    private final ListProperty<Appointment> events = new SimpleListProperty<>(FXCollections.observableArrayList());

    public Day(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public Day(String name, ArrayList<Appointment> events) {
        this(name);
        this.events.addAll(events);
    }

    public ObservableList<Appointment> getEvents() {
        return events.get();
    }

    public ListProperty<Appointment> eventsProperty() {
        return events;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }
}
