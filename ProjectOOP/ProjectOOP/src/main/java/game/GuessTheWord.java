// Source code is decompiled from a .class file using FernFlower decompiler.
package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import com.example.projectoop.Word;

public class GuessTheWord extends GameManagement {
   private static final int maxWord = 82084;
   private static final int maxGuess = 5;
   private static ArrayList<Word> dataFile = new ArrayList();
   private Word word;
   private GameManagement.State state;

   public GuessTheWord() {
      this.health = 5;
      this.point = 0;
      this.state = State.PLAYING;
   }

   private void printHint() {
      System.out.println("This word has " + this.word.getWordTarget().length() + " characters.");
      System.out.println("Meaning: " + this.word.getWordExplain());
   }

   private void checkGuess(String guess) {
      guess = guess.toLowerCase();
      if (guess.equals(this.word.getWordTarget())) {
         this.state = State.WIN;
      } else {
         --this.health;
         System.out.println("Wrong guess! You have " + this.health + " guesses left.");
      }
   }

   private void getRandomWord() {
      Random rand = new Random();
      int index = rand.nextInt(82084);
      this.word = (Word)dataFile.get(index);
   }

   public void start() {
      System.out.println("You are playing Guess The Word");
      this.getRandomWord();
      this.printHint();

      while(this.state == State.PLAYING) {
         System.out.println("Guess a word: ");
         Scanner sc = new Scanner(System.in);
         String guess = sc.nextLine();
         this.checkGuess(guess);
         if (this.health == 0) {
            this.state = State.LOSE;
         }
      }

      if (this.state == State.WIN) {
         System.out.println("You win!");
         System.out.println("The word is: " + this.word.getWordTarget());
      } else {
         System.out.println("You lose!");
         System.out.println("The correct word is: " + this.word.getWordTarget());
      }

   }

   static {
      System.out.println("Reading data from Guess The Word...");

      try {
         Scanner sc = new Scanner(new File("/btl_oop_k66k1/ProjectOOP/ProjectOOP/data/GuessTheWordData.txt"));

         while(sc.hasNext()) {
            String[] tmp = sc.nextLine().split("\t");
            dataFile.add(new Word(tmp[0], tmp[1]));
         }

         sc.close();
      } catch (IOException var2) {
         System.out.println("Error: " + String.valueOf(var2));
      }

   }
}
