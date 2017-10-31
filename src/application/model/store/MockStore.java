package application.model.store;

import application.model.Appointment;
import application.model.Day;

import java.time.LocalDate;
import java.util.ArrayList;

public class MockStore extends Store {

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

}
