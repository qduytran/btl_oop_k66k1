package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;
import word.Word;

public class AdditionController extends DictionaryController {
    @FXML
    private TextField wordTargetInput;
    @FXML
    private TextArea explanationInput;
    @FXML
    private Button addBtn;
    @FXML
    private Label successAlert;

    private void resetInput() {
        wordTargetInput.setText("");
        explanationInput.setText("");
    }

    private void showSuccessAlert() {
        successAlert.setVisible(true);
        dm.setTimeout(() -> successAlert.setVisible(false), 1500);
    }

    @FXML
    private void handleOnClicked() {
        try {
            String englishWord = wordTargetInput.getText().trim();
            String meaning = explanationInput.getText().trim();

            Word word = new Word(englishWord, meaning);
            if (dictionary.containsKey(word.getWordTarget())) {
                System.out.println("Add không thành công");
            } else {
                dictionary.put(word.getWordTarget(), word);
                showSuccessAlert();
            }
            addBtn.setDisable(true);
            resetInput();

        } catch (Exception e) {
            // Xử lý ngoại lệ ở đây
            e.printStackTrace(); // Hoặc sử dụng log để ghi lại thông tin lỗi
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        successAlert.setVisible(false);

        if (explanationInput.getText().isEmpty() || wordTargetInput.getText().isEmpty())
            addBtn.setDisable(true);

        wordTargetInput.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (explanationInput.getText().isEmpty() || wordTargetInput.getText().isEmpty())
                    addBtn.setDisable(true);
                else
                    addBtn.setDisable(false);
            }
        });

        explanationInput.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (explanationInput.getText().isEmpty() || wordTargetInput.getText().isEmpty())
                    addBtn.setDisable(true);
                else
                    addBtn.setDisable(false);
            }
        });
    }
}
