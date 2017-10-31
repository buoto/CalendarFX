package application.model.store;

import application.model.Appointment;
import application.model.Day;
import javafx.collections.ListChangeListener;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class FileStore extends Store {

    private static final String FILENAME = "CalendarFX";
    private final HashMap<LocalDate, ArrayList<Appointment>> appointmentsMap = new HashMap<>();

    public FileStore() {
        loadFile();
    }

    @Override
    public ArrayList<Day> getDays(LocalDate startWeek) {
        LocalDate startDay = startWeek.with(DayOfWeek.MONDAY);
        LocalDate endDay = startDay.plus(Period.ofWeeks(4));

        ArrayList<Day> days = new ArrayList<>();
        for (LocalDate date = startDay; date.isBefore(endDay); date = date.plusDays(1)) {
            ArrayList<Appointment> appointments = appointmentsMap.get(date);
            Day day = (appointments != null) ? new Day(date, appointments) : new Day(date);
            day.appointmentsProperty().addListener(this::handleAppointmentsListChange);
            days.add(day);
        }

        return days;
    }

    private void handleAppointmentsListChange(ListChangeListener.Change<? extends Appointment> c) {
        while (c.next()) {
            if (c.wasAdded()) {
                for (Appointment a : c.getAddedSubList()) {
                    registerAppointment(a);
                }
            }
            if (c.wasRemoved()) {
                for (Appointment a : c.getRemoved()) {
                    deregisterAppointment(a);
                }
            }
        }
        updateFile();
    }

    private void updateFile() {
        File file = new File(FILENAME);
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file, false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            appointmentsMap.values().stream()
                    .flatMap(Collection::stream)
                    .forEach(a -> {
                        try {
                            objectOutputStream.writeObject(a);
                            objectOutputStream.reset();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadFile() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(FILENAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            while (fileInputStream.available() > 0) {
                Appointment appointment = (Appointment) objectInputStream.readObject();
                registerAppointment(appointment);
            }
        } catch (EOFException e) {
            // OK
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean deregisterAppointment(Appointment appointment) {
        LocalDate key = appointment.getStart().toLocalDate();
        ArrayList<Appointment> list = appointmentsMap.getOrDefault(key, null);
        return list == null || list.remove(appointment);
    }

    private void registerAppointment(Appointment appointment) {
        LocalDate key = appointment.getStart().toLocalDate();
        ArrayList<Appointment> list = appointmentsMap.getOrDefault(key, new ArrayList<>());
        list.add(appointment);
        appointmentsMap.put(key, list);
    }

}
