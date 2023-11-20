package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;


public class TranslationController implements Initializable {
    @FXML
    private TextArea sourceLangField, toLangField;
    @FXML
    private Button translateBtn;
    @FXML
    private ComboBox<String> comboBox1;
    @FXML
    private ComboBox<String> comboBox2;

    String lang_first = "auto";
    String lang_second = "en";

    public String translateText(String input) {
        String translation = "";
        try {
            String url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=" + lang_first +
                    "&tl=" + lang_second + "&dt=t&q=" + URLEncoder.encode(input, "UTF-8");
            HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
            httpClient.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpClient.getInputStream()));
            String result = reader.readLine();
            reader.close();

            JsonArray jsonData = JsonParser.parseString(result).getAsJsonArray();
            JsonArray translationItems = jsonData.get(0).getAsJsonArray();

            for (JsonElement item : translationItems) {
                String translationLine = item.getAsJsonArray().get(0).getAsString();
                translation += " " + translationLine;
            }

            if (translation.length() > 1) {
                translation = translation.substring(1);
            }
        } catch (IOException | JsonIOException | JsonSyntaxException e) {
            e.printStackTrace();
        } 
        return translation;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("Tiếng Anh", "Tiếng Việt", "Tiếng Trung", "Phát hiện ngôn ngữ");
        comboBox1.setItems(list);
        comboBox2.setItems(list);

        comboBox1.setOnAction(e -> {
            String s = comboBox1.getSelectionModel().getSelectedItem().toString();
            if (s.equals("Tiếng Anh")) {
                lang_first = "en";
            } else if (s.equals("Tiếng Việt")) {
                lang_first = "vi";
            } else if (s.equals("Tiếng Trung")) {
                lang_first = "zh-tw";
            } else if (s.equals("Tiếng Pháp")) {
                lang_first = "fr";
            }
        });

        comboBox2.setOnAction(e -> {
            String s = comboBox2.getSelectionModel().getSelectedItem().toString();
            if (s.equals("Tiếng Anh")) {
                lang_second = "en";
            } else if (s.equals("Tiếng Việt")) {
                lang_second = "vi";
            } else if (s.equals("Tiếng Trung")) {
                lang_second = "zh-tw";
            } else if (s.equals("Tiếng Pháp")) {
                lang_second = "fr";
            }
        });

        translateBtn.setOnAction(e -> {
            String inputText = sourceLangField.getText();
            String translation = translateText(inputText);
            toLangField.setText(translation);
        });

        sourceLangField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (sourceLangField.getText().trim().isEmpty()) 
                    translateBtn.setDisable(true);
                else 
                    translateBtn.setDisable(false);
            }
        });

        translateBtn.setDisable(true);
        toLangField.setEditable(false);
    }

}
