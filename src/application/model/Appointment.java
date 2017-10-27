package application.model;

public class Appointment {
    private String name;

    public Appointment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
