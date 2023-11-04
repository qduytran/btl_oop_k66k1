// Source code is decompiled from a .class file using FernFlower decompiler.
package game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameManagement {
   protected int point;
   protected int health;
   private int curGame = 1;

   public GameManagement() {
   }

   public void setPoint(int point) {
      this.point = point;
   }

   public int getPoint() {
      return this.point;
   }

   enum State {
      WIN,
      LOSE,
      PLAYING;
   
      private State() {
      }
   }
   protected void printGap() {
      for(int i = 0; i < 30; ++i) {
         System.out.println();
      }

      System.out.println("----------------------------------------");
   }

   public void printMenu() {
      System.out.println("Welcome to the game!");
      System.out.println("\n\n\n----------------------------------------\n\n\n");

      while(this.curGame != 0) {
         System.out.println("[0] Back to menu");
         System.out.println("[1] Hangman");
         System.out.println("[2] Guess The Word");
         System.out.println("[3] Multiple Choice");
         System.out.println("Your action: ");
         Scanner sc = new Scanner(System.in);
         this.curGame = sc.nextInt();
         if (this.curGame < 0 || this.curGame > 3) {
            throw new InputMismatchException("Invalid action!");
         }

         this.printGap();
         switch (this.curGame) {
            case 0:
            default:
               break;
            case 1:
               HangMan hangman = new HangMan();
               hangman.start();
               break;
            case 2:
               GuessTheWord guessTheWord = new GuessTheWord();
               guessTheWord.start();
               break;
            case 3:
               MultipleChoice multipleChoice = new MultipleChoice();
               multipleChoice.start();
         }
      }

   }
}
