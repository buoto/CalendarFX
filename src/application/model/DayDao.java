package application.model;

import java.util.ArrayList;

public interface DayDao {
    ArrayList<Day> getDays(int startWeek);

    ArrayList<Day> getDaysWithToday();
}
