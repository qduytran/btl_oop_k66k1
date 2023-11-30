package controller;

import java.net.URL;
import java.util.ResourceBundle;

import game.GuessTheWord;
import game.question.WordGuess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class GuessTheWordController extends GameController {
    @FXML
    TextArea worddisplay;
    @FXML
    TextField answer;
    @FXML
    Label wordcorrect, wordwrong, healthText, scoreText;
    @FXML
    ImageView wordcorrect1, wordwrong1;
    @FXML
    Button nextwordbtn, replayBtn;

    private GuessTheWord game;
    private WordGuess wordGuess;
    private int point = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = new GuessTheWord();
        game.insertFromFile();
        replayBtn.setDisable(false);
        wordcorrect.setVisible(false);
        wordcorrect1.setVisible(false);
        wordwrong.setVisible(false);
        wordwrong1.setVisible(false);
        displayWord();
        setupWordDisplay();
        setupAnswerField();
        replayBtn.setOnAction(event -> handleReplay());
        nextwordbtn.setOnAction(event -> {
            displayWord();
            answer.setEditable(true);
        });

        healthText.setText("Health: " + game.getHealth());
        scoreText.setText("Score: " + game.getPoint());
    }

    private void handleReplay() {
        nextwordbtn.setDisable(false);
        replayBtn.setDisable(true);
        game.setPoint(0);
        game.setHealth(3);
        displayWord();
    }

    @FXML
    private void displayWord() {
        if (game != null) {
            wordGuess = game.randWord();
            worddisplay.setText("Điền ô trống: "
                    + wordGuess.wordGuess()
                    + "\nNghĩa : " + wordGuess.getWordExplain());
        }
    }

    @FXML
    private void setupWordDisplay() {
        worddisplay.setWrapText(true);
        worddisplay.setEditable(false);
    }

    @FXML
    private void setupAnswerField() {
        answer.setEditable(true);
        answer.setOnAction(this::handleAnswer);
    }

    private void handleAnswer(ActionEvent event) {
        if (wordGuess != null && game != null) {
            String userInput = answer.getText().trim().toLowerCase();
            char userChar = userInput.charAt(0);

            if (wordGuess.checkAnswers(userChar)) {
                wordcorrect.setVisible(true);
                wordcorrect1.setVisible(true);
                wordwrong.setVisible(false);
                wordwrong1.setVisible(false);
                game.increasePoint();
                healthText.setText("Health: " + game.getHealth());
                scoreText.setText("Score: " + game.getPoint());
            } else {
                wordcorrect.setVisible(false);
                wordcorrect1.setVisible(false);
                wordwrong.setVisible(true);
                wordwrong1.setVisible(true);
                game.decreaseHealth();
                healthText.setText("Health: " + game.getHealth());
                scoreText.setText("Score: " + game.getPoint());
                worddisplay.setText(
                        "Từ đúng: " + wordGuess.getWordTarget() + "\nNghĩa Tiếng Việt: " + wordGuess.getWordExplain());
            }

            if (game.getHealth() == 0) {
                replayBtn.setDisable(false);
                nextwordbtn.setDisable(true);
                worddisplay.setText("Your score: " + game.getPoint() + "\nYou lose!");
            }
            if (game.getPoint() == 5) {
                replayBtn.setDisable(false);
                nextwordbtn.setDisable(true);
                worddisplay.setText("Your score: " + game.getPoint() + "\nYou win!");
            }
            answer.clear();
            answer.setEditable(false);
        }
    }
}
