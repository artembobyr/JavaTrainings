package Main;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumbers {
    public static List<Integer> getPrimeNumbers(int firstNumber, int lastNumber) {
        List<Integer> list = new ArrayList<>();
        if (lastNumber <= 10000 && firstNumber >= 1) {
            if (firstNumber == 1 || firstNumber == 2) {
                list.add(2);
            }
            for (int i = firstNumber; i <= lastNumber; i++) {
                for (int j = 3; j <= i; j = j + 2) {
                    if (j == i) {
                        list.add(i);
                    }
                    if (i % j == 0) {
                        break;
                    }
                }
            }
        } else {
            System.out.println("Wrong number");
        }
        return list;
    }
}
