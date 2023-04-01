import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        int MAX_LIFE = 6;

        // 단어 입력받기
        BufferedReader br = new BufferedReader(new FileReader("src/word.txt"));

        int count = 0;
        String[] word_arr = new String[1619];

        while(true) {
            String line = br.readLine();
            if (line==null) break;

            if(line.length() > 5){
                word_arr[count] = line;
                count++;
            }
        }

        br.close();

        Random rand = new Random();
        int select = rand.nextInt(count);
        String answer = word_arr[select];

        String[] status = new String[answer.length()];
        String[] wrong = new String[MAX_LIFE];
        int wrong_count = 0;
        int life = MAX_LIFE;

        for(int i = 0; i < answer.length(); i++) status[i] = "_";
        for(int i = 0; i < MAX_LIFE; i++) wrong[i] = "";

        while(true) {
            System.out.println("[status]");
            System.out.println(String.join(" ", status));
            System.out.println("wrong: " + String.join(" ", wrong));
            System.out.println("life : " + life);
            System.out.println();

            System.out.print("Enter a Character: ");
            Scanner scanner = new Scanner(System.in);

            String input = scanner.next();

            boolean exist = false;

            for(int i = 0; i < answer.length(); i++){
                if (Objects.equals(input, answer.substring(i, i + 1))) {
                    exist = true;

                    status[i] = answer.substring(i, i + 1);
                }
            }

            if(!exist) {
                wrong[wrong_count++] = input;
                life--;
            }

            boolean win = true;
            for(int i = 0; i < answer.length(); i++){
                if (Objects.equals(status[i], "_")) {
                    win = false;
                    break;
                }
            }

            if(win) {
                System.out.println("Answer is : " + answer);
                System.out.println("Congratulation!");
                break;
            }

            if(life == 0){
                System.out.println("Failed..");
                System.out.println("Answer is : " + answer);
                break;
            }
        }

    }
}
