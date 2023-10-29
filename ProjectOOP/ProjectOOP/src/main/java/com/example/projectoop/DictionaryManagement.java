package com.example.projectoop;

import java.io.*;
import java.net.URL;
import java.util.Scanner;
import java.nio.file.Paths;

public class DictionaryManagement {
    private Dictionary dictionary = new Dictionary();

    public void insertFromFile(String name) {
        try {
            name = "ProjectOOP/src/main/resources/com/example/projectoop/" + name;
            FileReader fr = new FileReader(name);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] anh_viet = line.split("\t");
                Word word = new Word(anh_viet[0], anh_viet[1]);
                dictionary.put(anh_viet[0], word);
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error open file");
        } catch (IOException e) {
            System.out.println("Error read file");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public  void dictionaryExportToFile(String name) {
        try {
            name = "ProjectOOP/src/main/resources/com/example/projectoop/" + name;
            FileWriter fr = new FileWriter(name);
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

    public void dictionaryLookup(String word_target) {
        // kiem tra word_target có nằm trong từ điển hay không
        if (dictionary.containsKey(word_target)) {
            System.out.println(word_target + " | " + dictionary.get(word_target));
        } else System.out.println("khong co trong tu dien");
    }

    public void dictionaryDelete(String word_target) {
        if (dictionary.containsKey(word_target)) {
            dictionary.remove(word_target);
            if (!dictionary.containsKey(word_target))
                System.out.println("xoa thanh cong");
            else
                System.out.println("xoa khong thanh cong");
        } else
            System.out.println("khong co trong tu dien");
    }

    public void dictionaryUpdate(Word newWord) {
        if (dictionary.containsKey(newWord.getWordTarget())) {
            dictionary.replace(newWord.getWordTarget(), newWord);
            if (newWord.getWordExplain().equals(dictionary.get(newWord.getWordTarget())))
                System.out.println("update thanh cong");
            else
                System.out.println("update khong thanh cong");
        } else
            System.out.println(newWord.getWordTarget() + " khong co trong tu dien");
    }

    public void dictionaryAdd(Word newWord) {
        if (!dictionary.containsKey(newWord.getWordTarget())) {
            dictionary.put(newWord.getWordTarget(), newWord);
            System.out.println("Add thanh cong");
        } else {
            System.out.println("Add khong thanh cong");
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
