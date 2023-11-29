package controller;

import java.net.URL;
import java.util.ResourceBundle;

import game.GameInterface;
import game.HangMan;
import game.question.HangmanWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
        incorrect.setVisible(false);
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
        game.randWord();
        displayGuessWord();
        health.setText("Health: " + game.getHealth());
        score.setText("Score: " + game.getPoint());
    }

    private void handleAnswer() {
        String userInput = guessChar.getText().trim().toLowerCase();
        char userChar = userInput.charAt(0);
        if (word.checkAnswers(userChar) == false) {
            game.decreaseHealth();
            correct.setVisible(false);
            incorrect.setVisible(true);
        } else {
            incorrect.setVisible(false);
            correct.setVisible(true);
            guessWord.setText(word.printInfoGraphic());
        }
        if (game.getHealth() == 0) {
            guessWord.setText("Hết lượt đoán!" + "\nTừ đúng: " + word.word);
            replaybtn.setDisable(false);
        }
        if (word.completedWord() == true) {
            guessWord.setText("Đoán đúng từ!");
            game.increasePoint();
            incorrect.setVisible(false);
            correct.setVisible(true);
            replaybtn.setDisable(false);
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
        if (game != null)
            guessWord.setText(word.printInfoGraphic());
    }
}
