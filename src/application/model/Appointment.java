package application.model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {

    private final StringProperty name;
    private final ObjectProperty<LocalDateTime> start;
    private final ObjectProperty<LocalDateTime> end;

    public Appointment(String name, LocalDateTime start, LocalDateTime end) {
        this.name = new SimpleStringProperty(name);
        this.start = new SimpleObjectProperty<>(start);
        this.end = new SimpleObjectProperty<>(end);
    }

    public Appointment() {
        this.name = new SimpleStringProperty();
        this.start = new SimpleObjectProperty<>();
        this.end = new SimpleObjectProperty<>();
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setStart(LocalDateTime start) {
        this.start.set(start);
    }

    public LocalDateTime getStart() {
        return start.get();
    }

    public ObjectProperty<LocalDateTime> startProperty() {
        return start;
    }

    public StringBinding timeStringBinding() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        return Bindings.createStringBinding(() -> {
            if (getStart() != null && getEnd() != null) {
                return getStart().format(format) + "-" + getEnd().format(format);
            }
            return "";
        }, start, end);
    }

    public void setEnd(LocalDateTime end) {
        this.end.set(end);
    }

    public LocalDateTime getEnd() {
        return end.get();
    }

    public ObjectProperty<LocalDateTime> endProperty() {
        return end;
    }
}
