public class DictionaryCommandLine {
    public static DictionaryManegerment user = new DictionaryManegerment();

    public static void dictionaryBasic() {
        user.insertFromCommandLine();
        user.showAllWords();
    }
    public static void main(String[] args) {
        while (true)
            dictionaryBasic();
    }
}
