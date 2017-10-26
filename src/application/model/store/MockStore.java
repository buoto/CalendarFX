package application.model.store;

import application.model.Day;
import application.model.PlannedEvent;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MockStore implements Store {

    private static final int DAYS_COUNT = 28;

    @Override
    public ArrayList<Day> getDays(int startWeek) {
        return getDaysWithToday(); // TODO
    }

    @Override
    public ArrayList<Day> getDaysWithToday() {
        ArrayList<Day> days = new ArrayList<>();
        LocalDate date = LocalDate.now().with(DayOfWeek.MONDAY);
        for (int i = 0; i < DAYS_COUNT; i++) {
            days.add(new Day(date.plusDays(i).format(DateTimeFormatter.ofPattern("MMMM d")))); // TODO day name String -> LocalDate
        }

        days.get(1).eventsProperty().add(new PlannedEvent("costam"));

        return days;
    }
}
