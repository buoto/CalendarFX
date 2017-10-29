package application;

import application.model.Appointment;
import application.model.Day;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AppointmentDetails {

    @FXML
    public TextField name;

    private Day day;
    private Appointment appointment;

    public AppointmentDetails() {
    }

    public void handleDelete(ActionEvent actionEvent) {
        day.appointmentsProperty().remove(appointment);
        closeParentWindow(actionEvent);

    }

    public void handleCancel(ActionEvent actionEvent) {
        closeParentWindow(actionEvent);
    }

    public void handleSave(ActionEvent actionEvent) {
        if (appointment == null) {
            appointment = new Appointment();
            day.appointmentsProperty().add(appointment);
        }

        appointment.setName(name.getText());
        closeParentWindow(actionEvent);
    }

    private void closeParentWindow(ActionEvent event) {
        Button button = ((Button) event.getSource());
        ((Stage) button.getScene().getWindow()).close();
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;

        if (appointment != null) {
            name.setText(appointment.getName());
        }
    }
}
