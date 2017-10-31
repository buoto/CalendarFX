package application.model.store;

import application.model.Day;
import application.model.DayDao;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Store implements DayDao {
    int DAYS_COUNT = 28;

    @Override
    public ArrayList<Day> getDaysWithToday() {
        LocalDate now = LocalDate.now().with(DayOfWeek.MONDAY);
        return getDays(now);
    }
}
