package controller;

import dictionary.Dictionary;
import dictionary.DictionaryCommandLine;
import dictionary.DictionaryManagement;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdditionController implements Initializable {
    @FXML
    private TextField wordTargetInput;
    @FXML
    private TextArea explanationInput;
    @FXML
    private Button addBtn;
    @FXML
    private Label successAlert;

    @FXML
    private void handleOnClicked() {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        successAlert.setVisible(false);
    }
}
