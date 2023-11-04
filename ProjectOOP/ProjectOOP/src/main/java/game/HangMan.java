// Source code is decompiled from a .class file using FernFlower decompiler.
package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HangMan extends GameManagement {
   private static final int maxWord = 77229;
   private static final int maxGuess = 8;
   private static final String[] figure = new String[]{"   -------------    \n   |                \n   |                \n   |                \n   |                \n   |                  \n -----                \n", "   -------------    \n   |           |    \n   |                \n   |                \n   |                \n   |     \n -----   \n", "   -------------    \n   |           |    \n   |           0    \n   |                \n   |                \n   |     \n -----   \n", "   -------------    \n   |           |    \n   |           0    \n   |           |    \n   |                \n   |     \n -----   \n", "   -------------    \n   |           |    \n   |           0    \n   |          /|    \n   |                \n   |     \n -----   \n", "   -------------    \n   |           |    \n   |           0    \n   |          /|\\  \n   |                \n   |     \n -----   \n", "   -------------    \n   |           |    \n   |           0    \n   |          /|\\  \n   |          /     \n   |     \n -----   \n", "   -------------    \n   |           |    \n   |           0    \n   |          /|\\  \n   |          / \\  \n   |     \n -----   \n"};
   private static ArrayList<String> dataFile = new ArrayList();
   private String word;
   private GameManagement.State state;
   private boolean[] guessed;
   private boolean[] guessedCharacter = new boolean[26];

   public HangMan() {
      this.health = 8;
      this.point = 0;
      this.state = State.PLAYING;
   }

   private void printHint() {
      System.out.print("Hint: ");

      for(int i = 0; i < this.word.length(); ++i) {
         if (this.guessed[i]) {
            System.out.print(this.word.charAt(i));
         } else {
            System.out.print("_");
         }
      }

      System.out.println();
   }

   private void updateRightGuess(int pos) {
      this.guessed[pos] = true;
      ++this.point;
   }

   private void updateWrongGuess() {
      --this.health;
   }

   public void checkGuess(char guess) {
      guess = Character.toLowerCase(guess);
      if (this.guessedCharacter[guess - 97]) {
         System.out.println("You have already guessed this letter!");
      } else {
         boolean isCorrect = false;

         for(int i = 0; i < this.word.length(); ++i) {
            if (this.word.charAt(i) == guess && !this.guessed[i]) {
               this.updateRightGuess(i);
               isCorrect = true;
            }
         }

         this.guessedCharacter[guess - 97] = true;
         if (!isCorrect) {
            this.updateWrongGuess();
            System.out.println("Incorrect guess!");
            if (8 - this.health >= 0 && 8 - this.health < figure.length) {
               System.out.println(figure[8 - this.health]);
            }
         } else {
            System.out.println("Correct guess!");
         }

      }
   }

   private void getRandomWord() {
      Random rand = new Random();
      int index = rand.nextInt(77229);
      this.word = (String)dataFile.get(index);
   }

   public void start() {
      System.out.println("You are playing Hangman");
      this.getRandomWord();
      this.guessed = new boolean[this.word.length()];

      for(int i = 0; i < this.word.length(); ++i) {
         this.guessed[i] = false;
      }

      while(this.state == State.PLAYING) {
         this.printHint();
         System.out.println("Guess a letter: ");
         Scanner sc = new Scanner(System.in);
         char guess = sc.next().charAt(0);
         this.checkGuess(guess);
         if (this.health == 0) {
            this.state = State.LOSE;
         }

         if (this.point == this.word.length()) {
            this.state = State.WIN;
         }
      }

      if (this.state == State.WIN) {
         System.out.println("You win!");
         System.out.println("The word is: " + this.word);
      } else {
         System.out.println("You lose!");
         System.out.println("The correct word is: " + this.word);
      }

   }

   static {
      System.out.println("Reading data from Hangman...");

      try {
         Scanner sc = new Scanner(new File( "/btl_oop_k66k1/ProjectOOP/ProjectOOP/data/HangmanData.txt"));

         while(sc.hasNext()) {
            String tmp = sc.nextLine();
            dataFile.add(tmp);
         }

         sc.close();
      } catch (IOException var2) {
         System.out.println("Error: " + String.valueOf(var2));
      }

   }
}
