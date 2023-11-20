package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class MultipleChoiceGame extends GameController {
    @FXML
    TextArea contentQuestion;
    @FXML
    Button selectAbtn, selectBbtn, selectCbtn, selectDbtn, playAgainbtn;
    @FXML
    Label win;
    @FXML
    ImageView correct1, correct2, correct3, correct4, correct5;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectAbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Đang xử lý khi chọn A");
            }
        });
        selectBbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Đang xử lý khi chọn B");
            }
        });
        selectCbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Đang xử lý khi chọn C");
            }
        });
        selectDbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Đang xử lý khi chọn D");
            }
        });
        playAgainbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Nút này để chơi lại từ đầu!");
            }
            
        });
        contentQuestion.setDisable(true);
        // selectAbtn.setDisable(true);
        // selectBbtn.setDisable(true);
        // selectCbtn.setDisable(true);
        // selectDbtn.setDisable(true);
        win.setVisible(false);
        correct1.setVisible(true);
        correct2.setVisible(false);
        correct3.setVisible(false);
        correct4.setVisible(false);
        correct5.setVisible(false);
    }
}
