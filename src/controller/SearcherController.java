package controller;

import dictionary.DictionaryManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearcherController implements Initializable {
    private ObservableList<String> list = FXCollections.observableArrayList();
    private static DictionaryManagement dm = new DictionaryManagement();
    static {
        dm.insertFromFile("WordList.txt");
    }
    @FXML
    private TextField searchTerm;

    @FXML
    private Button cancelBtn, saveBtn;

    @FXML
    private Label englishWord, headerList, notAvailableAlert;

    @FXML
    private TextArea explanation;

    @FXML
    private ListView<String> listResults;

    @FXML
    private Pane headerOfExplanation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchTerm.setOnKeyTyped(
            new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (searchTerm.getText().isEmpty()) {
                    cancelBtn.setVisible(false);
                    setListDefault(0);
                } else {
                    cancelBtn.setVisible(true);
                    handleOnKeyTyped();
                }
            }
        });
    }

    @FXML
    private void handleOnKeyTyped() {
        list = dm.search(searchTerm.getText().trim());
        System.out.println(list);

        if (list.isEmpty()) {
            notAvailableAlert.setVisible(true);
        } else {
            notAvailableAlert.setVisible(false);
            headerList.setText("Kết quả");
            listResults.setItems(list);
        }
    }

    @FXML
    private void handleMouseClickAWord(MouseEvent arg0) {

    }

    @FXML
    private void handleClickEditBtn() {

    }

    @FXML
    private void handleClickSoundBtn() {

    }

    @FXML
    private void handleClickSaveBtn() {

    }

    @FXML
    private void handleClickDeleteBtn() {

    }

    private void refreshAfterDeleting() {

    }

    private void setListDefault(int index) {

    }
}
