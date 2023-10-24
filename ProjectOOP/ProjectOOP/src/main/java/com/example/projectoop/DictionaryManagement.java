package com.example.projectoop;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {
    private Dictionary dictionary = new Dictionary();

    public void ínsertFromFile(String path) {
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] anh_viet = line.split("\t");
                Word word = new Word(anh_viet[0], anh_viet[1]);
                dictionary.put(anh_viet[0], word);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("Error read file");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public  void dictionaryExportToFile(String path) {
        try {
            FileWriter fr = new FileWriter(path);
            BufferedWriter br = new BufferedWriter(fr);
            for (Word word:
                 dictionary.values()) {
                br.write(word.getWordTarget() + "\t" + word.getWordExplain());
                br.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error read file");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

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
