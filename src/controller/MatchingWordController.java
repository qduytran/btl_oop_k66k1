package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ResourceBundle;

import game.MatchingWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class MatchingWordController extends GameController {
    @FXML
    TextArea chatArea;
    @FXML
    TextField messageField;
    @FXML
    Button replayBtn;
    @FXML
    Label correct, wrong, healthText, scoreText;
    @FXML
    ImageView correct1, wrong1;

    private MatchingWord game;
    private String wordStart;
    private String wordContinue;
    // private Character characterKey = 'b';

    private Hashtable<Character, ArrayList<String>> charStarts = new Hashtable<>();
    private Hashtable<String, Integer> answersList = new Hashtable<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = new MatchingWord();
        game.insertFromFile();
        displayWord();
        replayBtn.setDisable(true);
        replayBtn.setOnAction(event -> handleReplay());
        // chatArea.setWrapText(true);
        chatArea.setEditable(false);
        correct.setVisible(false);
        correct1.setVisible(false);
        wrong.setVisible(false);
        wrong1.setVisible(false);

        messageField.setOnAction(this::handleAnswer);
        healthText.setText("Health: " + game.getHealth());
        scoreText.setText("Score: " + game.getPoint());
    }

    private void handleAnswer(ActionEvent e) {
        if (game != null && wordStart != null) {
            String wordResponse = messageField.getText();
            if (game.checkAnswers(wordResponse)) {
                wrong.setVisible(false);
                wrong1.setVisible(false);
                correct.setVisible(true);
                correct1.setVisible(true);
                // game.increasePoint();
                updateHealthAndScore();
                messageField.clear();
                displayWord();
            } else {
                correct.setVisible(false);
                correct1.setVisible(false);
                wrong.setVisible(true);
                wrong1.setVisible(true);
                // game.decreaseHealth();
                updateHealthAndScore();
            }

            if (game.getHealth() == 0) {
                replayBtn.setDisable(false);
                chatArea.setText("Your score: " + game.getPoint() + "\nYou lose!");
            }
            if (game.getPoint() == 10) {
                replayBtn.setDisable(false);
                chatArea.setText("Your score: " + game.getPoint() + "\nYou win!");
            }
        }
        displayWord();
    }

    private void handleReplay() {
        replayBtn.setDisable(true);
        game.setPoint(0);
        game.setHealth(3);
        displayWord();
        updateHealthAndScore();
        messageField.clear();
    }

    private void updateHealthAndScore() {
        healthText.setText("Health: " + game.getHealth());
        scoreText.setText("Score: " + game.getPoint());
    }

    void displayWord() {
        correct.setVisible(false);
        correct1.setVisible(false);
        wrong.setVisible(false);
        wrong1.setVisible(false);
    }
}
