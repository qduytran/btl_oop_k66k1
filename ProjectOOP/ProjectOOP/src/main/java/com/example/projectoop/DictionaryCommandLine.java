package com.example.projectoop;

import java.util.Scanner;

public class DictionaryCommandLine {
    private DictionaryManagement DM = new DictionaryManagement();

    public DictionaryCommandLine() {
        System.out.println("Welcome to My Application");
    }

    public void menu() {
        System.out.println("[0]     Exit");
        System.out.println("[1]     Add");
        System.out.println("[2]     Remove");
        System.out.println("[3]     Update");
        System.out.println("[4]     Display");
        System.out.println("[5]     Lookup");
        System.out.println("[6]     Search");
        System.out.println("[7]     Game");
        System.out.println("[8]     Import from file");
        System.out.println("[9]     Export to file");
    }

    public void yourAction() {
        Scanner input = new Scanner(System.in);
        System.out.println("Your Action:");
        int opt = input.nextInt();
        input.nextLine();
        while (opt != 0) {
            switch (opt) {
                case 1: {
                    System.out.println("Nhap tu Tieng Anh");
                    String wordTarget = input.nextLine();
                    System.out.println("Nhap giai thich nghia bang Tieng Viet");
                    String wordExplain = input.nextLine();
                    Word word = new Word(wordTarget, wordExplain);
                    DM.dictionaryAdd(word);
                    break;
                }
                case 2: {
                    System.out.println("Nhap tu Tieng Anh can xoa");
                    String word_target = input.nextLine();
                    DM.dictionaryDelete(word_target);
                    break;
                }
                case 3: {
                    System.out.println("Nhap tu Tieng Anh can update");
                    String wordTarget = input.nextLine();
                    System.out.println("Nhập nghĩa cần update:");
                    String wordExplain = input.nextLine();
                    Word word = new Word(wordTarget, wordExplain);
                    DM.dictionaryUpdate(word);
                    break;
                }
                case 4: {
                    this.showAllWords();
                    break;
                }
                case 5: {
                    System.out.println("Nhap tu Tieng Anh can lookup");
                    String word_target = input.nextLine();
                    DM.dictionaryLookup(word_target);
                    break;
                }
                case 8: {
                    DM.insertFromFile("WordList.txt");
                    break;
                }

            }
            System.out.println("Your acion:");
            opt = input.nextInt();
            input.nextLine();
        }
    }

    public void showAllWords() {
        System.out.println("No | English | Vietnamese");
        int i = 1;
        for (Word word:DM.getDictionary().values()) {
            System.out.println(i + " | " + word.getWordTarget() + " | " + word.getWordExplain());
            i++;
        }
    }

    public void dictionaryBasic() {
        //DM.insertFromCommandLine();
        DM.insertFromFile("WordList.txt");
        DM.dictionaryExportToFile("WordListOut.txt");
    }
}
