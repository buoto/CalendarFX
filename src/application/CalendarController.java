package application;

import application.model.Day;
import application.model.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {

    @FXML
    private ArrayList<DayComponent> dayComponents;
    private Store store;

    public CalendarController(Store store) {
        this.store = store;
    }

    public void prev(ActionEvent actionEvent) {
        System.out.println("prev");
    }

    public void next(ActionEvent actionEvent) {
        System.out.println("next");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Day> days = store.getDaysWithToday();

        Iterator<DayComponent> it = dayComponents.iterator();
        for (Day day : days) {
            it.next().setDay(day);
        }


    }
}
