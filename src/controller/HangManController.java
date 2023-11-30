package controller;

import java.net.URL;
import java.util.ResourceBundle;

import game.HangMan;
import game.question.HangmanWord;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class HangManController extends GameController {
    @FXML
    Button replaybtn;
    @FXML
    TextArea guessWord;
    @FXML
    TextField guessChar;
    @FXML
    Label score, health, correct, incorrect, noround;
    @FXML
    ImageView correctimg, incorrectimg;

    private HangMan game = new HangMan();
    HangmanWord word = new HangmanWord(game.randWord());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game.insertFromFile();
        setupAnswerField();
        setupWordDisplay();
        displayGuessWord();
        replaybtn.setOnAction(e -> handleReplay());

        correct.setVisible(false);
        correctimg.setVisible(false);
        incorrect.setVisible(false);
        incorrectimg.setVisible(false);
        noround.setVisible(false);
        health.setText("Health: " + game.getHealth());
        score.setText("Score: " + game.getPoint());
    }

    private void setupAnswerField() {
        if (guessChar != null) {
            guessChar.setEditable(true);

            guessChar.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    handleAnswer();
                }
            });
        } else {
            System.out.println("guessChar is null!"); // Thông báo lỗi để debug
        }
    }

    private void handleReplay() {
        replaybtn.setDisable(true);
        game.setPoint(0);
        game.setHealth(3);
        word = new HangmanWord(game.randWord()); // Tạo từ mới
        displayGuessWord();
        health.setText("Health: " + game.getHealth());
        score.setText("Score: " + game.getPoint());
        incorrect.setVisible(false);
        correct.setVisible(false);
        incorrectimg.setVisible(false);
        correctimg.setVisible(false);
    }

    private void handleAnswer() {
        String userInput = guessChar.getText().trim().toLowerCase();
        char userChar = userInput.charAt(0);
        if (word.checkAnswers(userChar) == false) {
            game.decreaseHealth();
            correct.setVisible(false);
            incorrect.setVisible(true);
            correctimg.setVisible(false);
            incorrectimg.setVisible(true);
            guessChar.clear();
        } else {
            incorrect.setVisible(false);
            correct.setVisible(true);
            incorrectimg.setVisible(false);
            correctimg.setVisible(true);
            guessWord.setText(word.printInfoGraphic());
            game.increasePoint();
            guessChar.clear();
        }
        if (game.getHealth() == 0) {
            guessWord.setText("Hết lượt đoán!" + "\nTừ đúng: " + word.word);
            replaybtn.setDisable(false);
            correct.setVisible(false);
            incorrect.setVisible(false);
            correctimg.setVisible(false);
            incorrectimg.setVisible(false);
            noround.setVisible(true);
            guessChar.clear();
        }
        if (word.completedWord() == true) {
            guessWord.setText("Đoán đúng từ!");
            // game.increasePoint();
            incorrect.setVisible(false);
            correct.setVisible(true);
            incorrectimg.setVisible(false);
            correctimg.setVisible(true);
            replaybtn.setDisable(false);
            guessChar.clear();
        }
        score.setText("Point: " + game.getPoint());
        health.setText("Health: " + game.getHealth());
    }

    @FXML
    private void setupWordDisplay() {
        guessWord.setWrapText(true);
        guessWord.setEditable(false);
    }

    @FXML
    private void displayGuessWord() {
        if (game != null && word != null)
            guessWord.setText(word.printInfoGraphic());
    }
}
