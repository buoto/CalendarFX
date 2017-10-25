package application;

import application.model.Day;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Iterator;

public class CalendarController {

    private final ObjectProperty<Day> day = new SimpleObjectProperty<>(new Day("dzien"));

    private final ListProperty<Day> days = new SimpleListProperty<>();

    @FXML
    private TextField display;

    @FXML
    private ArrayList<DayComponent> dayComponents;

    public void prev(ActionEvent actionEvent) {
        System.out.println("prev");
    }

    public void next(ActionEvent actionEvent) {
        System.out.println("next");
    }

    public void initialize() {
        Iterator<DayComponent> it = dayComponents.iterator();

        ArrayList<Day> d = new ArrayList<>();
        d.add(new Day("dddzien"));
        d.add(new Day("ddd22"));

        for (Day day : d) {
            it.next().setDay(day);
        }

        days.setValue(FXCollections.observableArrayList(d));
    }

    public Day getDay() {
        return day.get();
    }

    public ObjectProperty<Day> dayProperty() {
        return day;
    }

    public ObservableList<Day> getDays() {
        return days.get();
    }

    public ListProperty<Day> daysProperty() {
        return days;
    }
}
