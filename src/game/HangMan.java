package game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import game.question.HangmanWord;

public class HangMan extends GameInterface {
    private static List<String> list = new ArrayList<>();
    static {
        try {
            FileReader fr = new FileReader("resources/data/HangmanData.txt");
            BufferedReader bf = new BufferedReader(fr);
            String line;

            while ((line = bf.readLine()) != null) {
                list.add(line);
            }
            bf.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("không tìm thấy file");
        } catch (IOException e) {
            System.out.println("Lỗi đọc file");
        } catch (Exception e) {
            System.out.println("Lỗi khác với file");
        }
    }

    public HangMan() {
        point = 0;
        health = 3;
        path = "resources/data/HangmanData.txt";
    }

    public void insertFromFile() {
        try {
            FileReader fr = new FileReader(path);
            BufferedReader bf = new BufferedReader(fr);
            String line;

            while ((line = bf.readLine()) != null) {
                list.add(line);
            }
            bf.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("không tìm thấy file");
        } catch (IOException e) {
            System.out.println("Lỗi đọc file");
        } catch (Exception e) {
            System.out.println("Lỗi khác với file");
        }
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void increasePoint() {
        point++;
    }

    public void decreaseHealth() {
        health--;
    }

    @Override
    public void start() {
        insertFromFile();
        System.out.println("--------------NEW GAME--------------");
        Scanner input = new Scanner(System.in);
        int index = 0;
        while (true) {
            HangmanWord x = new HangmanWord(this.randWord());
            index++;
            int health_word = 3;
            while (x.completedWord() == false) {
                System.out.print("\n---Word " + index + " :\t");
                x.printInfo();
                System.out.print("Nhập chữ cái bạn đoán: ");
                String s = input.nextLine();
                char answers = 'a';
                if (s.length() != 0)
                    answers = s.charAt(0);
                if (x.checkAnswers(answers) == false) {
                    health_word--;
                }
                if (health_word == 0) {
                    System.out.println("Hết lượt đoán. Đáp án : " + x.word);
                    printInfo();
                    break;
                }
            }
            if (x.completedWord() == true) {
                System.out.println("-------Correct-------");
                point += 1;
            } else {
                health -= 1;
            }
            System.out.print("\n\n\n");
            if (isEndGame()) {
                printEndGame();
                break;
            }
        }
    }

    public String randWord() {
        int n = list.size();
        // if (n <= 0) {
        // // Xử lý khi danh sách rỗng hoặc không có phần tử nào
        // return null; // Hoặc giá trị mặc định phù hợp
        // }

        Random rand = new Random();
        int index = rand.nextInt(n);
        return list.get(index);
    }

}