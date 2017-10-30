package application.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Day {
    private final ObjectProperty<LocalDate> date;

    private final ListProperty<Appointment> appointments = new SimpleListProperty<>(FXCollections.observableArrayList());

    public Day(LocalDate date) {
        this.date = new SimpleObjectProperty<>(date);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public ObservableList<Appointment> getAppointments() {
        return appointments.get();
    }

    public ListProperty<Appointment> appointmentsProperty() {
        return appointments;
    }

    public SortedList<Appointment> getSortedAppointments() {
        return appointments.sorted(Comparator.comparing(Appointment::getStart));
    }

    @Override
    public String toString() {
        return date.get().format(DateTimeFormatter.ofPattern("MMMM d"));
    }
}
