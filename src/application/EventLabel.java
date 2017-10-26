package application;

import application.model.PlannedEvent;
import javafx.scene.control.Label;

public class EventLabel extends Label {

    public EventLabel(PlannedEvent plannedEvent) {
        this.setText(plannedEvent.getName());
        this.getStyleClass().add("day__event");
    }
}
