package application;

import application.model.Appointment;
import application.model.Day;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class AppointmentDetails implements Initializable {

    @FXML
    public TextField name;
    @FXML
    public TextField startTimeHour;
    @FXML
    public TextField startTimeMinute;
    @FXML
    public TextField endTimeHour;
    @FXML
    public TextField endTimeMinute;

    private Day day;
    private Appointment appointment;

    public AppointmentDetails() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UnaryOperator<TextFormatter.Change> hourFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty()) {
                return change;
            }
            try {
                Integer value = Integer.parseInt(newText);
                if (value > 0 && value < 24) {
                    return change;
                }
            } catch (NumberFormatException ignored) {
            }
            return null;
        };

        UnaryOperator<TextFormatter.Change> minuteFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("([1-5]?[0-9])?")) {
                return change;
            }
            return null;
        };
        startTimeHour.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), null, hourFilter));
        startTimeMinute.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), null, minuteFilter));
        endTimeHour.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), null, hourFilter));
        endTimeMinute.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), null, minuteFilter));
    }

    public void handleDelete(ActionEvent actionEvent) {
        day.appointmentsProperty().remove(appointment);
        closeParentWindow(actionEvent);

    }

    public void handleCancel(ActionEvent actionEvent) {
        closeParentWindow(actionEvent);
    }

    public void handleSave(ActionEvent actionEvent) {
        boolean isNew = false;
        if (appointment == null) {
            appointment = new Appointment();
            isNew = true;
        }
        LocalDate date = day.getDate();

        appointment.setName(name.getText());

        Integer startHour = (Integer) startTimeHour.getTextFormatter().getValue();
        Integer startMinute = (Integer) startTimeMinute.getTextFormatter().getValue();
        appointment.setStart(date.atTime(startHour, startMinute));

        Integer endHour = (Integer) endTimeHour.getTextFormatter().getValue();
        Integer endMinute = (Integer) endTimeMinute.getTextFormatter().getValue();
        appointment.setEnd(date.atTime(endHour, endMinute));

        if (isNew) {
            day.appointmentsProperty().add(appointment);
        }

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
            startTimeHour.setText(String.format("%d", appointment.getStart().getHour()));
            startTimeMinute.setText(String.format("%d", appointment.getStart().getMinute()));
            endTimeHour.setText(String.format("%d", appointment.getEnd().getHour()));
            endTimeMinute.setText(String.format("%d", appointment.getEnd().getMinute()));
        }
    }

}
