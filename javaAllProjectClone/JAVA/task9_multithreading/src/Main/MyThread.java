package Main;

import java.util.List;

public class MyThread implements Runnable {

    private final int firstNumber;
    private final int lastNumber;
    private List<Integer> list;

    MyThread(int firstNumber, int lastNumber, List<Integer> list) {
        this.firstNumber = firstNumber;
        this.lastNumber = lastNumber;
        this.list = list;
    }

    public void run() {
        list = PrimeNumbers.getPrimeNumbers(firstNumber, lastNumber);
        for (int i : list) {
            System.out.println(Thread.currentThread().getName() + " - " + i);
        }
    }
}
