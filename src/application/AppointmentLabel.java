package application;

import application.model.Appointment;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;

public class AppointmentLabel extends Label {

    public AppointmentLabel(Appointment appointment) {
        this.textProperty().bind(Bindings.concat(appointment.timeStringBinding(), " ", appointment.nameProperty()));
        this.getStyleClass().add("day__appointment");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
