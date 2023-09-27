import java.util.ArrayList;

public class Dictionary {
    public ArrayList<Word> listWords;

    public Dictionary() {
        listWords = new ArrayList<>();
    }
    
    public void addWord(String x, String y) {
        Word a = new Word(x, y);
        listWords.add(a);
    }
}
