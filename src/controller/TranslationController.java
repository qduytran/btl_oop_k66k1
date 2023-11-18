package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class TranslationController implements Initializable {
    @FXML
    private TextArea sourceLangField, toLangField;
    @FXML
    private Button translateBtn;
    @FXML
    private Label englishLabel, vietnameseLabel;

    @FXML
    private void handleOnClickSwitchToggle() {
        System.out.println("Nút này để swap 2 box");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        translateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Nut nay de dich");
            }
        });
    }

}
