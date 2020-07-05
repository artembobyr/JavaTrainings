package lesson3.task2;

import java.io.IOException;
import java.util.Scanner;

public class Task2_main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Input number of lines/words: ");
        int number = in.nextInt();

        StringTasks str = new StringTasks();
        str.inputLinesOrWords(number, false); //true if lines, false if words


        //str.getLengthLines_1();
        //str.getNumbersMoreThanAverage_2();
       // str.getNumbersLessThanAverage_3();
        str.getWordWithMinimalDifferentCharacters_4();
        //str.getOnlyDifferentCharacters_5();
 //       str.getOnlyNumbers_6();

    }
}
