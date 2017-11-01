package application;

import application.model.Day;
import application.model.store.Store;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {

    public final StringProperty firstWeekname = new SimpleStringProperty("");
    public final StringProperty secondWeekname = new SimpleStringProperty("");
    public final StringProperty thirdWeekname = new SimpleStringProperty("");
    public final StringProperty fourthWeekname = new SimpleStringProperty("");
    private final ListProperty<Day> days = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final Store store;

    public GridPane root;
    @FXML
    private ArrayList<DayComponent> dayComponents;


    public CalendarController(Store store) {
        this.store = store;

        firstWeekname.bind(Bindings.createStringBinding(() -> getWeekname(0), days));
        secondWeekname.bind(Bindings.createStringBinding(() -> getWeekname(1), days));
        thirdWeekname.bind(Bindings.createStringBinding(() -> getWeekname(2), days));
        fourthWeekname.bind(Bindings.createStringBinding(() -> getWeekname(3), days));

    }

    private String getWeekname(int weekNumber) {
        int dayNumber = Period.ofWeeks(weekNumber).getDays();
        if (days.size() <= dayNumber) {
            return "";
        }
        return String.format("W%02d\n%d",
                days.get(dayNumber).getDate().getDayOfYear() / Period.ofWeeks(1).getDays() + 1,
                days.get(dayNumber).getDate().getYear()
        );
    }

    public void prev(ActionEvent actionEvent) {
        setDays(store.getDays(days.get(0).getDate().minusWeeks(1)));
    }

    public void next(ActionEvent actionEvent) {
        setDays(store.getDays(days.get(0).getDate().plusWeeks(1)));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Day> daysWithToday = store.getDaysWithToday();
        setDays(daysWithToday);
    }

    private void setDays(ArrayList<Day> daysWithToday) {
        days.clear();
        days.addAll(daysWithToday);

        int i = 0;
        final LocalDate now = LocalDate.now();
        for (DayComponent day : dayComponents) {
            if (day.dayProperty().isBound()) {
                day.dayProperty().unbind();
            }
            final ObjectBinding<Day> dayI = Bindings.valueAt(days, i++);
            day.styleProperty().bind(Bindings.createStringBinding(() -> (dayI.get().getDate().equals(now)) ? "-fx-background-color: pink;" : "", dayI));
            day.dayProperty().bind(dayI);
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
