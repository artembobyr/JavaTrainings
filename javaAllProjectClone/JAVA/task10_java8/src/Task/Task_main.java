package Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task_main {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        ArrayList<String> listOfWords;
        listOfWords = Helper.inputData(System.getProperty("user.dir") + "\\JAVA\\task10_java8\\src\\Task\\note.txt");
        System.out.printf("Select the operation:\nf - Frequency\nl - Length\nd - Dublicates\n");
        Map<String, Integer> map = new HashMap<>();
        char c = sc.next().charAt(0);
        switch (c) {
            case 'f':
                Frequency frequency = new Frequency();
                frequency.getFrequency(listOfWords);
                break;
            case 'l':
                Length length = new Length();
                length.getLength(listOfWords);
                break;
            case 'd':
                Dublicates dublicates = new Dublicates();
                dublicates.getDublicates(listOfWords);
                break;
            default:
                System.out.println("You input wrong data");
        }
    }
}
