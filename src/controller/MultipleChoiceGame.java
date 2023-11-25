package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import game.MultipleChoice;
import game.question.Question;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class MultipleChoiceGame extends GameController {
    @FXML
    TextArea contentQuestion;
    @FXML
    Button selectAbtn, selectBbtn, selectCbtn, selectDbtn, nextquestionbtn;
    @FXML
    Label correct, wrong;
    @FXML
    ImageView correct1, wrong1;

    private MultipleChoice game;
    private Question currentQuestion;
    private List<String> currentAnswers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = new MultipleChoice();
        game.insertQuestionFromFile();
        displayQuestion();
        selectAbtn.setOnAction(event -> handleAnswer(0));
        selectBbtn.setOnAction(event -> handleAnswer(1));
        selectCbtn.setOnAction(event -> handleAnswer(2));
        selectDbtn.setOnAction(event -> handleAnswer(3));
        nextquestionbtn.setOnAction(event -> displayQuestion());

        contentQuestion.setWrapText(true);
        contentQuestion.setEditable(false);
        correct.setVisible(false);
        correct1.setVisible(false);
        wrong.setVisible(false);
        wrong1.setVisible(false);
    }

    private void displayQuestion() {
        if (game != null) {
            currentQuestion = game.randomQuestion();
            contentQuestion.setText(currentQuestion.getQuestion());
            currentAnswers = game.randomAnswers(currentQuestion.getCorrectAnswers());

            // Cập nhật các đáp án cho Button tương ứng ở đây
            selectAbtn.setText(currentAnswers.get(0));
            selectBbtn.setText(currentAnswers.get(1));
            selectCbtn.setText(currentAnswers.get(2));
            selectDbtn.setText(currentAnswers.get(3));
        }
    }

    private void handleAnswer(int selectedAnswerIndex) {
        if (game != null && currentQuestion != null && currentAnswers != null) {
            String selectedAnswer = currentAnswers.get(selectedAnswerIndex);

            if (currentQuestion.checkAnswers(selectedAnswer)) {
                correct.setVisible(true);
                correct1.setVisible(true);
                wrong.setVisible(false);
                wrong1.setVisible(false);
            } else {
                correct.setVisible(false);
                correct1.setVisible(false);
                wrong.setVisible(true);
                wrong1.setVisible(true);
            }

            // Kiểm tra và xử lý kết thúc trò chơi ở đây nếu cần
        }
    }
}
