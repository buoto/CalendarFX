package application.model.store;

import application.model.Appointment;
import application.model.Day;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class MockStore implements Store {

    private static final int DAYS_COUNT = 28;

    @Override
    public ArrayList<Day> getDays(LocalDate startWeek) {
        ArrayList<Day> days = new ArrayList<>();
        for (int i = 0; i < DAYS_COUNT; i++) {
            days.add(new Day(startWeek.plusDays(i)));
        }

        LocalDate secondDate = days.get(1).getDate();
        days.get(1).appointmentsProperty()
                .add(new Appointment("FooBar", secondDate.atTime(10, 20), secondDate.atTime(11, 40)));

        return days;
    }

    @Override
    public ArrayList<Day> getDaysWithToday() {
        LocalDate now = LocalDate.now().with(DayOfWeek.MONDAY);
        return getDays(now);
    }
}
