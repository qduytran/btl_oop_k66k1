import java.util.Scanner;

public class DictionaryManegerment {
    public Dictionary manager = new Dictionary();

    public void insertFromCommandLine() {
        Scanner input = new Scanner(System.in);
        System.out.print("Moi ban nhap so luong tu muon nhap: ");
        int n = input.nextInt();
        input.nextLine();
        for (int i = 0; i < n; i++) {
            String x = input.nextLine();
            String y = input.nextLine();
            manager.addWord(x, y);
        }
        //input.close();
    }

    public void showAllWords() {
        int length = manager.listWords.size();
        System.out.println("Danh sach tu co trong dictionary");
        System.out.println("No          viet            anh");
        for (int i = 0; i < length; i++) {
            Word x = manager.listWords.get(i);
            System.out.println(i + "        " + x.getWordTarget() + "           " + x.getWordExplain());
            
        }
    }
}
