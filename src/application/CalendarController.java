package application;

import application.model.Day;
import application.model.store.Store;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {

    @FXML
    private ArrayList<DayComponent> dayComponents;
    private Store store;
    public final StringProperty firstWeekname = new SimpleStringProperty("");
    public final StringProperty secondWeekname = new SimpleStringProperty("");
    public final StringProperty thirdWeekname = new SimpleStringProperty("");
    public final StringProperty fourthWeekname = new SimpleStringProperty("");
    private final ListProperty<Day> days = new SimpleListProperty<>(FXCollections.observableArrayList());


    public CalendarController(Store store) {
        this.store = store;
        firstWeekname.bind(Bindings.createStringBinding(() -> {
            if (days.size() != 28) {
                return "";
            }
            return String.format("W%02d\n%d",
                    days.get(0).getDate().getDayOfYear() / 7,
                    days.get(0).getDate().getYear()
            );
        }, days));

        secondWeekname.bind(Bindings.createStringBinding(() -> {
            if (days.size() != 28) {
                return "";
            }
            return String.format("W%02d\n%d",
                    days.get(7).getDate().getDayOfYear() / 7,
                    days.get(7).getDate().getYear()
            );
        }, days));
        thirdWeekname.bind(Bindings.createStringBinding(() -> {
            if (days.size() != 28) {
                return "";
            }
            return String.format("W%02d\n%d",
                    days.get(14).getDate().getDayOfYear() / 7,
                    days.get(14).getDate().getYear()
            );
        }, days));
        fourthWeekname.bind(Bindings.createStringBinding(() -> {
            if (days.size() != 28) {
                return "";
            }
            return String.format("W%02d\n%d",
                    days.get(21).getDate().getDayOfYear() / 7,
                    days.get(21).getDate().getYear()
            );
        }, days));
    }

    public void prev(ActionEvent actionEvent) {
        System.out.println("prev"); // TODO
    }

    public void next(ActionEvent actionEvent) {
        System.out.println("next"); // TODO
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        days.addAll(store.getDaysWithToday());

        int i = 0;
        for (DayComponent day : dayComponents) {
            day.dayProperty().bind(Bindings.valueAt(days, i++));
        }
    }

    public String getFirstWeekname() {
        return firstWeekname.get();
    }

    public StringProperty firstWeeknameProperty() {
        return firstWeekname;
    }

    public String getSecondWeekname() {
        return secondWeekname.get();
    }

    public StringProperty secondWeeknameProperty() {
        return secondWeekname;
    }

    public String getThirdWeekname() {
        return thirdWeekname.get();
    }

    public StringProperty thirdWeeknameProperty() {
        return thirdWeekname;
    }

    public String getFourthWeekname() {
        return fourthWeekname.get();
    }

    public StringProperty fourthWeeknameProperty() {
        return fourthWeekname;
    }
}
