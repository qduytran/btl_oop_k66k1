import java.io.IOException;
import java.util.Objects;

import dictionary.Dictionary;
import dictionary.DictionaryManagement;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DictionaryApplication extends Application {
    public static Dictionary dictionary;
    public static DictionaryManagement dm = new DictionaryManagement();
    static {
        dm.insertFromFile("WordList.txt");
        dictionary = dm.getDictionary();
    }

    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/DictionaryGui.fxml")));
            Scene scene = new Scene(root);
            stage.setTitle("Dictionary");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            stage.setOnCloseRequest(e -> {
                Platform.exit();
                System.exit(0);
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}