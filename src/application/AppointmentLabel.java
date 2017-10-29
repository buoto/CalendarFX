package application;

import application.model.Appointment;
import javafx.scene.control.Label;

public class AppointmentLabel extends Label {

    public AppointmentLabel(Appointment appointment) {
        this.textProperty().bindBidirectional(appointment.nameProperty());
        this.getStyleClass().add("day__appointment");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
