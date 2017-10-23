package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalendarController {
    @FXML
    private TextField display;

    @FXML
    public void prev(ActionEvent actionEvent) {
        System.out.println("prev");
    }

    public void next(ActionEvent actionEvent) {
        System.out.println("next");
    }
}
