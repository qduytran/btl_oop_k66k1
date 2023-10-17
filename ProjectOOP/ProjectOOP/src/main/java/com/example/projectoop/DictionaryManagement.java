package com.example.projectoop;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {
    private Dictionary dictionary = new Dictionary();

    public void insertFromCommandLine() {
        Scanner sn = new Scanner(System.in);
        int counterWord;
        System.out.println("Nhap so tu:");
        counterWord = Integer.parseInt(sn.nextLine());

        for (int i = 0; i < counterWord; i++) {
            System.out.println("Nhap tu Tieng Anh");
            String wordTarget = sn.nextLine();
            System.out.println("Nhap giai thich nghia bang Tieng Viet");
            String wordExplain = sn.nextLine();
            Word word = new Word(wordTarget, wordExplain);
            dictionary.put(wordTarget, word);
        }
        sn.close();
    }

    public Dictionary getDictionary() {
        return this.dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

}
