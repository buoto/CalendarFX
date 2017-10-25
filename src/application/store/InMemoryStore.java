package application.store;

import application.model.Day;
import application.model.DayDao;

import java.util.ArrayList;

public class InMemoryStore implements DayDao {

    @Override
    public ArrayList<Day> getDays(int startWeek) {
        ArrayList<Day> result = new ArrayList<>();
        result.add(new Day("1"));
        result.add(new Day("2"));
        result.add(new Day("3"));
        return result;
    }

    @Override
    public ArrayList<Day> getDaysWithToday() {
        return null;
    }
}
