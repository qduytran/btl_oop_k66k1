package com.example.projectoop;
public class DictionaryCommandLine {
    private DictionaryManagement DM = new DictionaryManagement();

    public void showAllWords() {
        System.out.println("No | English | Vietnamese");
        int i = 1;
        for (Word word:DM.getDictionary().values()) {
            System.out.println(i + " | " + word.getWordTarget() + " | " + word.getWordExplain());
            i++;
        }
    }

    public void dictionaryBasic() {
        DM.insertFromCommandLine();
        showAllWords();
    }
}
