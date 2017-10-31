package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

public interface DayDao {
    ArrayList<Day> getDays(LocalDate startWeek);

    ArrayList<Day> getDaysWithToday();
}
