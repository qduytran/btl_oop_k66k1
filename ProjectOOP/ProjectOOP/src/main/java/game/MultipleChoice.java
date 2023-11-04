// Source code is decompiled from a .class file using FernFlower decompiler.
package game;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MultipleChoice extends GameManagement {
   private static int maxQuestion = 33482;
   private static ArrayList<Question> dataFile = new ArrayList();
   private GameManagement.State state;
   private Question question;
   private String[] answer = new String[4];
   private char correctAnswer;

   public MultipleChoice() {
      this.point = 0;
      this.health = 3;
      this.state = State.PLAYING;
   }

   private void generateQuestion() {
      Random rand = new Random();
      int index = rand.nextInt(maxQuestion);
      this.question = (Question)dataFile.get(index);
   }

   private void generateAnswer() {
      this.answer[0] = this.question.getWord();

      int i;
      Random rand;
      int index;
      for(i = 1; i < 4; ++i) {
         rand = new Random();
         index = rand.nextInt(maxQuestion);
         this.answer[i] = ((Question)dataFile.get(index)).getWord();
      }

      for(i = 0; i < 4; ++i) {
         rand = new Random();
         index = rand.nextInt(4);
         String tmp = this.answer[i];
         this.answer[i] = this.answer[index];
         this.answer[index] = tmp;
      }

   }

   private void getCorrectAnswer() {
      for(int i = 0; i < 4; ++i) {
         if (this.answer[i].equals(this.question.getWord())) {
            this.correctAnswer = (char)(i + 65);
            break;
         }
      }

   }

   private void printQuestion() {
      String questionScript = this.question.getExample();
      questionScript = questionScript.replaceAll("(?i)" + this.question.getWord(), "______");
      System.out.println("Question: " + questionScript);
   }

   private void printAnswer() {
      System.out.println("A. " + this.answer[0]);
      System.out.println("B. " + this.answer[1]);
      System.out.println("C. " + this.answer[2]);
      System.out.println("D. " + this.answer[3]);
   }

   private void checkAnswer(char guess) {
      if (guess == this.correctAnswer) {
         System.out.println("Correct answer!");
         ++this.point;
      } else {
         System.out.println("Incorrect answer!");
         System.out.println("Correct answer is " + this.correctAnswer);
         --this.health;
      }

   }

   private void printInformation() {
      System.out.println("Your point: " + this.point);
      System.out.println("Your health: " + this.health);
   }

   public void start() {
      System.out.println("You are playing Multiple Choice Game");

      while(this.state == State.PLAYING) {
         this.generateQuestion();
         this.generateAnswer();
         this.getCorrectAnswer();
         this.printQuestion();
         this.printAnswer();
         System.out.print("Your answer: ");
         Scanner sc = new Scanner(System.in);
         char guess = Character.toUpperCase(sc.next().charAt(0));
         this.checkAnswer(guess);
         this.printInformation();
         if (this.health == 0) {
            this.state = State.LOSE;
            break;
         }

         if (this.point == 10) {
            this.state = State.WIN;
            break;
         }
      }

      if (this.state == State.LOSE) {
         System.out.println("You lose!");
      } else {
         System.out.println("You win!");
      }

   }

   static {
      System.out.println("Reading data from Multiple Choice...");

      try {
         Scanner sc = new Scanner(new File("/btl_oop_k66k1/ProjectOOP/ProjectOOP/data/MultipleChoiceData.txt"));

         while(sc.hasNext()) {
            String[] tmp = sc.nextLine().split("\t");
            dataFile.add(new Question(tmp[0], tmp[1]));
         }

         sc.close();
      } catch (IOException var2) {
         System.out.println("Error: " + String.valueOf(var2));
      }

   }
}
