package application;

import application.model.Day;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Iterator;

public class CalendarController {

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

    }

}
