package application;

import application.model.Appointment;
import javafx.scene.control.ListCell;


public class AppointmentCell extends ListCell<Appointment> {

    @Override
    protected void updateItem(Appointment item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            setGraphic(new AppointmentLabel(item));
        } else {
            setGraphic(null);
        }
    }
}
