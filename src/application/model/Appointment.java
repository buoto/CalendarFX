package application.model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment implements Serializable {

    private transient StringProperty name = new SimpleStringProperty("");
    private transient ObjectProperty<LocalDateTime> start = new SimpleObjectProperty<>(LocalDateTime.MIN);
    private transient ObjectProperty<LocalDateTime> end = new SimpleObjectProperty<>(LocalDateTime.MAX);

    public Appointment(String name, LocalDateTime start, LocalDateTime end) {
        this.name.setValue(name);
        this.start.setValue(start);
        this.end.setValue(end);
    }

    public Appointment() {
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

    public LocalDateTime getStart() {
        return start.get();
    }

    public void setStart(LocalDateTime start) {
        this.start.set(start);
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

    public LocalDateTime getEnd() {
        return end.get();
    }

    public void setEnd(LocalDateTime end) {
        this.end.set(end);
    }

    public ObjectProperty<LocalDateTime> endProperty() {
        return end;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeUTF(name.getValueSafe());
        s.writeObject(start.getValue());
        s.writeObject(end.getValue());
    }


    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        name = new SimpleStringProperty("");
        start = new SimpleObjectProperty<>(LocalDateTime.MIN);
        end = new SimpleObjectProperty<>(LocalDateTime.MAX);
        name.setValue(s.readUTF());
        start.setValue((LocalDateTime) s.readObject());
        end.setValue((LocalDateTime) s.readObject());
    }
}
